package elementosRoleros;

import escenarios.Mapa;
import personajes.Personaje;

public class CentralComandos {
    public void interpretarComando(String comando, Mapa mapaActual, Personaje personaje){
        switch(comando.toUpperCase()){
            case ("MIRAR"):
                System.out.println(mapaActual.describir());
                break;
            case ("SALIR"):
                System.out.println("Chau!");
                break;
            case("STATS"):
                System.out.println(personaje.personajeFormateado());
                break;
            default:
                System.out.println("Comando incorrecto");
        }
    }
}
