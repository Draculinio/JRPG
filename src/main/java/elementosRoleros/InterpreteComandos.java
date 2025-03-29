package elementosRoleros;

import escenarios.Mapa;
import personajes.Enemigo;

import java.util.ArrayList;

public class InterpreteComandos {

    public Mapa manejarInstrucciones(String instruccion, Mapa mapa){
        CambiaMapas cm = new CambiaMapas();
        String[] comando = instruccion.split(" ");
        Mapa newMapa;
        switch (comando[0].toUpperCase()){
            case "ATACAR":
                newMapa = cm.atacar(comando, mapa);
                break;
            case "SALIDAS":
                newMapa = cm.verSalidas(mapa);
                break;
            case "IR":
                newMapa = cm.ir(comando, mapa);
                break;
            default:
                newMapa = mapa; //Supongo que hay formas mejores de hacer esto
        }
        return newMapa;
    }

    public String verSalidas(Mapa mapa){
        StringBuilder retorno = new StringBuilder();
        for(Mapa conexion : mapa.getConexiones()) {
            retorno.append(conexion.getNombre()).append("\n");
        }
        return retorno.toString();
    }
}
