import java.util.ArrayList;
import java.util.Scanner;

public class Carrito {
    private ArrayList<Producto> productos;
    private double total;

    public void agregarProducto(Producto newProd){
        //Se agrega el producto pasado por el cliente
        this.productos.add(newProd);
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
            prod.getInfo();
        }
    }

    public void verTotal(){
        //Imprime por pantalla todos los elementos del carrito con su precio, y el precio final
        int total=0;
        for(Producto prod:this.productos){
            total+=prod.getPrecio();
        }

    }
}
