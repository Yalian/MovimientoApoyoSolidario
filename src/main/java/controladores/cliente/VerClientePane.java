package controladores.cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import helpers.Data;
import helpers.QR;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Cliente;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerClientePane implements Initializable{

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
    private JFXComboBox CB_TipoPlan;

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
    private JFXDatePicker TF_FechaRegistro;

    @FXML
    private JFXButton BTN_Cerrar;

    @FXML
    private ImageView codigoQR;


    @FXML
    private ImageView cedulaPic;


    int id;

    @FXML
    void cerrarVista(ActionEvent event) {
        Stage stage = (Stage) BTN_Cerrar.getScene().getWindow();
        stage.close();

    }

    public void setClientePane(int id){
        this.id = id ;
    }

    @FXML
    void guardarQR(MouseEvent event) {
            if (event.getClickCount() > 1){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setSelectedExtensionFilter(
                        new FileChooser.ExtensionFilter("Image PNG","*.png"));
                fileChooser.setInitialFileName("Codigo QR "+ TF_Nombres.getText() +" "+TF_Apellidos.getText());

                File file = fileChooser.showSaveDialog(BTN_Cerrar.getScene().getWindow());


                if(file != null){
                    if (!file.getName().contains(".png")){
                        file = new File(file.getAbsolutePath() + ".png");
                    }

                    QR.saveImage(QR.createQR(Data.findClienteByID(id).getQrCode()),file);
                }
            }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cliente s = Data.findClienteByID(id);

        System.out.println(s.toString());

        TXT_ID.setText(s.getID_Cliente()+"");
        TF_Apellidos.setText(s.getApellidos());
        TF_Nombres.setText(s.getNombres());
        TF_Cedula.setText(s.getCelular());
        TF_Celular.setText(s.getCelular());
        CB_Ciudad.getSelectionModel().select(s.getCiudad());
        TF_Copatrocinador.setText(s.getCoPatrocinador());
        TF_FechaRegistro.setValue(s.getFechaRegistro());
        TF_Direccion.setText(s.getDireccion());
        TF_Patrocinador.setText(s.getPatrocinador());
        TF_Correo.setText(s.getCorreo());

        QR.displayImage(codigoQR,s.getQrCode());

        TF_Apellidos.setEditable(false);
        TF_Nombres.setEditable(false);
        TF_Cedula.setEditable(false);
        TF_Celular.setEditable(false);
        CB_Ciudad.setEditable(false);
        TF_Copatrocinador.setEditable(false);
        TF_FechaRegistro.setDisable(true);
        TF_Direccion.setEditable(false);
        TF_Patrocinador.setEditable(false);
        TF_Correo.setEditable(false);

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
