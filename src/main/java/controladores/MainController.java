package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import helpers.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import modelo.Cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private VBox menu;

    @FXML
    private BorderPane border;

    @FXML
    private JFXButton BTN_Configuracion;

    private void setNode(Node node) {
        border.setCenter(node);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Data.getClientes().size() == 0){
            Cliente c = new Cliente("Ninguno");
            Data.persist(c);
        }

        try {
            HBox configuracionPane = FXMLLoader.load(getClass().getResource("/vista/configuracionPane.fxml"));
            HBox inicioPane = FXMLLoader.load(getClass().getResource("/vista/home/inicioPane.fxml"));
            HBox registroPane = FXMLLoader.load(getClass().getResource("/vista/cliente/registroPane.fxml"));
            HBox proximamentePane = FXMLLoader.load(getClass().getResource("/vista/proximamente.fxml"));
            HBox eventoPane = FXMLLoader.load(getClass().getResource("/vista/eventos/eventoPane.fxml"));
            HBox planesPane = FXMLLoader.load(getClass().getResource("/vista/planes/planesPane.fxml"));
            JFXTabPane estadisticasPane = FXMLLoader.load(getClass().getResource("/vista/estadisticasPane.fxml"));

            setNode(inicioPane);

            BTN_Configuracion.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) ->
                    setNode(configuracionPane));

            for (Node node : menu.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            case "inicioVista":
                                setNode(inicioPane);
                                break;
                            case "registrosVista":
                                setNode(registroPane);
                                break;
                            case "configuracionVista":
                                setNode(configuracionPane);
                                break;
                            case "planesVista":
                                setNode(planesPane);
                                break;
                            case "eventosVista":
                                setNode(eventoPane);
                                break;
                            case "contactoVista":
                                setNode(proximamentePane);
                                break;
                            case "estadisticas":
                                setNode(estadisticasPane);
                                break;
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


