package helpers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.Column;
import javax.persistence.Id;

public class PlanesFX extends RecursiveTreeObject<PlanesFX> {

    SimpleIntegerProperty ID;

    SimpleStringProperty nombre;

    SimpleIntegerProperty dias;

    SimpleIntegerProperty bonoGratitudPrimer;

    SimpleIntegerProperty bonoPersonalPrimer;

    SimpleIntegerProperty bonoCoPatrocinadorPrimer;

    SimpleIntegerProperty bonoCoPatrocinadorSegundo;

    SimpleIntegerProperty bonoPersonalSegundo;

    SimpleIntegerProperty bonoGratitudSegundo;

    public PlanesFX(int ID, String nombre, int dias, int bonoGratitudPrimer,
                    int bonoPersonalPrimer, int bonoCoPatrocinadorPrimer, int bonoCoPatrocinadorSegundo,
                    int bonoPersonalSegundo, int bonoGratitudSegundo) {
        this.ID = new SimpleIntegerProperty(ID);
        this.nombre = new SimpleStringProperty(nombre);
        this.dias = new SimpleIntegerProperty(dias);
        this.bonoGratitudPrimer = new SimpleIntegerProperty(bonoGratitudPrimer);
        this.bonoPersonalPrimer = new SimpleIntegerProperty(bonoPersonalPrimer);
        this.bonoCoPatrocinadorPrimer = new SimpleIntegerProperty(bonoCoPatrocinadorPrimer);
        this.bonoCoPatrocinadorSegundo = new SimpleIntegerProperty(bonoCoPatrocinadorSegundo);
        this.bonoPersonalSegundo = new SimpleIntegerProperty(bonoPersonalSegundo);
        this.bonoGratitudSegundo = new SimpleIntegerProperty(bonoGratitudSegundo);
    }

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getDias() {
        return dias.get();
    }

    public SimpleIntegerProperty diasProperty() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias.set(dias);
    }

    public int getBonoGratitudPrimer() {
        return bonoGratitudPrimer.get();
    }

    public SimpleIntegerProperty bonoGratitudPrimerProperty() {
        return bonoGratitudPrimer;
    }

    public void setBonoGratitudPrimer(int bonoGratitudPrimer) {
        this.bonoGratitudPrimer.set(bonoGratitudPrimer);
    }

    public int getBonoPersonalPrimer() {
        return bonoPersonalPrimer.get();
    }

    public SimpleIntegerProperty bonoPersonalPrimerProperty() {
        return bonoPersonalPrimer;
    }

    public void setBonoPersonalPrimer(int bonoPersonalPrimer) {
        this.bonoPersonalPrimer.set(bonoPersonalPrimer);
    }

    public int getBonoCoPatrocinadorPrimer() {
        return bonoCoPatrocinadorPrimer.get();
    }

    public SimpleIntegerProperty bonoCoPatrocinadorPrimerProperty() {
        return bonoCoPatrocinadorPrimer;
    }

    public void setBonoCoPatrocinadorPrimer(int bonoCoPatrocinadorPrimer) {
        this.bonoCoPatrocinadorPrimer.set(bonoCoPatrocinadorPrimer);
    }

    public int getBonoCoPatrocinadorSegundo() {
        return bonoCoPatrocinadorSegundo.get();
    }

    public SimpleIntegerProperty bonoCoPatrocinadorSegundoProperty() {
        return bonoCoPatrocinadorSegundo;
    }

    public void setBonoCoPatrocinadorSegundo(int bonoCoPatrocinadorSegundo) {
        this.bonoCoPatrocinadorSegundo.set(bonoCoPatrocinadorSegundo);
    }

    public int getBonoPersonalSegundo() {
        return bonoPersonalSegundo.get();
    }

    public SimpleIntegerProperty bonoPersonalSegundoProperty() {
        return bonoPersonalSegundo;
    }

    public void setBonoPersonalSegundo(int bonoPersonalSegundo) {
        this.bonoPersonalSegundo.set(bonoPersonalSegundo);
    }

    public int getBonoGratitudSegundo() {
        return bonoGratitudSegundo.get();
    }

    public SimpleIntegerProperty bonoGratitudSegundoProperty() {
        return bonoGratitudSegundo;
    }

    public void setBonoGratitudSegundo(int bonoGratitudSegundo) {
        this.bonoGratitudSegundo.set(bonoGratitudSegundo);
    }
}
