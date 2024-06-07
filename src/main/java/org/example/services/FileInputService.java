package org.example.services;

import java.io.*;
import java.util.Scanner;

public class FileInputService implements Closeable {
    public FileInputService(String filename) throws FileNotFoundException {
        this.file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        in = new Scanner(fileInputStream);
    }
    public String readString(){
        String next = in.next().replace("\";\"", ";");
        next = next.substring(1, next.length() - 1);
        return next;
    }
    public boolean hasNext(){
        return in.hasNext();
    }

    private File file;
    private Scanner in;

    @Override
    public void close() throws IOException {
        in.close();
    }
}
