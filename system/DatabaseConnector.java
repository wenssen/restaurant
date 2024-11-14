package system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conector para la base de datos.
 */
public class DatabaseConnector {

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return Objeto Connection a la base de datos.
     */
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/nombre_base_datos";  // URL de la base de datos
        String user = "usuario";  // Usuario para la conexión
        String password = "contraseña";  // Contraseña para la conexión

        try {
            // Intentamos establecer la conexión a la base de datos
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // En caso de error en la conexión, se imprime el error y se retorna null
            e.printStackTrace();
            return null;
        }
    }
}
