package controladores.eventos;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import helpers.Data;
import helpers.EventoFX;
import helpers.Preferencias;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Evento;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class EventoPane implements Initializable {


    @FXML
    private JFXDatePicker DP_FechaEvento;

    @FXML
    private JFXTimePicker TP_HoraEvento;

    @FXML
    private JFXComboBox<String> CB_Ciudad;

    @FXML
    private JFXTextField TF_Direccion;

    @FXML
    private JFXButton BT_Add;

    @FXML
    private JFXTextField TF_Presentador;

    @FXML
    private JFXTextField TF_Responsable;

    @FXML
    private JFXTextField TF_ID;

    @FXML
    private JFXTextField TF_Busqueda;

    @FXML
    private JFXTreeTableView<EventoFX> TV_Evento;

    @FXML
    private JFXComboBox<String> CB_TipoBusqueda;

    @FXML
    void agregarPlan(ActionEvent event) {
        Evento evento = new Evento(
                DP_FechaEvento.getValue(),
                TP_HoraEvento.getValue(),
                CB_Ciudad.getSelectionModel().getSelectedItem(),
                TF_Direccion.getText(),
                TF_Responsable.getText(),
                TF_Presentador.getText()
        );

        Data.persist(evento);

        refrescar();
    }


    @FXML
    void eliminarPlan(ActionEvent event) {
        int id = TV_Evento.getSelectionModel().getSelectedItem().getValue().getID_Planes();
        Data.removeEventoByID(id);
        refrescar();
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
                DP_FechaEvento.setValue(LocalDate.now());
                TP_HoraEvento.setValue(LocalTime.now());
                CB_Ciudad.getSelectionModel().clearSelection();
                TF_Direccion.setText("");
                TF_Responsable.setText("");
                TF_Presentador.setText("");
    }


    @FXML
    void abrirPlan(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/eventos/verEventoPane.fxml"));
                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == VerEvento.class) {
                        VerEvento controller = new VerEvento();
                        controller.setEventoID(TV_Evento.getSelectionModel().getSelectedItem().getValue().ID_PlanesProperty().get());
                        return controller ;
                    } else {
                        try {
                            return controllerClass.newInstance();
                        } catch (Exception exc) {
                            throw new RuntimeException(exc); // just bail
                        }
                    }
                });
                Parent root = loader.load();

                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void modificarPlan(ActionEvent event) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/eventos/modEventoPane.fxml"));
                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == ModEvento.class) {
                        ModEvento controller = new ModEvento();
                        controller.setIdEvento(TV_Evento.getSelectionModel().getSelectedItem().getValue().ID_PlanesProperty().get());
                        return controller ;
                    } else {
                        try {
                            return controllerClass.newInstance();
                        } catch (Exception exc) {
                            throw new RuntimeException(exc); // just bail
                        }
                    }
                });
                Parent root = loader.load();

                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void refrescar(){
        ObservableList<EventoFX> data = Data.getEventos();
        TreeItem<EventoFX> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        TV_Evento.setRoot(root);
        TV_Evento.setShowRoot(false);
    }

    private void crearTablas(){
        JFXTreeTableColumn<EventoFX, Number> idCol = new JFXTreeTableColumn<>("ID");
        idCol.setCellValueFactory(param -> param.getValue().getValue().ID_PlanesProperty());
        idCol.setVisible(false);

        JFXTreeTableColumn<EventoFX, String> fechaCol = new JFXTreeTableColumn<>("Fecha");
        fechaCol.setCellValueFactory(param ->  param.getValue().getValue().fechaProperty());

        JFXTreeTableColumn<EventoFX, String> direccionCol = new JFXTreeTableColumn<>("Direccion");
        direccionCol.setCellValueFactory(param -> param.getValue().getValue().direccionProperty());

        JFXTreeTableColumn<EventoFX, String> presentadorCol = new JFXTreeTableColumn<>("Presentador");
        presentadorCol.setCellValueFactory(param -> param.getValue().getValue().presentadorProperty());

        JFXTreeTableColumn<EventoFX, String> responsableCol = new JFXTreeTableColumn<>("Responsable");
        responsableCol.setCellValueFactory(param -> param.getValue().getValue().responDineroProperty());
        responsableCol.setVisible(false);

        TV_Evento.getColumns().setAll(idCol,fechaCol,direccionCol,presentadorCol,responsableCol);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CB_Ciudad.getItems().setAll(Preferencias.leer().getCiudades());
        CB_TipoBusqueda.getItems().setAll("Fecha","Responsable","Dirección");
        DP_FechaEvento.setValue(LocalDate.now());
        TP_HoraEvento.setValue(LocalTime.now());
        crearTablas();
        refrescar();

        TF_Busqueda.textProperty().addListener((observable, oldValue, newValue) -> TV_Evento.setPredicate(planesFXTreeItem -> {
            switch (CB_TipoBusqueda.getSelectionModel().getSelectedItem()){
                case "Fecha":
                    return planesFXTreeItem.getValue().fechaProperty().getValue().startsWith(newValue);
                case "Responsable":
                    return planesFXTreeItem.getValue().responDineroProperty().getValue().startsWith(newValue);
                case "Dirección":
                    return planesFXTreeItem.getValue().direccionProperty().getValue().startsWith(newValue);
            }
            return false;
        }));
    }

}
