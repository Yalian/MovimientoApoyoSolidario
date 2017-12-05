package controladores.cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import helpers.AutoComplete;
import helpers.ClienteFX;
import helpers.Data;
import helpers.Preferencias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Planes;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AgregarClientePane implements Initializable{

    @FXML
    private JFXTextField TXT_ID;

    @FXML
    private JFXTextField TF_Cedula;

    @FXML
    private JFXTextField TF_Nombres;

    @FXML
    private JFXTextField TF_Apellidos;

    @FXML
    private JFXComboBox<String> CB_Ciudad;

    @FXML
    private JFXTextField TF_Celular;

    @FXML
    private JFXTextField TF_Direccion;

    @FXML
    private JFXTextField TF_Correo;

    @FXML
    private JFXTextField TF_Patrocinador;

    @FXML
    private JFXComboBox<Planes> CB_Planes;

    @FXML
    private DatePicker TF_FechaRegistro;

    @FXML
    private JFXComboBox<ClienteFX> CB_Patrocinador;

    @FXML
    private JFXTextField TXT_Patro;

    @FXML
    private JFXButton BTN_Aceptar;

    @FXML
    private JFXButton BTN_Cancelar;

    private static void Error(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e);

        alert.showAndWait();
    }

    @FXML
    void aceptarRegistro(ActionEvent event) {
        if (TF_Nombres.getText().equalsIgnoreCase("")) {
            Error("Por favor ingrese un nombre");
        }
        else if (CB_Patrocinador.getSelectionModel().getSelectedItem() == null) {
            Error("Por favor seleccione un Patrocinador");
        }
        else if (TF_Celular.getText().equalsIgnoreCase("")) {
            Error("Por favor ingrese un numero de contacto");
        }
        else if (TF_Correo.getText().equalsIgnoreCase("")) {
            Error("Por favor ingrese un correo");
        }else {
            Cliente e = new Cliente(
                    TF_Cedula.getText(),
                    TF_Nombres.getText(),
                    TF_Apellidos.getText(),
                    CB_Ciudad.getSelectionModel().getSelectedItem(),
                    CB_Patrocinador.getSelectionModel().getSelectedItem().getId(),
                    CB_Patrocinador.getSelectionModel().getSelectedItem().getNombres(),
                    Data.findClienteByID(CB_Patrocinador.getSelectionModel().getSelectedItem().getId()).getCoPatrocinador(),
                    Data.findClienteByID(CB_Patrocinador.getSelectionModel().getSelectedItem().getId()).getID_CoPatrocinador(),
                    TF_Celular.getText(),
                    TF_Correo.getText(),
                    TF_Direccion.getText(),
                    TF_FechaRegistro.getValue());

            if (CB_Planes.getSelectionModel().getSelectedItem() != null){
                e.getPlanes().add(CB_Planes.getSelectionModel().getSelectedItem());
            }

            Data.persist(e);

            System.out.println("Agregado correctamente");

            Stage stage = (Stage) BTN_Cancelar.getScene().getWindow();

            stage.close();
        }
    }



    @FXML
    void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) BTN_Cancelar.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TF_FechaRegistro.setValue(LocalDate.now());
        CB_Patrocinador.setItems(Data.getClientes());
        CB_Planes.getItems().setAll(Data.getPlanes());
        CB_Ciudad.getItems().addAll(Preferencias.leer().getCiudades());


        new AutoComplete<ClienteFX>(CB_Patrocinador);
    }

}