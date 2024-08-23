package com.athletetrack.router;

import com.athletetrack.controller.UserController;
import com.athletetrack.controller.WorkoutController;
import com.athletetrack.repository.UserRepository;
import com.athletetrack.repository.WorkoutRepository;
import com.athletetrack.service.UserService;
import com.athletetrack.service.WorkoutService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {

    private final Map<String, Route> staticRoutes = new HashMap<>();
    private final Map<Pattern, Route> dynamicRoutes = new HashMap<>();
    private final Map<Pattern, String[]> dynamicRouteVariables = new HashMap<>();
    private final Object lock = new Object();

    public Router() {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);

        WorkoutRepository workoutRepository = new WorkoutRepository();
        WorkoutService workoutService = new WorkoutService(workoutRepository);
        WorkoutController workoutController = new WorkoutController(workoutService);

        registerRoute("GET", "/users", request -> userController.getAllUsers());

        registerRoute("GET", "/users/{id}", request -> {
            String id = request.getPathVariables().get("id");
            return userController.getUserById(id);
        });

        registerRoute("POST", "/coachRegistration", request -> {
            String requestBody = request.getBody();
            JSONObject json = new JSONObject(requestBody);

            String name = json.optString("name", null);
            String username = json.optString("username", null);
            String password = json.optString("password", null);
            String email = json.optString("email", null);
            String phone = json.optString("phone", null);
            String sport = json.optString("sport", null);
            String club = json.optString("club", null);

            return userController.registerCoach(name, username, password, email, phone, sport, club);
        });

        registerRoute("POST", "/athleteRegistration", request -> {
            String requestBody = request.getBody();
            JSONObject json = new JSONObject(requestBody);

            Long coachId = json.optLong("coachId", 0L);
            String name = json.optString("name", null);
            String username = json.optString("username", null);
            String password = json.optString("password", null);
            String email = json.optString("email", null);
            String phone = json.optString("phone", null);

            return userController.registerAthlete(coachId, name, username, password, email, phone);
        });

        registerRoute("POST", "/login", request -> {
            String requestBody = request.getBody();
            JSONObject json = new JSONObject(requestBody);

            String username = json.optString("username", null);
            String password = json.optString("password", null);

            return userController.login(username, password);
        });

        registerRoute("POST", "/workoutsAthlete", request -> {
            String requestBody = request.getBody();
            JSONObject json = new JSONObject(requestBody);

            String athleteId = json.optString("athleteId", null);
            LocalDateTime startDate = LocalDateTime.parse(json.optString("startDate", null));
            LocalDateTime endDate = LocalDateTime.parse(json.optString("endDate", null));

            return workoutController.getWorkoutsByUserId(athleteId, startDate, endDate);
        });

        registerRoute("POST", "/workoutsCoach", request -> {
            String requestBody = request.getBody();
            JSONObject json = new JSONObject(requestBody);

            String coachId = json.optString("coachId", null);
            LocalDateTime startDate = LocalDateTime.parse(json.optString("startDate", null));
            LocalDateTime endDate = LocalDateTime.parse(json.optString("endDate", null));

            return workoutController.getWorkoutsByCoachId(coachId, startDate, endDate);
        });

        registerRoute("POST", "/workoutsClub", request -> {
            String requestBody = request.getBody();
            JSONObject json = new JSONObject(requestBody);

            String clubName = json.optString("clubName", null);
            LocalDateTime startDate = LocalDateTime.parse(json.optString("startDate", null));
            LocalDateTime endDate = LocalDateTime.parse(json.optString("endDate", null));

            return workoutController.getWorkoutsByClubName(clubName, startDate, endDate);
        });

        registerRoute("POST", "/saveWorkout", request -> {
            String requestBody = request.getBody();
            JSONObject json = new JSONObject(requestBody);

            Long athleteId = json.optLong("athleteId", 0L);
            String workoutType = json.optString("workoutType", null);
            String description = json.optString("description", null);
            LocalDateTime doneAt = LocalDateTime.parse(json.optString("doneAt", null));

            return workoutController.saveWorkout(athleteId, workoutType, description, doneAt);
        });
    }

    public String route(String path, String method, String body, Map<String, String> headers) throws JSONException, UnsupportedEncodingException {
        synchronized (lock) {
            String staticKey = method + " " + path;
            if (staticRoutes.containsKey(staticKey)) {
                return staticRoutes.get(staticKey).handle(createRequest(method, path, body, new HashMap<>(), headers));
            }

            for (Map.Entry<Pattern, Route> entry : dynamicRoutes.entrySet()) {
                Matcher matcher = entry.getKey().matcher(path);
                if (matcher.matches()) {
                    Map<String, String> pathVariables = new HashMap<>();
                    String[] variableNames = dynamicRouteVariables.get(entry.getKey());
                    for (int i = 1; i <= matcher.groupCount(); i++) {
                        pathVariables.put(variableNames[i - 1], matcher.group(i));
                    }
                    return entry.getValue().handle(createRequest(method, path, body, pathVariables, headers));
                }
            }
            return "HTTP/1.1 404 Not Found\r\n\r\n";
        }
    }

    private void registerRoute(String method, String path, Route route) {
        synchronized (lock) {
            if (path.contains("{")) {
                String regexPath = path.replaceAll("\\{[^/]+\\}", "([^/]+)");
                Pattern pattern = Pattern.compile(regexPath);
                String[] variableNames = extractVariableNames(path);
                dynamicRoutes.put(pattern, route);
                dynamicRouteVariables.put(pattern, variableNames);
            } else {
                staticRoutes.put(method + " " + path, route);
            }
        }
    }

    private String[] extractVariableNames(String path) {
        return path.split("\\{")[1]
                .replace("}", "")
                .split("/");
    }

    private HttpRequest createRequest(String method, String path, String body, Map<String, String> pathVariables, Map<String, String> headers) {
        return new HttpRequest(method, path, body, pathVariables, headers);
    }
}
