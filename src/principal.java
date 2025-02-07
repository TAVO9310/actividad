import acividad.Cita;
import acividad.Doctor;
import acividad.Paciente;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.System.*;

public class principal {
    private final List<Doctor> doctores;
    private final List<Paciente> pacientes;
    private final List<Cita> citas;
    private static final String DOCTORES_FILE = "db/doctores.csv";
    private static final String PACIENTES_FILE = "db/pacientes.csv";
    private static final String CITAS_FILE = "db/citas.csv";
    private String idPaciente;

    public principal() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        cargarDatos();
    }

    public static void main(String[] args) {
        principal sistema = new principal();
        Scanner scanner = new Scanner(in);

        // Ejemplo de interacción con el usuario
        sistema.menuPrincipal(scanner);
        sistema.guardarDatos();
    }

    private void menuPrincipal(Scanner scanner) {
        while (true) {
            out.println("1. Dar de alta doctor");
            out.println("2. Dar de alta paciente");
            out.println("3. Crear cita");
            out.println("4. Relacionar cita con doctor y paciente");
            out.println("5. Salir");
            out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    agregarDoctor(scanner);
                    break;
                case 2:
                    agregarPaciente(scanner);
                    break;
                case 3:
                    crearCita(scanner);
                    break;
                case 4:
                    relacionarCita(scanner);
                    break;
                case 5:
                    out.println("¡Hasta luego!");
                    return;
                default:
                    out.println("Opción no válida.");
            }
        }
    }

    private void agregarDoctor(Scanner scanner) {
        out.print("ID del doctor: ");
        String id = scanner.nextLine();
        out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctores.add(doctor);
    }

    private void agregarPaciente(Scanner scanner) {
        out.print("ID del paciente: ");
        String id = scanner.nextLine();
        out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre);
        pacientes.add(paciente);
    }

    private void crearCita(Scanner scanner) {
        out.print("ID de la cita: ");
        String id = scanner.nextLine();
        out.print("Fecha y hora de la cita (YYYY-MM-DDTHH:MM): ");
        String fechaHoraStr = scanner.nextLine();
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);
        out.print("Motivo de la cita: ");
        String motivo = scanner.nextLine();

        out.print("ID del doctor: ");
        String idDoctor = scanner.nextLine();
        Doctor doctor = buscarDoctorPorId(idDoctor);

        out.print("ID del paciente: ");
        String idPaciente = scanner.nextLine();
        Paciente paciente = buscarPacientePorId(idPaciente);

        Cita cita = new Cita(id, fechaHora, motivo, doctor, paciente);
        citas.add(cita);
    }

    private Paciente buscarPacientePorId(String idPaciente) {
        this.idPaciente = idPaciente;
        return null;
    }

    private Doctor buscarDoctorPorId(String idDoctor) {
        return null;
    }

    private void relacionarCita(Scanner scanner) {
        out.print("ID de la cita: ");
        String idCita = scanner.nextLine();
        Cita cita = buscarCitaPorId(idCita);

        if (cita != null) {
            out.print("ID del doctor: ");
            String idDoctor = scanner.nextLine();
            Doctor doctor = buscarDoctorPorId(idDoctor);
            cita.setDoctor(doctor);

            out.print("ID del paciente: ");
            String idPaciente = scanner.nextLine();
            Paciente paciente = buscarPacientePorId(idPaciente);
            cita.setPaciente(paciente);
        } else {
            out.println("Cita no encontrada.");
        }
    }

    private Cita buscarCitaPorId(String idCita) {
        return null;
    }

    private void cargarDatos() {
        cargarDoctores();
        cargarPacientes();
        cargarCitas();
        cargarAdministradores();
    }

    private void cargarAdministradores() {
    }

    private void guardarDatos() {
        guardarDoctores();
        guardarPacientes();
        guardarCitas();
        guardarAdministradores();
    }

    private void guardarAdministradores() {
    }

    private void guardarCitas() {
    }

    private void cargarDoctores() {
        try (BufferedReader br = new BufferedReader(new FileReader(DOCTORES_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                doctores.add(new Doctor(datos[0], datos[1], datos[2]));
            }
        } catch (IOException e) {
            err.println("Error al cargar doctores: " + e.getMessage());
        }
    }

    private void guardarDoctores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DOCTORES_FILE))) {
            for (Doctor doctor : doctores) {
                bw.write(doctor.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            err.println("Error al guardar doctores: " + e.getMessage());
        }
    }

    private void cargarPacientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(PACIENTES_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                pacientes.add(new Paciente(datos[0], datos[1]));
            }
        } catch (IOException e) {
            err.println("Error al cargar pacientes: " + e.getMessage());
        }
    }

    private void guardarPacientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PACIENTES_FILE))) {
            for (Paciente paciente : pacientes) {
                bw.write(paciente.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            err.println("Error al guardar pacientes: " + e.getMessage());
        }
    }

    private void cargarCitas() {
        try (BufferedReader br = new BufferedReader(new FileReader(CITAS_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Doctor doctor = buscarDoctorPorId(datos[3]);
                Paciente paciente = buscarPacientePorId(datos[4]);
                citas.add(new Cita(datos[0], LocalDateTime.parse(datos[1]), datos[2], doctor, paciente));
            }
        } catch (IOException e) {
            {
                err.println("Error al cargar citas: " );

        }
    }}}