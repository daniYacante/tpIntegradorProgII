import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Administrador extends Usuario implements pedirPorTeclado{
    private int idEmpleado;

    public Administrador(int id, int dni, String nombre,String apellido, String direccion,String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idEmpleado = id;
        this.dni = dni;
        this.email = email;
        this.direccion = direccion;
        this.tipo = "Administrador";
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    private void readTabla(Connection conn,String tabla){
        String sql = "SELECT * FROM " + tabla;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnas = metaData.getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= columnas; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    private String selectTabla(String accion){
        int opt2;
        String tabla="";
        while (true) {
            try {
                opt2 = (int) leerPorTeclado("¿Que Tabla Desea "+accion+"?" +
                        "\n1) Clientes" +
                        "\n2) Administradores" +
                        "\n3) Televisores" +
                        "\n4) PCs" +
                        "\n5) Notebooks" +
                        "\n6) Celulares" +
                        "\n7) Tablets" +
                        "\n8) Salir", Integer.class);
                break;
            } catch (EntradaInvalidaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        switch (opt2) {
            case 1:
                tabla="Clientes";
                break;
            case 2:
                tabla= "Administradores";
                break;
            case 3:
                tabla=  "Televisores";
                break;
            case 4:
                tabla=  "PCs";
                break;
            case 5:
                tabla=  "Notebooks";
                break;
            case 6:
                tabla=  "Celulares";
                break;
            case 7:
                tabla=  "Tablets";
                break;
            case 8:
                break;
            default:
                System.out.println("Opcion no valida");
        }
        return tabla;
    }
    public void takeConn(Connection connexion){
        boolean seguir = true;
        int opt;
        while (seguir) {
            try {
                opt = (int) leerPorTeclado("¿Que desea hacer?" +
                        "\n1) Ver datos" +
                        "\n2) Modificar datos" +
                        "\n3) Borrar datos" +
                        "\n4) Añadir datos" +
                        "\n5) Salir", Integer.class);
            } catch (EntradaInvalidaException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }
            String resp="";
            switch (opt) {

                case 1:
                    resp=selectTabla("Ver");
                    if (!resp.isEmpty()){
                        readTabla(connexion,resp);
                    }else{
                        continue;
                    }
                    break;
                case 2:
                    resp=selectTabla("Modificar");
                    if (!resp.isEmpty()){
                        try {
                            modifyTabla(connexion,resp);
                        } catch (SQLException e) {
                            System.out.println("Error al modificar la tabla--"+e.getMessage());;
                        } catch (EntradaInvalidaException e) {
                            System.out.println("Error al modificar la tabla por problemas de tipos de datos--"+e.getMessage());;
                        }
                    }else{
                        continue;
                    }
                    break;
                case 3:
                    resp=selectTabla("Borrar");
                    if (!resp.isEmpty()){
                        readTabla(connexion,resp);
                    }else{
                        continue;
                    }
                    break;
                case 4:
                    resp=selectTabla("Añadir");
                    if (!resp.isEmpty()){
                        readTabla(connexion,resp);
                    }else{
                        continue;
                    }
                    break;
                default:
                    seguir = false;
                    break;
            }
        }
    }
    private void modifyTabla(Connection conn,String tabla) throws SQLException, EntradaInvalidaException {
        StringBuilder sql = new StringBuilder("UPDATE " + tabla + " SET ");
        Map<String, Object> values = new HashMap<>();

        String sqlColumns = "SELECT * FROM " + tabla;
        int id= (int) leerPorTeclado("Ingrese el id de la fila a modificar",Integer.class);
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlColumns);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnas = metaData.getColumnCount();
            System.out.println("Campos disponibles a modificacion:");
            for (int i = 1; i <= columnas; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
        } catch (SQLException e) {
            throw new SQLException("Error al mostrar las columnas: " + e.getMessage());
        }
        System.out.println("Ingrese los campos a modificar de la siguiente forma:\nCampo:Valor\nPara finalizar ingrese la palabra 'end'");

        while (true) {
            String entrada = leerPorTeclado("",String.class).toString().trim();
            if (entrada.equalsIgnoreCase("end")) {
                break;
            }
            if (!entrada.contains(":")) {
                System.out.println("Formato incorrecto. Use Campo:Valor");
                continue;
            }
            String[] partes = entrada.split(":", 2);
            String campo = partes[0].trim();
            String valor = partes[1].trim();
            if (campo.isEmpty() || valor.isEmpty()) {
                System.out.println("Campo o valor vacío. Intente nuevamente.");
                continue;
            }

            values.put(campo, valor);
        }

        int i = 0;
        for (String campo : values.keySet()) {
            sql.append(campo).append(" = ?");
            if (i < values.size() - 1) {
                sql.append(", ");
            }
            i++;
        }

        sql.append(" WHERE ").append("Id").append(" = ?");

        try{
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            int index = 1;
            for (Object valor : values.values()) {
                stmt.setObject(index++, valor);
            }
            stmt.setObject(index, id);

            stmt.executeUpdate();
            System.out.println("Campos Actualizados Exitosamente");
        }catch(SQLException e) {
            System.out.println("Error al modificar los campos");
        }
    }


    private void deleteTabla(){
        System.out.println("Vamos a borrar algo");
    }
    private void createTabla(){
        System.out.println("Vamos a crear algo");
    }
}
