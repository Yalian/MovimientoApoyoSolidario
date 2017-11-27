package controladores.cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import helpers.Data;
import helpers.QR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Cliente;

import java.net.URL;
import java.util.ResourceBundle;

public class modClientePane implements Initializable{

    @FXML
    private JFXTextField TXT_ID;

    @FXML
    private JFXTextField TF_Cedula;

    @FXML
    private JFXTextField TF_Nombres;

    @FXML
    private JFXTextField TF_Apellidos;

    @FXML
    private JFXTextField TF_Ciudad;

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
    private JFXButton BTN_Cerrar;

    @FXML
    private ImageView codigoQR;


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
    void actualizarUsr(ActionEvent event) {
        Cliente n = new Cliente(TF_Cedula.getText(),TF_Nombres.getText(),TF_Apellidos.getText(),
                TF_Ciudad.getText(),Data.findClienteByID(id).getID_Patrocinador(),TF_Patrocinador.getText(),
                TF_Copatrocinador.getText(),TF_Celular.getText(),TF_Correo.getText(),TF_Direccion.getText(),
                TF_FechaRegistro.getValue());

        Data.mergeClientByID(id,n);

        Stage stage = (Stage) TF_Patrocinador.getScene().getWindow();

        stage.close();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cliente s = Data.findClienteByID(id);

        System.out.println(s.toString());

        TXT_ID.setText(s.getID_Cliente()+"");
        TF_Apellidos.setText(s.getApellidos()+"");
        TF_Nombres.setText(s.getNombres()+"");
        TF_Cedula.setText(s.getCelular()+"");
        TF_Celular.setText(s.getCelular()+"");
        TF_Ciudad.setText(s.getCiudad()+"");
        TF_Copatrocinador.setText(s.getCoPatrocinador()+"");
        TF_FechaRegistro.setValue(s.getFechaRegistro());
        TF_Direccion.setText(s.getDireccion()+"");
        TF_Patrocinador.setText(s.getPatrocinador());
        TF_Correo.setText(s.getCorreo()+"");

        TF_Patrocinador.setEditable(false);
        TXT_ID.setEditable(false);

        QR.displayImage(codigoQR,s.getID_Cliente());
    }
}
