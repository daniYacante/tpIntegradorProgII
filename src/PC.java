public class PC extends Producto{
    private String procesador;
    private int disco;
    private int ram;
    private String tarjetaVideo;
    private String fuente;
    public PC(int id, String marca, int stock, double precio, String procesador, int disco,int ram,String fuente,String tarjetaVideo) {
        super(id, "PC", marca, stock, precio);
        this.procesador = procesador;
        this.ram=ram;
        this.tarjetaVideo=tarjetaVideo;
        this.fuente=fuente;
        this.disco = disco;
    }
    public String getInfo(){
        return String.format("%s\tMarca: %-8s\tProcesador: %-8s\tPrecio: %-8.2f",this.categoria,this.marca,this.procesador,this.precio);
    }
}
