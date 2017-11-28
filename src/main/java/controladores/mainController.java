package controladores;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

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
        try {
            HBox inicioPane = FXMLLoader.load(getClass().getResource("/vista/inicioPane.fxml"));
            HBox registroPane = FXMLLoader.load(getClass().getResource("/vista/cliente/registroPane.fxml"));
            HBox proximamentePane = FXMLLoader.load(getClass().getResource("/vista/proximamente.fxml"));
            HBox eventoPane = FXMLLoader.load(getClass().getResource("/vista/eventos/eventoPane.fxml"));
            HBox planesPane = FXMLLoader.load(getClass().getResource("/vista/planes/planesPane.fxml"));

            setNode(inicioPane);

            BTN_Configuracion.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) ->
                    setNode(proximamentePane));

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
                                setNode(proximamentePane);
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
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


