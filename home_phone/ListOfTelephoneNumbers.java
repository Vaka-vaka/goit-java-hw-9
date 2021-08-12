package home_work.home_phone;

import java.io.*;
import java.util.regex.Pattern;

public class ListOfTelephoneNumbers {
    private static final String PHONE_NUMBER1 = "\\d{3}-\\d{3}-\\d{4}";
    private static final String PHONE_NUMBER2 = "\\(\\d{3}\\) \\d{3}-\\d{4}";

    public static void main(String[] args) {
        File file = new File(
                "src\\home_work\\resources\\file.txt");
        checkIfFileAvailable(file);
        reedFile1(file);
    }

    private static boolean correctsNumber(String number) {
        return Pattern.matches(PHONE_NUMBER1, number) || Pattern.matches(PHONE_NUMBER2, number);
    }

    private static void reedFile1(File file) { //метод для читання файлу

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String number = reader.readLine();

            while (number != null) {
                if (correctsNumber(number)) {
                    System.out.println(number);
                }
                number = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkIfFileAvailable(File file) { //метод для перевірки наявності файла
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

