package personajes;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import elementosRoleros.Dados;

public class Personaje {
    private String nombre;
    private int ataque;
    private int defensa;
    private int vida;
    private int oro;
    private int experiencia;
    private int nivel;
    public Personaje(String nombre){
        this.nombre = nombre;
        this.ataque = setAtaque();
        this.defensa = setDefensa();
        this.vida = setVida();
        this.oro = 0;
        this.experiencia = 0;
        this.nivel = 0;
    }

    public List<Object> datosDelPersonaje(){
        List<Object> datos = new ArrayList<>();
        datos.add(nombre);
        datos.add(ataque);
        datos.add(defensa);
        datos.add(vida);
        datos.add(oro);
        datos.add(experiencia);
        datos.add(nivel);
        return datos;
    }

    private int setAtaque(){
        int classMax = 5;
        int classMin = 1;
        return Dados.dado(classMin,classMax);
    }


    public int setDefensa() {
        int classMax = 5;
        int classMin = 1;
        return Dados.dado(classMin,classMax);
    }

    public int setVida() {
        int classMax = 100;
        int classMin = 50;
        return Dados.dado(classMin,classMax);
    }

    public void setOro(int oro) {
        this.oro = oro;
    }
}
