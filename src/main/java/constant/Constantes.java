package constant;


import javassist.SerialVersionUID;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Constantes implements Serializable{

    private static final long serialVersionUID = 1L;

    private ArrayList<String> ciudades= new ArrayList<>();

    private ArrayList<String> responsables = new ArrayList<>();

    private ArrayList<String> presentadores = new ArrayList<>();

    private String correo_user = "";
    private String correo_pass = "";

    public Constantes() {
        this.ciudades.add("Quito");
    }

    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<String> ciudades) {
        this.ciudades = ciudades;
    }

    public ArrayList<String> getResponsables() {
        return responsables;
    }

    public void setResponsables(ArrayList<String> responsables) {
        this.responsables = responsables;
    }

    public ArrayList<String> getPresentadores() {
        return presentadores;
    }

    public void setPresentadores(ArrayList<String> presentadores) {
        this.presentadores = presentadores;
    }

    public String getCorreo_user() {
        return correo_user;
    }

    public void setCorreo_user(String correo_user) {
        this.correo_user = correo_user;
    }

    public String getCorreo_pass() {
        return correo_pass;
    }

    public void setCorreo_pass(String correo_pass) {
        this.correo_pass = correo_pass;
    }

    @Override
    public String toString() {
        return "Constantes{" +
                "ciudades=" + ciudades +
                ", responsables=" + responsables +
                ", presentadores=" + presentadores +
                ", correo=" + correo_user +
                '}';
    }






}
