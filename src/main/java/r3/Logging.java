package r3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Logging {
    public static FileOutputStream fs;
    public static PrintWriter pw;

    public static void init() {
        if (fs == null) {
            try {
                fs = new FileOutputStream("/home/lvuser/logs");
                pw = new PrintWriter(fs);
            } catch (FileNotFoundException e) { System.out.println("Noo!!!!"); }
        }
    }

    public static void log(String s) {
        pw.write(s + "\n");
    }

    public static void log(Object s) {
        log(s.toString());
    }
}
