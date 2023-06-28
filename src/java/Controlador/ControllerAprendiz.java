
package Controlador;

import Modelo.Datos.DatosAprendiz;
import Modelo.Entidad.Aprendiz;
import Modelo.Entidad.Curso;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControllerAprendiz", urlPatterns = {"/ControllerAprendiz"})
public class ControllerAprendiz extends HttpServlet {

    //Crear un objeto de tipo DatosAprendiz
    private DatosAprendiz dAprendiz = new DatosAprendiz();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerAprendiz</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerAprendiz at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     //   processRequest(request, response);
        consultar(request, response); 
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String tarea = request.getParameter("accion");        
        switch (tarea){
            case    "Agregar":      agregar2(request, response); 
                break;
            case    "Consultar":    consultar(request, response);
                break;   
            case    "Actualizar":   actualizar(request, response);
                break; 
            case    "Eliminar":     eliminar(request, response);
                break; 
            case    "Listar":       listar(request, response);
                break;
            case    "Existe":       consultar(request, response);
                break;
        }        
    }
    
    /**
     * Método que recibe de la vista los datos del aprendiz en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void agregar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{       
        String identifica = request.getParameter("txtIdentificacion");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String correo = request.getParameter("txtCorreo");    
        String genero = request.getParameter("cbGenero");  
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        int idCurso = Integer.parseInt(request.getParameter("cbCurso"));
        Curso unCurso = new Curso();
        unCurso.setIdCurso(idCurso);
        //se crea el objeto aprendiz
        Aprendiz unAprendiz = new Aprendiz(identifica, nombres, apellidos, 
                correo, genero, fechaNacimiento, unCurso);       
       
        //se agrega el aprendiz utilizando el objeto dAprendiz
        boolean agregado = dAprendiz.agregarAprendiz(unAprendiz);
        //se crea una variable mensaje para enviar a la vista
        String mensaje = dAprendiz.getMensaje(); 
        // se redirecciona a la vista con una variable mensaje pasada por la URL
        response.sendRedirect(request.getContextPath()+"/Vista/frmAprendiz.jsp?mensaje=" + mensaje);               
    }
    
    /**
     * Método que recibe de la vista los datos del aprendiz en el objeto request
     * y devuelve una respuesta mediante response.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
     private void agregar2(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{       
        String identifica = request.getParameter("txtIdentificacion");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String correo = request.getParameter("txtCorreo");    
        String genero = request.getParameter("cbGenero");  
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        int idCurso = Integer.parseInt(request.getParameter("cbCurso"));
        Curso unCurso = new Curso();
        unCurso.setIdCurso(idCurso);
        Aprendiz unAprendiz = new Aprendiz(identifica, nombres, apellidos, 
                correo, genero, fechaNacimiento, unCurso);
        boolean agregado = dAprendiz.agregarAprendiz(unAprendiz);     
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(agregado);   
        out.print(json);       
    }
    
     /**
      * Método que recibe una petición de la vista para consultar un aprendiz
      * dada su identificación
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException 
      */
    private void consultar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String identifica = request.getParameter("identificacion");  
        Aprendiz unAprendiz = dAprendiz.obtenerAprendizPorIdentificacion(identifica); 
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(unAprendiz);   
        out.print(json);       
    }
    
    /**
     * Actualizar un aprendiz.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int idAprendiz  =Integer.parseInt(request.getParameter("idAprendiz"));
        String identifica = request.getParameter("txtIdentificacion");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String correo = request.getParameter("txtCorreo");    
        String genero = request.getParameter("cbGenero");  
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        int idCurso = Integer.parseInt(request.getParameter("cbCurso"));
        Curso unCurso = new Curso();
        unCurso.setIdCurso(idCurso);
        Aprendiz unAprendiz = new Aprendiz(identifica, nombres, apellidos, 
                correo, genero, fechaNacimiento, unCurso);       
        unAprendiz.setIdAprendiz(idAprendiz);
        boolean actualizado = dAprendiz.actualizarAprendiz(unAprendiz);        
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(actualizado);   
        out.print(json);   
    }
    
    /**
     * Elimina un aprendiz dado su IdAprendiz
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        int idAprendiz = Integer.parseInt(request.getParameter("idAprendiz"));  
        boolean eliminado = dAprendiz.eliminarAprendiz(idAprendiz);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(eliminado);   
        out.print(json);    
    }
    
    /**
     * Método que recibe la petición de la vista para listar los aprendices
     * que se encuentren en la base de datos. 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void listar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        ArrayList<Aprendiz> lista = dAprendiz.listarAprendices();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        String json = new Gson().toJson(lista);   
        out.print(json);   
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
