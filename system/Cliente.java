package system;

/**
 * Representa un cliente en el sistema.
 */
public class Cliente {
    private int id;
    private String nombre;
    private String contacto;
    private String correo;

    /**
     * Constructor para crear una instancia de Cliente.
     *
     * @param id       Identificador único del cliente.
     * @param nombre   Nombre del cliente.
     * @param contacto Información de contacto del cliente.
     * @param correo   Correo de contacto del cliente.
     */
    public Cliente(int id, String nombre, String contacto, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.correo = correo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
    public String getCorreo() { return correo; }
}
