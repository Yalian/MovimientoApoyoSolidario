package controladores.configuracion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import constant.Constantes;
import helpers.Preferencias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CorreoController {


    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXPasswordField rePass;

    @FXML
    private JFXButton agregarCorreo;

    @FXML
    private JFXButton cancelar;

    Constantes constantes;


    private static void Error(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e);

        alert.showAndWait();
    }

    @FXML
    void agregarCorreo(ActionEvent event) {
        if (pass.getText().equals(rePass.getText())){
            constantes = Preferencias.leer();
            constantes.setCorreo_user(user.getText());
            constantes.setCorreo_pass(pass.getText());

            Preferencias.guardar(constantes);
        }else {
            Error("Las contraseñas no coinciden");
        }

        Stage stage = (Stage) pass.getScene().getWindow();
        stage.close();



    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) pass.getScene().getWindow();
        stage.close();

    }



}
