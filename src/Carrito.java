import java.util.ArrayList;
import java.util.Scanner;

public class Carrito {
    private ArrayList<Producto> productos;
    private double total;
    public Carrito(){
        this.productos=new ArrayList<Producto>();
        this.total=0;
    }
    public void agregarProducto(Producto newProd){
        //Se agrega el producto pasado por el cliente
        this.productos.add(newProd);
        this.total+=newProd.getPrecio();
    }
    public void quitarProducto(int id){
        //Se elimina del carrito el producto pasado por el cliente
        for( Producto prod:this.productos){
            if(prod.getId()==id){
                this.productos.remove(prod);
            }
        }
    }
    public void ver(){
        for(Producto prod:this.productos){
            System.out.println(prod.getInfo());
        }
    }

    public String getTotal(){
        return String.format("%f",this.total);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}
