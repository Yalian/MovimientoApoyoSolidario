package controladores.eventos;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import constant.Constantes;
import helpers.Data;
import helpers.Preferencias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;
import modelo.Evento;

import java.net.URL;
import java.util.ResourceBundle;

public class ModEvento implements Initializable {
    @FXML
    private JFXTimePicker hora;

    @FXML
    private JFXDatePicker fecha;

    @FXML
    private JFXComboBox<String> CB_Ciudad;

    @FXML
    private JFXTextField direccion;

    @FXML
    private JFXComboBox<String> CB_Responsable;

    @FXML
    private JFXComboBox<String> CB_Presentador;

    @FXML
    private Accordion acordion;

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

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }


    int idEvento;



    @FXML
    void modificar(ActionEvent event) {
        Evento evento = new Evento(
                fecha.getValue(),
                hora.getValue(),
                CB_Ciudad.getSelectionModel().getSelectedItem(),
                direccion.getText(),
                CB_Responsable.getSelectionModel().getSelectedItem(),
                CB_Presentador.getSelectionModel().getSelectedItem()
        );
        Data.mergePlanByID(idEvento,evento);

        cerrarV();
    }

    private void cerrarV(){
        Stage stage = (Stage) registros.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cerrar(ActionEvent event) {
        cerrarV();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Evento evento = Data.findEventoByID(idEvento);
        Constantes constantes = Preferencias.leer();

        CB_Ciudad.getItems().setAll(constantes.getCiudades());
        CB_Responsable.getItems().setAll(constantes.getResponsables());
        CB_Presentador.getItems().setAll(constantes.getPresentadores());

        hora.setValue(evento.getHora());
        fecha.setValue(evento.getFecha());
        CB_Ciudad.getSelectionModel().select(evento.getCiudad());
        direccion.setText(evento.getDireccion());
        CB_Responsable.getSelectionModel().select(evento.getResponDinero());
        CB_Presentador.getSelectionModel().select(evento.getPresentador());

    }
}
