package com.cham.hazelcastquerybuilder.service;

import com.cham.hazelcastquerybuilder.domain.PersonKey;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.QueryCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.map.QueryCache;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;

import java.util.Collection;
import java.util.Iterator;

public class QueryMain {

    private final static String cacheName="person-map-cache";

    public static void main(String args[]){

        ClientConfig clientConfig = new ClientConfig();
        QueryCacheConfig queryCacheConfig = new QueryCacheConfig(cacheName);
        clientConfig.addQueryCacheConfig(cacheName, queryCacheConfig);

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<PersonKey, String> clientEmpMap = (IMap) client.getMap(cacheName);

        EntryObject entryObject = new PredicateBuilder().getEntryObject();

        Predicate namePredicate = entryObject.key()
                                 .get("name").equal("Alice")
                                 .or(entryObject.key().get("salary").greaterThan(50));;

        QueryCache<PersonKey, String> queryCache = clientEmpMap.getQueryCache(cacheName, namePredicate, true);

        Collection<String> employeeCollection = queryCache.values();

        int size = queryCache.size();
        System.out.println("Continuous query cache size = " + size);

        Iterator itr = employeeCollection.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
