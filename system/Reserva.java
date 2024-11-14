package system;
import java.time.LocalDateTime;

/**
 * Representa una reserva en el sistema.
 */
public class Reserva {
    private int id;
    private int mesaId;
    private int clienteId;
    private LocalDateTime fechaHora;
    private String estado;

    /**
     * Constructor para crear una instancia de Reserva.
     *
     * @param id         Identificador único de la reserva.
     * @param mesaId     Identificador de la mesa reservada.
     * @param clienteId  Identificador del cliente que realizó la reserva.
     * @param fechaHora  Fecha y hora de la reserva.
     * @param estado     Estado actual de la reserva.
     */
    public Reserva(int id, int mesaId, int clienteId, LocalDateTime fechaHora, String estado) {
        this.id = id;
        this.mesaId = mesaId;
        this.clienteId = clienteId;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getMesaId() { return mesaId; }
    public int getClienteId() { return clienteId; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
