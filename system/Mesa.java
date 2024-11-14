package system;

/**
 * Representa una mesa en el sistema.
 */
public class Mesa {
    private int id;
    private int capacidad;
    private boolean disponible;

    /**
     * Constructor para crear una instancia de Mesa.
     *
     * @param id         Identificador único de la mesa.
     * @param capacidad  Capacidad de personas de la mesa.
     * @param disponible Indica si la mesa está disponible o no.
     */
    public Mesa(int id, int capacidad, boolean disponible) {
        this.id = id;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    public int getId() { return id; }
    public int getCapacidad() { return capacidad; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
