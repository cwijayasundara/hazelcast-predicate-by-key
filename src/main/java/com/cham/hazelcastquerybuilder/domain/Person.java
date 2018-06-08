package com.cham.hazelcastquerybuilder.domain;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String address;
    private String version;
    private int salary;

    public Person(String name, String address, String version, int salary) {
        this.name = name;
        this.address = address;
        this.version = version;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", version='" + version + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
