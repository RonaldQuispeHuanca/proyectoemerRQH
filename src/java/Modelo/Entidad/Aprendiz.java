package Modelo.Entidad;
/**
 *
 * @author CesarCuellar
 */
public class Aprendiz {
    private int idAprendiz;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String correo;
    private String genero;
    private String fechaNacimiento;
    private Curso unCurso;

  /**
   * Constructor con parametros
   * @param identificacion
   * @param nombres
   * @param apellidos
   * @param correo
   * @param genero
   * @param fechaNacimiento
   * @param unCurso 
   */
    public Aprendiz(String identificacion, String nombres, String apellidos, 
            String correo, String genero, String fechaNacimiento, Curso unCurso) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.unCurso = unCurso;
    }
    
    /**
     * Constructor vacío
     */
    public Aprendiz(){
        this.unCurso = new Curso();
    }

    public int getIdAprendiz() {
        return idAprendiz;
    }

    public void setIdAprendiz(int idAprendiz) {
        this.idAprendiz = idAprendiz;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }  

    public Curso getUnCurso() {
        return unCurso;
    }

    public void setUnCurso(Curso unCurso) {
        this.unCurso = unCurso;
    }
    
    
 
}
