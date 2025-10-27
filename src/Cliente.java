public class Cliente extends Usuario implements showActions{
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
    public void verAcciones(){
        System.out.println("¿Que desea hacer?" +
                "\n1) Ver datos" +
                "\n2) Modificar datos" +
                "\n3) Ver Carrito" +
                "\n4) Ver Productos de la tienda" +
                "\n5) Salir");
    }
    public void selectAction(int option){
        switch(option){
            case 1:
                System.out.println("Nombre: " +this.nombre+
                        "Apellido: " +this.apellido+
                        "DNI: " +this.email+
                        "Email: " +this.email+
                        "Direccion: " +this.direccion+
                        "Medio de pago:"+this.metodoPago);
                break;
            case 2:
                break;
            case 3:
                this.carrito.ver();
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }
}
