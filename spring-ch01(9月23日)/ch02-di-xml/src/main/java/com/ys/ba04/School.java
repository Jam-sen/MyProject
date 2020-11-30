package com.ys.ba04;

public class School {
    String name;
    String address;

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public School() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}