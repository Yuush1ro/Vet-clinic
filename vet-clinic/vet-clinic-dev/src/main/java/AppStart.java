import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import service.impl.AppointmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.PatientServiceImpl;
import utils.Authentication;

import java.util.Scanner;

public class AppStart {
    private static final PatientService patientService = new PatientServiceImpl();
    private static final DoctorService doctorService = new DoctorServiceImpl();
    private static final AppointmentService appointmentService = new AppointmentServiceImpl();

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        if (Authentication.auth()) while (true) {
            System.out.print("""
                    0 - Выход
                    1 - Создать пациента
                    2 - Создать доктора
                    3 - Создать прием
                    4 - Удалить пациента
                    5 - Показать всех пациентов
                    6 - изменить имя пациента
                    7 - Показать все приемы пациентов
                    8 - изменить статус приема
                    """);
            switch (scanner.nextInt()) {
                case 0 -> System.exit(0);
                case 1 -> patientService.createPatient();
                case 2 -> doctorService.createDoctor();
                case 3 -> appointmentService.createAppointment();
                case 4 -> patientService.deleteUser();
                case 5 -> patientService.showAllPatients();
                case 6 -> patientService.editPatientFio();
                case 7 -> appointmentService.showAllPatientAppointments();
                case 8 -> appointmentService.changeAppointmentStatus();
            }
        }
        else {
            System.out.println("НЕВЕРНО!");
        }
    }
}
