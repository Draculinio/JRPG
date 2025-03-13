package escenarios;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private int mapaID;
    private String nombre;
    private String descripcion;
    private List<Mapa> conexiones;

    public Mapa(int mapaID, String nombre, String descripcion){
        this.mapaID = mapaID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.conexiones = new ArrayList<>();
    }

    public List<Mapa> getConexiones(){
        return conexiones;
    }

    public String getNombre(){
        return nombre;
    }

    public String describir(){
        StringBuilder str = new StringBuilder();
        str.append(this.nombre);
        str.append("\n");
        str.append(this.descripcion);
        return str.toString();
    }

    public void conectar(Mapa otroMapa){
        this.conexiones.add(otroMapa);
        otroMapa.conexiones.add(this);
    }
}
