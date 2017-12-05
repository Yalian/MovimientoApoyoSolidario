package modelo;

import org.h2.table.Plan;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SIEMBRAS")
public class Siembra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Siembra")
    int ID_Siembra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Cliente")
    Cliente ID_Cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Evento")
    Evento ID_Evento;


    @Column(name = "Plan")
    Planes plan;

    @Column(name = "Tipo_BTC")
    boolean btc = false;

    @Column(name = "MONTO")
    double monto;



    public Siembra() {
    }

    public Siembra(Cliente clienteID, Evento eventoID, double monto) {
        this.ID_Cliente = clienteID;
        this.ID_Evento = eventoID;
        this.monto = monto;
    }

    public Siembra(Cliente ID_Cliente, Evento ID_Evento, boolean btc, double monto) {
        this.ID_Cliente = ID_Cliente;
        this.ID_Evento = ID_Evento;
        this.btc = btc;
        this.monto = monto;
    }

    public int getID_Siembra() {
        return ID_Siembra;
    }

    public Cliente getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(Cliente ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public Evento getID_Evento() {
        return ID_Evento;
    }

    public void setID_Evento(Evento ID_Evento) {
        this.ID_Evento = ID_Evento;
    }

    public boolean isBtc() {
        return btc;
    }

    public void setBtc(boolean btc) {
        this.btc = btc;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Siembra{" +
                "ID_Siembra=" + ID_Siembra +
                ", btc=" + btc +
                ", monto=" + monto +
                '}';
    }
}
