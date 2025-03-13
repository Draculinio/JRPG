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
    private String clase;
    public Personaje(String nombre, String clase){
        this.nombre = nombre;
        this.clase = clase;
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
        datos.add(clase);
        return datos;
    }

    private int setAtaque(){
        int classMax = 5, classMin = 1;
        switch(clase){
            case "M":
                classMin = 2;
                classMax = 7;
                break;
            case "G":
                classMin = 4;
                classMax = 9;
                break;
            default:
                classMin = 100;
                classMax = 500;
        }

        return Dados.dado(classMin,classMax);
    }


    public int setDefensa() {
        int classMax = 5, classMin = 1;
        switch(clase){
            case "M":
                classMin = 5;
                classMax = 8;
                break;
            case "G":
                classMin = 3;
                classMax = 6;
                break;
            default:
                classMin = 100;
                classMax = 500;
        }
        return Dados.dado(classMin,classMax);
    }

    public int setVida() {
        int classMax = 5, classMin = 1;
        switch(clase){
            case "M":
                classMin = 50;
                classMax = 100;
                break;
            case "G":
                classMin = 80;
                classMax = 120;
                break;
            default:
                classMin = 100;
                classMax = 500;
        }
        return Dados.dado(classMin,classMax);
    }

    public void setOro(int oro) {
        this.oro = oro;
    }
}
