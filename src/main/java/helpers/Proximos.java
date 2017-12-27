package helpers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import modelo.Cliente;

import java.time.LocalDate;

public class Proximos extends RecursiveTreeObject<Proximos> {


    SimpleIntegerProperty id;

    SimpleStringProperty cliente;

    SimpleStringProperty fecha;

    SimpleIntegerProperty invitadosP;

    SimpleIntegerProperty invitadosS;


    public Proximos(int id,String cliente, LocalDate fecha, int invitados,int invitadosC) {
        this.id = new SimpleIntegerProperty(id);
        this.cliente = new SimpleStringProperty(cliente);
        this.fecha = new SimpleStringProperty(fecha.toString());
        this.invitadosP = new SimpleIntegerProperty(invitados);
        this.invitadosS = new SimpleIntegerProperty(invitadosC);
    }

    public String getCliente() {
        return cliente.get();
    }

    public int getId() {
        return id.get();
    }

    public int getInvitadosS() {
        return invitadosS.get();
    }

    public SimpleIntegerProperty invitadosSProperty() {
        return invitadosS;
    }

    public void setInvitadosS(int invitadosS) {
        this.invitadosS.set(invitadosS);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleStringProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
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

    public int getInvitados() {
        return invitadosP.get();
    }

    public SimpleIntegerProperty invitadosProperty() {
        return invitadosP;
    }

    public void setInvitados(int invitados) {
        this.invitadosP.set(invitados);
    }
}
