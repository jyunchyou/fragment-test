package io.openmessaging.demo;

import java.io.FileInputStream;

/**
 * Created by E450C on 2017/4/22.
 */
public class Node{
    private String key;
    private FileInputStream value;
    private  Node next;

    public Node(String key,FileInputStream value){
        this.key=key;
        this.value=value;
    }

    public Node(String key,FileInputStream value,boolean isUpdate){
        this.key=key;
        this.value=value;

    }


    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public FileInputStream getValue() {
        return value;
    }

    public void setValue(FileInputStream value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}