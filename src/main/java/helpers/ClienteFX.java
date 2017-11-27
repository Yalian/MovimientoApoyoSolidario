package helpers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class ClienteFX extends RecursiveTreeObject<ClienteFX> {


    SimpleIntegerProperty id;

    SimpleStringProperty cedula;

    SimpleStringProperty nombres;

    SimpleStringProperty apellidos;

    SimpleStringProperty ciudad;

    SimpleStringProperty celular;

    SimpleStringProperty correo;

    SimpleStringProperty direccion;

    SimpleIntegerProperty patrocinadorID;

    SimpleStringProperty patrocinador;

    SimpleStringProperty coPatrocinador;

    LocalDate fechaRegistro;

    boolean visitante = true;

    Byte[] cedulaPic;


    public ClienteFX(int id, String cedula, String nombres, String apellidos, String ciudad, String celular,
                     String correo, String direccion,int patrocinadorID, String patrocinador, String coPatrocinador, LocalDate fechaRegistro) {
        this.id = new SimpleIntegerProperty(id);
        this.cedula = new SimpleStringProperty(cedula);
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.celular = new SimpleStringProperty(celular);
        this.correo = new SimpleStringProperty(correo);
        this.direccion = new SimpleStringProperty(direccion);
        this.patrocinadorID = new SimpleIntegerProperty(patrocinadorID);
        this.patrocinador = new SimpleStringProperty(patrocinador);
        this.coPatrocinador = new SimpleStringProperty(coPatrocinador);
        this.fechaRegistro = fechaRegistro;
    }


    public int getId() {
        return id.getValue();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCedula() {
        return cedula.get();
    }

    public SimpleStringProperty cedulaProperty() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }

    public String getNombres() {
        return nombres.get();
    }

    public SimpleStringProperty nombresProperty() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public SimpleStringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
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

    public String getCelular() {
        return celular.get();
    }

    public SimpleStringProperty celularProperty() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
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

    public String getDireccion() {
        return direccion.get();
    }

    public SimpleStringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getPatrocinador() {
        return patrocinador.get();
    }

    public SimpleStringProperty patrocinadorProperty() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador.set(patrocinador);
    }

    public String getCoPatrocinador() {
        return coPatrocinador.get();
    }

    public SimpleStringProperty coPatrocinadorProperty() {
        return coPatrocinador;
    }

    public void setCoPatrocinador(String coPatrocinador) {
        this.coPatrocinador.set(coPatrocinador);
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public SimpleStringProperty fechaRegistroProperty() {
        return new SimpleStringProperty(fechaRegistro.toString());
    }

    @Override
    public String toString() {
        return nombres.getValue()+"";
    }
}
