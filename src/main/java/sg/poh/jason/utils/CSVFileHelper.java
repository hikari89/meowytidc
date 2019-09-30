package sg.poh.jason.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileHelper {
    public static List<String> LoadFileAsList(String fileName) {
        List<String> lines = new ArrayList<>();
        String line = null;
        File file = getFile(fileName);
        if (file == null) {
            return lines;
        }
        readLines(lines, file);
        return lines;
    }

    private static void readLines(List<String> lines, File file) {
        String line;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.trim().length() > 0 && line.contains(",")) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static File getFile(String fileName) {
        File file = null;
        Resource resource = new ClassPathResource(fileName);
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
