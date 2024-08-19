module athletetrack.data {
    requires java.sql;
    requires java.management;
    exports com.athletetrack.repository;
    exports com.athletetrack.entity;
}