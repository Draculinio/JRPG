package elementosRoleros;

import escenarios.Mapa;
import personajes.Enemigo;
import personajes.Personaje;

import java.util.ArrayList;
import java.util.List;

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
            case ("ATACAR"):
                List<Enemigo> e = mapaActual.getEnemigos();
                for(Enemigo enemigo : e){
                    int ataque = personaje.atacar(enemigo);
                    enemigo.setVida(enemigo.getVida()-ataque);
                    System.out.printf("Le quitaste a %s %d puntos de vida. Le quedan %s %n", enemigo.getNombre() , ataque, enemigo.getVida());
                    if(enemigo.getVida() <=0){
                        personaje.addExperiencia(enemigo.getExperiencia());
                        System.out.printf("%s esta muerto %n", enemigo.getNombre());
                    }
                }
                e.removeIf(enemigo -> enemigo.getVida()<=0);
                break;
            default:
                System.out.println("Comando incorrecto");
        }
    }
}