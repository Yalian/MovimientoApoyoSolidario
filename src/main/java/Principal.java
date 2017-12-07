
import constant.Constantes;
import helpers.Data;
import helpers.Preferencias;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Cliente;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Principal extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/vista/main.fxml"));
        primaryStage.setTitle("M.A.S");
        primaryStage.setScene(new Scene(root));

        if (Data.getClientes().size() == 0){
            Cliente c = new Cliente("Ninguno");
            Data.persist(c);
        }

        Path path = Paths.get("Preferencias.obj");

        if (Preferencias.leer() == null){
            Preferencias.guardar(new Constantes());
        }

        for (Cliente c:Data.getList()){
            System.out.println("Estos son los invitados de : " + c.getNombres());
            System.out.println(c.getInvitados());
        }




        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });



        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


