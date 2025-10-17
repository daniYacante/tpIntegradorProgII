public class Cliente extends Usuario{
    private int dni;
    private String direccion;
    private String metodoPago;
    private Carrito carrito;
    public void crearCliente(){}
    public void verProducto(){
        //El cliente pide ver la descripcion de un producto mediante el id
    }
    public void agregarAlCarrito(){
        //El cliente agrega el id de un producto a su carrito
    }
    public void pagar(){
        //El cliente paga el total de su carrito generando una orden de compra
    }
}
