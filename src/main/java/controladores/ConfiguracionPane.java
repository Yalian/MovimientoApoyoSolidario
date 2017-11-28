package controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import constant.Constantes;
import helpers.Preferencias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

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
    private JFXComboBox<String> CB_Correos;

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
        if (!Files.exists(path)){
            Preferencias.guardar(new Constantes());
        }else {
            try{
                constantes = Preferencias.leer();
                if (constantes != null){
                    CB_Ciudades.getItems().setAll(constantes.getCiudades());
                    CB_Correos.getItems().setAll(constantes.getCorreo_user());
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
