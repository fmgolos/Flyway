import org.flywaydb.core.Flyway
import java.sql.Connection
import java.sql.DriverManager


class DB(private val url: String, private val user: String, private val pass: String) {
    fun connection(): Connection {
        val flyway = Flyway.configure().dataSource(url, user, pass).load()
        try {
            flyway.migrate()
        } catch (e: Exception) {
            println("Не могу мигрировать $e")
        }
        return DriverManager.getConnection(url, user, pass)
    }
}

fun main() {
    val connection = DB("jdbc:postgresql://localhost:5432/postgres", "postgres", "password").connection()


}