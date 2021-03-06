package controladores.eventos;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import constant.Constantes;
import controladores.cliente.ModClientePane;
import helpers.Calculos;
import helpers.Data;
import helpers.Preferencias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Cosecha;
import modelo.Evento;
import modelo.Siembra;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static helpers.Data.getSiembrasPorEvento;

public class VerEvento implements Initializable{
    @FXML
    private JFXTimePicker hora;

    @FXML
    private JFXDatePicker fecha;

    @FXML
    private JFXTextField ciudad;

    @FXML
    private JFXTextField direccion;

    @FXML
    private JFXTextField responsable;

    @FXML
    private JFXTextField presentador;

    @FXML
    private JFXTextField corteSiembras;

    @FXML
    private JFXTextField corteCosechas;

    @FXML
    private JFXTextField corteBalance;

    @FXML
    private JFXTextField asistenciaTotal;

    @FXML
    private JFXTextField asistenciaVisitantes;

    @FXML
    private JFXTextField asistenciaActivos;

    @FXML
    private JFXTextField siembrasBTC;

    @FXML
    private JFXTextField siembrasUSD;

    @FXML
    private JFXTextField cosechasTotales;

    @FXML
    private JFXTextField registros;

    @FXML
    private Accordion acordion;

    int eventoID;

    Calculos.LogicaEvento logica;

    public void setEventoID(int eventoID) {
        this.eventoID = eventoID;
    }

    @FXML
    void cerrar(ActionEvent event) {
        Stage stage = (Stage) registros.getScene().getWindow();
        stage.close();
    }

    private static void Error(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e);

        alert.showAndWait();
    }

    @FXML
    void enviarCorreos(ActionEvent event) {
        if (!Preferencias.leer().getCorreo_user().equalsIgnoreCase("")){
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/enviarCorreo.fxml"));
                Parent root = loader.load();

                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Error("Configurar primero un correo, Por Favor.");
        }


    }

    @FXML
    void ingresarDatos(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Leer Datos");

        File file = fileChooser.showOpenDialog(registros.getScene().getWindow());

        if (file != null) {
            Data.procesarData(file,eventoID);
            Data.setDatosIngresados(eventoID,true);
        }

    }

    private void rellenar(){
        corteSiembras.setText(logica.getcSiembras()+"");
        corteCosechas.setText(logica.getcCosechas()+"");
        corteBalance.setText(logica.getcBalance()+"");

        asistenciaTotal.setText(logica.getaTotal()+"");
        asistenciaVisitantes.setText(logica.getaVisitantes()+"");
        asistenciaActivos.setText(logica.getaActivos()+"");

        siembrasBTC.setText(logica.getsBTC()+"");
        siembrasUSD.setText(logica.getsUSD()+"");

        cosechasTotales.setText(logica.getcTotal()+"");

        registros.setText(logica.getRegTotales()+"");

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Evento plan = Data.findEventoByID(eventoID);
        logica = new Calculos.LogicaEvento(plan);
        rellenar();

        if (!plan.isDatosIngresados()){
            acordion.setDisable(true);
        }

        hora.setValue(plan.getHora());
        fecha.setValue(plan.getFecha());
        ciudad.setText(plan.getCiudad());
        direccion.setText(plan.getDireccion());
        responsable.setText(plan.getResponDinero());
        presentador.setText(plan.getPresentador());

    }
}
