package modelo;

import org.h2.table.Plan;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Patrocinador")
    Cliente ID_Patrocinador;

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
    byte[] cedulaPic;

    @Column(name = "Actual_Cosecha")
    int currentCosecha = 0;

    @Column(name = "Actual_Siembra")
    int currentSiembra = 0;


    //**********************************
    @Column(name = "FASE")
    int fase = 0;

    @OneToMany(mappedBy = "ID_Patrocinador", fetch = FetchType.EAGER)
    @Column(name = "Invitados")
    List<Cliente> invitados;

    @Column(name = "SOLO_VISITANTE")
    boolean visitante = true;

    @OneToMany(mappedBy = "ID_Cliente" ,cascade = CascadeType.ALL)
    @Column(name = "ID_Cosechas")
    List<Cosecha> ID_Cosechas = new ArrayList<>() ;

    @OneToMany(mappedBy ="ID_Cliente", cascade = CascadeType.ALL)
    @Column(name = "ID_Siembras")
    List<Siembra> ID_Siembras = new ArrayList<>() ;

    @OneToMany(mappedBy = "clientes" , cascade = CascadeType.ALL)
    @Column(name = "ASISTENCIAS")
    List<Asistencias> asistencias = new ArrayList<>() ;

    @ManyToOne
    @JoinColumn(name = "Planes")
    Planes planes;




    //*****************************************

    public Cliente(){}


    public Cliente(String cedula, String nombres, String apellidos, String ciudad, Cliente ID_Patrocinador, String celular,
                   String correo, String direccion, LocalDate fechaRegistro) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.ID_Patrocinador = ID_Patrocinador;
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
    }

    public String getQrCode(){
        return nombres+","+ID_Cliente;

    }

    public int getFase() {
        return fase;
    }

    public Cliente getID_Patrocinador() {
        return ID_Patrocinador;
    }

    public void setID_Patrocinador(Cliente ID_Patrocinador) {
        this.ID_Patrocinador = ID_Patrocinador;
    }

    public List<Cliente> getInvitados() {
        return invitados;
    }

    public void setInvitados(List<Cliente> invitados) {
        this.invitados = invitados;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getCurrentCosecha() {
        return currentCosecha;
    }

    public void setCurrentCosecha(int currentCosecha) {
        this.currentCosecha = currentCosecha;
    }

    public int getCurrentSiembra() {
        return currentSiembra;
    }

    public void setCurrentSiembra(int currentSiembra) {
        this.currentSiembra = currentSiembra;
    }

    public Planes getPlanes() {
        return planes;
    }

    public void setPlanes(Planes planes) {
        this.planes = planes;
    }

    public boolean isVisitante() {
        return visitante;
    }

    public void setVisitante(boolean visitante) {
        this.visitante = visitante;
    }

    public List<Cosecha> getID_Cosechas() {
        return ID_Cosechas;
    }

    public void setID_Cosechas(List<Cosecha> ID_Cosechas) {
        this.ID_Cosechas = ID_Cosechas;
    }

    public List<Siembra> getID_Siembras() {
        return ID_Siembras;
    }

    public void setID_Siembras(List<Siembra> ID_Siembras) {
        this.ID_Siembras = ID_Siembras;
    }

    public List<Asistencias> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencias> asistencias) {
        this.asistencias = asistencias;
    }

    public byte[] getCedulaPic() {
        return cedulaPic;
    }

    public void setCedulaPic(byte[] cedulaPic) {
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
                "ID_Cliente=" + ID_Cliente +
                ", cedula='" + cedula + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", ID_Patrocinador=" + ID_Patrocinador +
                ", celular='" + celular + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fase=" + fase +
                ", visitante=" + visitante +
                '}';
    }

}
