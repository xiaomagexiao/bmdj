package com.joke.bamenshenqi.util;


import com.google.gson.Gson;

public class GsonUtil {
    private static Gson gson;

    private GsonUtil() {
        super();
    }

    public static Gson getGsonInstance() {
        if(GsonUtil.gson == null) {
            GsonUtil.gson = new Gson();
        }

        return GsonUtil.gson;
    }

    public Object parserResult(String json, Class arg3) {
        return GsonUtil.gson.fromJson(json, arg3);
    }
}

