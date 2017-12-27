package helpers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Invitados extends RecursiveTreeObject<Invitados> {


    SimpleStringProperty nombre;

    SimpleIntegerProperty nivel;

    public Invitados(String nombre, int nivel) {
        this.nombre = new SimpleStringProperty(nombre);
        this.nivel = new SimpleIntegerProperty(nivel);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getNivel() {
        return nivel.get();
    }

    public SimpleIntegerProperty nivelProperty() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel.set(nivel);
    }
}
