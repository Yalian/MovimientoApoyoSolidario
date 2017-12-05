package controladores.cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import helpers.Data;
import helpers.Preferencias;
import helpers.QR;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Planes;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ModClientePane implements Initializable{

    @FXML
    private JFXTextField TXT_ID;

    @FXML
    private JFXTextField TF_Cedula;

    @FXML
    private JFXTextField TF_Nombres;

    @FXML
    private JFXTextField TF_Apellidos;

    @FXML
    private JFXComboBox<String> CB_Ciudad;

    @FXML
    private JFXTextField TF_Celular;

    @FXML
    private JFXTextField TF_Direccion;

    @FXML
    private JFXTextField TF_Correo;

    @FXML
    private JFXTextField TF_Patrocinador;

    @FXML
    private JFXTextField TF_Copatrocinador;

    @FXML
    private DatePicker TF_FechaRegistro;

    @FXML
    private JFXComboBox<Planes> CB_PlanesDisponibles;

    @FXML
    private JFXComboBox<Planes> CB_PlanesInscritos;

    @FXML
    private JFXButton BTN_Cerrar;

    @FXML
    private ImageView codigoQR;

    @FXML
    private ImageView cedulaPic;

    int id;

    @FXML
    void addPlan(ActionEvent event) {
        Cliente s = Data.findClienteByID(id);

        //s.getPlanes().add(CB_PlanesDisponibles.getSelectionModel().getSelectedItem());

        Data.addPlan(s,CB_PlanesDisponibles.getSelectionModel().getSelectedItem());
    }

    @FXML
    void delPlan(ActionEvent event) {
        Cliente s = Data.findClienteByID(id);

       // s.getPlanes().remove(CB_PlanesInscritos.getSelectionModel().getSelectedItem());

        Data.removePlan(s,CB_PlanesInscritos.getSelectionModel().getSelectedItem());
    }

    @FXML
    void cargarImagen(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setSelectedExtensionFilter(
                    new FileChooser.ExtensionFilter("Image PNG","*.png"));

            File file = fileChooser.showOpenDialog(BTN_Cerrar.getScene().getWindow());

            Data.agregarFoto(id,file);
    }

    @FXML
    void cerrarVista(ActionEvent event) {
        Stage stage = (Stage) BTN_Cerrar.getScene().getWindow();
        stage.close();
    }

    public void setClientePane(int id){
        this.id = id ;
    }

    @FXML
    void actualizarUsr(ActionEvent event) {
        Cliente n = new Cliente(
                TF_Cedula.getText(),
                TF_Nombres.getText(),
                TF_Apellidos.getText(),
                CB_Ciudad.getSelectionModel().getSelectedItem(),
                TF_Celular.getText(),
                TF_Correo.getText(),
                TF_Direccion.getText()
        );

        Data.mergeClientByID(id,n);

        Stage stage = (Stage) TF_Patrocinador.getScene().getWindow();

        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cliente s = Data.findClienteByID(id);

        CB_Ciudad.getItems().setAll(Preferencias.leer().getCiudades());

        CB_PlanesInscritos.getItems().setAll(s.getPlanes());
        CB_PlanesDisponibles.getItems().setAll(Data.getPlanes());

        System.out.println(s.toString());

        TXT_ID.setText(s.getID_Cliente()+"");
        TF_Apellidos.setText(s.getApellidos()+"");
        TF_Nombres.setText(s.getNombres()+"");
        TF_Cedula.setText(s.getCelular()+"");
        TF_Celular.setText(s.getCelular()+"");
        CB_Ciudad.getSelectionModel().select(s.getCiudad());
        TF_Copatrocinador.setText(s.getCoPatrocinador()+"");
        TF_FechaRegistro.setValue(s.getFechaRegistro());
        TF_Direccion.setText(s.getDireccion()+"");
        TF_Patrocinador.setText(s.getPatrocinador());
        TF_Correo.setText(s.getCorreo()+"");

        TF_Patrocinador.setEditable(false);
        TXT_ID.setEditable(false);

        QR.displayImage(codigoQR,s.getQrCode());

        byte[] bytes = Data.findClienteByID(id).getCedulaPic();

        if (bytes != null){
            try {
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
                cedulaPic.setImage(SwingFXUtils.toFXImage(img,null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
