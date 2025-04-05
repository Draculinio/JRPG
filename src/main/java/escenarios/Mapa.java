package escenarios;

import elementosRoleros.ObtenerDatos;
import lombok.Getter;
import org.json.JSONObject;
import personajes.Enemigo;
import personajes.Character;

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
    @Getter
    private String imagen;
    @Getter
    private Character character;
    @Getter
    private String mensaje;

    public Mapa(String mapaID, Character character){
        this.mapaID = mapaID;
        this.conexiones = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        ObtenerDatos od = new ObtenerDatos();
        JSONObject habitaciones = od.cargarHabitaciones("datos_habitaciones");
        JSONObject habitacion = habitaciones.getJSONObject(this.mapaID);
        this.descripcion = habitacion.getString("descripcion");
        this.nombre = habitacion.getString("nombre");
        this.imagen = habitacion.getString("image");
        this.character = character;
        this.mensaje = "";
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

    public void setMensaje(String mensaje){
        this.mensaje = "";
        this.mensaje = mensaje;
    }
}