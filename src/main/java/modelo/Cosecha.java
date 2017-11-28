package modelo;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "COSECHA")
public class Cosecha implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Cosecha")
    int ID_Cosecha;

    @Column(name = "MONTO")
    double monto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Cliente")
    Cliente ID_Cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Evento")
    Evento ID_Evento;

    public Cosecha() {
    }

    public Cosecha(double monto, Cliente ID_Cliente, Evento ID_Evento) {
        this.monto = monto;
        this.ID_Cliente = ID_Cliente;
        this.ID_Evento = ID_Evento;
    }

    public int getId() {
        return ID_Cosecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }


}
