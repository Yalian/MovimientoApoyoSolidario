package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_Cliente")
    int ID_Cliente;

    @Column(name = "CEDULA")
    String cedula;

    @Column(name = "NOMBRES")
    String nombres = "";

    @Column(name = "APELLIDOS")
    String apellidos = "";

    @Column(name = "CIUDAD")
    String ciudad = "";

    @Column(name = "ID_Patrocinador")
    int ID_Patrocinador;

    @Column(name = "PATROCINADOR")
    String patrocinador;

    @Column(name = "CO_PATROCINADOR")
    String coPatrocinador = "";

    @Column(name = "ID_CoPatrocinador")
    int ID_CoPatrocinador;

    @Column(name = "CELULAR")
    String celular = "";

    @Column(name = "CORREO")
    String correo = "";

    @Column(name = "DIRECCION")
    String direccion = "";

    @Column(name = "FECHA_REGISTRO")
    LocalDate fechaRegistro;

    @Lob
    @Column(name = "CEDULA_PIC")
    Byte[] cedulaPic;


    //**********************************
    @Column(name = "FASE")
    int fase;

    @Column(name = "SOLO_VISITANTE")
    boolean visitante = true;

    @OneToMany(mappedBy = "ID_Cliente", cascade = CascadeType.ALL)
    @Column(name = "ID_Cosechas")
    List<Cosecha> ID_Cosechas = new ArrayList<>();

    @OneToMany(mappedBy ="ID_Cliente", cascade = CascadeType.ALL)
    @Column(name = "ID_Siembras")
    List<Siembra> ID_Siembras = new ArrayList<>();

    @Column(name = "ASISTENCIAS")
    LocalDate[] asistencias;


    //*****************************************

    public Cliente(){}


    public Cliente(String cedula, String nombres, String apellidos, String ciudad, int ID_Patrocinador, String patrocinador, String coPatrocinador, int ID_CoPatrocinador, String celular, String correo, String direccion, LocalDate fechaRegistro) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.ID_Patrocinador = ID_Patrocinador;
        this.patrocinador = patrocinador;
        this.coPatrocinador = coPatrocinador;
        this.ID_CoPatrocinador = ID_CoPatrocinador;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente(String cedula, String nombres, String apellidos, String ciudad, String celular, String correo, String direccion) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Cliente(String nombres) {
        this.nombres = nombres;
        this.ID_Patrocinador = 0;
    }

    public Byte[] getCedulaPic() {
        return cedulaPic;
    }

    public void setCedulaPic(Byte[] cedulaPic) {
        this.cedulaPic = cedulaPic;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public int getID_Patrocinador() {
        return ID_Patrocinador;
    }

    public void setID_Patrocinador(int patrocinadorID) {
        this.ID_Patrocinador = patrocinadorID;
    }

    public String getCoPatrocinador() {
        return coPatrocinador;
    }

    public void setCoPatrocinador(String coPatrocinador) {
        this.coPatrocinador = coPatrocinador;
    }

    public int getID_CoPatrocinador() {
        return ID_CoPatrocinador;
    }

    public void setID_CoPatrocinador(int ID_CoPatrocinador) {
        this.ID_CoPatrocinador = ID_CoPatrocinador;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + ID_Cliente +
                ", cedula='" + cedula + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", patrocinador='" + patrocinador + '\'' +
                ", coPatrocinador='" + coPatrocinador + '\'' +
                ", celular='" + celular + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
