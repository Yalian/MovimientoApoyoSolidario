
import helpers.Data;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Cliente;


public class Principal extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/vista/main.fxml"));
        primaryStage.setTitle("M.A.S");
        primaryStage.setScene(new Scene(root));

        if (Data.getClientes().size() == 0){
            Cliente c = new Cliente("NINGUNO");
            Data.persist(c);
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


