package com.challenge.generators.services;

import com.challenge.generators.model.Info;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class JsonService {
    public Info parseJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Info.class);
    }
}
