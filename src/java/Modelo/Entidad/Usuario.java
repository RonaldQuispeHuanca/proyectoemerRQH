/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Entidad;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Usuario {
    private int idUsuario;
    private String login;
    private String password;
    private String estado;
    private String rol;

    /**
     * Constructor con parametros
     * @param login
     * @param password
     * @param estado
     * @param rol 
     */
    public Usuario(String login, String password, String estado, String rol) {
        this.login = login;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
    }
    
    /**
     * Constructor sin parametros de inicialización
     */
    public Usuario(){
        
    }
    
    /**
     * Encripta el password formato MD5 Hexadecimal
     * @return String con el password encriptado
     */
    public String passwordEncriptado(){
        String encriptado="";
       try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(this.password.getBytes());
            BigInteger number = new BigInteger(1, digest);
            encriptado = number.toString(16); 
            while(encriptado.length()<32){
                encriptado = "0" + encriptado;
            }
            }catch(NoSuchAlgorithmException ex){
                System.out.println(ex.getMessage());
            }
       return encriptado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
      
    
    
}
