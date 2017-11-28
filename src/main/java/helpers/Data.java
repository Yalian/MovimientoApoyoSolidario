package helpers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Cliente;
import modelo.Evento;
import modelo.Planes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Data {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");


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

    public static ObservableList<EventoFX> getEventos(){
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

    public static ObservableList<PlanesFX> getPlanes(){
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

    public static Evento findPlanByID(int s){
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





}
