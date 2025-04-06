package elementosRoleros;

import escenarios.Map;
import personajes.Enemigo;

public class CambiaMapas {

    public Map ir(String[] comando, Map map){
        if (comando.length<2){
            map.setMessage("Debe indicar adonde quiere ir");
        }else{
            boolean estado = false;
            for(int i = 0; i< map.getConexiones().size(); i++){
                Map m = map.getConexiones().get(i);
                if(m.getNombre().equalsIgnoreCase(comando[1])){
                    estado = true;
                    map = m;
                    map.setMessage(map.getMensaje() + "Moviendome a " + map.getNombre());
                }
            }
            if(!estado){
                map.setMessage("No se encontro esa salida");
            };
        }
        return map;
    }

    public Map atacar(String[] comando, Map map){
        if (comando.length<2){
            map.setMessage("Debe indicar a quien atacar");
        }else{
            boolean estado = false;
            for(int i = 0; i< map.getEnemigos().size(); i++){
                Enemigo e = map.getEnemigos().get(i);
                if(e.getNombre().equals(comando[1].toUpperCase())){
                    int ataque = map.getCharacter().atacar(e);
                    map.setMessage("Atacas a " + comando[1] +" por "+ ataque);
                    e.setVida(e.getVida()-ataque);
                    if (e.getVida()<=0){
                        map.getCharacter().setOro(e.getOro());
                        map.getCharacter().addExperiencia(e.getExperiencia());
                        map.getEnemigos().remove(e);
                        map.setMessage(map.getMensaje()+ "\n" + comando[1] + " muere");
                    }
                    estado = true;
                    break;
                }
            }
            if(!estado){
                map.setMessage("\nNo encontre el enemigo.");
            }
        }
        return map;
    }


    public Map verSalidas(Map map){
        map.setMessage("");
        for(Map conexion : map.getConexiones()) map.setMessage(map.getMensaje() + conexion.getNombre() + "\n");
        return map;
    }

    public Map recibirAtaque(Map map){
        int defensa = map.getCharacter().getDefensa();
        for(Enemigo enemigo: map.getEnemigos()){
            map.getCharacter().setVida(map.getCharacter().getVida()-(enemigo.getFuerza()-defensa));
            map.setMessage(map.getMensaje()+enemigo.getNombre()+" te ha golpeado por "+enemigo.getFuerza()+"\n");
        }
        return map;
    }
}
