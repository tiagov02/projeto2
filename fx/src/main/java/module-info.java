module org.example.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.example.bd;
    requires java.persistence;
    requires java.sql;

    opens org.example.fx to javafx.fxml;
    exports org.example.fx;
    exports org.example.fx.Logica;
    opens org.example.fx.Logica to javafx.fxml;
    exports org.example.fx.ModelClasses;
    opens org.example.fx.ModelClasses to javafx.fxml;
}