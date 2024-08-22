package com.athletetrack.router;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;


@FunctionalInterface
public interface Route {
    String handle(HttpRequest request) throws JSONException, UnsupportedEncodingException;
}
