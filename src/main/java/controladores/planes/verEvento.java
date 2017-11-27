package controladores.planes;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import helpers.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;
import modelo.Evento;

import java.net.URL;
import java.util.ResourceBundle;

public class verEvento implements Initializable{
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
    private JFXTextField siembrasPrimera;

    @FXML
    private JFXTextField siembrasSegunda;

    @FXML
    private JFXTextField siembrasTercera;

    @FXML
    private JFXTextField cosechasTotales;

    @FXML
    private JFXTextField cosechasPrimera;

    @FXML
    private JFXTextField cosechasSegunda;

    @FXML
    private JFXTextField cosechasTercera;

    @FXML
    private JFXTextField registros;

    @FXML
    private Accordion acordion;

    int eventoID;

    public void setEventoID(int eventoID) {
        this.eventoID = eventoID;
    }

    @FXML
    void cerrar(ActionEvent event) {
        Stage stage = (Stage) registros.getScene().getWindow();
        stage.close();
    }

    @FXML
    void enviarCorreos(ActionEvent event) {

    }

    @FXML
    void ingresarDatos(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Evento plan = Data.findPlanByID(eventoID);
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
