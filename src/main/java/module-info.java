module lk.ijse.gdse71.mrphone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    //requires java.desktop;
    //requires mysql.connector.j;
    requires java.mail;
    requires net.sf.jasperreports.core;
    requires java.desktop;
    requires mysql.connector.j;


    opens lk.ijse.gdse71.mrphone.controller to javafx.fxml;
    exports lk.ijse.gdse71.mrphone;
    opens lk.ijse.gdse71.mrphone.dto.tm to javafx.base;
    exports lk.ijse.gdse71.mrphone.controller;
}