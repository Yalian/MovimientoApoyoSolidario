package controladores.cliente;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import helpers.Data;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import helpers.ClienteFX;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class registroPaneController implements Initializable {


    @FXML
    private JFXButton btn_agregar;

    @FXML
    private JFXButton btn_actualizar;

    @FXML
    private JFXButton btn_eliminar;

    @FXML
    private JFXButton btn_cargarCSV;

    @FXML
    private JFXTreeTableView<ClienteFX> TV_Principal;

    @FXML
    private JFXTextField TXT_BuscarField;

    @FXML
    private JFXComboBox<String> CB_TipoBusqueda;

    @FXML
    private TableColumn<ClienteFX, String> fechaCol;



    @FXML
    void verCliente(MouseEvent event) {
        if (event.getClickCount() == 2) {
             try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/cliente/verClientePane.fxml"));


                 loader.setControllerFactory(controllerClass -> {
                     if (controllerClass == verClientePane.class) {
                         verClientePane controller = new verClientePane();
                         controller.setClientePane(TV_Principal.getSelectionModel().getSelectedItem().getValue().getId());
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
    void cargarMod(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/cliente/modClientePane.fxml"));

            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == modClientePane.class) {
                    modClientePane controller = new modClientePane();
                    controller.setClientePane(TV_Principal.getSelectionModel().getSelectedItem().getValue().getId());
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


    public void crearColum(){
        JFXTreeTableColumn<ClienteFX, Number> idCol = new JFXTreeTableColumn<>("ID");
        idCol.setCellValueFactory(param -> param.getValue().getValue().idProperty());
        idCol.setVisible(false);

        JFXTreeTableColumn<ClienteFX, String> fechaCol = new JFXTreeTableColumn<>("Fecha de Registro");
        fechaCol.setCellValueFactory(param -> param.getValue().getValue().fechaRegistroProperty());
        fechaCol.setVisible(false);

        JFXTreeTableColumn<ClienteFX, String> nombreCol = new JFXTreeTableColumn<>("Nombres");
        nombreCol.setCellValueFactory(param -> param.getValue().getValue().nombresProperty());

        JFXTreeTableColumn<ClienteFX, String> apellidosCol = new JFXTreeTableColumn<>("Apellidos");
        apellidosCol.setCellValueFactory(param -> param.getValue().getValue().apellidosProperty());
        apellidosCol.setVisible(false);

        JFXTreeTableColumn<ClienteFX, String> ciudadCol = new JFXTreeTableColumn<>("Ciudad");
        ciudadCol.setCellValueFactory(param -> param.getValue().getValue().ciudadProperty());

        JFXTreeTableColumn<ClienteFX, String> celularCol = new JFXTreeTableColumn<>("Celular");
        celularCol.setCellValueFactory(param -> param.getValue().getValue().celularProperty());
        celularCol.setVisible(false);

        JFXTreeTableColumn<ClienteFX, String> correoCol = new JFXTreeTableColumn<>("Correo");
        correoCol.setCellValueFactory(param -> param.getValue().getValue().correoProperty());

        JFXTreeTableColumn<ClienteFX, String> patrocinadorCol = new JFXTreeTableColumn<>("Patrocinador");
        patrocinadorCol.setCellValueFactory(param -> param.getValue().getValue().patrocinadorProperty());

        TV_Principal.getColumns().setAll(idCol,fechaCol,nombreCol,apellidosCol,ciudadCol,celularCol,correoCol,patrocinadorCol);

    }

    private void refrescar(){
        ObservableList<ClienteFX> data = Data.getClientes();
        if (data.size() > 0){
            data.remove(0);
        }
        TreeItem<ClienteFX> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        TV_Principal.setRoot(root);
        TV_Principal.setShowRoot(false);
    }


    @FXML
    void actualizarTabla(ActionEvent event) {
        refrescar();
    }


    @FXML
    void cargarAgregar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/cliente/agregarClientePane.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void eliminarTabla(ActionEvent event) {
        ClienteFX selected = TV_Principal.getSelectionModel().getSelectedItem().getValue();
        Data.remove(selected);
        refrescar();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CB_TipoBusqueda.getItems().addAll("Nombres","Apellidos","Patrocinador");
        CB_TipoBusqueda.getSelectionModel().selectFirst();
        System.out.println(Data.getList());

        TXT_BuscarField.textProperty().addListener((observable, oldValue, newValue) -> TV_Principal.setPredicate(planesFXTreeItem -> {
            switch (CB_TipoBusqueda.getSelectionModel().getSelectedItem()){
                case "Nombres":
                    return planesFXTreeItem.getValue().nombresProperty().getValue().startsWith(newValue);
                case "Apellidos":
                    return planesFXTreeItem.getValue().apellidosProperty().getValue().startsWith(newValue);
                case "Patrocinador":
                    return planesFXTreeItem.getValue().patrocinadorProperty().getValue().startsWith(newValue);
            }
            return false;
        }));

        crearColum();
        refrescar();
    }
}
