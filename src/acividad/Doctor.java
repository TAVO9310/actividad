package acividad;

public class Doctor {
    private String id;
    private String nombreCompleto;
    private String especialidad;

    public Doctor(String id, String nombreCompleto, String especialidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    // Getters y Setters

    @Override
    public String toString() {
        return id + "," + nombreCompleto + "," + especialidad;
    }

    public String getId() {
        return id;
    }
}
