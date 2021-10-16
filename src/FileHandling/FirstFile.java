package FileHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FirstFile {
    public static void main(String[] args) {
//        File file=new File("db");
        try {
            FileWriter fw=new FileWriter("db/data.txt");

            fw.write("my name is manish");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
