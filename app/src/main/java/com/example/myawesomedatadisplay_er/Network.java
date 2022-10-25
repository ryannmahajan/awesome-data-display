package com.example.myawesomedatadisplay_er;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Network {
    static String FILE_NAME = "Stored_json.json";

    public static void downloadFileIfNotPresent(Context context) {
        if (!does_file_exist(context))
            download_file(context);
    }

    private static boolean does_file_exist(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        return file.exists();
    }

    private static void download_file(Context context) {
        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
        File file = new File(context.getFilesDir(), FILE_NAME);

        try (BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fis = new FileOutputStream(file)){

            byte[] buffer = new byte[1024];
            int count=0;
            while((count = bis.read(buffer,0,1024)) != -1)
            {
                fis.write(buffer, 0, count);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error occurred while downloading file");
        }
    }

    public static String get_text_from_file(Context context) {
        StringBuilder stringBuilder = new StringBuilder();

        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading downloaded file");
        }

        return stringBuilder.toString();
    }
}
