import java.net.ConnectException;
import java.sql.*;

public class Tienda {
    private String nombre;
    private Connection conn;
    private boolean checkCredenciales(String userName, String password){
        String patternName="^[a-zA-Z0-9]{4,}$"; //Solo letras minusculas y mayusculas y numeros del 0 al 9, minimo 4 caracteres
        String patternPass="^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";//
        return  userName.matches(patternName) && password.matches(patternPass);
    }
    public void iniciarConn() throws Exception {
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
        if(rs.next()){
            int id =rs.getInt("Id");
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
        if(rs.next()){
            int id =rs.getInt("Id");
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
        if(rs.next()){
            int id =rs.getInt("Id");
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
        if(rs.next()){
            int id =rs.getInt("Id");
            String marca=rs.getString("Marca");
            int pantalla =rs.getInt("Pantalla");
            String procesador=rs.getString("Procesador");
            String disco=rs.getString("Disco");
            String ram=rs.getString("RAM");
            String camara=rs.getString("Camara");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tProcesador: %s\tDisco: %s\tRAM: %s\tCamara: %s\tPrecio: %.2f",id,marca,pantalla,procesador,disco,ram,camara,precio));
        }
    }
    public void leerPCs() throws SQLException{
        ResultSet rs=null;
        Statement stm = this.conn.createStatement();
        rs=stm.executeQuery("SELECT * FROM PCs");
        if(rs.next()){
            int id =rs.getInt("Id");
            String marca=rs.getString("Marca");
            String procesador=rs.getString("Procesador");
            String disco=rs.getString("Disco");
            String ram=rs.getString("RAM");
            String tarjetaVideo=rs.getString("Tarjeta de video");
            String fuente=rs.getString("Fuente");
            double precio= rs.getDouble("Precio");
            System.out.println(String.format("ID: %d\tMarca: %s\tPantalla: %d\tProcesador: %s\tDisco: %s\tRAM: %s\tTarjeta de video: %s\tFuente: %s\tPrecio: %.2f",id,marca,procesador,disco,ram,tarjetaVideo,fuente,precio));
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
    public String loginUsuario(String userName, String password){
        String tipoUsuario="NULL";
        if(checkCredenciales(userName, password)) {
            try {
                String sql = "SELECT tipo FROM usuarios WHERE userName = ? AND password = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    tipoUsuario = rs.getString("tipo");
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
