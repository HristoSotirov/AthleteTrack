package com.athletetrack.router;

import com.athletetrack.controller.UserController;
import com.athletetrack.service.UserService;
import com.athletetrack.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<String, Route> routes = new HashMap<>();
    private final Object lock = new Object();

    public Router() {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);

        registerRoute("GET", "/users", userController::getAllUsers);
    }

    public String route(String path, String method) {
        synchronized (lock) {
            Route route = routes.get(method + " " + path);
            if (route != null) {
                return route.handle();
            } else {
                return "HTTP/1.1 404 Not Found\r\n\r\n";
            }
        }
    }

    private void registerRoute(String method, String path, Route route) {
        synchronized (lock) {
            routes.put(method + " " + path, route);
        }
    }
}

