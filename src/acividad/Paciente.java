package acividad;

public class Paciente {
    private String id;
    private String nombreCompleto;

    public Paciente(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    // Getters y Setters

    @Override
    public String toString() {
        return id + "," + nombreCompleto;
    }

    public String getId() {
        return id;
    }
}
