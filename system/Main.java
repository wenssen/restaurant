package system;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection conexion = DatabaseConnector.getConnection();
        if (conexion != null) {
            ReservaService reservaService = new ReservaService(conexion);
            
            try {
                // Ejemplo de uso: Buscar mesas disponibles
                List<Mesa> mesasDisponibles = reservaService.buscarMesasDisponibles(4, LocalDateTime.now().plusDays(1));
                System.out.println("Mesas disponibles: " + mesasDisponibles.size());
                
                // Ejemplo de uso: Reservar una mesa
                if (!mesasDisponibles.isEmpty()) {
                    boolean reservaExitosa = reservaService.reservarMesa(mesasDisponibles.get(0).getId(), 1, LocalDateTime.now().plusDays(1));
                    System.out.println("Reserva exitosa: " + reservaExitosa);
                }
                
                // Ejemplo de uso: Cancelar una reserva
                boolean cancelacionExitosa = reservaService.cancelarReserva(1);
                System.out.println("Cancelación exitosa: " + cancelacionExitosa);

                // Ejemplo de uso: Obtener reservas por cliente
                List<Reserva> reservasCliente = reservaService.obtenerReservasPorCliente(1);
                System.out.println("Reservas del cliente: " + reservasCliente.size());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error al conectar con la base de datos.");
        }
    }
}
