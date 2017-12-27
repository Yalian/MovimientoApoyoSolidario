package helpers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controladores.eventos.EnviarCorreoControlador;
import javafx.beans.property.SimpleStringProperty;

public class CorreosHelper extends RecursiveTreeObject<CorreosHelper> {

    SimpleStringProperty correo;

    public CorreosHelper(String correo) {
        this.correo = new SimpleStringProperty(correo);
    }

    public String getCorreo() {
        return correo.get();
    }

    public SimpleStringProperty correoProperty() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }
}