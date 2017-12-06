package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLANES")
public class Planes implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Planes")
    int ID;

    @Column(name = "Nombre")
    String nombre;

    @Column(name = "Dias")
    int dias;

    @Column(name = "Gratitud_Primera")
    int bonoGratitudPrimer;

    @Column(name = "Personal_Primera")
    int bonoPersonalPrimer;

    @Column(name = "CoPatrocinador_Primera")
    int bonoCoPatrocinadorPrimer;

    @Column(name = "CoPatrocinador_Segunda")
    int bonoCoPatrocinadorSegundo;

    @Column(name = "Personal_Segunda")
    int bonoPersonalSegundo;

    @Column(name = "Gratitud_Segunda")
    int bonoGratitudSegundo;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "planes")
    @Column(name = "Clientes")
    private List<Cliente> clientes = new ArrayList<>();


    public Planes() {
    }

    public Planes(String nombre, int dias, int bonoGratitudPrimer, int bonoPersonalPrimer, int bonoCoPatrocinadorPrimer,
                  int bonoCoPatrocinadorSegundo, int bonoPersonalSegundo, int bonoGratitudSegundo) {
        this.nombre = nombre;
        this.dias = dias;
        this.bonoGratitudPrimer = bonoGratitudPrimer;
        this.bonoPersonalPrimer = bonoPersonalPrimer;
        this.bonoCoPatrocinadorPrimer = bonoCoPatrocinadorPrimer;
        this.bonoCoPatrocinadorSegundo = bonoCoPatrocinadorSegundo;
        this.bonoPersonalSegundo = bonoPersonalSegundo;
        this.bonoGratitudSegundo = bonoGratitudSegundo;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getBonoGratitudPrimer() {
        return bonoGratitudPrimer;
    }

    public void setBonoGratitudPrimer(int bonoGratitudPrimer) {
        this.bonoGratitudPrimer = bonoGratitudPrimer;
    }

    public int getBonoPersonalPrimer() {
        return bonoPersonalPrimer;
    }

    public void setBonoPersonalPrimer(int bonoPersonalPrimer) {
        this.bonoPersonalPrimer = bonoPersonalPrimer;
    }

    public int getBonoCoPatrocinadorPrimer() {
        return bonoCoPatrocinadorPrimer;
    }

    public void setBonoCoPatrocinadorPrimer(int bonoCoPatrocinadorPrimer) {
        this.bonoCoPatrocinadorPrimer = bonoCoPatrocinadorPrimer;
    }

    public int getBonoCoPatrocinadorSegundo() {
        return bonoCoPatrocinadorSegundo;
    }

    public void setBonoCoPatrocinadorSegundo(int bonoCoPatrocinadorSegundo) {
        this.bonoCoPatrocinadorSegundo = bonoCoPatrocinadorSegundo;
    }

    public int getBonoPersonalSegundo() {
        return bonoPersonalSegundo;
    }

    public void setBonoPersonalSegundo(int bonoPersonalSegundo) {
        this.bonoPersonalSegundo = bonoPersonalSegundo;
    }

    public int getBonoGratitudSegundo() {
        return bonoGratitudSegundo;
    }

    public void setBonoGratitudSegundo(int bonoGratitudSegundo) {
        this.bonoGratitudSegundo = bonoGratitudSegundo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
