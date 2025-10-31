import java.io.Serializable;

public class Tablet extends Producto{
    private double pantalla;
    private String procesador;
    private int disco;
    private int ram;
        public Tablet(int id, String marca, int stock, double precio, double pantalla, String procesador, int disco) {
        super(id, "Tablet", marca, stock, precio);
        this.pantalla = pantalla;
        this.procesador = procesador;
        this.disco = disco;
    }
    @Override
    public String getInfo() {
        return String.format("%s\tMarca: %-8s\tPrecio: %-8.2f",this.categoria,this.marca,this.precio);
    }
}
