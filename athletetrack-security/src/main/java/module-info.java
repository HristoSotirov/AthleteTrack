module athletetrack.security {
    requires jjwt.api;
    requires javax.servlet.api;
    exports com.athletetrack.security;
    exports com.athletetrack.jwtToken;
}