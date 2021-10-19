package Assignment.assignment1;

import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

class Company{
    private String companyName;
    private String designation;
    private String startDate;
    private String endDate;
    Company(String companyName,String designation,String startDate,String endDate){
        this.companyName=companyName;
        this.designation=designation;
        this.startDate=startDate;
        this.endDate=endDate;
    }

}

class Resume{
     private String name;
     private String email;
     private String phone;
     private double experience;
     private Company company;
     Resume(String name,String email,String phone,double experience,Company company){
         this.name=name;
         this.email=email;
         this.phone=phone;
         this.experience=experience;
         this.company=company;

     }

 }

public class JsonParsing {

    static void createResume(ArrayList<String> json,FileWriter fw){
        try {
            for(String s:json){
                fw.write(s);
                fw.write("\n");
            }


            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Resume Create successfully ........");
        }
    }
    static void readResume(File file){
        if(file.exists()){



            try {
                Scanner  dataReader = new Scanner(file);
                System.out.println("Reading ..................");
                while (dataReader.hasNextLine()){
                    String fileData = dataReader.nextLine();
                    System.out.println(fileData);
                }

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    static void validateExperience(File file){
        if(file.exists()){



            try {
                Scanner  dataReader = new Scanner(file);

                while (dataReader.hasNextLine()){
                    String fileData = dataReader.nextLine();
                    Object obj =new JSONParser().parse(fileData);
                    JSONObject jo = (JSONObject) obj;
                    double experience = (double) jo.get("experience");
                    Map company = ((Map)jo.get("company"));
                    String startDate=(String)company.get("startDate");
                    String endDate=(String)company.get("endDate");

                    SimpleDateFormat sdf
                            = new SimpleDateFormat(
                            "dd-MM-yyyy");
                    try{
                        Date d1 = sdf.parse(startDate);
                        Date d2 = sdf.parse(endDate);
                        long difference_In_Time
                                = d2.getTime() - d1.getTime();
                        double difference_In_Years
                                = (difference_In_Time
                                / (1000l * 60 * 60 * 24 * 365));

                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Company company1=new Company("Avizva","software trainee","20-8-2019","25-11-2021");
        Company company2=new Company("Avizva","software trainee","20-8-2018","25-11-2021");
        Resume resume =new Resume("Manish Chauhan","manish@gmail.com","123456789",2,company1);
        Resume resume1 =new Resume("Varun Chauhan","varun@gmail.com","123456789",5,company2);
        Gson gson = new Gson();
        String json = gson.toJson(resume);
        String json1 = gson.toJson(resume1);
//        FileWriter fw = new FileWriter("resume.txt");
//        ArrayList<String> arr=new ArrayList<>();
//        arr.add(json);
//        arr.add(json1);
//        createResume(arr,fw);

        File file =new File("resume.txt");
//        readResume(file);
          validateExperience(file);
    }
}
