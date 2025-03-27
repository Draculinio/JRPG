package escenarios;

import elementosRoleros.ObtenerDatos;
import lombok.Getter;
import org.json.JSONObject;
import personajes.Enemigo;
import personajes.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private String mapaID;
    @Getter
    private String nombre;
    @Getter
    private String descripcion;
    private List<Mapa> conexiones;
    @Getter
    private List<Enemigo> enemigos;
    private String imagen;
    @Getter
    private Personaje personaje;

    public Mapa(String mapaID, String nombre, String imagen, Personaje personaje){
        this.mapaID = mapaID;
        this.nombre = nombre;
        this.conexiones = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.imagen = imagen;
        ObtenerDatos od = new ObtenerDatos();
        JSONObject habitaciones = od.cargarHabitaciones("datos_habitaciones");
        JSONObject habitacion = habitaciones.getJSONObject(this.mapaID);
        this.descripcion = habitacion.getString("descripcion");
        this.personaje = personaje;
    }

    public List<Mapa> getConexiones(){
        return conexiones;
    }

    public void conectar(Mapa otroMapa){
        this.conexiones.add(otroMapa);
        otroMapa.conexiones.add(this);
    }

    public void setEnemigo(Enemigo enemigo){
        this.enemigos.add(enemigo);
    }

    public void setEnemigo(List<Enemigo> nuevosEnemigos){
        this.enemigos.addAll(nuevosEnemigos);
    }

    public String getImagen() {
        return imagen;
    }

}