package helpers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import modelo.Cliente;

import java.time.LocalDate;

public class Proximos extends RecursiveTreeObject<Proximos> {


    SimpleStringProperty cliente;

    SimpleStringProperty fecha;

    SimpleIntegerProperty invitados;


    public Proximos(String cliente, LocalDate fecha, int invitados) {
        this.cliente = new SimpleStringProperty(cliente);
        this.fecha = new SimpleStringProperty(fecha.toString());
        this.invitados = new SimpleIntegerProperty(invitados);
    }

    public String getCliente() {
        return cliente.get();
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
        return invitados.get();
    }

    public SimpleIntegerProperty invitadosProperty() {
        return invitados;
    }

    public void setInvitados(int invitados) {
        this.invitados.set(invitados);
    }
}
