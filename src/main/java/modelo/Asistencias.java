package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Asistencias")
public class Asistencias implements Serializable{

    @Id
    @GeneratedValue
    int id;

    @OneToMany
    @Column(name = "Clientes")
    List<Cliente> clientes;

    @OneToOne(mappedBy = "asistencias")
    Evento evento;

    @Column(name = "Fecha")
    LocalDate fecha;

    public Asistencias() {
    }

    public Asistencias(Evento evento, LocalDate fecha) {
        this.evento = evento;
        this.fecha = fecha;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
