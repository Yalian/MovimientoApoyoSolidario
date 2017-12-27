package controladores.eventos;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import helpers.Correos;
import helpers.CorreosHelper;
import helpers.Data;
import helpers.Preferencias;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import modelo.Cliente;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EnviarCorreoControlador implements Initializable{

    @FXML
    private JFXTextField asunto;

    @FXML
    private JFXTextArea mensaje;

    @FXML
    private Label estado;

    @FXML
    private JFXTextField correo;

    @FXML
    private JFXTreeTableView<CorreosHelper> lstCorreos;

    ObservableList<CorreosHelper> listaCorreos = FXCollections.observableArrayList();

    @FXML
    void agregarCorreo(ActionEvent event) {
        listaCorreos.add(new CorreosHelper(correo.getText()));
        refrescar();

    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) correo.getScene().getWindow();

        stage.close();

    }

    @FXML
    void eliminarCorreo(ActionEvent event) {
        listaCorreos.remove(lstCorreos.getSelectionModel().getSelectedItem().getValue());
        refrescar();

    }

    @FXML
    void enviarCorreo(ActionEvent event) {
        if (!Preferencias.leer().getCorreo_user().equalsIgnoreCase("")){
            Correos correos = new Correos(Preferencias.leer().getCorreo_user(),Preferencias.leer().getCorreo_pass());

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    boolean fxApplicationThread = Platform.isFxApplicationThread();
                    System.out.println("Is call on FXApplicationThread: " + fxApplicationThread);
                    // Run your time consuming task here!
                    for (CorreosHelper correo: listaCorreos) {
                        updateMessage("Estado : Enviando Mensajes...");
                        correos.EnviarCorreo(correo.getCorreo(),asunto.getText(),mensaje.getText());
                    }
                    return null; }
                @Override
                protected void succeeded() {
                    boolean fxApplicationThread = Platform.isFxApplicationThread();
                    System.out.println("Is call on FXApplicationThread: " + fxApplicationThread);
                    super.succeeded();
                    estado.textProperty().unbind();
                    estado.setText("Estado : Mensajes Enviados.");
                }
            };

            estado.textProperty().bind(task.messageProperty());
            new Thread(task).start();
        }
    }

    private void refrescar(){
        TreeItem<CorreosHelper> root = new RecursiveTreeItem<>(listaCorreos, RecursiveTreeObject::getChildren);
        lstCorreos.setRoot(root);
        lstCorreos.setShowRoot(false);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Cliente> clientes = Data.getList();

        for (Cliente c:clientes){
            if (!c.getCorreo().equalsIgnoreCase("")){
                listaCorreos.add(new CorreosHelper(c.getCorreo()));
            }
        }

        JFXTreeTableColumn<CorreosHelper, String> correosCol = new JFXTreeTableColumn<>("Correos");
        correosCol.setCellValueFactory(param -> param.getValue().getValue().correoProperty());

        lstCorreos.getColumns().setAll(correosCol);

        refrescar();



    }





}
