package org.example;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Init {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        // Get pre string
        String arrayString = jedis.get("array_string");
        System.out.println("pre " + arrayString);

        // Get pre list
        List<String> list = jedis.lrange("array_list", 0, 999);
        System.out.println("pre list " + Arrays.toString(list.toArray()));

        //Get pre set
        Set<String> set = jedis.smembers("array_set");
        System.out.println("pre set " + Arrays.toString(set.toArray()));

        // Initialize string
        jedis.set("array_string", "[0]");
        arrayString = jedis.get("array_string");

        // Initialize list
        jedis.ltrim("array_list", 0, 0);
        list = jedis.lrange("array_list", 0, 999);

        // Initialize set
        for(String s: set){
            if(jedis.sismember("array_set", s)) {
                jedis.srem("array_set", s);
            }
        }

        System.out.println("init value " + arrayString);
        System.out.println("init list " + Arrays.toString(list.toArray()));
        System.out.println("init set " + Arrays.toString(set.toArray()));
    }
}