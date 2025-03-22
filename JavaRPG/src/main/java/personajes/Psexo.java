package personajes;

public enum Psexo {
    HOMBRE(1,0,60),
    MUJER(0,1,70),;

    private int fuerza;
    private int defensa;
    private int vida;


    Psexo(int fuerza,int defensa, int vida) {
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
