package com.athletetrack.router;

import org.json.JSONException;


@FunctionalInterface
public interface Route {
    String handle(HttpRequest request) throws JSONException;
}
