module org.example.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.example.bd;

    opens org.example.fx to javafx.fxml;
    exports org.example.fx;
}