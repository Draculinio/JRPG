package elementosRoleros;

import escenarios.Mapa;
import personajes.Enemigo;

public class InterpreteComandos {

    public String manejarInstrucciones(String instruccion, Mapa mapa){
        String[] comando = instruccion.split(" ");
        String retorno = "";
        if (comando[0].equalsIgnoreCase("ATACAR")){
            if (comando.length<2){
                retorno = "Debe indicar a quien atacar";
            }else{
                retorno = "Usted quiere atacar a "+comando[1];
                boolean estado = false;
                for(int i = 0; i< mapa.getEnemigos().size(); i++){
                    Enemigo e = mapa.getEnemigos().get(i);
                    if(e.getNombre().equals(comando[1].toUpperCase())){
                        int ataque = mapa.getPersonaje().atacar(e);
                        retorno = retorno + "\n Atacas a " + comando[1] +" por "+ ataque;
                        e.setVida(e.getVida()-ataque);
                        if (e.getVida()<=0){
                            mapa.getPersonaje().setOro(e.getOro());
                            mapa.getPersonaje().addExperiencia(e.getExperiencia());
                            mapa.getEnemigos().remove(e);
                            retorno = retorno+ "\n" + comando[1] + " muere";
                        }
                        estado = true;
                        break;
                    }
                }
                if(!estado){
                    retorno = retorno + "\nNo encontre el enemigo.";
                }
            }

        }
        return retorno;
        //return "Usted puso la instruccion en el mapa "+ mapa.getNombre() + instruccion;
    }
}
