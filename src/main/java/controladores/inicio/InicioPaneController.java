package controladores.inicio;

import helpers.Data;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import modelo.Evento;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InicioPaneController implements Initializable {


    @FXML
    private VBox listaEventos;

    @FXML
    private VBox eve1;

    @FXML
    private Label eve1nombre;

    @FXML
    private Label eve1fecha;

    @FXML
    private Label eve1dic;

    @FXML
    private VBox eve2;

    @FXML
    private Label eve2nombre;

    @FXML
    private Label eve2fecha;

    @FXML
    private Label eve2dic;

    @FXML
    private VBox eve3;

    @FXML
    private Label eve3nombre;

    @FXML
    private Label eve3fecha;

    @FXML
    private Label eve3dic;

    private void rellenarEventos(List<Evento> eventos, int cont){
        if (cont > 0){
            eve1nombre.setText(eventos.get(0).getPresentador());
            eve1fecha.setText(eventos.get(0).getFecha().toString());
            eve1dic.setText(eventos.get(0).getDireccion());
            eve1nombre.setVisible(true);
            eve1fecha.setVisible(true);
            eve1dic.setVisible(true);
            if (cont > 1){
                eve2nombre.setText(eventos.get(1).getPresentador());
                eve2fecha.setText(eventos.get(1).getFecha().toString());
                eve2dic.setText(eventos.get(0).getDireccion());
                eve2nombre.setVisible(true);
                eve2fecha.setVisible(true);
                eve2dic.setVisible(true);
                if (cont > 2){
                    eve3nombre.setText(eventos.get(2).getPresentador());
                    eve3fecha.setText(eventos.get(2).getFecha().toString());
                    eve3dic.setText(eventos.get(0).getDireccion());
                    eve3nombre.setVisible(true);
                    eve3fecha.setVisible(true);
                    eve3dic.setVisible(true);
                }
            }
        }
    }

    private List<Evento> filtrar(){
        List<Evento> eventos = Data.getEventos();
        List<Evento> eventosFil = new ArrayList<>();

        int cont = 0;

        for (Evento e:eventos){
            if (0<3){
                if (e.getFecha().compareTo(LocalDate.now()) >= 0){
                    eventosFil.add(e);
                    cont++;
                }
            }
        }
        return eventosFil;
    }

    private void iniciarEventos(){
        List<Evento> eventosFil = filtrar();
        rellenarEventos(eventosFil,eventosFil.size());
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarEventos();
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), event -> iniciarEventos()));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }


}
