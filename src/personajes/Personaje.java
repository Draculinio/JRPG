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
    private  Pclase clase;
    private Praza raza;
    private Psexo sexo;

    public Personaje(String nombre, String clase, String raza, String sexo){
        switch(clase.toUpperCase()){
            case "G":
                this.clase = Pclase.GUERRERO;
                break;
            case "M":
                this.clase = Pclase.MAGO;
                break;
            case "C":
                this.clase = Pclase.CAZADOR;
                break;
            default:
                this.clase = Pclase.GUERRERO; //TODO: Y esto?
        }
        switch(raza.toUpperCase()){
            case "H":
                this.raza = Praza.HUMANO;
                break;
            case "E":
                this.raza = Praza.ELFO;
                break;
            case "B":
                this.raza = Praza.HOBBIT;
                break;
            case "S":
                this.raza = Praza.SEMIELFO;
                break;
            default:
                this.raza = Praza.HUMANO;
        }
        this.sexo = (sexo.equalsIgnoreCase("H")) ? Psexo.HOMBRE : Psexo.MUJER;
        this.nombre = nombre;
        this.ataque = Dados.dado(this.clase.getFuerzaMin(),this.clase.getFuerzaMax()) + this.raza.getFuerza() + this.sexo.getFuerza();
        this.defensa = Dados.dado(this.clase.getDefensaMin(),this.clase.getDefensaMax()) + this.raza.getDefensa() + this.sexo.getDefensa();
        this.vida = Dados.dado(this.clase.getVidaMin(),this.clase.getVidaMax()) + this.raza.getVida() + this.sexo.getVida();
        this.oro = 0;
        this.experiencia = 0;
        this.nivel = 1;
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
        this.oro = oro;
    }
}