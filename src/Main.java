import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection conn=null;
        try{
            Class.forName("org.sqlite.JDBC");
            conn=DriverManager.getConnection("jdbc:sqlite:test01:sqlite");
            if(conn!=null){
                System.out.println("Conexion a base de datos...OK");
            }else{
                System.out.println("Conexion a base de datos...problemas!");
            }
            conn.close();
        }catch(SQLException a){
            System.out.println(a);
        }catch(ClassNotFoundException c){
            System.out.println(c);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}