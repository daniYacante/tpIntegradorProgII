import java.util.ArrayList;
import java.util.Arrays;

public class Cliente extends Usuario implements pedirPorTeclado{
    final private int id;
    private int dni;
    private String userName;
    private  String nombre;
    private  String apellido;
    private String direccion;
    private String metodoPago;
    private Carrito carrito;

    public Cliente(int id, int dni, String userName,String nombre,String apellido, String direccion, String metodoPago) {
        this.userName = userName;
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.dni = dni;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
    }

    public void verProducto(){
        //El cliente pide ver la descripcion de un producto mediante el id
    }
    public void agregarAlCarrito(){
        //El cliente agrega el id de un producto a su carrito
    }
    public void pagar(){
        //El cliente paga el total de su carrito generando una orden de compra
    }
    public int  getId() {
        return this.id;
    }
    public int getDni() {
        return this.dni;
    }

    public void verDatos(){
        System.out.println("Nombre: " +this.nombre+
                "Apellido: " +this.apellido+
                "DNI: " +this.email+
                "Email: " +this.email+
                "Direccion: " +this.direccion+
                "Medio de pago:"+this.metodoPago);
    }
    public ArrayList<String> updateDatos(){
        //Orden de las columnas (Id,Nombre,Apellido,UserName,Password,Email,Direccion,MetodoPago)
        ArrayList<String> values = new ArrayList<String>(Arrays.asList(null,null,null,null,null,null,null));
        boolean seguir=true;
        while(seguir){
            int result= (int) leerPorTeclado("¿Que datos desea modificar?" +
                    "\n1) Nombre" +
                    "\n2) Apellido" +
                    "\n3) Email" +
                    "\n4) Direccion" +
                    "\n5) Medio de pago" +
                    "\n6) Salir",Integer.class);
            switch(result){
                case 1:
                    values.set(1,(String)leerPorTeclado("Ingrese nuevo Nombre:\n",String.class));
                    break;
                case 2:
                    values.set(2,(String)leerPorTeclado("Ingrese nuevo Apellido:\n",String.class));
                    break;
                case 3:
                    values.set(3,(String)leerPorTeclado("Ingrese nuevo Email:\n",String.class));
                    break;
                case 4:
                    values.set(4,(String)leerPorTeclado("Ingrese nuevo Direccion:\n",String.class));
                    break;
                case 5:
                    values.set(5,(String)leerPorTeclado("Ingrese nuevo Medio de pago:\n",String.class));
                    break;
                default:
                    seguir=false;
                    break;
            }
        }
    return values;
    }
    public void verCarrito(){
        this.carrito.ver();
    }
}
