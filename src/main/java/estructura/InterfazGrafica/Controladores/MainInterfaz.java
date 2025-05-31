package estructura.InterfazGrafica.Controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainInterfaz extends Application {
    private static StackPane root;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pantallaPrincipal.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Sistema con múltiples menús");
        stage.setScene(scene);
        stage.show();

        // Guarda referencia al Stage para cambiar vistas desde otros lugares
        GestorVentanas.setStage(stage);
        GestorVentanas.setRoot((StackPane) root);
    }


}
