package escenarios;

import elementosRoleros.ObtenerDatos;
import lombok.Getter;
import org.json.JSONObject;
import personajes.Enemigo;
import personajes.Character;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private String mapaID;
    @Getter
    private String name;
    @Getter
    private String descripcion;
    @Getter
    private List<Map> conexiones;
    @Getter
    private List<Enemigo> enemigos;
    @Getter
    private String imagen;
    @Getter
    private Character character;
    @Getter
    private String mensaje;

    public Map(String mapaID, Character character){
        this.mapaID = mapaID;
        this.conexiones = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        ObtenerDatos od = new ObtenerDatos();
        JSONObject habitaciones = od.cargarHabitaciones("datos_habitaciones");
        JSONObject habitacion = habitaciones.getJSONObject(this.mapaID);
        this.descripcion = habitacion.getString("descripcion");
        this.name = habitacion.getString("nombre");
        this.imagen = habitacion.getString("image");
        this.character = character;
        this.mensaje = "";
    }

    public void conectar(Map otroMap){
        this.conexiones.add(otroMap);
        otroMap.conexiones.add(this);
    }

    public void setEnemy(Enemigo enemigo){
        this.enemigos.add(enemigo);
    }

    public void setEnemy(List<Enemigo> nuevosEnemigos){
        this.enemigos.addAll(nuevosEnemigos);
    }

    public void setMessage(String mensaje){
        this.mensaje = mensaje;
    }

    public void clearMessage(){
        this.mensaje = "";
    }
}