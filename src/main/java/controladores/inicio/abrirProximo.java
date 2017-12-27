package controladores.inicio;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import helpers.Data;
import helpers.Invitados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import modelo.Cliente;

import java.net.URL;
import java.util.ResourceBundle;

public class abrirProximo implements Initializable {
    @FXML
    private Label nombre;

    @FXML
    private JFXTextField totalInv;

    @FXML
    private JFXTextField invPrimer;

    @FXML
    private JFXTextField invSegundo;

    @FXML
    private JFXTextField planInscrito;

    @FXML
    private JFXTextField bonoPatro;

    @FXML
    private JFXTextField bonoCoPatro;

    @FXML
    private JFXTreeTableView<Invitados> invitadosPrimer;



    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Cliente base;
    ObservableList<Invitados> invitados;
    int invPrim = 0;
    int invSeg = 0;

    private ObservableList<Invitados> obtenerInvitados(){
        invitados = FXCollections.observableArrayList();

        for (Cliente c:base.getInvitados()){
            if (!c.isVisitante()){
                invitados.add(new Invitados(c.getNombres(),1));
                for (Cliente l: c.getInvitados()){
                    if (!l.isVisitante()){
                        invitados.add(new Invitados(l.getNombres(),2));
                    }
                }
            }
        }
        return invitados;
    }

    private void crearColumnas(){
        JFXTreeTableColumn<Invitados, String> nombresCol = new JFXTreeTableColumn<>("Nombre");
        nombresCol.setCellValueFactory(param -> param.getValue().getValue().nombreProperty());

        JFXTreeTableColumn<Invitados, Number> nivelCol = new JFXTreeTableColumn<>("Invitados");
        nivelCol.setCellValueFactory(param -> param.getValue().getValue().nivelProperty());

        invitadosPrimer.getColumns().setAll(nombresCol,nivelCol);
    }

    private void refrescarTabla(){
        ObservableList<Invitados> invitados = obtenerInvitados();
        TreeItem<Invitados> root = new RecursiveTreeItem<>(invitados, RecursiveTreeObject::getChildren);
        invitadosPrimer.setRoot(root);
        invitadosPrimer.setShowRoot(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        base = Data.findClienteByID(id);
        crearColumnas();
        refrescarTabla();
        String plan;

        if (base.getPlanes() == null){
            plan = "No hay plan inscrito";
        }else {
            plan = base.getPlanes().getNombre();
        }

        for (Invitados i: invitados){
            if (i.getNivel() == 1){
                invPrim++;
            }else {
                invSeg++;
            }
        }


        nombre.setText(base.getNombres());
        totalInv.setText(invitados.size()+"");
        invPrimer.setText(invPrim+"");
        invSegundo.setText(invSeg+"");
        planInscrito.setText(plan);
        bonoPatro.setText(invPrim*base.getPlanes().getBonoGratitudPrimer()+"");
        bonoCoPatro.setText(invSeg*base.getPlanes().getBonoCoPatrocinadorPrimer()+"");

    }
}
