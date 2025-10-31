public class Notebook extends Producto{
    private double pantalla;
    private String procesador;
    private int disco;
    private int ram;
        public Notebook(int id, String marca, int stock, double precio, double pantalla, String procesador, int disco) {
        super(id, "Notebook", marca, stock, precio);
        this.pantalla = pantalla;
        this.procesador = procesador;
        this.disco = disco;
    }
    public String getInfo(){
        return String.format("%s\tMarca: %-8s\tProcesador: %-8s\tPrecio: %-8.2f",this.categoria,this.marca,this.procesador,this.precio);
    }
}
