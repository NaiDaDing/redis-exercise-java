package org.example;

import redis.clients.jedis.Jedis;

import java.util.Arrays;

import static java.lang.Thread.sleep;

public class TestArrayAsSet {

    public static void main(String[] args) throws InterruptedException {
        int sleepArg = (args.length == 0) ? 0 : Integer.parseInt(args[0]);
        System.out.println("echo loop will emulate sleep " + sleepArg + " millis");

        Jedis jedis = new Jedis("localhost", 6379);

        for(int i=0; i<10; i++) {

            sleep(sleepArg);

            jedis.sadd("array_set", String.valueOf(i));
        }

        System.out.println(Arrays.toString(jedis.smembers("array_set").toArray()));
    }
}
