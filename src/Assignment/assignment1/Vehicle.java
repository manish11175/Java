package Assignment.assignment1;


import java.io.*;
import java.util.*;

import java.lang.Math;

class Car{
     String make;
     String manufacturer;
     int horsePower;
     int mileage;
     int passengerCapacity;

     public Car(String make,String manufacturer,int horsePower,int mileage,int passengerCapacity){
         this.make=make;
         this.manufacturer=manufacturer;
         this.horsePower=horsePower;
         this.mileage=mileage;
         this.passengerCapacity=passengerCapacity;


     }
     public void display(){
         System.out.println(" make -> "+make+", manufacturer -> "+manufacturer+", horse power -> "+horsePower+
                 ", mileage ->"+mileage+", passegen ->"+passengerCapacity);
     }

 }
public class Vehicle {

    static void swap(Car[] arr, int i, int j)
    {
        Car temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    static int partition(Car [] arr, int low, int high)
    {

        // pivot
        Car pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            // If current element is smaller
            // than the pivot
            // formula for comparing  horsePower+mileage/passengerCapacity
            if (arr[j].horsePower+arr[j].mileage/arr[j].passengerCapacity < pivot.horsePower+pivot.mileage/pivot.passengerCapacity)
            {


                i++;
                swap(arr, i, j);
            }


        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
    static void quickSort(Car cars[],int low,int high){
          if(low<high){
              int pi=partition(cars,low,high);
              quickSort(cars,low,pi-1);
              quickSort(cars,pi+1,high);
          }
    }
    public static void storeData(Car cars[]){
        try {
            FileWriter fw=new FileWriter("cars.txt");
            for(Car c:cars){
                fw.write(c.make+" "+c.manufacturer+" "+c.horsePower+" "+c.mileage+" "+c.passengerCapacity);
                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Data overwrite successfully ........");
        }
    }
    public static void main(String[] args) {

        int passengerList[]={2,4,6,8,10};
        String manufacturerList[]={ "ford", "hyundai", "tata", "Maruti"};
        String makeList[]={"Acura","Audi","Reo","Dina","farari","mustang"};
        int numberOfCar=100;
        Car cars[]=new Car[numberOfCar];


      // creating the cars objects
        for(int i=0;i<numberOfCar;i++){
            String make=makeList[(int)(Math.random()* makeList.length)];
            String manufacturer=manufacturerList[(int)(Math.random()* manufacturerList.length)];
            int horsepower = (int)(Math.random()*(10-1+1)+1);
            int mileage= (int)(Math.random()*(30-1+1)+1);
            int passenger=passengerList[(int)(Math.random()* passengerList.length)];
            cars[i]=new Car(make,manufacturer,horsepower,mileage,passenger);
        }
        //System.out.println("Before Sortin-------------");
//       for(Car c:cars){
//           c.display();
//       }
//        quickSort(cars,0,cars.length-1);
//        System.out.println("After sorting using quickSort---------");
//       for(Car c:cars){
//           c.display();
//       }

        // Store the cars data into cars.txt
          storeData(cars);


        // read data from cars.txt and print average mileage per cars manufactures

        File file =new File("cars.txt");

        if(file.exists()){


            try {
                Scanner  dataReader = new Scanner(file);
                HashMap<String,ArrayList<Integer>> map =new HashMap<>();
                while (dataReader.hasNextLine()) {
                    String fileData = dataReader.nextLine();
                    String arr[]=fileData.split(" ");

                    int mileage=Integer.parseInt(arr[3]);

                    if(map.containsKey(arr[1])){
                        ArrayList<Integer> arrlist=new ArrayList<>();
                        arrlist.add(map.get(arr[1]).get(0)+1); // increase the number of cars belong to manufacture
                        arrlist.add(map.get(arr[1]).get(1)+mileage); // sum of mileage of cars
                        map.put(arr[1],arrlist);
                    }
                    else{
                        ArrayList<Integer> arrlist=new ArrayList<>();
                        arrlist.add(1);
                        arrlist.add(mileage);
                        map.put(arr[1],arrlist);

                    }



                }
                System.out.println(map);
                // mileage per manufacture car

               for(String manufacture:map.keySet()){
                   double avg=(double)map.get(manufacture).get(1)/map.get(manufacture).get(0);
                   System.out.println("average of "+manufacture+" "+avg);
               }
                dataReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        }



    }
}
