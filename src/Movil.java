public class Movil extends Producto{
    private double pantalla;
    private String camara;
    private int rom;
    private int ram;
        public Movil(int id,String Categoria, String marca, int stock, double precio, double pantalla, String camara, int ram, int rom) {
        super(id, Categoria, marca, stock, precio);
        this.pantalla = pantalla;
        this.camara = camara;
        this.rom = rom;
        this.ram=ram;
    }
    public String getInfo(){
            return String.format("%s\tMarca: %-8s\tPrecio: %-8.2f",this.categoria,this.marca,this.precio);
    }
}
