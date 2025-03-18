package org.jrpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import elementosRoleros.CentralComandos;
import escenarios.GameOver;
import escenarios.Mapa;
import personajes.Enemigo;
import personajes.Personaje;

public class Main {
    public static void main(String[] args) {
        CentralComandos cc = new CentralComandos();
        Scanner scanner = new Scanner(System.in);

        //ARMADO DEL PERSONAJE
        System.out.print("Ingrese el nombre del personaje: ");
        String nombre = scanner.nextLine();
        String clase = "";
        String raza = "";
        String sexo = "";
        System.out.print("Elija clase (M/G/C): ");
        clase = scanner.nextLine();
        System.out.print("Elija raza (H/E/B/S): ");
        raza = scanner.nextLine();
        System.out.print("Elija sexo (H/M): ");
        sexo = scanner.nextLine();

        //ARMANDO EL AMBIENTE
        Enemigo zombi = new Enemigo("ZOMBIE");
        Enemigo otroZombie = new Enemigo("ZOMBIE");
        Personaje personaje = new Personaje(nombre,clase,raza,sexo);
        Mapa mapa1 = new Mapa(1,"Ciudad Esmeralda - Casa", "Esta es la casa humilde del personaje");
        Mapa mapa2 = new Mapa(2, "Ciudad Esmeralda - Portico", "Estas en la puerta de tu casa");
        mapa1.conectar(mapa2);
        mapa1.setEnemigo(zombi);
        List<Enemigo> nuevosEnemigos = new ArrayList<>();
        nuevosEnemigos.add(new Enemigo("ZOMBIE"));
        nuevosEnemigos.add(new Enemigo("ZOMBIE"));

        mapa1.setEnemigo(nuevosEnemigos);
        mapa1.setEnemigo(otroZombie);
        Mapa mapaActual = mapa1;

        String comando = "";
        while (!comando.equalsIgnoreCase("salir")){
            System.out.print(">");
            comando = scanner.nextLine();
            cc.interpretarComando(comando, mapaActual, personaje);
            //Una vez que tiro un comando, si hay enemigos en la sala, deben atacarme
            if (!mapaActual.getEnemigos().isEmpty() && !comando.equalsIgnoreCase("salir")){
                for (Enemigo enemigo : mapaActual.getEnemigos()) {
                    int ataque = enemigo.atacar(personaje.getDefensa());
                    System.out.printf("%s te ataca por %d %n", enemigo.getNombre(), ataque);
                    personaje.setVida(personaje.getVida() - ataque);
                    if(personaje.getVida() <= 0){
                        GameOver.showGameOver();
                        comando = "salir";
                        break;
                    }
                }
            }
        }
    }
}