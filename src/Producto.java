public abstract class Producto implements MostrarEspecificaciones{
    protected int id;
    protected String categoria;
    protected String marca;
    protected int stock;
    protected double precio;

    public int getId() {
        return id;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getMarca() {
        return marca;
    }
    public int getStock() {
        return stock;
    }
    public double getPrecio() {
        return precio;
    }

    public Producto(int id, String categoria, String marca, int stock, double precio) {
        this.id = id;
        this.categoria = categoria;
        this.marca = marca;
        this.stock = stock;
        this.precio = precio;

    }
}
