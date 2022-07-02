package service.impl;

import data.Storage;
import dto.Doctor;
import service.DoctorService;

import java.util.Scanner;

public class DoctorServiceImpl implements DoctorService {


    @Override
    public void createDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя доктора ");
        String fio = scanner.nextLine();

        Storage.doctors.add(new Doctor(fio));
        System.out.println("Доктор " + fio + " добавлен!");
    }
}
