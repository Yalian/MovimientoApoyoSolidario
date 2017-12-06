package helpers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Path;
import modelo.*;
import org.h2.table.Plan;

import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Data {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");

    private static final String fileName = "Datos.obj";

    public static List<Cliente> getList(){
        EntityManager em  = emf.createEntityManager();
        try{

            return (List<Cliente>) em.createQuery("FROM Cliente").getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return null;

    }


    public static ObservableList<ClienteFX> getClientes(){
        EntityManager em  = emf.createEntityManager();
        ObservableList<ClienteFX> datos = FXCollections.observableArrayList();
        try{
            List<Cliente> t = em.createQuery("FROM Cliente").getResultList();
                for (Cliente t1: t) {
                    datos.add(new ClienteFX(
                            t1.getID_Cliente(),
                            t1.getCedula(),
                            t1.getNombres(),
                            t1.getApellidos(),
                            t1.getCiudad(),
                            t1.getCelular(),
                            t1.getCorreo(),
                            t1.getDireccion(),
                            t1.getPatrocinador(),
                            t1.getCoPatrocinador(),
                            t1.getFechaRegistro()
                    ));
                }

            return datos;
        }catch (Exception e){
            e.printStackTrace();
        }
        return datos;
    }

    public static ObservableList<EventoFX> getEventosFX(){
        EntityManager em  = emf.createEntityManager();
        ObservableList<EventoFX> datos = FXCollections.observableArrayList();
        try{
            List<Evento> t = em.createQuery("FROM Evento").getResultList();
            if (t != null){
                for (Evento t1: t) {
                    datos.add(new EventoFX(
                                    t1.getID_Planes(),
                                    t1.getFecha(),
                                    t1.getHora(),
                                    t1.getCiudad(),
                                    t1.getDireccion(),
                                    t1.getResponDinero(),
                                    t1.getPresentador()
                            )
                    );
                }
                return datos;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return datos;
    }

    public static ObservableList<PlanesFX> getPlanesFX(){
        EntityManager em  = emf.createEntityManager();
        ObservableList<PlanesFX> datos = FXCollections.observableArrayList();
        try{
            List<Planes> t = em.createQuery("FROM Planes").getResultList();
            if (t != null){
                for (Planes t1: t) {
                    datos.add(new PlanesFX(
                            t1.getID(),
                            t1.getNombre(),
                            t1.getDias(),
                            t1.getBonoGratitudPrimer(),
                            t1.getBonoPersonalPrimer(),
                            t1.getBonoCoPatrocinadorPrimer(),
                            t1.getBonoCoPatrocinadorSegundo(),
                            t1.getBonoPersonalSegundo(),
                            t1.getBonoGratitudSegundo()
                            )
                    );
                }
                return datos;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return datos;
    }

    public static List<Evento> getEventos(){
        EntityManager em  = emf.createEntityManager();
        List<Evento> eventos = em.createQuery("FROM Evento").getResultList();

        return eventos;
    }

    public static List<Planes> getPlanes(){
        EntityManager em  = emf.createEntityManager();
        List<Planes> planes = em.createQuery("FROM Planes").getResultList();

        em.close();
        return planes;

    }

    public static void persist(Cliente e){
        EntityManager em  = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            System.out.println(e.toString());
            em.getTransaction().commit();
        }catch (Exception e1){
            e1.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void persist(Object e){
        EntityManager em  = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            System.out.println(e.toString());
            em.getTransaction().commit();
        }catch (Exception e1){
            e1.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void remove(ClienteFX e){
        EntityManager em  = emf.createEntityManager();

        Cliente client = em.find(Cliente.class, e.getId());

        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();

        em.close();
    }

    public static void removeEventoByID(int obj){
        EntityManager em  = emf.createEntityManager();

        Evento evento = em.find(Evento.class, obj);

        em.getTransaction().begin();
        em.remove(evento);
        em.getTransaction().commit();

        em.close();
    }

    public static void removePlanByID(int obj){
        EntityManager em  = emf.createEntityManager();

        Planes planes = em.find(Planes.class, obj);

        em.getTransaction().begin();
        em.remove(planes);
        em.getTransaction().commit();

        em.close();
    }

    public static Cliente findByID(ClienteFX obj){
        EntityManager em  = emf.createEntityManager();

        Cliente c = em.getReference(Cliente.class, obj.getId());

        em.close();

        System.out.println(c);

        System.out.println( "id es :" + obj);

        System.out.println(c.toString());

        return c;
    }

    public static Evento findEventoByID(int s){
        EntityManager em  = emf.createEntityManager();
        System.out.println("Buscando Plan con ID : " + s);

        Evento plan = em.find(Evento.class,s);

        System.out.println("Plan encontrado : "+plan.toString());

        em.close();

        return plan;

    }

    public static Cliente findClienteByID(int s){
        EntityManager em  = emf.createEntityManager();

        Cliente c = em.find(Cliente.class, s);

        System.out.println(c);

        System.out.println( "id es :" + s);

        System.out.println(c.toString());
        em.close();

        return c;
    }

    public static Planes findPlanByID(int s){
        EntityManager em  = emf.createEntityManager();

        Planes c = em.find(Planes.class, s);

        System.out.println(c);

        System.out.println( "id es :" + s);

        System.out.println(c.toString());
        em.close();

        return c;
    }

    public static void mergeClientByID(int s, Cliente c){
        EntityManager em  = emf.createEntityManager();

        Cliente c1 = em.find(Cliente.class,s);

        em.getTransaction().begin();

        c1.setCedula(c.getCedula());
        c1.setNombres(c.getNombres());
        c1.setApellidos(c.getApellidos());
        c1.setCiudad(c.getCiudad());
        c1.setCelular(c.getCelular());
        c1.setCorreo(c.getCorreo());
        c1.setDireccion(c.getDireccion());

        em.getTransaction().commit();

        em.close();
    }

    public static void agregarFoto(int IDCliente, File foto){
        EntityManager em  = emf.createEntityManager();
        Cliente cliente = em.find(Cliente.class, IDCliente);

        em.getTransaction().begin();


        byte[] picInBytes = new byte[(int) foto.length()];
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(foto);
            fileInputStream.read(picInBytes);
            fileInputStream.close();
            cliente.setCedulaPic(picInBytes);

            em.getTransaction().commit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        em.close();



    }

    public static List<Modelo> leerDatos(File p){
        try {
            String fileReader = new FileReader(p).toString();
            byte[] encoded = Files.readAllBytes(Paths.get(p.toURI()));
            String json= new String(encoded, StandardCharsets.UTF_8);

            Type listType = new TypeToken<ArrayList<Modelo>>(){}.getType();

            List<Modelo> constantes = new Gson().fromJson(json,listType);

            System.out.println(constantes);


            return constantes;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void procesarData(File obj,int id){
        EntityManager em  = emf.createEntityManager();
        em.getTransaction().begin();

        List<Modelo> datos = leerDatos(obj);

        for (Modelo dato : datos){
            Cliente cliente = findClienteByID(dato.getId());
            Evento evento = findEventoByID(id);
            switch (dato.getTipo()){
                case "Entrega":
                    Cosecha cosecha = new Cosecha(dato.getMonto(),cliente,evento);
                    em.persist(cosecha);
                    break;
                case "Recepcion":
                    if (dato.isBTC()){
                        Siembra siembra = new Siembra(cliente,evento,true,dato.getMonto());
                        em.persist(siembra);
                    }else {
                        Siembra siembra = new Siembra(cliente,evento,dato.getMonto());
                        em.persist(siembra);
                    }
                    break;
                case "Visitante":
                    evento.getAsistencias().getClientes().add(cliente);
                    em.merge(evento);
                    break;
            }
        }



        em.getTransaction().commit();
        em.close();





    }

    public static List<Cosecha> getCosechas(int id){
        EntityManager em  = emf.createEntityManager();
        List<Cosecha> cosechas;

        Cliente cliente = em.find(Cliente.class,id);
        cosechas = cliente.getID_Cosechas();
        cosechas.size();

        em.close();

        System.out.println(cosechas);

        return cosechas;
    }

    public static List<Siembra> getSiembras(int id){
        EntityManager em  = emf.createEntityManager();
        List<Siembra> siembras;

        Cliente cliente = em.find(Cliente.class,id);
        siembras = cliente.getID_Siembras();
        siembras.size();

        em.close();

        System.out.println(""+siembras);

        return siembras;


    }

    public static List<Siembra> getSiembrasPorEvento(int id){
        EntityManager em  = emf.createEntityManager();
        List<Siembra> siembras;

        Evento evento = findEventoByID(id);
        siembras = evento.getSiembras();
        siembras.size();

        em.close();


        return siembras;
    }

    public static List<Cosecha> getCosechasPorEvento(int id){
        EntityManager em  = emf.createEntityManager();
        List<Cosecha> cosechas;

        Evento evento = findEventoByID(id);
        cosechas = evento.getCosechas();
        cosechas.size();

        em.close();


        return cosechas;
    }

    public static List<Cliente> getClientesPorFecha(LocalDate date){
        EntityManager em  = emf.createEntityManager();
        List<Cliente> clienteFil = new ArrayList<>();

        try{
            List<Cliente> clientes = getList();
            for (Cliente c:clientes){
                if (c.getFechaRegistro() == date){
                    clienteFil.add(c);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        em.close();

        return clienteFil;
    }

    public static void mergePlanByID(int s, Evento p){
        EntityManager em  = emf.createEntityManager();

        Evento p1 = em.find(Evento.class,s);

        em.getTransaction().begin();

        p1.setFecha(p.getFecha());
        p1.setHora(p.getHora());
        p1.setCiudad(p.getCiudad());
        p1.setDireccion(p.getDireccion());
        p1.setPresentador(p.getPresentador());
        p1.setResponDinero(p.getResponDinero());

        em.getTransaction().commit();

        em.close();
    }

    public static Asistencias getAsistenciaPorEvento(int eventoID) {
        EntityManager em  = emf.createEntityManager();
        Asistencias asistencias;
        Evento evento = findEventoByID(eventoID);
        asistencias = evento.getAsistencias();

        em.close();

        return asistencias;

    }

    public static void removePlan(Cliente cliente, Planes planes){
        EntityManager em  = emf.createEntityManager();

        em.getTransaction().begin();
        Cliente cliente1 = em.find(Cliente.class,cliente.getID_Cliente());
        Planes planes1 = em.find(Planes.class, planes.getID());

        cliente1.getPlanes().remove(planes1);

        em.getTransaction().commit();
        em.close();
    }

    public static void addPlan(Cliente cliente, Planes planes){
        EntityManager em  = emf.createEntityManager();

        em.getTransaction().begin();

        Cliente cliente1 = em.find(Cliente.class,cliente.getID_Cliente());
        Planes p = em.find(Planes.class,planes.getID());

        cliente1.getPlanes().add(p);
        em.persist(cliente1);

        em.flush();

        em.getTransaction().commit();
        em.close();


    }







}
