package com.cham.hazelcastquerybuilder.service;

import com.cham.hazelcastquerybuilder.domain.Person;
import com.cham.hazelcastquerybuilder.domain.PersonKey;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class MapProducerMain {

    private final static String mapName="person-map-cache";
    private final static Gson gson = new GsonBuilder().create();


    public static void main(String args[]){

        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        // create the IMap
        IMap<PersonKey, String> serverEmpMap = client.getMap(mapName);

        // populate data to the server Map
        Person person1 = new Person("Alice", "UK", "v1", 20 );
        Person person2 = new Person("Alicey", "US", "v1", 45);
        Person person3 = new Person("Ben", "AUS", "v2", 60);

        // convert to String
        String person1Str = gson.toJson(person1);
        String person2Str = gson.toJson(person2);
        String person3Str = gson.toJson(person3);

        // create the Key as an object
        PersonKey personKey1 = new PersonKey(person1.getName(), person1.getVersion(), person1.getSalary());
        PersonKey personKey2 = new PersonKey(person2.getName(), person2.getVersion(), person2.getSalary());
        PersonKey personKey3 = new PersonKey(person3.getName(), person3.getVersion(), person3.getSalary());

        serverEmpMap.put(personKey1, person1Str);
        serverEmpMap.put(personKey2, person2Str);
        serverEmpMap.put(personKey3, person3Str);
    }
}
