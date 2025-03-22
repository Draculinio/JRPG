package personajes;

public enum Pclase {
    GUERRERO(4,9,3,6, 80,120),
    MAGO(2,7,5,8, 50, 100),
    CAZADOR(5,8,2,6, 60, 150),
    ;

    private int fuerzaMin;
    private int fuerzaMax;
    private int defensaMin;
    private int defensaMax;
    private int vidaMin;
    private int vidaMax;

    Pclase(int fuerzaMin, int fuerzaMax, int defensaMin, int defensaMax, int vidaMin, int vidaMax) {
        this.fuerzaMin = fuerzaMin;
        this.fuerzaMax = fuerzaMax;
        this.defensaMin = defensaMin;
        this.defensaMax = defensaMax;
        this.vidaMin = vidaMin;
        this.vidaMax = vidaMax;
    }


    public int getFuerzaMin() {
        return fuerzaMin;
    }

    public int getFuerzaMax() {
        return fuerzaMax;
    }

    public int getDefensaMin() {
        return defensaMin;
    }

    public int getDefensaMax() {
        return defensaMax;
    }

    public int getVidaMin() {
        return vidaMin;
    }

    public int getVidaMax() {
        return vidaMax;
    }
}
