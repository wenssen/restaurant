package system;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaService {
    private Connection conexion;

    public ReservaService(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener mesas disponibles
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

    // Crear una reserva
    public boolean reservarMesa(int mesaId, int clienteId, LocalDateTime fechaHora) throws SQLException {
        String query = "INSERT INTO reservas (mesa_id, cliente_id, fecha_hora, estado) VALUES (?, ?, ?, 'Confirmada')";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, mesaId);
            stmt.setInt(2, clienteId);
            stmt.setTimestamp(3, Timestamp.valueOf(fechaHora));
            return stmt.executeUpdate() > 0;
        }
    }

    // Cancelar reserva
    public boolean cancelarReserva(int reservaId) throws SQLException {
        String query = "UPDATE reservas SET estado = 'Cancelada' WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, reservaId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Ver reservas por cliente
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

