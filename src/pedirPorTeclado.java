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

    default Object leerPorTeclado(String mensaje,Class<?> tipo){
        System.out.println(mensaje);
        Scanner sc = new Scanner(System.in);
        if(tipo==String.class){
            return sc.next();
        }else if(tipo==Integer.class){
            return sc.nextInt();
        }else{
            return null;
        }

    }
}
