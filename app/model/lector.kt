import java.io.IOException
import java.io.BufferedReader
import java.io.InputStreamReader

public class Utiles {
    public static String leerJson(Context context.String filename) throws IOException{
        BufferedReader lector = null
        lector = new BufferedReader(new InputStreamReader(context.getAssets().open(filename), "UTF-8"))

        String content = ""
        String line
        while ((line = reader.readLine()) != null){
            content = content + line
        }
        return content
    }
}
