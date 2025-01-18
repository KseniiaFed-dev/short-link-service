package com.example.shortlink;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final LinkManager linkManager = new LinkManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Сократить ссылку");
            System.out.println("2. Перейти по короткой ссылке");
            System.out.println("3. Управлять ссылкой");
            System.out.println("4. Выход");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createShortLink();
                    break;
                case "2":
                    followShortLink();
                    break;
                case "3":
                    manageLink();
                    break;
                case "4":
                    System.out.println("Завершение программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private static void createShortLink() {
        System.out.println("Введите длинную ссылку:");
        String longUrl = scanner.nextLine();
        UUID userUuid = UUID.randomUUID();
        System.out.println("Введите максимальное количество кликов (0 - без ограничений):");
        int maxClicks = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите количество дней жизни ссылки (0 - использовать из config):");
        int linkLifetimeDays = Integer.parseInt(scanner.nextLine());

        String shortUrl = linkManager.createLink(longUrl, userUuid, maxClicks, linkLifetimeDays);
        System.out.println("Короткая ссылка: " + shortUrl);
    }

    private static void followShortLink() {
        System.out.println("Введите короткую ссылку:");
        String shortUrl = scanner.nextLine();
        String longUrl = linkManager.getLongUrl(shortUrl);
        if (longUrl != null) {
            System.out.println("Переадресация на: " + longUrl);
        } else {
            System.out.println("Ссылка не найдена или неактивна.");
        }
    }

    private static void manageLink(){
        System.out.println("Введите короткую ссылку:");
        String shortUrl = scanner.nextLine();
        System.out.println("Введите максимальное количество кликов (0 - без ограничений):");
        int newMaxClicks = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите количество дней жизни ссылки (0 - использовать из config):");
        int newLinkLifetimeDays = Integer.parseInt(scanner.nextLine());
        linkManager.updateLink(shortUrl, newMaxClicks, newLinkLifetimeDays);
        System.out.println("Ссылка обновлена.");
    }
}