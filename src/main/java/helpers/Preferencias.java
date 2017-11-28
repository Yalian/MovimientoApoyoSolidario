package helpers;

import constant.Constantes;

import java.io.*;

public class Preferencias {


    private static final File direccion = new File("Preferencias.obj");

    public static void guardar(Constantes obj) {
        ObjectOutputStream salida;
        try {
            salida = new ObjectOutputStream(new FileOutputStream(direccion));

            salida.writeObject(obj);

            System.out.println(obj);

            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Constantes leer(){
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(direccion));

            Constantes constantes = (Constantes) entrada.readObject();

            System.out.println(constantes);

            entrada.close();

            return constantes;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




}
