package personajes;

import elementosRoleros.Dados;

public class Enemigo {
    private String nombre;
    private int vida;
    private int fuerza;
    private int defensa;
    private int experiencia;
    private StatsEnemigos stats;

    public Enemigo(String nombre){
        this.nombre = nombre;
        this.stats = StatsEnemigos.valueOf(this.nombre);
        this.vida = this.stats.getVida();
        this.experiencia = this.stats.getExperiencia();
        this.fuerza = this.stats.getFuerza();
    }

    public String getNombre(){
        return this.nombre;
    }


    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getFuerza() {
        return fuerza;
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

    public int atacar(int defensa) {
        int ataque = this.fuerza+ Dados.dado(1, stats.getFactor()) - defensa;
        if(ataque > 0){
          return ataque;
        }else{
            return 0;
        }
    }
}
