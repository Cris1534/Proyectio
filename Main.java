

import java.util.*;

/**
 *
 * @author crist
 */

   class Medicina {
    private final String nombre;

    public Medicina(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

// Clase que representa a un paciente
class Paciente {
    private final String id;
    private final List<Medicina> medicamentos;

    public Paciente(String id) {
        this.id = id;
        this.medicamentos = new ArrayList<>();
    }

    public void agregarMedicamento(Medicina medicamento) {
        this.medicamentos.add(medicamento);
    }

    public List<Medicina> getMedicamentos() {
        return medicamentos;
    }

    public String getId() {
        return id;
    }
}

// Clase que representa una cita médica
class CitaDoc {
    private final String fecha;
    private final String horaInicio;
    private final String horaFin;
    private final List<Paciente> pacientes;

    public CitaDoc(String fecha, String horaInicio, String horaFin) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) {
        this.pacientes.add(paciente);
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }
}

// Clase que representa a un médico
class Medico {
    private final String nombre;
    private final List<CitaDoc> agenda;

    public Medico(String nombre) {
        this.nombre = nombre;
        this.agenda = new ArrayList<>();
    }

    public void agregarCita(CitaDoc cita) {
        this.agenda.add(cita);
    }

    public List<CitaDoc> getAgenda() {
        return agenda;
    }

    public String getNombre() {
        return nombre;
    }
}

public class Main {

    public static void main(String[] args) {
        
     
        Medicina Formula = new Medicina("Dolex Gripa ");
        Medicina  Formula2 = new Medicina("Acetaminofen");

        // Crear un paciente y agregarle medicamentos
        
        Paciente paciente1 = new Paciente("8165231");
        Paciente paciente2 = new Paciente("Cristian");
        paciente1.agregarMedicamento(Formula);
        paciente1.agregarMedicamento(Formula2);

        // Crear una cita médica 
        CitaDoc cita1 = new CitaDoc("2023-10-27", "09:00", "10:00");
        cita1.agregarPaciente(paciente1);

        // Crear un médico 
        Medico medico1 = new Medico("Doc Samy");
        medico1.agregarCita(cita1);

        // Servicio 1: Obtener medicamentos formulados a un paciente
        String idPaciente = "8165231";
         String idPaciente2 = "Cristian";
        Paciente paciente = Buscarpaciente(idPaciente, medico1);
        if (paciente != null) {
            List<Medicina> medicamentos = paciente.getMedicamentos();
            System.out.println("Medicamentos a recetar  " + idPaciente2 +  ":" + idPaciente );
            for (Medicina medicamento : medicamentos) {
                System.out.println("- " + medicamento.getNombre());
            }
        } else {
            System.out.println("El paciente no fue encontrado, por favor validar ");
        }

        // Servicio 2: Listar agenda de citas de un médico por fecha y hora
        String fecha = "2023-10-27";
        String horaInicio = "09:00";
        String horaFin = "10:00";
        List<Paciente> pacientesEnCitas = obtenerPacientesEnCitas(medico1, fecha, horaInicio, horaFin);
        if (!pacientesEnCitas.isEmpty()) {
            System.out.println("Pacientes agendados  " + medico1.getNombre() +
                    " para el " + fecha + " entre " + horaInicio + " y " + horaFin + ":");
            for (Paciente pacienteEnCita : pacientesEnCitas) {
                System.out.println("- " + pacienteEnCita.getId());
            }
        } else {
            System.out.println("No hay agenda libre para la agenda escogida.");
        }
    }

    // Método para buscar un paciente por su identificación en la agenda de un médico
    private static Paciente Buscarpaciente(String idPaciente, Medico medico) {
        for (CitaDoc cita : medico.getAgenda()) {
            for (Paciente paciente : cita.getPacientes()) {
                if (paciente.getId().equals(idPaciente)) {
                    return paciente;
                }
            }
        }
        return null;
    }

    // Método para obtener la lista de pacientes en las citas de un médico para una fecha y rango de horas
    private static List<Paciente> obtenerPacientesEnCitas(Medico medico, String fecha, String horaInicio, String horaFin) {
        List<Paciente> pacientesEnCitas = new ArrayList<>();
        for (CitaDoc cita : medico.getAgenda()) {
            if (cita.getFecha().equals(fecha) &&
                    horaInicio.compareTo(cita.getHoraInicio()) <= 0 &&
                    horaFin.compareTo(cita.getHoraFin()) >= 0) {
                pacientesEnCitas.addAll(cita.getPacientes());
            }
        }
        return pacientesEnCitas;
    }
}


