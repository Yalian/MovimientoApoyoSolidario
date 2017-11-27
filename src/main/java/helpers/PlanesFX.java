package helpers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class PlanesFX extends RecursiveTreeObject<PlanesFX> {

    SimpleIntegerProperty ID_Planes;

    SimpleStringProperty fecha;

    SimpleStringProperty hora;

    SimpleStringProperty ciudad;

    SimpleStringProperty direccion;

    SimpleStringProperty responDinero;

    SimpleStringProperty presentador;

    public PlanesFX(int ID_Planes, LocalDate fecha, LocalTime hora,
                    String ciudad, String direccion, String responDinero,
                    String presentador) {
        this.ID_Planes = new SimpleIntegerProperty(ID_Planes);
        this.fecha = new SimpleStringProperty(fecha.toString());
        this.hora = new SimpleStringProperty(hora.toString());
        this.ciudad = new SimpleStringProperty(ciudad);
        this.direccion = new SimpleStringProperty(direccion);
        this.responDinero = new SimpleStringProperty(responDinero);
        this.presentador = new SimpleStringProperty(presentador);
    }

    public int getID_Planes() {
        return ID_Planes.get();
    }

    public SimpleIntegerProperty ID_PlanesProperty() {
        return ID_Planes;
    }

    public void setID_Planes(int ID_Planes) {
        this.ID_Planes.set(ID_Planes);
    }

    public String getFecha() {
        return fecha.get();
    }

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getHora() {
        return hora.get();
    }

    public SimpleStringProperty horaProperty() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora.set(hora);
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public SimpleStringProperty ciudadProperty() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public SimpleStringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getResponDinero() {
        return responDinero.get();
    }

    public SimpleStringProperty responDineroProperty() {
        return responDinero;
    }

    public void setResponDinero(String responDinero) {
        this.responDinero.set(responDinero);
    }

    public String getPresentador() {
        return presentador.get();
    }

    public SimpleStringProperty presentadorProperty() {
        return presentador;
    }

    public void setPresentador(String presentador) {
        this.presentador.set(presentador);
    }
}
