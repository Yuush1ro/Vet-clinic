package service.impl;

import data.Storage;
import dto.Appointment;
import enums.AppointmentStatus;
import service.AppointmentService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.UUID;

public class AppointmentServiceImpl implements AppointmentService {


    @Override
    public void createAppointment() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя пациента: ");
        String patientFio = scanner.nextLine();
        System.out.print("Введите год регистрации: ");
        int year = scanner.nextInt();
        System.out.print("Введите месяц регистрации: ");
        int month = scanner.nextInt();
        System.out.print("Введите день регистрации: ");
        int day = scanner.nextInt();
        Calendar registrationDate = new GregorianCalendar(year, month, day);

        Appointment appointment = new Appointment(patientFio, registrationDate, AppointmentStatus.NEW);

        Storage.appointments.add(appointment);
    }

    @Override
    public void showAllPatientAppointments() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя пациента: ");
        String patientFio = scanner.nextLine();

        if (Storage.appointments.stream().noneMatch(a -> a.getPatientFio().equals(patientFio))) {
            System.out.println("У этого клиента отсутствуют записи");
            return;
        }

        System.out.println("Список приемов для пациента: " + patientFio);
        Storage.appointments.forEach(a -> {
            if (a.getPatientFio().equals(patientFio))
                System.out.println(a.getRegistrationDate() + " " + a.getStatus().toString());
        });

    }

    @Override
    public void changeAppointmentStatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ID приема: ");
        UUID appointmentId = UUID.fromString(scanner.nextLine());

        System.out.print("""
                Укажите новый статус:
                0 - Новый
                1 - В процессе
                2 - Отменен
                3 - Ожидает оплаты
                4 - Завершен
                """);

        int statusCode = scanner.nextInt();
        AppointmentStatus newStatus = null;

        switch (statusCode) {
            case 0 -> newStatus = AppointmentStatus.NEW;
            case 1 -> newStatus = AppointmentStatus.IN_PROCESS;
            case 2 -> newStatus = AppointmentStatus.CANCELLED;
            case 3 -> newStatus = AppointmentStatus.AWAITING_PAYMENT;
            case 4 -> newStatus = AppointmentStatus.COMPLETED;
        }

        Storage.appointments.stream().filter(a -> a.getId().equals(appointmentId)).findFirst().get().setStatus(newStatus);

    }
}
