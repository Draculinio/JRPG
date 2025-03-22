package personajes;

public enum Armas {
    ESPADA_DE_MADERA(2,0);

    private int fuerza;
    private int defensa;

    Armas(int fuerza, int defensa){
        this.fuerza =  fuerza;
        this.defensa = defensa;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getDefensa() {
        return defensa;
    }
}
