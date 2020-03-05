package com.phei.netty.codec.messagepack;


import org.msgpack.annotation.Message;

import java.io.Serializable;
@Message
public class Student implements Serializable {
    private String name;
    private Integer age;
    //getter和setter方法
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}