package controladores.planes;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controladores.eventos.modEvento;
import helpers.Data;
import helpers.PlanesFX;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Planes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class planesPane implements Initializable{

    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextField dias;

    @FXML
    private JFXTextField grat_1;

    @FXML
    private JFXTextField grat_2;

    @FXML
    private JFXTextField personal_1;

    @FXML
    private JFXTextField personal_2;

    @FXML
    private JFXTextField patro_1;

    @FXML
    private JFXTextField patro_2;

    @FXML
    private JFXButton BTN_Aceptar;

    @FXML
    private JFXTextField TF_Busqueda;

    @FXML
    private JFXComboBox<String> CB_TipoBusqueda;

    @FXML
    private JFXTreeTableView<PlanesFX> TV_Planes;

    private void refrescar(){
        ObservableList<PlanesFX> data = Data.getPlanes();
        TreeItem<PlanesFX> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        TV_Planes.setRoot(root);
        TV_Planes.setShowRoot(false);
    }

    private void crearTablas(){
        JFXTreeTableColumn<PlanesFX, Number> idCol = new JFXTreeTableColumn<>("ID");
        idCol.setCellValueFactory(param -> param.getValue().getValue().IDProperty());
        idCol.setVisible(false);

        JFXTreeTableColumn<PlanesFX, Number> diasCol = new JFXTreeTableColumn<>("Dias");
        diasCol.setCellValueFactory(param ->  param.getValue().getValue().diasProperty());

        JFXTreeTableColumn<PlanesFX, String> nombreCol = new JFXTreeTableColumn<>("Nombre");
        nombreCol.setCellValueFactory(param -> param.getValue().getValue().nombreProperty());

        TV_Planes.getColumns().setAll(idCol,diasCol,nombreCol);
    }

    @FXML
    void abrirModificar(MouseEvent event) {
        if (event.getClickCount() == 2){
            nombre.setText(TV_Planes.getSelectionModel().getSelectedItem().getValue().getNombre());
            dias.setText(String.valueOf(TV_Planes.getSelectionModel().getSelectedItem().getValue().getDias()));
            grat_1.setText(String.valueOf(TV_Planes.getSelectionModel().getSelectedItem().getValue().getBonoGratitudPrimer()));
            grat_2.setText(String.valueOf(TV_Planes.getSelectionModel().getSelectedItem().getValue().getBonoGratitudSegundo()));
            personal_1.setText(String.valueOf(TV_Planes.getSelectionModel().getSelectedItem().getValue().getBonoPersonalPrimer()));
            personal_2.setText(String.valueOf(TV_Planes.getSelectionModel().getSelectedItem().getValue().getBonoPersonalSegundo()));
            patro_1.setText(String.valueOf(TV_Planes.getSelectionModel().getSelectedItem().getValue().getBonoCoPatrocinadorPrimer()));
            patro_2.setText(String.valueOf(TV_Planes.getSelectionModel().getSelectedItem().getValue().getBonoCoPatrocinadorSegundo()));

            BTN_Aceptar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Data.persist(new Planes(
                            nombre.getText(),
                            Integer.parseInt(dias.getText()),
                            Integer.parseInt(grat_1.getText()),
                            Integer.parseInt(grat_2.getText()),
                            Integer.parseInt(personal_1.getText()),
                            Integer.parseInt(personal_2.getText()),
                            Integer.parseInt(patro_1.getText()),
                            Integer.parseInt(patro_2.getText())
                            )
                    );
                    BTN_Aceptar.setOnAction(event1 -> {
                        Planes plan = new Planes(
                                nombre.getText(),
                                Integer.parseInt(dias.getText()),
                                Integer.parseInt(grat_1.getText()),
                                Integer.parseInt(grat_2.getText()),
                                Integer.parseInt(patro_1.getText()),
                                Integer.parseInt(patro_2.getText()),
                                Integer.parseInt(personal_1.getText()),
                                Integer.parseInt(personal_2.getText())
                        );
                        Data.persist(plan);
                    });



                }
            });

        }

    }

    @FXML
    void agregarPlan(ActionEvent event) {
        Planes plan = new Planes(
                nombre.getText(),
                Integer.parseInt(dias.getText()),
                Integer.parseInt(grat_1.getText()),
                Integer.parseInt(grat_2.getText()),
                Integer.parseInt(patro_1.getText()),
                Integer.parseInt(patro_2.getText()),
                Integer.parseInt(personal_1.getText()),
                Integer.parseInt(personal_2.getText())
                );
        Data.persist(plan);
    }

    @FXML
    void eliminarPlan(ActionEvent event) {
        int id = TV_Planes.getSelectionModel().getSelectedItem().getValue().getID();
        Data.removePlanByID(id);
        refrescar();

    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        nombre.setText("");
        dias.setText("");
        grat_1.setText("");
        grat_2.setText("");
        patro_1.setText("");
        patro_2.setText("");
        personal_1.setText("");
        personal_2.setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crearTablas();
        refrescar();

    }
}
