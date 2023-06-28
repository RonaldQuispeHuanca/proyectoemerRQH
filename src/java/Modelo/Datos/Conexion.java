package Modelo.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CesarCuellar
 */
public class Conexion {
    private static Connection conexion;
    private static final String driver ="com.mysql.jdbc.Driver";
    private static final String usuario="root";
    private static final String password="";
    private static final String url="jdbc:mysql://localhost:3306/adsi1693428";
    private static String mensaje;
    
    /**
     * Obtiene la conexión a la base de datos
     * @return objeto Connection
     */
    public static Connection getConexion() {
        if(conexion!=null){
            return conexion;
        }
        try{
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, password);
            mensaje="Conectado a la base de datos";
            return conexion;
        }catch(ClassNotFoundException | SQLException ex){
            mensaje=ex.getMessage();
            return null;
        }
    }    
    /**
     * Cierra la conexión a la base de datos existente
     */
    public static void cerrar(){
        try{
            conexion.close();
            mensaje="Conexión cerrada";
        }catch(SQLException ex){
            mensaje="Problemas al cerrar la conexión";
        }
    }

    public static String getMensaje() {
        return mensaje;
    }  
}
