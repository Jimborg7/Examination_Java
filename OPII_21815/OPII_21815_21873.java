/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.sql.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import static opii_21815_21873.Database_Cities.*;
import static opii_21815_21873.GUI.frame;


public class OPII_21815_21873 {
                                       
     static void clear_Arraylists(ArrayList<String> cityname,ArrayList<String> countrycode){
                cityname.clear();
                countrycode.clear();                            
    }
     
    public static  void create_File(String filename){
        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("File created succesfully: " + file.getName());
                
            } else {   
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("error" + e);
            
        }        
    }                 
    
    public static void save_Travellers(List<Traveller> Collection_List_of_Travellers, String filename){       
            create_File(filename);                               
            try {
                FileOutputStream out = new FileOutputStream(filename);
                try(ObjectOutputStream traveller_output = new ObjectOutputStream(out);){               
                for(Traveller t : Collection_List_of_Travellers){                    
                    try {
                      
                          traveller_output.writeObject(t);
                    }catch (NotSerializableException e) {
                           System.err.println("An object was not serializable, it has not been saved.");
                       }                                      
                    }                                                            
                    System.out.print("Travellers have been saved!\n"); 
                    traveller_output.flush();
                    traveller_output.close();
                }catch (IOException e) {
                    System.err.println("error"+e);
                }                                                       
            }catch (FileNotFoundException e) {
                System.err.println("error"+e);
            }       
    }    
        
    public static void load_Travellers(Set<Traveller> Collection_of_Travvellers , String filename) throws ClassNotFoundException{
        boolean to_read = true;                
        try{
            FileInputStream in = new FileInputStream(filename);                         
                try( ObjectInputStream traveller_input = new ObjectInputStream(in);){
                    while(to_read){                       
                        try{                            
                            Traveller t1 = (Traveller)traveller_input.readObject();
                            
                            if(t1 != null){
                                Collection_of_Travvellers.add(t1);
                            }else{
                                to_read = false;                                                      
                            }                                                      
                        }catch(EOFException | ClassCastException e){
                            traveller_input.close();  
                            System.out.print("Succesfully loaded the collection for travellers.\n");
                            break;                            
                        }                       
                    }
                }catch (IOException e) {                    
                    System.err.print("File is empty so loading did not occur.\n");
                }                       
        }catch (FileNotFoundException e) {            
            System.err.println("File does not exits. File will be created later when save is enabled.");
       }        
    }                        
    
    public static void main(String[] args) throws IOException{
        GUI gui = new GUI();
        gui.createAndShowGUI();
    }
}    
        //Connection conn = connectionToDatabase(); 
        /*City candidate_city = new City(null,null,null,null,null,null,0.0,0.0,null); 
        
        System.out.print("---------------------------------------------------\n");
        System.out.print("Welcome to the best Traveller Destination program!\n");
        System.out.print("---------------------------------------------------\n");
                            
        ArrayList<String> cityname = new ArrayList();
        ArrayList<String> countrycode = new ArrayList();        
        ArrayList<Integer> ucriteria = new ArrayList();
        ArrayList<String> criteria = new ArrayList();                
        ArrayList<City> cities = new ArrayList();                                
        ArrayList<City> cities_excluded = new ArrayList<>();
        Set<Traveller> Collection_of_Travellers = new HashSet<>();
        List<Traveller> Collection_List_of_Travellers = null;*/
   
               
        /*try{
            if(create_File()==1){
                load_Travellers(Collection_of_Travellers);   
                Iterator support = Collection_of_Travellers.iterator(); 
                     /*while(support.hasNext()){                
                     System.out.println(support.next());                
                     }   
                System.out.println("File already exists.");            
            }      
                                                    
        }catch(ClassNotFoundException e){        
            e.printStackTrace();
        }*/      
        
        //selectToDatabase(conn,cities);
       
        
        /*String city=null;
        int age;
        int citynum = 0;
        int acity=0;
        char c;
        int count_travellers=0;
       
        while (true) {
            count_travellers++;            
            System.out.println("Please pick a number\n1. Traveller\n2. Buisiness\n3. Tourist\n4. Free Ticket \n");
            System.out.print("Your choice:");            
            Scanner input = new Scanner(System.in);                                                     
            
        int type;
        type = input.nextInt();             
        switch (type) {   
            case 1:    
                Traveller t1 = new Traveller(null,0,null,0,0,null,null,null,null,null,null);
                System.out.println("Traveller");
                int i=0;                
                t1.Name_getter(input,t1);
                t1.Age_getter(input, t1);                                                                                                                 
              
                do {      
                    
                    System.out.println("Please enter a city you would like to visit following by the countrys 2 letter country code in the following format: athens, gr.(You must enter at least two cities.)");                                                 
                    city = input.next();
                    if(city.indexOf(',')==-1){
                        System.out.print("The format is City,AB(country code)\n");
                        break;                    
                    }
                    
                    String[] cityList = city.split(",");                    
                    cityname.add(cityList [0]);                
                    countrycode.add(cityList [1]);                                                             
                    candidate_city = new City(cityname.get(citynum),countrycode.get(citynum),null,null,null,null,0.0,0.0,null);  
                    
                    if(cities.isEmpty()){                                                        
                            candidate_city.ExtractData(cityname.get(citynum),countrycode.get(citynum));                                                    
                            System.out.print("You have entered the country code correctly\n");
                            if(candidate_city.city_weather.equals("Rain")){                              
                                    t1.CompareCities(Boolean.TRUE);                                    
                                    cities_excluded.add(new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape));                                                        
                                    citynum++; 
                            }else{
                                t1.CompareCities(Boolean.FALSE);        
                                cities.add( new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape));                                                     
                                citynum++; 
                            }                               
                    }else{                        
                        System.out.print("You have entered the city corrrectly\n");
                        System.out.println(candidate_city.city_name);  
                        Iterator<City> value = cities.iterator();   
                        while (value.hasNext()){                                   
                            if(!((candidate_city.equals(value.next())))){                                    
                                candidate_city.ExtractData(cityname.get(citynum),countrycode.get(citynum));
                                if(candidate_city.city_weather.equals("Rain")){
                                    t1.CompareCities(Boolean.TRUE);
                                    cities_excluded.add(candidate_city);
                                    cities_excluded.add(new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape));                                                                                           
                                }else{                                      
                                    t1.CompareCities(Boolean.FALSE);        
                                    cities.add( new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape));                                                         
                                }                                        
                                citynum++; 
                                break; 
                            }else{      
                                System.out.print("The city inserted already exists.\n");                                                               
                                break;
                            }                                  
                        }                                                 
                    }
                    
                    System.out.println("Press 1 to enter another city or 2 to continue.");                               
                    acity = input.nextInt();
                    if (citynum < 2 ) {                
                        
                        acity = 1;
                    }                           
                }while(acity == 1);                                
                
                for(i=0;i<citynum;i++){
                    t1.cities += cityname.get(i);
                }               
                int critnum;
                int c1num;                                                                
                int acrit;
                do {    
                    System.out.println("These are the available criteria:\n1. Transportation\n2. Weather\n3. Museum\n4. CafeRestaurantBar\n5. Landscape");
                    System.out.println("Pick a number according to the criteria you would like to be used");
                    critnum = input.nextInt();
                    switch( critnum ) {
                        case 1:                            
                            if(ucriteria.contains(1)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(1);
                                System.out.println("Transportation:\n1. Metro\n2. Bus\n3. Airport");
                                System.out.println("Pick a number to choose");                                
                                c1num = input.nextInt();
                                switch (c1num) {
                                    case 1:
                                        t1.transportation = "Metro";
                                        criteria.add("Metro");
                                        break;
                                    case 2:
                                        t1.transportation = "Bus";
                                        criteria.add("Bus");
                                        break;
                                    case 3:         
                                        t1.transportation = "Airport";
                                        criteria.add("Airport");
                                        break;
                                    default:
                                        System.out.println("PLease pick a number between 1-3!");
                                        break;
                                }
                            }                                                       
                            break;
                        case 2:                                                        
                            if(ucriteria.contains(2)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(2);
                                System.out.println("Weather:\n1. Clouds\n2. Clear\n3. Rain\n4. Snow");
                                System.out.println("Pick a number to choose");                         
                                c1num = input.nextInt();                                
                                switch (c1num) {
                                    case 1:
                                        t1.weather = "Clouds";
                                        criteria.add("Clouds");
                                        break;
                                    case 2:
                                        t1.weather = "Clear";
                                        criteria.add("Clear");
                                        break;
                                    case 3:
                                        t1.weather = "Rain";
                                        criteria.add("Rain");
                                        break;
                                    case 4:
                                        t1.weather = "Snow";
                                        criteria.add("Snow");
                                    default:
                                        System.out.println("PLease pick a number between 1-4!");
                                        break;
                                }                                
                            }                            
                            break;
                        case 3:                            
                            if(ucriteria.contains(3)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(3);
                                System.out.println("Museum:\n1. Archaeology museum\n2. Art museum\n3. Military and war museum");
                                System.out.println("Pick a number to choose");                                
                                c1num = input.nextInt();
                                switch (c1num) {
                                    case 1:
                                        t1.museum = "Archaeology museum";
                                        criteria.add("Archaeology museum");
                                        break;
                                    case 2:
                                        t1.museum = "Art museum";
                                        criteria.add("Art museum");
                                        break;
                                    case 3:
                                         t1.museum = "Military and war museum";
                                        criteria.add("Military and war museum");
                                        break;
                                    default:
                                        System.out.println("PLease pick a number between 1-3!");
                                        break;
                                }                                                                
                            }                            
                            break;
                        case 4:                            
                            if(ucriteria.contains(4)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(4);
                                System.out.println("CafeRestaurantBar:\n1. cafe\n2. restaurant\n3. bar");
                                System.out.println("Pick a number to choose");                                
                                c1num = input.nextInt();
                                switch (c1num) {
                                    case 1:
                                        t1.CafeRestaurantBar = "cafe";
                                        criteria.add("cafe");
                                        break;
                                    case 2:
                                        t1.CafeRestaurantBar = "restaurant";
                                        criteria.add("restaurant");
                                        break;
                                    case 3:
                                        t1.CafeRestaurantBar = "bar";
                                        criteria.add("bar");
                                        break;
                                    default:
                                        System.out.println("PLease pick a number between 1-3!");
                                        break;
                                }                                
                            }                            
                            break;
                        case 5:                        
                            if(ucriteria.contains(5)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(5);
                                System.out.println("Landscapes:\n1. Valley\n2. Mount\n3. Desert\n4. Forest\n5. Glacier");
                                System.out.println("Pick a number to choose");                                
                                c1num = input.nextInt();
                                switch (c1num) {
                                    case 1:
                                        t1.landscape = "Valley";
                                        criteria.add("Valley");
                                        break;
                                    case 2:
                                        t1.landscape = "Mount";
                                        criteria.add("Mount");
                                        break;
                                    case 3:
                                        t1.landscape = "Desert";
                                        criteria.add("Desert");
                                        break;
                                    case 4:
                                        t1.landscape = "Forest";
                                        criteria.add("Forest");
                                        break;
                                    case 5:
                                        t1.landscape = "Glacier";
                                        criteria.add("Glacier");
                                        break;
                                    default:
                                        System.out.println("PLease pick a number between 1-5!");
                                        break;
                                }                                                                
                            }                            
                            break;                        
                        default:                         
                            System.out.println("Please enter a number between 1-5!");
                            break;
                    }                    
                    do {
                        System.out.println("Press 1 to choose another criteria or press 2 to continue.");
                        acrit =input.nextInt();
                        if (acrit != 1 && acrit != 2) {
                            System.out.println("This is not an acceptable answer, please pick between 1 and 2.");                            
                        }                        
                    }while (acrit != 1 && acrit != 2);
                    
                }while (acrit != 2);
                
                Collection_of_Travellers.add(t1);                          
                String Answer = t1.CompareCities(cities,criteria); 
                t1.setVisit(Answer);
                System.out.print("\nThe city that best matches your choice, based on given criteria, is: \n************************\n"+Answer+"\n");                                                                                                                
               
                clear_Arraylists(cityname,countrycode);
                /*Traveller t2 =  (new Traveller("Jimborg",19,null,0,0,null,null,null,null,null,null) ); 
                Traveller t3 =  (new Traveller("Jimborg",61,null,0,0,null,null,null,null,null,null) ); 
                Traveller t4 =  (new Traveller("Jimborg",34,null,0,0,null,null,null,null,null,null) ); 
                Traveller t5 =  (new Traveller("Jimborg",55,null,0,0,null,null,null,null,null,null) ); 
                Traveller t6 =  (new Traveller("Jimborg",20,null,0,0,null,null,null,null,null,null) ); 
                Traveller t7 =  (new Traveller("Jimborg",34,null,0,0,null,null,null,null,null,null) ); 
                                                                                                                        
                Collection_of_Travellers.add(t2);                
                Collection_of_Travellers.add(t3);               
                Collection_of_Travellers.add(t4);
                Collection_of_Travellers.add(t5);                
                Collection_of_Travellers.add(t6);       
                Collection_of_Travellers.add(t7);*/  	                        
                
                /*Collection_List_of_Travellers = Collection_of_Travellers.stream().collect(Collectors.toList());                
                for(i=0;i<Collection_List_of_Travellers.size();i++){
                    for(int j=i+1;j<Collection_List_of_Travellers.size();j++){
                        if(Collection_List_of_Travellers.get(i).age == Collection_List_of_Travellers.get(j).age && Collection_List_of_Travellers.get(i).name.equals(Collection_List_of_Travellers.get(j).name)){
                            Collection_List_of_Travellers.remove(i);
                        }
                    }                    
                }    
                
                Collections.sort(Collection_List_of_Travellers);
                for(int j=0;j < Collection_List_of_Travellers.size();j++){
                    System.out.println(j + ") " + Collection_List_of_Travellers.get(j).name + "  "+Collection_List_of_Travellers.get(j).age);                    
                }  
                break;
            case 2:
                System.out.println("Business");                        
                Business businessman = new Business(null,0,null,0.0,0.0,null,null,null,null,null,null);
                i=0;
                businessman.Name_getter(input, businessman);
                businessman.Age_getter(input, businessman);
                              
                System.out.print("Please provide the unit of latitude and longtitude (such as Kilometers and Nautic miles).");
                Scanner kilo_nautic = new Scanner(System.in);
                String unit=kilo_nautic.next();
                criteria.add(unit);
                System.out.println("Please provide your coordinates:\n"+"First Latitude:");
                double coords =input.nextDouble();
                businessman.current_lan = coords;
                System.out.println("Now provide the Longtitude:");
                coords = input.nextDouble();
                businessman.current_lon = coords;                                                              
                                        
                do {
                                 
                    System.out.println("Please enter a city you would like to visit following by the countrys 2 letter country code in the following format: athens, gr.");        
                                        
                    city = input.next();
                    if(city.indexOf(',')==-1){
                        System.out.print("the format is City,AB(country code)\n");
                        break;
                    
                    }
                    String[] cityList = city.split(",");
                    
                    cityname.add(cityList [0]);
                    try{
                        countrycode.add(cityList [1]);
                        System.out.print("You hsve entered the country code corrrectly\n");

                    }catch(NullPointerException e){
                        System.out.println("Exception caught in Catch block"); 
                    
                    }
                    
                   
                    candidate_city.ExtractData(cityname.get(citynum),countrycode.get(citynum));                    
                    

                    if(candidate_city.city_weather.equals("Rain")){
                            businessman.CompareCities(Boolean.TRUE);
                            cities_excluded.add(candidate_city);
                            cities_excluded.add(new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape));                    
                            citynum++; 
                    }else{
                        businessman.CompareCities(Boolean.FALSE);        
                        cities.add( new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape));                    
                        citynum++;  
                    }                                                           
                    System.out.println("Press 1 to enter another city or 2 to continue.");            
                    //acity=0;
                    acity = input.nextInt();
                    if (citynum < 2 ) {
                     
                        System.out.println("Please enter at least two cities.");
                        acity = 1;
                    }                            
                } while(acity == 1);                                                                                                      
                               
                //Collection_of_Travvellers.add(businessman);
                String BAnswer = businessman.CompareCities(cities,criteria); 
                System.out.print("The city that best matches your choice, based on given criteria, is: \n************************\n"+BAnswer+"\n");
                businessman.setVisit(BAnswer);
                clear_Arraylists(cityname,countrycode);
                break;
                
            case 3:    
               System.out.println("Tourist");
               Tourist tourist = new Tourist(null,0,null,0.0,0.0,null,null,null,null,null,null);                
               tourist.Name_getter(input, tourist);
               tourist.Age_getter(input, tourist);
               
                do {
                                 
                    System.out.println("Please enter a city you would like to visit following by the countrys 2 letter country code in the following format: athens, gr.");        
                                         
                    city = input.next();
                    if(city.indexOf(',')==-1){
                        System.out.print("the format is City,AB(country code)\n");
                        break;
                    
                    }
                    String[] cityList = city.split(",");
                    
                    cityname.add(cityList [0]);
                    try{
                        countrycode.add(cityList [1]);
                        System.out.print("You hsve entered the country code corrrectly\n");

                    }catch(NullPointerException e){
                        System.out.println("Exception caught in Catch block"); 
                    
                    }
                
                    candidate_city.ExtractData(cityname.get(citynum),countrycode.get(citynum));                    
                    if(candidate_city.city_weather.equals("Rain")){
                            tourist.CompareCities(Boolean.TRUE);
                            cities_excluded.add(candidate_city);
                            cities_excluded.add(new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape));                    
                            citynum++; 
                    }else{
                        tourist.CompareCities(Boolean.FALSE);        
                        cities.add( new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape));                    
                        citynum++;  
                    }                    
                    System.out.println("Press 1 to enter another city or 2 to continue.");            
                    //acity=0;
                    acity = input.nextInt();
                    if (citynum < 2 ) {
                
                        System.out.println("Please enter at least two cities.");
                        acity = 1;
                    }                            
                } while(acity == 1);                                
                
              System.out.println("These are the available criteria:\n1. Transportation\n2. Weather\n3. Museum\n4. CafeRestaurantBar\n5. Landscape");
                System.out.println("Pick a number according to the criteria you would like to be used");
                acrit=0;
                do {
                    
                    critnum = input.nextInt();
                    switch( critnum ) {
                        case 1:
                            
                            if(ucriteria.contains(1)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(1);
                                System.out.println("Transportation:\n1. Metro\n2. Bus\n3. Airport");
                                System.out.println("Pick a number to choose");
                                
                                c1num = input.nextInt();
                                switch (c1num) {
                                    case 1:
                                        tourist.transportation = "Metro";
                                        criteria.add("Metro");
                                        break;
                                    case 2:
                                        tourist.transportation = "Bus";
                                        criteria.add("Bus");
                                        break;
                                    case 3:         
                                        tourist.transportation = "Metro";
                                        criteria.add("Airport");
                                        break;
                                    default:
                                        System.out.println("PLease pick a number between 1-3!");
                                        break;
                                }
                            }
                                                       
                            break;
                        case 2:
                                                        
                            if(ucriteria.contains(2)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(2);
                                System.out.println("Weather:\n1. Clouds\n2. Clear\n3. Rain\n4. Snow");
                                System.out.println("Pick a number to choose");
                                //c1num = 0;
                                c1num = input.nextInt();                                
                                switch (c1num) {
                                    case 1:
                                        tourist.weather = "Clouds";
                                        criteria.add("Clouds");
                                        break;
                                    case 2:
                                        tourist.weather = "Clear";
                                        criteria.add("Clear");
                                        break;
                                    case 3:
                                        tourist.weather = "Rain";
                                        criteria.add("Rain");
                                        break;
                                    case 4:
                                        tourist.weather = "Snow";
                                        criteria.add("Snow");
                                    default:
                                        System.out.println("PLease pick a number between 1-4!");
                                        break;
                                }                                
                            }                            
                            break;
                        case 3:                            
                            if(ucriteria.contains(3)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(3);
                                System.out.println("Museum:\n1. Archaeology museum\n2. Art museum\n3. Military and war museum");
                                System.out.println("Pick a number to choose");
                                
                                c1num = input.nextInt();
                                switch (c1num) {
                                    case 1:
                                        tourist.museum = "Archaeology museum";
                                        criteria.add("Archaeology museum");
                                        break;
                                    case 2:
                                         tourist.museum = "Art museum";
                                        criteria.add("Art museum");
                                        break;
                                    case 3:
                                         tourist.museum = "Military and war museum";
                                        criteria.add("Military and war museum");
                                        break;
                                    default:
                                        System.out.println("PLease pick a number between 1-3!");
                                        break;
                                }                                                                
                            }                            
                            break;
                        case 4:                            
                            if(ucriteria.contains(4)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(4);
                                System.out.println("CafeRestaurantBar:\n1. cafe\n2. restaurant\n3. bar");
                                System.out.println("Pick a number to choose");
                                
                                c1num = input.nextInt();
                                switch (c1num) {
                                    case 1:
                                        tourist.CafeRestaurantBar = "cafe";
                                        criteria.add("cafe");
                                        break;
                                    case 2:
                                        tourist.CafeRestaurantBar = "restaurant";
                                        criteria.add("restaurant");
                                        break;
                                    case 3:
                                        tourist.CafeRestaurantBar = "bar";
                                        criteria.add("bar");
                                        break;
                                    default:
                                        System.out.println("PLease pick a number between 1-3!");
                                        break;
                                }                                
                            }                            
                            break;
                        case 5:
                        
                            if(ucriteria.contains(5)) {
                                System.out.println("You can not choose the same criteria twice.");
                            } else {
                                ucriteria.add(5);
                                System.out.println("Landscapes:\n1. Valley\n2. Mount\n3. Desert\n4. Forest\n5. Glacier");
                                System.out.println("Pick a number to choose");
                                
                                c1num = input.nextInt();
                                switch (c1num) {
                                    case 1:
                                        tourist.landscape = "Valley";
                                        criteria.add("Valley");
                                        break;
                                    case 2:
                                        tourist.landscape = "Mount";
                                        criteria.add("Mount");
                                        break;
                                    case 3:
                                        tourist.landscape = "Desert";
                                        criteria.add("Desert");
                                        break;
                                    case 4:
                                        tourist.landscape = "Forest";
                                        criteria.add("Forest");
                                        break;
                                    case 5:
                                        tourist.landscape = "Glacier";
                                        criteria.add("Glacier");
                                        break;
                                    default:
                                        System.out.println("PLease pick a number between 1-5!");
                                        break;
                                }                                                                
                            }                            
                            break;                        
                        default:                         
                            System.out.println("Please enter a number between 1-5!");
                            break;
                    }                                        
                    do {
                        System.out.println("Press 1 to choose another criteria or press 2 to continue.");
                        acrit =input.nextInt();
                        if (acrit != 1 && acrit != 2) {
                            System.out.println("This is not an acceptable answer, please pick between 1 and 2.");                            
                        }                        
                    }while (acrit != 1 && acrit != 2);                    
                }while (acrit != 2);        

                //Collection_of_Travvellers.add("Tourist - "+count_travellers+"):" +tourist.getAge()+tourist.getCafeRestaurantBar()+tourist.getLandscape()+tourist.getMuseum()+tourist.getName()+tourist.getTransportation()+tourist.getWeather()+t1.getCities()+tourist.getCurrent_lan()+tourist.getCurrent_lon());
                //Collection_of_Travvellers.add(tourist);
                String TAnswer = tourist.CompareCities(cities,criteria); 
                System.out.print("The city that best matches your choice, based on given criteria, is: \n************************\n"+TAnswer+"\n");               
                tourist.setVisit(TAnswer);
                clear_Arraylists(cityname,countrycode);                
                break;
            case 4:
                ArrayList<City> Cities = new ArrayList();
                ArrayList<String> cityname3 = new ArrayList();
                ArrayList<String> countrycode3 = new ArrayList();
                ArrayList<String> Criteria = new ArrayList();
                //Gia to 3 free ticket!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
                Traveller[] Ntravellers = new Traveller[4];
                
                Ntravellers[0]= new Business("Nikos",19,"Metro",37.98,27.92,"Clouds","Archeological museums","cafe","Glacier","London,UK Athens,GR",null);
                Ntravellers[1]= new Traveller("Eleni",44,"Airport",37.03,22.11,"Clear","Archeological museums",null,"Desert","Cairo,EG Barcelona,ES",null);
                Ntravellers[2]= new Tourist("Panos",21,"Airport",24.98,23.54,"Clouds","Military and war museums","bar","Mount","Madrid,ES Casablanca,MA",null);
                Ntravellers[3]= new Tourist("Maria",36,"Airport",40.64,22.94,"Snow","Art museums",null,"Mount","Berlin,DE Cairo,EG",null);           
                final String free = "Cairo,EG";
                cityname3.add("Cairo"); 
                countrycode3.add("Eg");   
                City candidate = new City(null,null,null,null,null,null,0.0,0.0,null);
                candidate.ExtractData(cityname3.get(0),countrycode3.get(0)); 
                Cities.add( new City(candidate.city_name,candidate.city_countrycode,candidate.city_transportation,candidate.city_museum,candidate.city_CafeRestaurantBar,candidate.city_weather,candidate.city_lat,candidate.city_lon,candidate.city_landscape));                                                                                       
                int position=0;         
                double max_traveller =-1.0,user;   
                for(int k=0;k<Ntravellers.length;k++){ 
                        System.out.println(k);
                        String[] substring = Ntravellers[k].getCities().split(" ");  
                        if(substring[0].contains(free)||substring[1].contains(free)){                                                                                   
                            Criteria.add(Ntravellers[k].getTransportation()+ Ntravellers[k].getWeather()+ Ntravellers[k].getMuseum()+Ntravellers[k].CafeRestaurantBar+Ntravellers[k].getLandscape());                                                                                                                
                            user=Ntravellers[k].Similarity(Cities.get(0), Criteria); 
                            if(max_traveller <= user){                                  
                                max_traveller = user;
                                position=k;
                            }                            
                        }                                    
                }                         
                System.out.println("The winner is: "+Ntravellers[position].name);                
                break;
            }   
        
       /* try {
            System.out.print("Please wait while we are adding the newly inserted cities in our database\n");
            for(int k=0; k<cities.size();k++){
               insertToDatabase(conn,cities.get(k));                
            }
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }*/
        
        //save_Travellers(Collection_List_of_Travellers);                                 
        /*System.out.print("Keep using the program?\nPress y or Y to keep using the program.");
        Scanner decide = new Scanner (System.in);
        c = decide.next().charAt(0);                        
        if(!(c == 'y'|c == 'Y')){                                    
            System.out.print("Program will now terminate...\nThank you for using us!\n");
            break;
        }        
       }   */                                       
 
                  
        

