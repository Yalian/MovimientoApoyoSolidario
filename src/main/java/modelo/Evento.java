package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EVENTOS")
public class Evento implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Planes")
    int ID_Planes;

    @Column(name = "FECHA_EVENTO")
    LocalDate fecha;

    @Column(name = "Hora_Evento")
    LocalTime hora;

    @Column(name = "Ciudad")
    String ciudad;

    @Column(name = "UBICACION")
    String direccion;

    @Column(name = "RESPONSABLE_DINERO")
    String responDinero;

    @Column(name = "PRESENTADOR")
    String presentador;

    @OneToMany(mappedBy = "ID_Evento")
    @Column(name = "Cosechas")
    List<Cosecha> cosechas = new ArrayList<>();

    @OneToMany(mappedBy = "ID_Evento")
    @Column(name = "Siembras")
    List<Siembra> siembras = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Asistencias")
    Asistencias asistencias;

    @Column(name = "CORTE_DIA")
    double corteDia = 0;

    @Column(name = "DATOS_INGRESADOS")
    boolean datosIngresados = false;

    @Column(name = "Plan")
    Planes plan;

    public Evento() {
    }

    public Evento(LocalDate fecha, LocalTime hora, String ciudad, String direccion,
                  String responDinero, String presentador) {
        this.fecha = fecha;
        this.hora = hora;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.responDinero = responDinero;
        this.presentador = presentador;
        asistencias = new Asistencias(this,this.fecha);
    }

    public Asistencias getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Asistencias asistencias) {
        this.asistencias = asistencias;
    }


    public Planes getPlan() {
        return plan;
    }

    public void setPlan(Planes plan) {
        this.plan = plan;
    }

    public int getID_Planes() {
        return ID_Planes;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getResponDinero() {
        return responDinero;
    }

    public void setResponDinero(String responDinero) {
        this.responDinero = responDinero;
    }

    public String getPresentador() {
        return presentador;
    }

    public void setPresentador(String presentador) {
        this.presentador = presentador;
    }

    public List<Cosecha> getCosechas() {
        return cosechas;
    }

    public void setCosechas(List<Cosecha> cosechas) {
        this.cosechas = cosechas;
    }

    public List<Siembra> getSiembras() {
        return siembras;
    }

    public void setSiembras(List<Siembra> siembras) {
        this.siembras = siembras;
    }

    public double getCorteDia() {
        return corteDia;
    }

    public void setCorteDia(double corteDia) {
        this.corteDia = corteDia;
    }

    public boolean isDatosIngresados() {
        return datosIngresados;
    }

    public void setDatosIngresados(boolean datosIngresados) {
        this.datosIngresados = datosIngresados;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "ID_Planes=" + ID_Planes +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", responDinero='" + responDinero + '\'' +
                ", presentador='" + presentador + '\'' +
                ", corteDia=" + corteDia +
                ", datosIngresados=" + datosIngresados +
                '}';
    }
}
