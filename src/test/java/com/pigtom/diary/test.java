package com.pigtom.diary;

import org.junit.Test;

import java.io.*;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/1 10:19 AM
 **/
public class test {
    @Test
    public void testWriteAFile() {
        String filename = System.getProperty("user.dir");
        File dir = new File(filename + "/b/c");
        if (!dir.mkdirs()) {
            System.out.println("make dir fail");
            return;
        }
        try {
            filename = dir.getCanonicalPath() + "/b.txt";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStream outputStream = new FileOutputStream(filename);
            try {
                outputStream.write("hello world".getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
