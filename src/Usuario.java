public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String contraseña;
    protected String tipo;
    protected void iniciarSecion(){}
    protected void cerrarSecion(){}
    private void actualizarCliente(){}
    public String getTipo(){
        return tipo;
    }

}
