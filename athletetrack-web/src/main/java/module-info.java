module athletetrack.web {
    requires athletetrack.service;
    requires java.sql;
    requires jdk.httpserver;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;
    requires athletetrack.data;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires android.json;
    requires athletetrack.security;
    requires org.slf4j;
}
