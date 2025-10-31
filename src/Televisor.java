public class Televisor extends Producto{
    private double pantalla;
    private String resolucion;
    private String tipoPantalla;
    public Televisor(int id, String marca, int stock, double precio,double pantalla, String resolucion, String tipoPantalla) {
        super(id, "Televisor", marca, stock, precio);
        this.pantalla = pantalla;
        this.resolucion = resolucion;
        this.tipoPantalla = tipoPantalla;
    }
    public String getInfo(){
        return String.format("%s\tMarca: %-8s\tPantalla: %-8.1f\tResolucion: %-8s\tPrecio: %-8.2f",this.categoria,this.marca,this.pantalla,this.resolucion,this.precio);
    }
}
