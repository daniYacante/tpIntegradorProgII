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
        mercadito.iniciar();
    }
}