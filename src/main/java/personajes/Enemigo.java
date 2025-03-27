package personajes;

import lombok.Getter;
import lombok.Setter;

public class Enemigo {
    @Getter
    private String nombre;
    @Setter
    @Getter
    private int vida;
    @Getter
    private int fuerza;
    private int defensa;
    private int experiencia;
    private StatsEnemigos stats;
    @Getter
    private int oro;

    public Enemigo(String nombre){
        this.nombre = nombre;
        this.stats = StatsEnemigos.valueOf(this.nombre);
        this.vida = this.stats.getVida();
        this.experiencia = this.stats.getExperiencia();
        this.oro = this.stats.getOro();
    }


    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getExperiencia() {
        return experiencia;
    }

}
