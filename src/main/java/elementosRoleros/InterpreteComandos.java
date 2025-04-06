package elementosRoleros;

import escenarios.Map;

public class InterpreteComandos {

    public Map manejarInstrucciones(String instruccion, Map map){
        CambiaMapas cm = new CambiaMapas();
        String[] comando = instruccion.split(" ");
        Map newMap;
        switch (comando[0].toUpperCase()){
            case "ATACAR":
            case "ATTACK":
                newMap = cm.atacar(comando, map);
                break;
            case "SALIDAS":
            case "EXIT":
                newMap = cm.verSalidas(map);
                break;
            case "IR":
            case "GO":
                newMap = cm.ir(comando, map);
                break;
            default:
                newMap = map; //Supongo que hay formas mejores de hacer esto
        }
        return newMap;
    }

    public String verSalidas(Map map){
        StringBuilder retorno = new StringBuilder();
        for(Map conexion : map.getConexiones()) {
            retorno.append(conexion.getNombre()).append("\n");
        }
        return retorno.toString();
    }
}
