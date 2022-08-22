package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public static void main(String[] args) {
        System.out.println(getDataFromFile(new File("src/main/resources/Profile.txt")));
    }

    public static Profile getDataFromFile(File file) {
        Profile profile = new Profile();

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            int ch;
            StringBuilder s = new StringBuilder();
            while ((ch = fileInputStream.read()) != -1) {
                char x = (char) ch;
                if (x == '\n') {
                    String[] split = s.toString().trim().split(": ");
                    switch (split[0]) {
                        case "Name":
                            profile.setName(split[1]);
                            break;
                        case "Age":
                            profile.setAge(Integer.valueOf(split[1]));
                            break;
                        case "Email":
                            profile.setEmail(split[1].trim());
                            break;
                        case "Phone":
                            profile.setPhone(Long.valueOf(split[1].trim()));
                            break;
                    }
                    s = new StringBuilder();
                }
                s.append(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }
}
