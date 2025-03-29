package elementosRoleros;

import escenarios.Mapa;
import personajes.Enemigo;

public class CambiaMapas {

    public Mapa ir(String[] comando, Mapa mapa){
        if (comando.length<2){
            mapa.setMensaje("Debe indicar adonde quiere ir");
        }else{
            boolean estado = false;
            for(int i = 0; i< mapa.getConexiones().size(); i++){
                Mapa m = mapa.getConexiones().get(i);
                if(m.getNombre().equalsIgnoreCase(comando[1])){
                    estado = true;
                    mapa = m;
                    mapa.setMensaje(mapa.getMensaje() + "Moviendome a " + mapa.getNombre());
                }
            }
            if(!estado){
                mapa.setMensaje("No se encontro esa salida");
            };
        }
        return mapa;
    }

    public Mapa atacar(String[] comando, Mapa mapa){
        if (comando.length<2){
            mapa.setMensaje("Debe indicar a quien atacar");
        }else{
            boolean estado = false;
            for(int i = 0; i< mapa.getEnemigos().size(); i++){
                Enemigo e = mapa.getEnemigos().get(i);
                if(e.getNombre().equals(comando[1].toUpperCase())){
                    int ataque = mapa.getPersonaje().atacar(e);
                    mapa.setMensaje("Atacas a " + comando[1] +" por "+ ataque);
                    e.setVida(e.getVida()-ataque);
                    if (e.getVida()<=0){
                        mapa.getPersonaje().setOro(e.getOro());
                        mapa.getPersonaje().addExperiencia(e.getExperiencia());
                        mapa.getEnemigos().remove(e);
                        mapa.setMensaje(mapa.getMensaje()+ "\n" + comando[1] + " muere");
                    }
                    estado = true;
                    break;
                }
            }
            if(!estado){
                mapa.setMensaje("\nNo encontre el enemigo.");
            }
        }
        return mapa;
    }


    public Mapa verSalidas(Mapa mapa){
        mapa.setMensaje("");
        for(Mapa conexion : mapa.getConexiones()) mapa.setMensaje(mapa.getMensaje() + conexion.getNombre() + "\n");
        return mapa;
    }
}
