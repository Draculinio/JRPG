package personajes;

public enum StatsEnemigos {
    ZOMBIE(5,3,1,2, 50, 2);

    private int fuerza;
    private int defensa;
    private int oro;
    private int experiencia;
    private int vida;
    private int factor;

    StatsEnemigos(int fuerza, int defensa, int oro, int experiencia, int vida, int factor){
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.oro = oro;
        this.experiencia = experiencia;
        this.vida = vida;
        this.factor = factor;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getOro() {
        return oro;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getVida() {
        return vida;
    }

    public int getFactor() {
        return factor;
    }
}
