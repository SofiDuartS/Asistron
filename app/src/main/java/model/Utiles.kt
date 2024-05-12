package model
import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Utiles {
    @Throws(IOException::class)
    fun leerJson(context: Context, fileName: String): String {
        val lector: BufferedReader = BufferedReader(InputStreamReader(context.assets.open(fileName), "UTF-8"))

        var content = ""
        var line: String?
        while (lector.readLine().also { line = it } != null) {
            content += line
        }
        return content
    }
}