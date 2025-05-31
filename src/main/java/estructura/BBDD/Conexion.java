package estructura.BBDD;

import estructura.Clases.Calzado;
import estructura.Clases.Factura;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;



import static java.lang.Integer.parseInt;

public class Conexion {

    Connection conectar = null;
    String url = "jdbc:sqlite:bbddPM.db";
    PreparedStatement ps = null;
    ResultSet resultado = null;

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            conectar = DriverManager.getConnection(url);
        } catch (Exception x) {
            System.out.println("error al conectar " + x.getMessage().toString());
        }
        return conectar;
    }

    public void desconectar() {
        try {
            conectar.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //CRUD Tecnico A C B E M U E D
    public void insertarCalzado(Calzado calzado) {
        try {
            conectar();
            ps = conectar.prepareStatement("INSERT INTO Calzados (codigo,descripcion,marca,precioCosto,Contacto) VALUES (?,?,?,?,?)");

            ps.setString(1, calzado.getCodigo());
            ps.setString(2, calzado.getDescripcion());
            ps.setString(3, calzado.getMarca());
            ps.setFloat(4, ((Float) (calzado.getPrecioCosto())));
            ps.setFloat(5, ((Float) (calzado.getPrecioVenta())));
            ps.setString(6, calzado.getCosto());

            //Cambiar fecha de tipo Date a tipo String
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String fechaNacimientoStr = dateFormat.format(tecnico.getFechaNacimiento());
            ps.setString(4, fechaNacimientoStr);

            ps.setString(5, tecnico.getContacto());
            ps.executeUpdate();
            desconectar();
        } catch (Exception x) {
            System.out.println("Error " + x.getMessage());
        }
    }

    public int obtenerFacturaID(Integer IdunicoFactura) {
        int id = -1;
        Factura facturabuscad = dniTecnico;
        try {
            conectar();
            ps = conectar.prepareStatement("SELECT TecnicoID FROM Tecnicos WHERE idUnico = ?");
            ps.setString(1, temp);
            resultado = ps.executeQuery();
            id = parseInt(resultado.getString("TecnicoID"));
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
        }
        return id;
    }

    public ResultSet obtenerTecnicos() {
        try {
            conectar();
            ps = conectar.prepareStatement("SELECT * FROM Tecnicos");
            resultado = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // No desconectar aquí, necesitamos el ResultSet activo
        }
        return resultado;
    }

    //CRUD Maquina   
    public void insertarMaquina(Maquina maquina) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = conectar();
            System.out.println("Conexión establecida");

            ps = conn.prepareStatement("INSERT INTO Maquinas (PlantaID, Numero, Marca, Modelo, Situacion) VALUES (?, ?, ?, ?, ?)");
            int plantaID = getPlantaID(maquina.getColorPlanta());
            if (plantaID == 0) {
                System.out.println("Error: PlantaID no encontrado");
                return; // Salir del método si no se encuentra el PlantaID
            }

            System.out.println("PlantaID: " + plantaID);
            System.out.println("Número: " + maquina.getNro());
            System.out.println("Marca: " + maquina.getMarca());
            System.out.println("Modelo: " + maquina.getModelo());
            System.out.println("Situación: " + maquina.getSituacion());

            // Establecer valores en el PreparedStatement
            ps.setInt(1, plantaID);
            ps.setString(2, String.valueOf(maquina.getNro()));
            ps.setString(3, maquina.getMarca());
            ps.setString(4, maquina.getModelo());
            ps.setString(5, String.valueOf(maquina.getSituacion()));

            // Ejecutar la declaración de actualización
            int rowsAffected = ps.executeUpdate();
            System.out.println("Filas insertadas: " + rowsAffected);
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception x) {
            System.out.println("Error: " + x.getMessage());
            x.printStackTrace();
        } finally {
            // Cerrar recursos en el bloque finally
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    desconectar();
                }
            } catch (SQLException ex) {
                System.out.println("Error cerrando recursos: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public int obtenerMaquinaID(String nroMaquina) {
        int id = -1;
        String temp = nroMaquina;
        try {
            conectar();
            ps = conectar.prepareStatement("SELECT MaquinaID FROM Maquinas WHERE Numero = ?");
            ps.setString(1, temp);
            resultado = ps.executeQuery();
            id = parseInt(resultado.getString("MaquinaID"));
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
        }
        return id;
    }

    public boolean obtenerSituacionMaquina(int idMaquina) {
        boolean estado = false;
        PreparedStatement pss;
        ResultSet resultadoAux;
        try {
            conectar();
            pss = conectar.prepareStatement("SELECT Situacion FROM Maquinas WHERE MaquinaID = ?");
            pss.setString(1, String.valueOf(idMaquina));
            System.out.println("numero " + idMaquina);
            resultadoAux = pss.executeQuery();
            System.out.println((resultadoAux.getString("Situacion")).toString());
            if ("Disponible".equals(resultadoAux.getString("Situacion"))) {
                estado = true;
            }
            System.out.println("estado " + estado);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
        }
        return estado;
    }

    public ResultSet obtenerMaquinas() {
        try {
            conectar();
            ps = conectar.prepareStatement("SELECT * FROM Maquinas");
            resultado = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // No desconectar aquí, necesitamos el ResultSet activo
        }
        return resultado;
    }

    //CRUD Planta
    public void insertarPlanta(Planta planta) {
        try {
            conectar();
            ps = conectar.prepareStatement("INSERT INTO Plantas (Color,Mts2) VALUES (?,?)");

            ps.setString(1, (planta.getColor()));
            ps.setString(2, String.valueOf(planta.getMts2()));

            ps.executeUpdate();

            desconectar();
        } catch (Exception x) {
            System.out.println("Error " + x.getMessage());
        }
    }

    private int getPlantaID(String color) {
        int id = 0;
        try {
            conectar();
            ps = conectar.prepareStatement("SELECT PlantaID FROM Plantas WHERE Color = ?");
            ps.setString(1, color);
            resultado = ps.executeQuery();
            // Asegurarse de que el ResultSet se mueva a la primera fila
            if (resultado.next()) {
                id = resultado.getInt("PlantaID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
        }
        return id;
    }

    public ResultSet obtenerPlantas() {
        try {
            conectar();
            ps = conectar.prepareStatement("SELECT * FROM Plantas");
            resultado = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // No desconectar aquí, necesitamos el ResultSet activo
        }
        return resultado;
    }

    //CRUD Proceso
    public void insertarProceso(Proceso proceso) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conectar();
            int plantaID = getPlantaID(proceso.getPlanta().getColor());

            String sql = "INSERT INTO Procesos (PlantaID, Nombre, Complejidad) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, plantaID);
            ps.setString(2, proceso.getNombre());
            ps.setString(3, String.valueOf(proceso.getComplejidad()));

            ps.executeUpdate();
            System.out.println("Proceso insertado correctamente");

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    desconectar();
                }
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //CRUD TURNO
    public void insertarTurno(Turno turno, int idMaquina, int idTecnico) {
        try {
            conectar();
            ps = conectar.prepareStatement("INSERT INTO Turnos (TecnicoID,MaquinaID,FechaDesde,FechaHasta,Turno) VALUES (?,?,?,?,?)");

            //ID DE MAQUINA Y TECNICO
            ps.setInt(1, idMaquina);
            ps.setInt(2, idTecnico);

            //Cambiar fecha de tipo Date a tipo String
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String fechaDesdeStr = dateFormat.format(turno.getFechaDesde());
            ps.setString(3, fechaDesdeStr);

            //Cambiar fecha de tipo Date a tipo String
            String fechaHastaStr = dateFormat.format(turno.getFechaHasta());
            ps.setString(4, fechaHastaStr);

            //Turno (texto)ps.setString(5, tecnico.getContacto());
            ps.setString(5, turno.getTurno().name());

            ps.executeUpdate();
            desconectar();
        } catch (Exception x) {
            System.out.println("Error " + x.getMessage());
        }
    }
    
    public ResultSet obtenerTurnos() {
        try {
            conectar();
            ps = conectar.prepareStatement("SELECT * From Turnos");
            resultado = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // No desconectar aquí, necesitamos el ResultSet activo
        }
        return resultado;
    }

    public ResultSet obtenerTurnosJOIN() {
        try {
            conectar();
            ps = conectar.prepareStatement("SELECT m.Numero AS NumeroMaquina, m.Marca AS MarcaMaquina, tec.DNI AS DniTecnico, tec.Apellido AS ApellidoTecnico, t.Turno, t.FechaDesde, t.FechaHasta FROM Turnos t JOIN Maquinas m ON t.MaquinaID = m.MaquinaID JOIN Tecnicos tec ON t.TecnicoID = tec.TecnicoID");
            resultado = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // No desconectar aquí, necesitamos el ResultSet activo
        }
        return resultado;
    }    

}
