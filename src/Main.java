import escenarios.Mapa;
import personajes.Personaje;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Personaje personaje = new Personaje("Draculinio");
        Mapa mapa1 = new Mapa(1,"Ciudad Esmeralda - Casa", "Esta es la casa humilde del personaje");
        System.out.println(personaje.datosDelPersonaje());
        System.out.println(mapa1.describir());
    }
}