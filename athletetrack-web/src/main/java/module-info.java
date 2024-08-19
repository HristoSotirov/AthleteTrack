module athletetrack.web {
    requires athletetrack.service;
    requires java.sql;
    requires jdk.httpserver;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;
    requires athletetrack.data;
    requires com.fasterxml.jackson.datatype.jsr310;
}