package com.wang;


import sun.security.krb5.internal.rcache.DflCache;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main {

    public static void main(String[] args) throws Exception{

        List<String> zombies = new ArrayList<>();
        zombies.add("1100");
        zombies.add("1110");
        zombies.add("0110");
        zombies.add("0001");
        int c = zombieCluster(zombies);
        int d = 1;
    }


    public static int zombieCluster(List<String> zombies) {
        // Write your code here
        int count = 0;
        while(peekCluster(zombies)){
            count++;
        }
        return count;
    }

    public static boolean peekCluster(List<String> zombies){
        if(zombies.size() == 0){
            return false;
        }

        Set<Integer> collection = new ConcurrentSkipListSet<>();
        Set<Integer> newCollection = new ConcurrentSkipListSet<>();
        int rootZombieIndex = 0;
        boolean hasZombie = false;
        for(int i = 0; i<zombies.size();i++){
            if(!zombies.get(i).equals("")){
                rootZombieIndex = i;
                hasZombie = true;
                break;
            }
        }
        if(!hasZombie){
            return false;
        }
        collection.add(rootZombieIndex);
        while(collection.size() != newCollection.size()){
            collection.addAll(newCollection);
            newCollection.addAll(collection);
            Iterator<Integer> iter = newCollection.iterator();
            while(iter.hasNext()){
                String zombie = zombies.get(iter.next());
                if(!zombie.equals("")) {
                    for (int i = 0; i < zombie.length(); i++) {
                        if (zombie.charAt(i) == '1') {
                            newCollection.add(i);
                        }
                    }
                }
            }
        }
        Iterator<Integer> iter = newCollection.iterator();
        while(iter.hasNext()){
            zombies.set(iter.next(), "");
        }

        return true;
    }
}