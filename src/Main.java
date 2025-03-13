import java.util.Scanner;

import elementosRoleros.CentralComandos;
import escenarios.Mapa;
import personajes.Personaje;

public class Main {
    public static void main(String[] args) {
        CentralComandos cc = new CentralComandos();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del personaje: ");
        String nombre = scanner.nextLine();
        String clase = "";
        while (!clase.equalsIgnoreCase("M") && !clase.equalsIgnoreCase("G")){
            System.out.print("Elija clase (M/G): ");
            clase = scanner.nextLine();
        }

        Personaje personaje = new Personaje(nombre,clase);
        Mapa mapa1 = new Mapa(1,"Ciudad Esmeralda - Casa", "Esta es la casa humilde del personaje");
        Mapa mapa2 = new Mapa(2, "Ciudad Esmeralda - Portico", "Estas en la puerta de tu casa");
        mapa1.conectar(mapa2);

        Mapa mapaActual = mapa1;
        String comando = "";
        while (!comando.equalsIgnoreCase("salir")){
            System.out.print(">");
            comando = scanner.nextLine();
            cc.interpretarComando(comando, mapaActual);
        }

        /*System.out.print(comando);
        System.out.println(mapa1.getConexiones().getFirst().getNombre());
        System.out.println(mapa2.getConexiones());
        System.out.println(personaje.datosDelPersonaje());
        System.out.println(mapa1.describir());*/
    }
}