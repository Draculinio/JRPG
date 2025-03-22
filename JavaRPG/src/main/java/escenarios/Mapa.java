package escenarios;

import personajes.Enemigo;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private int mapaID;
    private String nombre;
    private String descripcion;
    private List<Mapa> conexiones;
    private List<Enemigo> enemigos;
    public Mapa(int mapaID, String nombre, String descripcion){
        this.mapaID = mapaID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.conexiones = new ArrayList<>();
        this.enemigos = new ArrayList<>();
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
        if (!this.enemigos.isEmpty()) {
            str.append("Enemigos: \n");
            for(Enemigo e : this.enemigos){
                str.append(e.getNombre());
                str.append("\n");
            }
        }
        return str.toString();
    }

    public void conectar(Mapa otroMapa){
        this.conexiones.add(otroMapa);
        otroMapa.conexiones.add(this);
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigo(Enemigo enemigo){
        this.enemigos.add(enemigo);
    }

    public void setEnemigo(List<Enemigo> nuevosEnemigos){
        this.enemigos.addAll(nuevosEnemigos);
    }
}
