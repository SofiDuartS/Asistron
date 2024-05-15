package model
import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Utiles {
    companion object {
        fun leerJson(context: Context, s: String): String {
            val lector: BufferedReader =
                BufferedReader(InputStreamReader(context.assets.open(s), "UTF-8"))

            var content = ""
            var line: String?
            while (lector.readLine().also { line = it } != null) {
                content += line
            }
            return content
        }
    }
}