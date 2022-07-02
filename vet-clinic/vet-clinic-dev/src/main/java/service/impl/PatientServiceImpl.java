package service.impl;

import data.Storage;
import dto.Patient;
import service.PatientService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.UUID;

public class PatientServiceImpl implements PatientService {


    @Override
    public void createPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя пациента: ");
        String fio = scanner.nextLine();
        System.out.print("Введите год регистрации: ");
        int year = scanner.nextInt();
        System.out.print("Введите месяц регистрации: ");
        int month = scanner.nextInt();
        System.out.print("Введите день регистрации: ");
        int day = scanner.nextInt();
        Calendar registrationDate = new GregorianCalendar(year, month, day);

        Storage.patients.add(new Patient(fio, registrationDate));
        System.out.println("Пациент добавлен!");
    }

    @Override
    public void showAllPatients() {
        if (Storage.patients.isEmpty()) {
            System.out.println("Пациенты отсутствуют");
            return;
        }

        Storage.patients.forEach(p -> {
            System.out.printf("ID: %s\nимя: %s\nДата регистрации: %s", p.getId(), p.getFio(), p.getRegistrationDate());
            System.out.println("\n");
        });
    }

    @Override
    public void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID пациента: ");
        UUID patientId = UUID.fromString(scanner.nextLine());
        Storage.patients.removeIf(p -> p.getId().equals(patientId));

        System.out.println("Пациент был удален!");
    }

    @Override
    public void editPatientFio() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ID пациента: ");
        UUID patientId = UUID.fromString(scanner.nextLine());
        Patient patient = Storage.patients.stream().filter(p -> p.getId().equals(patientId)).findFirst().get();


        System.out.print("Введите имя ");
        String fio = scanner.nextLine();

        patient.setFio(fio);
    }
}
