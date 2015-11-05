package com.skilltradiez.skilltraderz;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by nweninge on 11/4/15.
 */
public class Elastic {
    SimpleHTTPClient httpClient;
    String baseUrl;
    Gson gson;
    public Elastic(String baseUrl) {
        httpClient = new SimpleHTTPClient();
        this.baseUrl = baseUrl;
        gson = new Gson();
    }

    public <T> void addDocument(String type, String id, T data) throws IOException {
        httpClient.post(baseUrl + type + "/" + id, gson.toJson(data));
    }

    public User getDocumentUser(String type, String id) throws IOException {
        String resp = httpClient.get(baseUrl + type + "/" + id);
        Type getResponseType = new TypeToken<GetResponse<User>>() { }.getType();
        return ((GetResponse<User>)gson.fromJson(resp, getResponseType))._source;
    }

    public void deleteDocument(String type, String id) throws IOException {
        httpClient.delete(baseUrl + type + "/" + id);
    }
}
