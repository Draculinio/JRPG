package personajes;

public enum Praza {
    HUMAN(0,0,20),
    ELF(-1,-1,100),
    HOBBIT(1,0,35),
    HALFELF(0,1,80);

    private int fuerza;
    private int defensa;
    private int vida;


    Praza(int fuerza,int defensa, int vida) {
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.vida = vida;
    }


    public int getFuerza() {
        return fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVida() {
        return vida;
    }
}
