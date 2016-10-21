package org.evsyukov;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LineCounter {
    public static void main(String[] args) throws IOException {
        System.out.println("Run program");
        if(args.length == 0) {
            System.out.println("Use: jar <filename>");
            System.exit(0);
        }

        long startTime = System.nanoTime();
        System.out.println("\nCount lines in " + args[0] + " file: " + countLines(args[0]));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Execute time: " + duration/1000000 + " ms");
    }

    private static int countLines(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : ++count;
        }
    }
}
