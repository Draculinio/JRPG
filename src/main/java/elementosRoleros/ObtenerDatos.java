package elementosRoleros;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ObtenerDatos {
    private JSONObject dataFile;

    public  JSONObject cargarHabitaciones(String entidad) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("texts/"+entidad+".json")) {
            if (is == null) {
                throw new RuntimeException("No se encontró el archivo JSON en resources/texts/");
            }
            String jsonText = new Scanner(is, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            dataFile = new JSONObject(jsonText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataFile;
    }
}
