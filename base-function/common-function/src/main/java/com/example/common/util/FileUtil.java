package com.example.common.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class FileUtil {

    public void readCsv() throws IOException {
        // 「src/main/resources/data.csv」を読み込む
        try (InputStream is = new ClassPathResource("data.csv").getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;

            // 1行ずつファイルを読み込む
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
