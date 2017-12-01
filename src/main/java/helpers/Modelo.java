package helpers;

import java.io.Serializable;

public class Modelo {

    private int id, monto;
    private String tipo, nombre;
    private boolean BTC = false;

    public Modelo(int id,String tipo, String nombre, int monto, boolean BTC) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.monto = monto;
        this.BTC = BTC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public boolean isBTC() {
        return BTC;
    }

    public void setBTC(boolean BTC) {
        this.BTC = BTC;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "id=" + id +
                ", monto=" + monto +
                ", tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", BTC=" + BTC +
                '}';
    }
}

