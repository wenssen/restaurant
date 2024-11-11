package system;
import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private int mesaId;
    private int clienteId;
    private LocalDateTime fechaHora;
    private String estado;

    public Reserva(int id, int mesaId, int clienteId, LocalDateTime fechaHora, String estado) {
        this.id = id;
        this.mesaId = mesaId;
        this.clienteId = clienteId;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() { return id; }
    public int getMesaId() { return mesaId; }
    public int getClienteId() { return clienteId; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
