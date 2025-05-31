package estructura.InterfazGrafica.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private Button backMPButton;

    //Boton y accion cerrar ventana
    @FXML
    private Button cerrarVentanaButton;

    public void cerrarVentanaButtonOnAction(ActionEvent event){
        GestorVentanas.cerrarAplicacion();
    }
    //Boton y accion cerrar ventana


    public void backMPButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        GestorVentanas.cambiarVistaZoom("pantallaPrincipal.fxml");
        //GestorVentanas.cambiarVistaIzquierda("pantallaPrincipal.fxml");
    }
}
