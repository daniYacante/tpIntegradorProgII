import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tienda implements pedirPorTeclado {
    private String nombre;
    private Connection conn;
    private Usuario user=null;
    Carrito carritoTemp;
    private boolean checkCredenciales(String userName, String password){
        String patternName="^[a-zA-Z0-9]{4,}$"; //Solo letras minusculas y mayusculas y numeros del 0 al 9, minimo 4 caracteres
        String patternPass="^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";//
        return  userName.matches(patternName) && password.matches(patternPass);
    }
    public void explorarTienda(){
        boolean NHF=true;
        int resp;
        if(this.user==null){
            carritoTemp=new Carrito();
        }
        while(NHF){
            try {
                resp=(int)leerPorTeclado("¿Que categoria de productos desea ver?" +
                        "\n1) Televisores" +
                        "\n2) Notebooks" +
                        "\n3) PC de Escritorio" +
                        "\n4) Celulares" +
                        "\n5) Tablets" +
                        "\n6) Salir",Integer.class);
            } catch (EntradaInvalidaException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }
            switch(resp){
                case 1:
                    try {
                        this.leerTelevisores();
                        int respAdd=(int)leerPorTeclado("¿Desea agregar algun elemento?\n1) Si\n2) No",Integer.class);
                        if (respAdd==1){
                            int idItem =(int)leerPorTeclado("Ingrese el Id del producto a añadir",Integer.class);
                            Statement stm = this.conn.createStatement();
                            ResultSet rs=stm.executeQuery("SELECT * FROM Televisores WHERE Product_Id="+idItem);
                            if(rs.next()){
                                Televisor tele= new Televisor(
                                        rs.getInt("Product_Id"),
                                        rs.getString("Marca"),
                                        rs.getInt("Stock"),
                                        rs.getDouble("Precio"),
                                        rs.getDouble("Pantalla"),
                                        rs.getString("Resolucion"),
                                        rs.getString("TipoPantalla")
                                );
                                if(this.user==null){
                                    carritoTemp.agregarProducto(tele);
                                }else{
                                    ((Cliente)user).agregarAlCarrito(tele);
                                }
                            }else{
                                System.out.println("El producto no existe");
                            }
                            stm.close();
                            rs.close();
                        }
                    }catch (Exception e){
                        System.out.println("Error al leer televisores");
                    }
                    break;
                case 2:
                    try {
                        this.leerNotebooks();
                        int respAdd=(int)leerPorTeclado("¿Desea agregar algun elemento?\n1) Si\n2) No",Integer.class);
                        if (respAdd==1){
                            int idItem =(int)leerPorTeclado("Ingrese el Id del producto a añadir",Integer.class);
                            Statement stm = this.conn.createStatement();
                            ResultSet rs=stm.executeQuery("SELECT * FROM Notebooks WHERE Product_Id="+idItem);
                            if(rs.next()){
                                Notebook note= new Notebook(
                                        rs.getInt("Product_Id"),
                                        rs.getString("Marca"),
                                        rs.getInt("Stock"),
                                        rs.getDouble("Precio"),
                                        rs.getDouble("Pantalla"),
                                        rs.getString("Procesador"),
                                        rs.getInt("Disco")
                                );
                                if(this.user==null){
                                    carritoTemp.agregarProducto(note);
                                }else{
                                    ((Cliente)user).agregarAlCarrito(note);
                                }
                            }else{
                                System.out.println("El producto no existe");
                            }
                            stm.close();
                            rs.close();
                        }
                    }catch (Exception e){
                        System.out.println("Error al leer Notebooks");
                    }
                    break;
                case 3:
                    try {
                        this.leerPCs();
                        int respAdd=(int)leerPorTeclado("¿Desea agregar algun elemento?\n1) Si\n2) No",Integer.class);
                        if (respAdd==1){
                            int idItem =(int)leerPorTeclado("Ingrese el Id del producto a añadir",Integer.class);
                            Statement stm = this.conn.createStatement();
                            ResultSet rs=stm.executeQuery("SELECT * FROM PCs WHERE Product_Id="+idItem);
                            if(rs.next()){
                                PC compu= new PC(
                                        rs.getInt("Product_Id"),
                                        rs.getString("Marca"),
                                        rs.getInt("Stock"),
                                        rs.getDouble("Precio"),
                                        rs.getString("Procesador"),
                                        rs.getInt("Disco"),
                                        rs.getInt("RAM"),
                                        rs.getString("Fuente"),
                                        rs.getString("TarjetaDeVideo")
                                );
                                if(this.user==null){
                                    carritoTemp.agregarProducto(compu);
                                }else{
                                    ((Cliente)user).agregarAlCarrito(compu);
                                }
                            }else{
                                System.out.println("El producto no existe");
                            }
                            stm.close();
                            rs.close();
                        }
                    }catch (Exception e){
                        System.out.println("Error al leer las PC");
                    }
                    break;
                case 4:
                    try {
                        this.leerCelulares();
                        int respAdd=(int)leerPorTeclado("¿Desea agregar algun elemento?\n1) Si\n2) No",Integer.class);
                        if (respAdd==1){
                            int idItem =(int)leerPorTeclado("Ingrese el Id del producto a añadir",Integer.class);
                            Statement stm = this.conn.createStatement();
                            ResultSet rs=stm.executeQuery("SELECT * FROM Celulares WHERE Product_Id="+idItem);
                            if(rs.next()){
                                Movil celu= new Movil(
                                        rs.getInt("Product_Id"),
                                        "Celular",
                                        rs.getString("Marca"),
                                        rs.getInt("Stock"),
                                        rs.getDouble("Precio"),
                                        rs.getDouble("Pantalla"),
                                        rs.getString("Camara"),
                                        rs.getInt("RAM"),
                                        rs.getInt("ROM")
                                );
                                if(this.user==null){
                                    carritoTemp.agregarProducto(celu);
                                }else{
                                    ((Cliente)user).agregarAlCarrito(celu);
                                }
                            }else{
                                System.out.println("El producto no existe");
                            }
                            stm.close();
                            rs.close();
                        }
                    }catch (Exception e){
                        System.out.println("Error al leer los Celulares");
                    }
                    break;
                case 5:
                    try {
                        this.leerTablets();
                        int respAdd=(int)leerPorTeclado("¿Desea agregar algun elemento?\n1) Si\n2) No",Integer.class);
                        if (respAdd==1){
                            int idItem =(int)leerPorTeclado("Ingrese el Id del producto a añadir",Integer.class);
                            Statement stm = this.conn.createStatement();
                            ResultSet rs=stm.executeQuery("SELECT * FROM Tablets WHERE Product_Id="+idItem);
                            if(rs.next()){
                                Movil tablet= new Movil(
                                        rs.getInt("Product_Id"),
                                        "Tablet",
                                        rs.getString("Marca"),
                                        rs.getInt("Stock"),
                                        rs.getDouble("Precio"),
                                        rs.getDouble("Pantalla"),
                                        rs.getString("Camara"),
                                        rs.getInt("RAM"),
                                        rs.getInt("ROM")
                                );
                                if(this.user==null){
                                    carritoTemp.agregarProducto(tablet);
                                }else{
                                    ((Cliente)user).agregarAlCarrito(tablet);
                                }
                            }else{
                                System.out.println("El producto no existe");
                            }
                            stm.close();
                            rs.close();
                        }
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
        if(user!=null && user instanceof Cliente) {
            boolean seguir = true;
            while (seguir) {
                try {
                    opt = (int) leerPorTeclado("¿Que desea hacer?" +
                            "\n1) Ver datos" +
                            "\n2) Modificar datos" +
                            "\n3) Ver Carrito" +
                            "\n4) Ver Productos de la tienda" +
                            "\n5) Salir", Integer.class);
                } catch (EntradaInvalidaException e) {
                    System.out.println("Error: " + e.getMessage());
                    continue;
                }
                switch (opt) {
                    case 1:
                        ((Cliente) user).verDatos();
                        break;
                    case 2:
                        ((Cliente) user).updateDatos();
                        try {
                            String sql = "UPDATE Clientes SET Nombre=?,Apellido=?,Email=?,Direccion=?,MetodoPago=? WHERE User_Id=" + ((Cliente) user).getId();
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, ((Cliente) user).getNombre());
                            pstmt.setString(2, ((Cliente) user).getApellido());
                            pstmt.setString(3, ((Cliente) user).getEmail());
                            pstmt.setString(4, ((Cliente) user).getDireccion());
                            pstmt.setString(5, ((Cliente) user).getMetodoPago());
                            pstmt.execute();

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 3:
                        ((Cliente) user).verCarrito();
                        if (((Cliente) user).getCarrito()!=null){
                            try {
                                if(((Cliente) user).pagar()){
                                    try {
                                        actulizarStock(((Cliente)user).getCarrito().getProductos());
                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }

                                }
                            } catch (EntradaInvalidaException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case 4:
                        this.explorarTienda();
                        break;
                    default:
                        seguir = false;
                        break;
                }
            }
        }else if(user!=null && user instanceof Administrador) {
            ((Administrador) user).takeConn(conn);
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
            double pantalla =rs.getDouble("Pantalla");
            String resolucion=rs.getString("Resolucion");
            String tipoPantalla=rs.getString("TipoPantalla");
            double precio= rs.getDouble("Precio");
            //System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tResolucion: %s\tTipo Pantalla: %s\tPrecio: %.2f",id,marca,pantalla,resolucion,tipoPantalla,precio));
            System.out.println(String.format("ID: %d\tMarca: %-8s\tPantalla: %-8f\tResolucion: %-8s\tTipo Pantalla: %-8s\tPrecio: %-8.2f",id,marca,pantalla,resolucion,tipoPantalla,precio));
        }
        stm.close();
        rs.close();
    }
    public void leerCelulares() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Celulares");
        while (rs.next()){
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            double pantalla =rs.getDouble("Pantalla");
            int ram=rs.getInt("RAM");
            int rom=rs.getInt("ROM");
            String camara=rs.getString("Camara");
            double precio= rs.getDouble("Precio");
            //System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tRAM: %s\tROM: %s\tCamara: %s\tPrecio: %.2f",id,marca,pantalla,ram,rom,camara,precio));
            System.out.println(String.format("ID: %d\tMarca: %-8s\tPantalla: %-8.1f\tRAM: %-8s\tROM: %-8s\tCamara: %-8s\tPrecio: %-8.2f",id,marca,pantalla,ram,rom,camara,precio));
        }
        stm.close();
        rs.close();
    }
    public void leerTablets() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Tablets");
        while (rs.next()){
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            double pantalla =rs.getDouble("Pantalla");
            int ram=rs.getInt("RAM");
            int rom=rs.getInt("ROM");
            String camara=rs.getString("Camara");
            double precio= rs.getDouble("Precio");
            //System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tRAM: %s\tROM: %s\tCamara: %s\tPrecio: %.2f",id,marca,pantalla,ram,rom,camara,precio));
            System.out.println(String.format("ID: %d\tMarca: %-8s\tPantalla: %-8.1f\tRAM: %-8s\tROM: %-8s\tCamara: %-8s\tPrecio: %-8.2f",id,marca,pantalla,ram,rom,camara,precio));
        }
        stm.close();
        rs.close();
    }
    public void leerNotebooks() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM Notebooks");
        while(rs.next()){
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            double pantalla =rs.getDouble("Pantalla");
            String procesador=rs.getString("Procesador");
            int disco=rs.getInt("Disco");
            int ram=rs.getInt("RAM");
            double precio= rs.getDouble("Precio");
            //System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tProcesador: %s\tDisco: %s\tRAM: %s\tPrecio: %.2f",id,marca,pantalla,procesador,disco,ram,precio));
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
            int id =rs.getInt("Product_Id");
            String marca=rs.getString("Marca");
            String procesador=rs.getString("Procesador");
            int disco=rs.getInt("Disco");
            int ram=rs.getInt("RAM");
            String tarjetaVideo=rs.getString("TarjetaDeVideo");
            String fuente=rs.getString("Fuente");
            double precio= rs.getDouble("Precio");
            //System.out.println(String.format("ID: %d\tMarca: %s\tProcesador: %s\tDisco: %s\tRAM: %s\tTarjeta de video: %s\tFuente: %s\tPrecio: %.2f",id,marca,procesador,disco,ram,tarjetaVideo,fuente,precio));
            System.out.println(String.format("ID: %d\tMarca: %-8s\tProcesador: %-8s\tDisco: %-8d\tRAM: %-8d\tTarjeta de video: %-8s\tFuente: %-8s\tPrecio: %-8.2f",id,marca,procesador,disco,ram,tarjetaVideo,fuente,precio));
        }
        stm.close();
        rs.close();
    }

    public void actulizarStock(ArrayList<Producto> productos) throws SQLException{
        for(Producto p: productos){
            Statement stm = this.conn.createStatement();
            stm.executeUpdate("UPDATE "+p.getCategoria()+"SET Stock="+(p.getStock()-1)+ "WHERE Product_Id="+p.getId());
            stm.close();
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

                String nombre = null;
                while (true) {
                    try {
                        nombre = leerPorTeclado("Indique su Nombre", String.class).toString();
                        break;
                    } catch (EntradaInvalidaException e) {
                        System.out.println("Error: el campo Nombre no puede estar vacio!");
                    }
                }
                String apellido = null;
                while (true) {
                    try {
                        apellido = leerPorTeclado("Indique su Apellido", String.class).toString();
                        break;
                    } catch (EntradaInvalidaException e) {
                        System.out.println("Error: el campo Apellido no puede estar vacio!");
                    }
                }
                String email = null;
                while (true) {
                    try {
                        email = leerPorTeclado("Indique su email", String.class).toString();
                        break;
                    } catch (EntradaInvalidaException e) {
                        System.out.println("Error: el campo Email no puede estar vacio!");
                    }
                }
                String direccion = null;
                while (true) {
                    try {
                        direccion = leerPorTeclado("Indique su Direccion", String.class).toString();
                        break;
                    } catch (EntradaInvalidaException e) {
                        System.out.println("Error: el campo Direcion no puede estar vacio!");
                    }
                }
                int dni = 0;
                while (true) {
                    try {
                        dni = (int) leerPorTeclado("Indique su DNI", Integer.class);
                        break;
                    } catch (EntradaInvalidaException e) {
                        System.out.println("Error: el campo DNI no puede estar vacio!");
                    }
                }
                String metodoPago = null;
                while (true) {
                    try {
                        metodoPago = leerPorTeclado("Indique su Metodo de pago", String.class).toString();
                        break;
                    } catch (EntradaInvalidaException e) {
                        System.out.println("Error: el campo 'Metodo de Pago' no puede estar vacio!");
                    }
                }
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

                pstmt.close();
                rs.close();
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
                    newPrepStm.close();
                    rs2.close();
                }else if(tipo.equals("ADMINISTRADOR")){
                    String consulta = "SELECT * FROM Administradores WHERE User_Id=?";
                    PreparedStatement newPrepStm = conn.prepareStatement(consulta);
                    newPrepStm.setInt(1, rs.getInt("Id"));
                    ResultSet rs2 = newPrepStm.executeQuery();
                    rs2.next();
                    tipoUsuario = new Administrador(rs2.getInt("Id"),rs2.getInt("DNI"),rs2.getString("Nombre"),rs2.getString("Apellido"),rs2.getString("Direccion"),rs2.getString("Email"));
                    newPrepStm.close();
                    rs2.close();
                }
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new IllegalArgumentException("Nombre de usuario o contraseña incorrectos");
        }
        return tipoUsuario;
    }


}
