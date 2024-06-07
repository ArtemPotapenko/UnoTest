package org.example;

import org.example.services.FileInputService;
import org.example.services.GroupService;

import java.io.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        String filename;
        if (args.length == 1){
            filename = args[0];
        }
        else{
            throw new RuntimeException("Некорректные аргументы");
        }
        Date date = new Date();
        GroupService groupService = new GroupService();
        try (FileInputService inputService = new FileInputService(filename)) {
            while (inputService.hasNext()) {
                groupService.addNewString(inputService.readString());
            }

            groupService.formGroups();
            System.out.printf("Количество групп из более 1 элемента: %d \n", groupService.getGroups().stream().filter(s -> s.size() > 1).toList().size());
            System.out.printf("Время выполнения: %.3f c \n", (new Date().getTime() - date.getTime())/1000d);
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }

    }
}