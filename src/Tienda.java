import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tienda implements pedirPorTeclado {
    private String nombre;
    private Connection conn;
    private Usuario user=null;
    private boolean checkCredenciales(String userName, String password){
        String patternName="^[a-zA-Z0-9]{4,}$"; //Solo letras minusculas y mayusculas y numeros del 0 al 9, minimo 4 caracteres
        String patternPass="^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";//
        return  userName.matches(patternName) && password.matches(patternPass);
    }
    public void explorarTienda(){
        boolean NHF=true;
        int resp;
        while(NHF){
            resp=(int)leerPorTeclado("¿Que categoria de productos desea ver?" +
                    "\n1) Televisores" +
                    "\n2) Notebooks" +
                    "\n3) PC de Escritorio" +
                    "\n4) Celulares" +
                    "\n5) Tablets" +
                    "\n6) Salir",Integer.class);
            switch(resp){
                case 1:
                    try {
                        this.leerTelevisores();
                    }catch (Exception e){
                        System.out.println("Error al leer televisores");
                    }
                    break;
                case 2:
                    try {
                        this.leerNotebooks();
                    }catch (Exception e){
                        System.out.println("Error al leer Notebooks");
                    }
                    break;
                case 3:
                    try {
                        this.leerPCs();
                    }catch (Exception e){
                        System.out.println("Error al leer las PC");
                    }
                    break;
                case 4:
                    try {
                        this.leerCelulares();
                    }catch (Exception e){
                        System.out.println("Error al leer los Celulares");
                    }
                    break;
                case 5:
                    try {
                        this.leerTablets();
                    }catch (Exception e){
                        System.out.println("Error al leer las Tablets");
                    }
                    break;
                default:
                    NHF=false;
                    break;
            }

        }
    }
    public void iniciar(){
        Scanner sc = new Scanner(System.in);
        int opt=0;
        //Console console = System.console();
        try {
            this.iniciarConn();
            boolean seguir=true;
            System.out.println("Bienvenidos a "+this.getName());
            while (seguir){
                System.out.println("¿Que desea hacer?");
                System.out.println("1)- Logearse\n2)- Crear Usuario\n3)- Ver Productos\n0)- Salir");
                try {
                    opt = sc.nextInt();
                    switch (opt) {
                        case 1:
                            System.out.print("Ingrese nombre de usuario\n");
                            String name = sc.next();
                            System.out.print("Ingrese contraseña\n");
                            String pass = sc.next();
                            try {
                                user=this.loginUsuario(name, pass);
                                System.out.println("Logueado como: "+user.getTipo());
                                seguir=false;
                            } catch (Exception e) {
                                System.out.println("Error al logearse"+e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println("El nombre de usuario debe tener un minimo de 4 caractes y contener solo letras, minusculas y mayusculas, y numeros del 0 al 9.");
                            System.out.println("La contraseña debe tener un minimo de 6 caractes y contener solo letras, minusculas y mayusculas, y numeros del 0 al 9.");
                            System.out.print("Ingrese nombre de usuario\n");
                            name = sc.next();
                            System.out.println("Ingrese contraseña");
                            pass = sc.next();
                            //char[] pass = console.readPassword("Contraseña: ");
                            try {
                                this.crearUsuario(name, pass);
                            } catch (Exception e) {
                                System.out.println("Error al crear usuario");
                            }
                            break;
                        case 3:
                            this.explorarTienda();
                            break;
                        default:
                            System.out.println("Gracias por elegirnos! Vuelva pronto");
                            seguir = false;
                            break;
                    }
                }catch (Exception e){
                    System.out.println("Lo ingresado no es un numero");
                    sc.nextLine();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(user!=null && user instanceof Cliente){
            boolean seguir=true;
            while(seguir){
                opt= (int) leerPorTeclado("¿Que desea hacer?" +
                        "\n1) Ver datos" +
                        "\n2) Modificar datos" +
                        "\n3) Ver Carrito" +
                        "\n4) Ver Productos de la tienda" +
                        "\n5) Salir",Integer.class);
                switch(opt){
                    case 1:
                        ((Cliente) user).verDatos();
                        break;
                    case 2:
                        ArrayList<String> values =((Cliente) user).updateDatos();
                        try {
                            String sql = "INSERT INTO Clientes VALUES (NULL,?,?,?,?,?,?,?) WHERE id="+((Cliente) user).getId();
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            for(int i=0;i<values.size();i++){
                                pstmt.setString(i, values.get(i));
                            }
                            pstmt.execute();

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 3:
                        ((Cliente) user).verCarrito();
                        break;
                    case 4:
                        this.explorarTienda();
                        break;
                    default:
                        seguir = false;
                        break;
                }
            }

        }else{
            try {
                this.closeConn();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
    private void iniciarConn() throws Exception {
        Connection conn=null;
        try{
            Class.forName("org.sqlite.JDBC");
            conn= DriverManager.getConnection("jdbc:sqlite:tienda.sqlite");
            if(conn!=null){
                System.out.println("Conexion a base de datos...OK");
                this.conn=conn;
            }else{
                throw new SQLException("Conexion a base de datos...problemas!");
            }
        }catch(SQLException a){
            throw new SQLException(a.getMessage());
        }catch(ClassNotFoundException c){
            throw new ClassNotFoundException(c.getMessage());
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public void closeConn() throws SQLException {
        this.conn.close();
    }
    public Tienda(String name){
        this.nombre=name;
    }
    public String getName(){
        return this.nombre;
    }
    public void leerTelevisores() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Televisores");

        while (rs.next()){
            int id =rs.getInt("Id");
            String marca=rs.getString("Marca");
            int pantalla =rs.getInt("Pantalla");
            String resolucion=rs.getString("Resolucion");
            String tipoPantalla=rs.getString("TipoPantalla");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %-8s\tPantalla: %-8d\tResolucion: %-8s\tTipo Pantalla: %-8s\tPrecio: %-8.2f",id,marca,pantalla,resolucion,tipoPantalla,precio));
        }
        stm.close();
        rs.close();
    }
    public void leerCelulares() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Celulares");
        while(rs.next()){
            int id =rs.getInt("Id");
            String marca=rs.getString("Marca");
            double pantalla =rs.getDouble("Pantalla");
            int ram=rs.getInt("RAM");
            int rom=rs.getInt("ROM");
            String camara=rs.getString("Camara");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %-8s\tPantalla: %-8.1f\tRAM: %-8s\tROM: %-8s\tCamara: %-8s\tPrecio: %-8.2f",id,marca,pantalla,ram,rom,camara,precio));
        }
        stm.close();
        rs.close();
    }
    public void leerTablets() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Tablets");
        while(rs.next()){
            int id =rs.getInt("Id");
            String marca=rs.getString("Marca");
            double pantalla =rs.getDouble("Pantalla");
            int ram=rs.getInt("RAM");
            int rom=rs.getInt("ROM");
            String camara=rs.getString("Camara");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %-8s\tPantalla: %-8.1f\tRAM: %-8s\tROM: %-8s\tCamara: %-8s\tPrecio: %-8.2f",id,marca,pantalla,ram,rom,camara,precio));
        }
        stm.close();
        rs.close();
    }
    //CAMBIO
    public void leerNotebooks() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Notebooks");
        while(rs.next()){
            int id =rs.getInt("Id");
            String marca=rs.getString("Marca");
            double pantalla =rs.getDouble("Pantalla");
            String procesador=rs.getString("Procesador");
            int disco=rs.getInt("Disco");
            int ram=rs.getInt("RAM");

            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %-8s\tPantalla: %-8.1f\tProcesador: %-8s\tDisco: %-8d\tRAM: %-8d\tPrecio: %-8.2f",id,marca,pantalla,procesador,disco,ram,precio));
        }
        stm.close();
        rs.close();
    }
    public void leerPCs() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM PCs");
        while(rs.next()){
            int id =rs.getInt("Id");
            String marca=rs.getString("Marca");
            String procesador=rs.getString("Procesador");
            int disco=rs.getInt("Disco");
            int ram=rs.getInt("RAM");
            String tarjetaVideo=rs.getString("Tarjeta de video");
            String fuente=rs.getString("Fuente");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %-8s\tProcesador: %-8s\tDisco: %-8d\tRAM: %-8d\tTarjeta de video: %-8s\tFuente: %-8s\tPrecio: %-8.2f",id,marca,procesador,disco,ram,tarjetaVideo,fuente,precio));
        }
        stm.close();
        rs.close();
    }
    public void crearUsuario(String userName, String password, Object tipo){
        if(tipo instanceof Administrador){

        }else{

        }
    }
    public void crearUsuario(String userName, String password){
        if(checkCredenciales(userName, password)) {
            try {
                String sql = "INSERT INTO Usuarios VALUES (NULL,?, ?,'CLIENTE')";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                pstmt.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("El usuario o la contraseña no cumple con los requisitos");
        }
    }
    public Usuario loginUsuario(String userName, String password){
        Usuario tipoUsuario=null;
        if(checkCredenciales(userName, password)) {
            try {
                String sql = "SELECT * FROM usuarios WHERE userName = ? AND password = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                String tipo="";
                if (rs.next()) {
                    tipo = rs.getString("tipo");
                }else{
                    throw new RuntimeException("No existe el usuario");
                }
                if(tipo.equals("CLIENTE")){
                    tipoUsuario = new Cliente(rs.getInt("Id"),rs.getInt("Dni"),rs.getString("UserName"),rs.getString("Nombre"),rs.getString("Apellido"),rs.getString("direccion"),rs.getString("medioPago"));
                }else if(tipo.equals("ADMINISTRADOR")){
                    tipoUsuario = new Administrador();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new IllegalArgumentException("Nombre de usuario o contraseña incorrectos");
        }
        return tipoUsuario;
    }


}
