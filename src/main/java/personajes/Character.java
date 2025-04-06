package personajes;

import elementosRoleros.Dices;
import lombok.Getter;

public class Character {
    @Getter
    private String nombre;
    private int ataque;
    @Getter
    private int defensa;
    @Getter
    private int vida;
    @Getter
    private int oro;
    @Getter
    private int experiencia;
    @Getter
    private int nivel;
    @Getter
    private  Pclase clase;
    @Getter
    private Praza raza;
    @Getter
    private Psexo sexo;
    private Armas brazo_derecho;

    public Character(String nombre, String clase, String raza, String sexo){
        this.clase = Pclase.valueOf(clase.toUpperCase());
        this.raza = Praza.valueOf(raza.toUpperCase());
        this.sexo = Psexo.valueOf(sexo.toUpperCase());
        this.nombre = nombre;
        this.ataque = Dices.dice(this.clase.getFuerzaMin(),this.clase.getFuerzaMax()) + this.raza.getFuerza() + this.sexo.getFuerza();
        this.defensa = Dices.dice(this.clase.getDefensaMin(),this.clase.getDefensaMax()) + this.raza.getDefensa() + this.sexo.getDefensa();
        this.vida = Dices.dice(this.clase.getVidaMin(),this.clase.getVidaMax()) + this.raza.getVida() + this.sexo.getVida();
        this.oro = 0;
        this.experiencia = 0;
        this.nivel = 1;
        this.brazo_derecho = Armas.ESPADA_DE_MADERA;
    }

    public String personajeFormateado(){
        return String.format("%s-%s-%s-%s A:%d D:%d V:%d O:%d  N:%d(%d)" , this.nombre, this.clase, this.raza, this.sexo, this.ataque, this.defensa, this.vida, this.oro, this.nivel, this.experiencia);
    }

    private void setAtaque(int ataque){
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setOro(int oro) {
        this.oro += oro;
    }

    public int atacar(Enemigo e){
        int resultado =  this.ataque + this.brazo_derecho.getFuerza() + Dices.dice(1,6) - e.getDefensa();
        return Math.max(resultado, 0); //TODO: Investigar esto
    }

    public void addExperiencia(int experiencia){
        this.experiencia += experiencia;
    }

}
