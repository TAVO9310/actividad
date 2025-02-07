package acividad;

import java.time.LocalDateTime;

public class Cita {
    private String id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id, LocalDateTime fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    // Getters y Setters

    @Override
    public String toString() {
        return id + "," + fechaHora + "," + motivo + "," + doctor.getId() + "," + paciente.getId();
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
