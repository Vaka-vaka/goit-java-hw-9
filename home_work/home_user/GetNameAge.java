package home_work.home_user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GetNameAge {
    private static final String REED_FILE =
            "src\\home_work\\resources\\file2.txt";
    private static final String WRITE_FILE =
            "src\\home_work\\resources\\user.json";

    private static void reedFile(File file, List<User> userList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] column = line.split(" ");
                if (!column[0].equals("name") && !column[1].equals("age")) {
                    userList.add(new User(column[0], Integer.parseInt(column[1])));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile(List<User> userList, File files) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(files))) {
             Gson gson = new GsonBuilder().setPrettyPrinting().create();
               String json = gson.toJson(userList);
               writer.write(json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkIfFileAvailable(File file) {

        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        File file = new File(REED_FILE);
        File files = new File(WRITE_FILE);
        checkIfFileAvailable(file);
        checkIfFileAvailable(files);

        List<User> userList = new LinkedList<>();
        reedFile(file, userList);
        writeFile(userList, files);
    }
}

