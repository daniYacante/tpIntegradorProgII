public class Cliente extends Usuario implements pedirPorTeclado{
    final private int id;
    private int dni;
    private String userName;
    private  String nombre;
    private  String apellido;
    private String direccion;
    private String metodoPago;
    private String email;
    private Carrito carrito;

    public String getUserName(){return this.userName;}
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String getEmail() {
        return email;
    }
    public int  getId() {
        return this.id;
    }
    public int getDni() {
        return this.dni;
    }
    public Cliente(int id, int dni, String userName,String nombre,String apellido, String direccion,String email, String metodoPago) {
        this.userName = userName;
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.dni = dni;
        this.email = email;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
        this.tipo = "Cliente";
        this.carrito=new Carrito();
    }

    public void agregarAlCarrito(Producto item){
        //El cliente agrega el id de un producto a su carrito
        this.carrito.agregarProducto(item);
    }
    public void pagar(){
        //El cliente paga el total de su carrito generando una orden de compra
    }

    public void verDatos(){
        System.out.println("Nombre: " +this.nombre+"\n"+
                "Apellido: " +this.apellido+"\n"+
                "DNI: " +this.dni+"\n"+
                "Email: " +this.email+"\n"+
                "Direccion: " +this.direccion+"\n"+
                "Medio de pago:"+this.metodoPago);
    }
    public void updateDatos(){
        //Orden de las columnas (Id,Nombre,Apellido,UserName,Password,Email,Direccion,MetodoPago)
        //ArrayList<String> values = new ArrayList<String>(Arrays.asList(null,null,null,null,null));
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
                    //values.set(0,(String)leerPorTeclado("Ingrese nuevo Nombre:",String.class));
                    this.nombre=leerPorTeclado("Ingrese nuevo Nombre:",String.class).toString();
                    break;
                case 2:
                    //values.set(1,(String)leerPorTeclado("Ingrese nuevo Apellido:",String.class));
                    this.apellido=leerPorTeclado("Ingrese apellido:",String.class).toString();
                    break;
                case 3:
                    //values.set(2,(String)leerPorTeclado("Ingrese nuevo Email:",String.class));
                    this.email=leerPorTeclado("Ingrese Email:",String.class).toString();
                    break;
                case 4:
                    //values.set(3,(String)leerPorTeclado("Ingrese nueva Direccion:",String.class));
                    this.direccion=leerPorTeclado("Ingrese Direccion:",String.class).toString();
                    break;
                case 5:
                    //values.set(4,(String)leerPorTeclado("Ingrese nuevo Medio de pago:",String.class));
                    this.metodoPago=leerPorTeclado("Ingrese Nuevo Medio de pago",String.class).toString();
                    break;
                default:
                    seguir=false;
                    break;
            }
        }
    //return values;
    }
    public void verCarrito(){
        try {
            System.out.println("Tu carrito:");
            this.carrito.ver();
            System.out.println("Total: "+carrito.getTotal());
        }catch(Exception e){
            System.out.println("El carrito esta vacio, añade algo primero");
        }

    }
}
