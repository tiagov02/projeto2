package org.example.fx.Logica;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.fx.HelloApplication;

import java.io.IOException;
import java.util.Objects;

public class TrocaPaineis {
    public static <T> Scene changePanel(javafx.event.ActionEvent event, String name_fxml, String name_view,Class<T> classe)
            throws IOException
    {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(classe.getResource(name_fxml)));
        Scene scene = new Scene(parent, 755, 537);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //stage.getIcons().add(new Image("resources/images/logotipo.jpg", true));
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle(name_view);
        stage.show();

        return scene;
    }
}
