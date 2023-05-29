package org.thread.chapter6.obj;

import java.util.HashMap;
import java.util.Map;

public class Database<K, V>
{
    private final Map<K, V> map = new HashMap<K, V>();
    private final ReadWriteLock lock = new ReadWriteLock();
    public void clear() throws InterruptedException{
        lock.writeLock();
        try {
            verySlowly();
            map.clear();
        }finally {
            lock.writeUnlock();
        }
    }

    public void assign(K key, V value) throws InterruptedException{
        lock.writeLock();
        try{
            verySlowly();
            map.put(key, value);
        }finally {
            lock.writeUnlock();
        }
    }

    public V retrieve(K key) throws InterruptedException{
        lock.readLock();
        try {
            slowly();
            return map.get(key);
        } finally {
            lock.readUnLock();
        }
    }

    private void slowly(){
        try{
            Thread.sleep(100);
        }catch (InterruptedException e) {}
    }

    private void verySlowly(){
        try{
            Thread.sleep(500);
        }catch (InterruptedException e) {}
    }
}
