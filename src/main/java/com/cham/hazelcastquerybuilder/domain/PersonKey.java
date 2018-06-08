package com.cham.hazelcastquerybuilder.domain;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;
import java.io.Serializable;

public class PersonKey implements Serializable, Portable {

    private String name;
    private String version;
    private int salary;

    public PersonKey(){};

    public PersonKey(String name, String version, int salary) {
        this.name = name;
        this.version = version;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "PersonKey{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int getClassId() {
        return PortableFactoryImpl.PERSON_CLASS_ID;
    }

    @Override
    public int getFactoryId() {
        return PortableFactoryImpl.FACTORY_ID;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        System.out.println("Serialize");
        writer.writeUTF("name", name );
        writer.writeUTF("version", version);
        writer.writeInt("salary", salary);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        System.out.println("Deserialize");
        this.name = reader.readUTF("name");
        this.version= reader.readUTF("version");
        this.salary=reader.readInt("salary");
    }
}
