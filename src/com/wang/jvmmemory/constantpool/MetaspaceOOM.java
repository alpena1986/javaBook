package com.wang.jvmmemory.constantpool;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果设置一下jvm 参数，会导致抛出java.lang.OutOfMemoryError: Java heap space
 * -Xmx64m -XX:-UseGCOverheadLimit -XX:MaxMetaspaceSize=16m
 * 这里面metaspace的max已经设置的很小了。
 * metaspace没有OOM，heap space OOM了，说明interned string 确实在java 8中被放在了堆中。
 */
public class MetaspaceOOM {

    public static void main(String[] args){

        //保持引用，防止自动垃圾回收
        List<String> list = new ArrayList<>();

        int i = 0;

        while(true){
            //通过intern方法向常量池中手动添加常量
            list.add(String.valueOf(i++).intern());
        }
    }
}
