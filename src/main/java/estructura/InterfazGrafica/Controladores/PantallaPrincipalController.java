package estructura.InterfazGrafica.Controladores;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class PantallaPrincipalController {

    @FXML
    private Button ingresarButton;
    @FXML

    private ImageView crearFacturaImageView;


    //Boton y accion cerrar ventana
    @FXML
    private Button cerrarVentanaButton;

    public void cerrarVentanaButtonOnAction(ActionEvent event){
        TranslateTransition translation = new TranslateTransition(Duration.millis(100), cerrarVentanaButton);
        translation.setByX(5);//Mueve le boton 20px a la derecha
        translation.setByY(5);
        translation.setAutoReverse(true);
        translation.setCycleCount(2);
        translation.play();


        GestorVentanas.cerrarAplicacion();


    }
    //Boton y accion cerrar ventana




    public void crearFacturaImageViewOnAction(ActionEvent event){
        System.out.println("imgCrearFacturaImageView");
    }


    public void ingresarButtonOnAction(ActionEvent event) throws IOException {
        System.out.println("Hola");
        GestorVentanas.cambiarVistaZoom("menuPrincipal.fxml");
        //GestorVentanas.cambiarVistaDerecha("menuPrincipal.fxml");

    }
}
