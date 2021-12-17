module com.example.mueblejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires java.naming;

    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.hibernate.orm.core;


    opens com.example.mueblejavafx to javafx.fxml;
    exports com.example.mueblejavafx;
}