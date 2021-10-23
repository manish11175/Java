package Assignment.assignment1;
import java.io.IOException;

import java.util.Scanner;


public class Logfile {

    static int sum(int a,int b){

        return a+b;


    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        try {

            Log my_log= new Log("log.txt");
           
            int x=scn.nextInt();
            my_log.logger.info("first input "+x);
            int y=scn.nextInt();
            my_log.logger.info("second input "+y);
            int add =sum(x,y);
            my_log.logger.info("The addition of two number is "+add);
            System.out.println(add);


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
