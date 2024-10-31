package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class VentanaPrincipalController {

    @FXML
    private Button btnPrueba;

    @FXML
    private Label lblTexto;

    @FXML
    void btnPressed(ActionEvent event) {
    	lblTexto.setText("Hola mundo");
    }

}
