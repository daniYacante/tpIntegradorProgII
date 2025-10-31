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
                try {
                    opt = (int) leerPorTeclado("¿Que desea hacer?\n1)- Logearse\n2)- Crear Usuario\n3)- Ver Productos\n0)- Salir", Integer.class);
                }catch (Exception e){
                    System.out.println("Lo ingresado no es un numero");
                    sc.nextLine();
                    continue;
                }
                switch (opt) {
                    case 1:
                        String userName= leerPorTeclado("Ingrese nombre de usuario",String.class).toString();
                        String password =leerPorTeclado("Ingrese contraseña",String.class).toString();
                        try {
                            user=this.loginUsuario(userName, password);
                            System.out.println("Logueado como: "+user.getTipo());
                            seguir=false;
                        } catch (Exception e) {
                            System.out.println("Error al logearse"+e.getMessage());
                        }
                        break;
                    case 2:
                        String usuario= leerPorTeclado("El nombre de usuario debe tener un minimo de 4 caractes y contener solo letras, minusculas y mayusculas, y numeros del 0 al 9.\nIngrese nombre de usuario",String.class).toString();
                        String contraseña = leerPorTeclado("La contraseña debe tener un minimo de 6 caractes y contener solo letras, minusculas y mayusculas, y numeros del 0 al 9.\nIngrese contraseña",String.class).toString();
                        //char[] pass = console.readPassword("Contraseña: ");
                        try {
                            this.crearUsuario(usuario, contraseña);
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
                        ((Cliente) user).updateDatos();
                        try {
                            String sql = "UPDATE Clientes SET Nombre=?,Apellido=?,Email=?,Direccion=?,MetodoPago=? WHERE User_Id="+((Cliente) user).getId();
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1,((Cliente) user).getNombre());
                            pstmt.setString(2,((Cliente) user).getApellido());
                            pstmt.setString(3,((Cliente) user).getEmail());
                            pstmt.setString(4,((Cliente)user).getDireccion());
                            pstmt.setString(5,((Cliente) user).getMetodoPago());
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
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            int pantalla =rs.getInt("Pantalla");
            String resolucion=rs.getString("Resolucion");
            String tipoPantalla=rs.getString("TipoPantalla");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tResolucion: %s\tTipo Pantalla: %s\tPrecio: %.2f",id,marca,pantalla,resolucion,tipoPantalla,precio));
        }
    }
    public void leerCelulares() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Celulares");
        while (rs.next()){
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            int pantalla =rs.getInt("Pantalla");
            String ram=rs.getString("RAM");
            String rom=rs.getString("ROM");
            String camara=rs.getString("Camara");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tRAM: %s\tROM: %s\tCamara: %s\tPrecio: %.2f",id,marca,pantalla,ram,rom,camara,precio));
        }
    }
    public void leerTablets() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Tablets");
        while (rs.next()){
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            int pantalla =rs.getInt("Pantalla");
            String ram=rs.getString("RAM");
            String rom=rs.getString("ROM");
            String camara=rs.getString("Camara");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tRAM: %s\tROM: %s\tCamara: %s\tPrecio: %.2f",id,marca,pantalla,ram,rom,camara,precio));
        }
    }
    public void leerNotebooks() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Notebooks");
        while(rs.next()){
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            int pantalla =rs.getInt("Pantalla");
            String procesador=rs.getString("Procesador");
            String disco=rs.getString("Disco");
            String ram=rs.getString("RAM");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tProcesador: %s\tDisco: %s\tRAM: %s\tPrecio: %.2f",id,marca,pantalla,procesador,disco,ram,precio));
        }
    }
    public void leerPCs() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM PCs");
        while(rs.next()){
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            String procesador=rs.getString("Procesador");
            String disco=rs.getString("Disco");
            String ram=rs.getString("RAM");
            String tarjetaVideo=rs.getString("TarjetaDeVideo");
            String fuente=rs.getString("Fuente");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %s\tProcesador: %s\tDisco: %s\tRAM: %s\tTarjeta de video: %s\tFuente: %s\tPrecio: %.2f",id,marca,procesador,disco,ram,tarjetaVideo,fuente,precio));
        }
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
                PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                pstmt.execute();
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                int newID=rs.getInt(1);
                String nombre= leerPorTeclado("Indique su nombre",String.class).toString();
                String apellido= leerPorTeclado("Indique su apellido",String.class).toString();
                String email= leerPorTeclado("Indique su email",String.class).toString();
                String direccion= leerPorTeclado("Indique su direccion",String.class).toString();
                int dni= (int) leerPorTeclado("Indique su dni",Integer.class);
                String metodoPago= leerPorTeclado("Indique su Metodo de pago",String.class).toString();
                String consulta = "INSERT INTO Clientes VALUES (NULL,?,?,?,?,?,?,?)";
                PreparedStatement newPrepStm = conn.prepareStatement(consulta);
                newPrepStm.setInt(1, newID);
                newPrepStm.setString(2, nombre);
                newPrepStm.setString(3, apellido);
                newPrepStm.setString(4, email);
                newPrepStm.setString(5, direccion);
                newPrepStm.setString(6, metodoPago);
                newPrepStm.setInt(7, dni);
                newPrepStm.execute();
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
                String sql = "SELECT * FROM Usuarios WHERE userName = ? AND password = ?";
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
                    String consulta = "SELECT * FROM Clientes WHERE User_Id=?";
                    PreparedStatement newPrepStm = conn.prepareStatement(consulta);
                    newPrepStm.setInt(1, rs.getInt("Id"));
                    ResultSet rs2 = newPrepStm.executeQuery();
                    rs2.next();
                    tipoUsuario = new Cliente(rs2.getInt("Id"),rs2.getInt("DNI"),rs.getString("UserName"),rs2.getString("Nombre"),rs2.getString("Apellido"),rs2.getString("Direccion"),rs2.getString("Email"),rs2.getString("MetodoPago"));
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
