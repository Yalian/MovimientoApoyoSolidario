package controladores.inicio;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controladores.cliente.ModClientePane;
import helpers.Calendario;
import helpers.Data;
import helpers.Proximos;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Cliente;
import modelo.Evento;

import java.io.IOException;
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
    private JFXTreeTableView<Proximos> TV_Proximos;

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

    private ObservableList<Proximos> obtenerPersonas(){
        List<Cliente> base = Data.getList();
        if (base.get(0).getNombres().equalsIgnoreCase("ninguno")){
            base.remove(0);
        }
        ObservableList<Proximos> proximos = FXCollections.observableArrayList();

        if (base != null){
            for (Cliente c: base){
                if (!c.isVisitante() || c.getPlanes() ==null){
                    int invitados = 0;
                    int invitadosC = 0;

                    invitados += c.getInvitados().size();

                    if (c.getInvitados() != null){
                        for (Cliente l:c.getInvitados()){
                            invitadosC += l.getInvitados().size();
                        }
                    }

                    proximos.add(new Proximos(c.getID_Cliente(),c.getNombres(),obtenerFecha(c),invitados,invitadosC));

                }
            }
        }

        return proximos;
    }

    private void crearColumnas(){
            JFXTreeTableColumn<Proximos, String> nombresCol = new JFXTreeTableColumn<>("Nombre");
            nombresCol.setCellValueFactory(param -> param.getValue().getValue().clienteProperty());

            JFXTreeTableColumn<Proximos, String> fechaCol = new JFXTreeTableColumn<>("Fecha Estimada");
            fechaCol.setCellValueFactory(param -> param.getValue().getValue().fechaProperty());

            JFXTreeTableColumn<Proximos, Number> invCol = new JFXTreeTableColumn<>("Invitados");
            invCol.setCellValueFactory(param ->
                    new SimpleIntegerProperty(param.getValue().getValue().invitadosProperty().get() +
                    param.getValue().getValue().invitadosSProperty().get()));

            TV_Proximos.getColumns().setAll(nombresCol,invCol,fechaCol);
    }

    private void refrescarTabla(){
        ObservableList<Proximos> proximos = obtenerPersonas();
        TreeItem<Proximos> root = new RecursiveTreeItem<>(proximos, RecursiveTreeObject::getChildren);
        TV_Proximos.setRoot(root);
        TV_Proximos.setShowRoot(false);
    }

    private LocalDate obtenerFecha(Cliente c){
        LocalDate fechaInicial = c.getFechaRegistro();
        return Calendario.getFecha(30,fechaInicial);
    }


    @FXML
    void abrirProximo(MouseEvent event) {
        if (event.getClickCount() >= 2){
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/home/abrirProximo.fxml"));
                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == abrirProximo.class) {
                        abrirProximo controller = new abrirProximo();
                        controller.setId(TV_Proximos.getSelectionModel().getSelectedItem().getValue().getId());
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarEventos();
        crearColumnas();
        refrescarTabla();

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), event ->
        {
            iniciarEventos();
            refrescarTabla();
        }
        ));

        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }


}
