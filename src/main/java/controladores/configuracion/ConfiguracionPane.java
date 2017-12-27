package controladores.configuracion;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import constant.Constantes;
import helpers.Preferencias;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static java.nio.file.Files.*;

public class ConfiguracionPane implements Initializable{

    @FXML
    private JFXTextField ciudad;

    @FXML
    private JFXComboBox<String> CB_Ciudades;

    @FXML
    private JFXTextField responsable;

    @FXML
    private JFXComboBox<String> Cb_Responsables;

    @FXML
    private JFXTextField presentadores;

    @FXML
    private JFXComboBox<String> CB_Presentadores;

    @FXML
    private JFXTextField TF_Correo;

    Constantes constantes;

    @FXML
    void agregarCiudad(ActionEvent event) {
        constantes = Preferencias.leer();
        constantes.getCiudades().add(ciudad.getText());
        Preferencias.guardar(constantes);
        constantes = Preferencias.leer();
        CB_Ciudades.getItems().setAll(constantes.getCiudades());
    }

    @FXML
    void agregarCorreo(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/nuevoCorreo.fxml"));
            Parent root = loader.load();

            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    TF_Correo.setText(constantes.getCorreo_user());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void agregarPresentador(ActionEvent event) {
        constantes = Preferencias.leer();
        constantes.getPresentadores().add(presentadores.getText());
        Preferencias.guardar(constantes);
        constantes = Preferencias.leer();
        CB_Presentadores.getItems().setAll(constantes.getPresentadores());

    }

    @FXML
    void agregarResponsable(ActionEvent event) {
        constantes = Preferencias.leer();
        constantes.getResponsables().add(responsable.getText());
        Preferencias.guardar(constantes);
        constantes = Preferencias.leer();
        Cb_Responsables.getItems().setAll(constantes.getResponsables());

    }

    @FXML
    void eliminarCiudad(ActionEvent event) {
        constantes = Preferencias.leer();
        constantes.getCiudades().remove(CB_Ciudades.getSelectionModel().getSelectedItem());
        Preferencias.guardar(constantes);
        constantes = Preferencias.leer();
        CB_Ciudades.getItems().setAll(constantes.getCiudades());


    }

    @FXML
    void eliminarCorreo(ActionEvent event) {
        constantes = Preferencias.leer();
        constantes.setCorreo_user("");
        constantes.setCorreo_pass("");
        Preferencias.guardar(constantes);
        TF_Correo.setText(constantes.getCorreo_user());

    }

    @FXML
    void eliminarPresentador(ActionEvent event) {
        constantes = Preferencias.leer();
        constantes.getPresentadores().remove(CB_Presentadores.getSelectionModel().getSelectedItem());
        Preferencias.guardar(constantes);
        constantes = Preferencias.leer();
        CB_Presentadores.getItems().setAll(constantes.getPresentadores());

    }

    @FXML
    void eliminarResponsable(ActionEvent event) {
        constantes = Preferencias.leer();
        constantes.getResponsables().remove(Cb_Responsables.getSelectionModel().getSelectedItem());
        Preferencias.guardar(constantes);
        constantes = Preferencias.leer();
        Cb_Responsables.getItems().setAll(constantes.getResponsables());

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Path path = Paths.get("Preferencias.obj");
        if (!exists(path)){
            Preferencias.guardar(new Constantes());
        }else {
            try{
                constantes = Preferencias.leer();
                if (constantes != null){
                    CB_Ciudades.getItems().setAll(constantes.getCiudades());
                    TF_Correo.setText(constantes.getCorreo_user());
                    CB_Presentadores.getItems().setAll(constantes.getPresentadores());
                    Cb_Responsables.getItems().setAll(constantes.getResponsables());
                }else {
                    System.out.println("Al leer la constante tira null en ConfiguracionPane Controller");
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }



    }
}
