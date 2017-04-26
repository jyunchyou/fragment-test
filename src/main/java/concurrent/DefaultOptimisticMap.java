package io.openmessaging.demo;

import io.openmessaging.OptimisticMap;
import java.io.FileInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by E450C on 2017/4/21.
 */
class DefaultOptimisticMap  implements OptimisticMap {
    private Node[] nodes;
    private int size;
    AtomicBoolean atomic = new AtomicBoolean(true);

    public DefaultOptimisticMap(int init) {
        size = init;
        nodes = new Node[init];
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Override
    public   Node put(String key, FileInputStream fileInputStream) {
        if (!atomic.compareAndSet(true, false)) {

            return null;
        }
       int hash=size & hash(key);
        Node node = new Node(key, fileInputStream, true);
        if(nodes[hash]==null){
            nodes[hash]=node;
            return node;
        }
        Node temp=nodes[hash];
        while(temp!=null) {
            if (key.equals(temp.getKey())) {
                node.setNext(temp.getNext());
                temp.setNext(node);
                break;
            }
                temp=temp.getNext();
        }
        atomic.set(true);
        return node;
    }
    @Override
    public void remove(String key) {

    }
    @Override
    public Node get(String key) {
        if (!atomic.compareAndSet(true, false)) {

            return null;
        }
        Node local = nodes[size & hash(key)];
        if (local == null) {
            atomic.set(true);
            return new Node(key,null);
        }
        if (key.equals(local.getKey())) {
            atomic.set(true);
            return local;
        }
        Node node;
        while ((node = local.getNext()) != null) {
            if (key.equals(node.getKey())) {
                atomic.set(true);
                return local;
            }
        }
        atomic.set(true);
        return new Node(key,null);
    }

    public static void main(String[] args) {
    }
}




