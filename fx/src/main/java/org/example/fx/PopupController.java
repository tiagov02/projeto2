package org.example.fx;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupController implements Initializable {

    private int id;

    public PopupController(int id) {
        this.id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ID" + id);
    }
}
