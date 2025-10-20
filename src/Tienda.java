import java.net.ConnectException;
import java.sql.*;

public class Tienda {
    private String nombre;
    private Connection conn;

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
    public void crearUsuario(String userName, String password, Object tipo){
        if(tipo instanceof Administrador){

        }else{

        }
    }
    public void crearUsuario(String userName, String password){
        String tipoUsuario;
        try{
            String sql = "SELECT tipoUsuario FROM usuarios WHERE email = ? AND contraseña = ?";
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
    }

}
