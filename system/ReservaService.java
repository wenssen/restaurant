package system;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar las reservas en el sistema.
 */
public class ReservaService {
    private Connection conexion;

    /**
     * Constructor que recibe una conexión a la base de datos.
     *
     * @param conexion Conexión a la base de datos.
     */
    public ReservaService(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Busca mesas disponibles según el número de personas y la fecha/hora solicitada.
     *
     * @param numPersonas Número de personas para la mesa.
     * @param fechaHora   Fecha y hora para la reserva.
     * @return Lista de mesas disponibles.
     * @throws SQLException Si ocurre un error en la consulta.
     */
    public List<Mesa> buscarMesasDisponibles(int numPersonas, LocalDateTime fechaHora) throws SQLException {
        String query = "SELECT * FROM mesas WHERE capacidad >= ? AND id NOT IN " +
                       "(SELECT mesa_id FROM reservas WHERE fecha_hora = ? AND estado = 'Confirmada')";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, numPersonas);
            stmt.setTimestamp(2, Timestamp.valueOf(fechaHora));

            ResultSet rs = stmt.executeQuery();
            List<Mesa> mesasDisponibles = new ArrayList<>();
            while (rs.next()) {
                mesasDisponibles.add(new Mesa(rs.getInt("id"), rs.getInt("capacidad"), rs.getBoolean("disponible")));
            }
            return mesasDisponibles;
        }
    }

    /**
     * Crea una nueva reserva para una mesa específica.
     *
     * @param mesaId    ID de la mesa a reservar.
     * @param clienteId ID del cliente que realiza la reserva.
     * @param fechaHora Fecha y hora de la reserva.
     * @return true si la reserva fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error en la inserción.
     */
    public boolean reservarMesa(int mesaId, int clienteId, LocalDateTime fechaHora) throws SQLException {
        String query = "INSERT INTO reservas (mesa_id, cliente_id, fecha_hora, estado) VALUES (?, ?, ?, 'Confirmada')";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, mesaId);
            stmt.setInt(2, clienteId);
            stmt.setTimestamp(3, Timestamp.valueOf(fechaHora));
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Cancela una reserva específica.
     *
     * @param reservaId ID de la reserva a cancelar.
     * @return true si la cancelación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error en la actualización.
     */
    public boolean cancelarReserva(int reservaId) throws SQLException {
        String query = "UPDATE reservas SET estado = 'Cancelada' WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, reservaId);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Obtiene todas las reservas de un cliente específico.
     *
     * @param clienteId ID del cliente.
     * @return Lista de reservas del cliente.
     * @throws SQLException Si ocurre un error en la consulta.
     */
    public List<Reserva> obtenerReservasPorCliente(int clienteId) throws SQLException {
        String query = "SELECT * FROM reservas WHERE cliente_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, clienteId);

            ResultSet rs = stmt.executeQuery();
            List<Reserva> reservas = new ArrayList<>();
            while (rs.next()) {
                reservas.add(new Reserva(
                    rs.getInt("id"),
                    rs.getInt("mesa_id"),
                    rs.getInt("cliente_id"),
                    rs.getTimestamp("fecha_hora").toLocalDateTime(),
                    rs.getString("estado")
                ));
            }
            return reservas;
        }
    }
}
