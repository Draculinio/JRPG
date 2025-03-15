package personajes;

public class Enemigo {
    private String nombre;
    private int vida;

    private StatsEnemigos stats;

    public Enemigo(String nombre){
        this.nombre = nombre;
        this.stats = StatsEnemigos.valueOf(this.nombre);
        this.vida = this.stats.getVida();
    }

    public String getNombre(){
        return this.nombre;
    }


}
