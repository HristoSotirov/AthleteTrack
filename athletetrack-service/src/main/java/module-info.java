module athletetrack.service {
    exports com.athletetrack.service;
    exports com.athletetrack.DTO;

    requires athletetrack.data;
    requires athletetrack.security;
    requires java.sql;

}