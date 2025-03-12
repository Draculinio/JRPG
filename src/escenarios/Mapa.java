package escenarios;

public class Mapa {
    private int mapaID;
    private String nombre;
    private String descripcion;

    public Mapa(int mapaID, String nombre, String descripcion){
        this.mapaID = mapaID;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String describir(){
        StringBuilder str = new StringBuilder();
        str.append(this.nombre);
        str.append("\n");
        str.append(this.descripcion);
        return str.toString();
    }
}
