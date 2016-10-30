package io.khasang.snet.model;

import com.google.gson.Gson;

public class GsonTest {
    public static void main(String[] args) {
        // Serialization
        Gson gson = new Gson();
        gson.toJson(1);            // ==> 1
        gson.toJson("abcd");       // ==> "abcd"
        gson.toJson(new Long(10)); // ==> 10
        int[] values = { 1, 3, 7, 4, 66, 890, 432 };
        gson.toJson(values);       // ==> [1]
        System.out.println(values);
        System.out.println(gson.toJson(values));
        System.out.println(gson.toJson("abcd"));

// Deserialization
        int one = gson.fromJson("1", int.class);
        System.out.println(gson.fromJson("1", int.class));
        Integer two = gson.fromJson("1", Integer.class);
        Long three = gson.fromJson("1", Long.class);
        Boolean four = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("abc", String.class);
        System.out.println(str);
        String[] anotherStr = gson.fromJson("[abc]", String[].class);
        System.out.println(anotherStr[0]);
    }
}
