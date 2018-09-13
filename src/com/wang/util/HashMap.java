package com.wang.util;

public class HashMap<K, V> {


    private static class Entry<K,V>{

        K key;

        V value;

        Entry<K,V> next;

        /** 这个hash字段的意义在于，存储key的hash值，方便以后进行比较。*/
        int hash;

        Entry(K key, V value , Entry<K,V> next, int hash){
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }

    }


    private Entry<K, V>[] table = null;

    private final int DEFAULT_SIZE = 16;

    /** 当table的capacity为n的时候，如果table中一共插入的entry个数超过了n*loadFactor，则需要resize这个table
     * 以减少bucket中链表的长度，提高查找效率。*/
    private final float loadFactor = 0.75f;

    private int threshold;

    /** 并非table的size，而是Map中所有entry的数量*/
    private int size = 0;

    public HashMap(){
    }


    public void put(K key, V value) {

        if (table == null) {
            inflateTable(DEFAULT_SIZE);
        }

        // key的哈希值
        int hash = hash(key);

        // 目标数组的下标
        int i = indexFor(hash, table.length);

        // 尝试找到链表中该key的位置（如果存在的话）
        for(Entry<K,V> e = table[i]; e != null; e= e.next){
            K k = e.key;

            // 如果找到了这个key
            if(e.hash == hash && (e.key.equals(key))){
                //替换值为新值
                table[i].value = value;
                return ;
            }
        }

        // 如果没找到该key，则创建一个Entry插入
        addEntry(hash, key ,value ,i);

    }


    public V get(K key){
        if(size == 0){
            return null;
        }
        // 根据hash值找到按照哈希规则应该所在的bucket
        int h = hash(key);
        Entry<K,V> e = table[indexFor(h, table.length)];

        // 遍历这个bucket
        while( e != null ){
            if(hash(key) == e.hash && (e.key.equals(key))){
                return e.value;
            }
            e = e.next;
        }

        return null;
    }


    /**
     * 向Map中插入一个新的Entry。（不包括覆盖的情况）
     * 当该bucketIndex上已经有bin的时候，则把该新Entry插入到最前面。
     */
    private void addEntry(int hash, K key, V value, int bucketIndex){
        // 如果超出了负载的阈值，则重哈希整个表
        if (size >= threshold){
            resize(2 * table.length);
            // 重新计算将要插入的Entry，在扩容之后的table中的位置。
            hash = hash(key);
            bucketIndex = indexFor(hash, table.length);
        }

        Entry<K, V> firstEntry = table[bucketIndex];
        table[bucketIndex] = new Entry<>(key ,value ,firstEntry, hash);
        size++;
    }


    /**
     * 改变table的大小（capacity）。一般来讲是扩大。
     * 把扩大前的table重新hash到扩大后的table中。
     * @param newCapacity 新capacity。
     */
    private void resize(int newCapacity){
        Entry[] oldTable = this.table;
        this.table = new Entry[newCapacity];
        transfer(oldTable, this.table);
        threshold = (int)(newCapacity * loadFactor);
    }

    private void transfer(Entry[] oldTable, Entry[] newTable){

        // 循环整个哈希表
        for(Entry e : oldTable){

            // 遍历整个bucket的所有Entry
            while(e != null){

                // 先取得next的目的是，接下来e的next会发生变化，只能事先取得备用。
                Entry next = e.next;

                /*
                 * 把这个entry插入到新的table中，对应的bucket中的链表的最前面。
                  */
                // 求得新的哈希值
                int hash = hash(e.key);
                int i = indexFor(hash, newTable.length);
                e.next = newTable[i];
                newTable[i] = e;

                // 处理Bucket中的下一个Entry
                e = next;
            }
        }
    }


    /**
     * 这个方法只适用于将空的table初始化到一定的size
     * 不可以被用来扩容table
     * @param toSize 目标size
     */
    private void inflateTable(int toSize){

        // 先把size调整为2的倍数
        int size = roundUpToPowerOf2(toSize);

        table = new Entry[size];

        threshold = (int)(toSize * loadFactor);
    }

    /**
     * 计算key的哈希值
     * @param key key
     * @return hash code
     */
    private static int hash(Object key){

        int h = key.hashCode();

        /*
         * 本来可以直接返回这个h
         * 然后在计算桶的下标的时候，
         * 用
         * h & (capacity - 1)的方法，取该hash值在数组中的位置。
         *
         * 但是这样，这样的话，相当于这个h只有后几位是有用的。
         * 这样，一旦K类的哈希方法做的不够好，哈希值的后几位呈现等差数列等等
         * 将产生灾难性的碰撞。
         *
         * 如果想让这个h的所有位数都有价值，则需要使用一种叫扰动函数的方法。
         *
         * 具体做法是，将左边16位移动到右边16位的位置上，然后用这个数值与原来的h做异或。
         */

        int hashCode = h ^ (h>>>16);
        return hashCode;
    }

    private static int indexFor(int h, int length){
        return h & (length -1);
    }

    private static int roundUpToPowerOf2(int number) {
        return (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }

}
