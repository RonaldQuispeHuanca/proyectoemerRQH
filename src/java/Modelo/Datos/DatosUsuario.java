/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.Entidad.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatosUsuario {
    private final Connection miConexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String mensaje;
    
    public DatosUsuario(){
        miConexion = Conexion.getConexion();
    }

    public String getMensaje() {
        return mensaje;
    }
    
    /**
     * Método que valida el inicio de sesión mediante
     * login y password
     * @param unUsuario
     * @return objeto de tipo Usuario o null
     */
    public Usuario iniciarSesion(Usuario unUsuario){
        Usuario user = null;
        String consulta= "select * from usuarios"
                + " where (usuLogin=?) and (usuPassword=?)";
        try{
            ps = miConexion.prepareStatement(consulta);
            ps.setString(1, unUsuario.getLogin());
            ps.setString(2, unUsuario.getPassword());
            rs=ps.executeQuery();
            if(rs.next()){
                user = new Usuario();
                user.setIdUsuario(Integer.parseInt(rs.getString("idUsuario")));
                user.setLogin(rs.getString("usuLogin"));
                user.setPassword(rs.getString("usuPassword"));
                user.setEstado(rs.getString("usuEstado"));
                user.setRol(rs.getString("usuRol"));
            }  
            rs.close();
        }catch(SQLException ex){
            this.mensaje=ex.getMessage();
        }
        return user;
    }
    
}
