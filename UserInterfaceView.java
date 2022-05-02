package Lesson7;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please, enter the name of the city (in English): ");
            String city = scanner.nextLine();

            System.out.println("Enter 1 to get weather forecast for ONE day; " +
                    "Enter 5 to get weather forecast for FIVE days; " +
                    "Enter 0 to exit. ");
            String command = scanner.nextLine();

            if ("0".equals(command)) break;

            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

