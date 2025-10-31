import java.util.Scanner;

public interface pedirPorTeclado {
    default boolean checkInput(Object input, Class<?> tipo){
        if(tipo==String.class){
            return input instanceof String;
        }else if(tipo==Integer.class){
            return input instanceof Integer;
        }else{
            return false;
        }
    }

    /*default Object leerPorTeclado(String mensaje,Class<?> tipo){
        System.out.println(mensaje);
        Scanner sc = new Scanner(System.in);
        Object respuesta=null;
        if(tipo==String.class){
            respuesta = sc.nextLine();
        }else if(tipo==Integer.class){
            respuesta= sc.nextInt();
        }
        //sc.close();
        return respuesta;
    }*/

    default Object leerPorTeclado(String mensaje, Class<?> tipo) throws EntradaInvalidaException {
        System.out.println(mensaje);
        Scanner sc = new Scanner(System.in);
        Object respuesta = null;

        try {
            if (tipo == String.class) {
                respuesta = sc.nextLine();
                if (((String) respuesta).trim().isEmpty()) {
                    throw new EntradaInvalidaException("La entrada no puede estar vacía.");
                }
            } else if (tipo == Integer.class) {
                String entrada = sc.nextLine();
                try {
                    respuesta = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    throw new EntradaInvalidaException("Debe ingresar un número entero válido.");
                }
            } else {
                throw new EntradaInvalidaException("Tipo de dato no soportado.");
            }
        } catch (Exception e) {
            throw new EntradaInvalidaException("Error al leer la entrada: " + e.getMessage());
        }

        return respuesta;
    }

}
