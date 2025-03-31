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
    @Getter
    @Setter
    private int defensa;
    @Getter
    private int experiencia;
    @Getter
    private StatsEnemigos stats;
    @Getter
    private int oro;

    public Enemigo(String nombre){
        this.nombre = nombre;
        this.stats = StatsEnemigos.valueOf(this.nombre);
        this.vida = this.stats.getVida();
        this.experiencia = this.stats.getExperiencia();
        this.oro = this.stats.getOro();
        this.fuerza = this.stats.getFuerza();
    }


    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

}
