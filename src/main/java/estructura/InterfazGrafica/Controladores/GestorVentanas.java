package estructura.InterfazGrafica.Controladores;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class GestorVentanas {
    private static StackPane root;
    private static Stage stage;

    public static void setRoot(StackPane pane) {
        root = pane;
    }

    public static void setStage(Stage s) {
        stage = s;
    }
    /*
    public static StackPane getRoot() {
        return root;
    }

     */


    public static void cambiarVistaIzquierda(String nombreVentana) throws IOException {
        // crea uno loader con el archivo fxml indicado en "nombreVentana" y le hace un load para cargarlo
        FXMLLoader fxmlLoader = new FXMLLoader(MainInterfaz.class.getResource(nombreVentana));
        Parent vista = fxmlLoader.load();

        // Asegurarse de que el StackPane no esté vacío
        if (root.getChildren().isEmpty()) {
            root.getChildren().add(vista);
            return;
        }

        // Obtener la vista actual (último hijo del StackPane)
        Parent vistaActual = (Parent) root.getChildren().get(root.getChildren().size() - 1);

        // Colocar la nueva vista detrás de la actual y desplazada hacia la izquierda
        vista.setTranslateX(-root.getWidth());
        root.getChildren().add(vista);

        // Transición para mover la vista actual hacia la derecha (fuera de pantalla)
        javafx.animation.TranslateTransition transOut = new javafx.animation.TranslateTransition(Duration.millis(200), vistaActual);
        transOut.setToX(root.getWidth());

        // Transición para mover la nueva vista al centro
        javafx.animation.TranslateTransition transIn = new javafx.animation.TranslateTransition(Duration.millis(200), vista);
        transIn.setToX(0);

        // Cuando termina la transición, elimina la vista anterior
        transOut.setOnFinished(e -> root.getChildren().remove(vistaActual));

        // Ejecutar ambas transiciones
        transOut.play();
        transIn.play();
        /*
        // ANIMACION DE TRANSICION
        FadeTransition ft = new FadeTransition(Duration.millis(250), vista);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        // ANIMACION DE TRANSICION

        //Reemplaza los "hijos" (que serian los datos actuales que tiene la ventana)
        //por datos nuevs pasados en "vista" que contiene los datos cargados anteriormente
        root.getChildren().setAll(vista);




        //Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        /*
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();

        NO NECESITO UNA NUEVA STAGE (VENTANA)
         */

    }

    public static void cambiarVistaZoom(String nombreVentana) throws IOException {


        // Guarda la vista actual en vistaActual
        Parent vistaActual = (Parent) root.getChildren().get(root.getChildren().size() - 1);

        //quita la ventana actual con un zoomOut
        ScaleTransition zoomOut = new ScaleTransition(Duration.millis(200), vistaActual);
        zoomOut.setToX(10.0);
        zoomOut.setToY(10.0);

        FadeTransition fadeOUT = new FadeTransition(Duration.millis(180), vistaActual);
        fadeOUT.setFromValue(1.0);
        fadeOUT.setToValue(0.2);
        fadeOUT.play();

        zoomOut.setOnFinished(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader((MainInterfaz.class.getResource(nombreVentana)));
                Parent vista = fxmlLoader.load();

                // Asegurarse de que el StackPane no esté vacío
                if (root.getChildren().isEmpty()) {
                    root.getChildren().add(vista);
                    return;
                }

                vista.setScaleX(10.0);
                vista.setScaleY(10.0);

                root.getChildren().setAll(vista);

                // Transición de ZoomIn
                ScaleTransition zoomIn = new ScaleTransition(Duration.millis(300), vista);
                zoomIn.setToX(1.0);
                zoomIn.setToY(1.0);
                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), vista);
                fadeIn.setFromValue(0.2);
                fadeIn.setToValue(1.0);
                fadeIn.play();
                zoomIn.play();

            } catch (IOException e) {
                e.printStackTrace();
            }


        });
        zoomOut.play();



    }

    public static void cambiarVistaDerecha(String nombreVentana) throws IOException {
        // crea uno loader con el archivo fxml indicado en "nombreVentana" y le hace un load para cargarlo
        FXMLLoader fxmlLoader = new FXMLLoader(MainInterfaz.class.getResource(nombreVentana));
        Parent vista = fxmlLoader.load();

        // verifica que la ventana no este vacia
        if (root.getChildren().isEmpty()) {
            root.getChildren().add(vista);
            return;
        }

        // Guarda la vista actual en vistaActual
        Parent vistaActual = (Parent) root.getChildren().get(root.getChildren().size() - 1);

        // Coloca la nueva vista detrás de la actual y desplazada hacia la izquierda
        vista.setTranslateX(root.getWidth());
        root.getChildren().add(vista);

        // Transición para mover la vista actual hacia la derecha (fuera de pantalla)
        javafx.animation.TranslateTransition transOut = new javafx.animation.TranslateTransition(Duration.millis(200), vistaActual);
        transOut.setToX(-root.getWidth());

        // Transición para mover la nueva vista al centro
        javafx.animation.TranslateTransition transIn = new javafx.animation.TranslateTransition(Duration.millis(200), vista);
        transIn.setToX(0);

        // Cuando termina la transición, elimina la vista anterior
        transOut.setOnFinished(e -> root.getChildren().remove(vistaActual));

        // Ejecutar ambas transiciones
        transIn.play();
        transOut.play();
    }
    public static void cerrarAplicacion() {
        //Crea una ventana de confirmacion al precionar el boton de cerrar aplicacion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Seguro que desea salir?");
        alert.setContentText("Se cerrará toda la aplicación.");

        //ESTO AÑADE EL ICONO icon.png A LA VENTANA DE CONFIRMACION
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(GestorVentanas.class.getResourceAsStream("/com/example/Images/icon.png"))));
        //ESTO AÑADE EL ICONO icon.png A LA VENTANA DE CONFIRMACION

        //Retiene lo que el usuario seleccione y verifica si quiso salir o no
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
