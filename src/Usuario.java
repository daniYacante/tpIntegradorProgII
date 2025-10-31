public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String contraseña;
    protected int dni;
    protected String direccion;
    protected String tipo;

    protected void cerrarSecion(){}
    public String getTipo(){
        return tipo;
    }

}
