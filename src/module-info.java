module EmailClient {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires activation;
    requires java.mail;
    requires java.desktop;

    opens com.poorva;
    opens com.poorva.controller;
    opens com.poorva.model;
    opens com.poorva.view;

}