package emergency;

import java.io.*;
import java.util.*;

public class FileStore {
    public static List<String> read(String file) {
        List<String> lines = new ArrayList<>();
        try {
            File f = new File(file);
            if (!f.exists()) return lines;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Could not read data.");
        }
        return lines;
    }

    public static void write(String file, List<String> lines) {
        try {
            File f = new File(file);
            File parent = f.getParentFile();
            if (parent != null) parent.mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Could not save data.");
        }
    }
}
