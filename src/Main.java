import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Tienda mercadito= new Tienda("Mercadito");
        try {
            mercadito.iniciarConn();
            mercadito.leerTelevisores();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mercadito.closeConn();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}