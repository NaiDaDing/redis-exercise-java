package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.List;

import static java.lang.Thread.sleep;

public class TestArrayAsStr {

    public static void main(String[] args) throws InterruptedException {
        int sleepArg = (args.length == 0) ? 0 : Integer.parseInt(args[0]);
        System.out.println("echo loop will emulate sleep " + sleepArg + " millis");

        Jedis jedis = new Jedis("localhost", 6379);

        String jsonArrayString = "";
        for(int i=0; i<10; i++) {
            String arrayString = jedis.get("array_string");

            Gson gson = new Gson();
            Type listType = new TypeToken<List<String>>(){}.getType();
            List<String> list =  gson.fromJson(arrayString, listType);

            sleep(sleepArg);

            list.add(String.valueOf(i));

            jsonArrayString = gson.toJson(list, listType);

            jedis.set("array_string", jsonArrayString);
        }

        System.out.println(jsonArrayString);
    }
}
