import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Console;

/*
tres niveles de herencia
Deberá conectarse a una base de datos SQLite para almacenar y gestionar información, permitiendo a los usuarios:
    Agregar nuevos registros a las tablas de la base de datos.
    Modificar los registros existentes.
    Eliminar registros de las tablas.
    Buscar y filtrar registros utilizando consultas SQL.
ingreso de información por consola, validando la entrada del usuario y manejando excepciones
El uso de excepciones personalizadas será necesario para controlar errores como intentos de acceso a posiciones inválidas dentro de la lista o entradas no válidas por parte del usuario.
Los grupos deberán demostrar el uso de encapsulamiento, gestionando los atributos con modificadores de acceso adecuados y proporcionando getters y setters.
Interfaces que implementen polimorfismo y permitan extender la funcionalidad de las clases mediante la implementación de diferentes comportamientos.
Deben utilizarse composición y agregación para representar relaciones entre clases, con al menos un ejemplo de cada tipo.

 */
public class Main {
    public static void main(String[] args) {
        Tienda mercadito= new Tienda("Mercadito");
        Scanner sc = new Scanner(System.in);
        Console console = System.console();

        try {
            mercadito.iniciarConn();
            boolean seguir=true;
            int opt=0;
            System.out.println("Bienvenidos a "+mercadito.getName());
            while (seguir){
                System.out.println("¿Que desea hacer?");
                System.out.println("1)- Logearse\n2)- Crear Usuario\n3)- Ver Productos\n0)- Salir");
                try {
                    opt = sc.nextInt();
                    switch (opt) {
                        case 1:
                            System.out.print("Ingrese nombre de usuario\n");
                            String name = sc.next();
                            System.out.println("Ingrese contraseña\n");
                            String pass = sc.next();
                            try {
                                String tipo=mercadito.loginUsuario(name, pass);
                                System.out.println("logueado como: "+tipo);
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
                                mercadito.crearUsuario(name, pass);
                            } catch (Exception e) {
                                System.out.println("Error al crear usuario");
                            }
                            break;
                        case 3:
                            break;
                        default:
                            seguir = false;
                            break;
                    }
                }catch (Exception e){
                    System.out.println("Lo ingresado no es un numero");
                    sc.nextLine();
                }
            }
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