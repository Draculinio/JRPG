package elementosRoleros;

import escenarios.Mapa;

public class CentralComandos {
    public void interpretarComando(String comando, Mapa mapaActual){
        switch(comando.toUpperCase()){
            case ("MIRAR"):
                System.out.println(mapaActual.describir());
                break;
            case ("SALIR"):
                System.out.println("Chau!");
                break;
            default:
                System.out.println("Comando incorrecto");
        }
    }
}
