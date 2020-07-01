/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;

import java.util.ArrayList;
import java.util.Scanner;

public class Traveller implements Comparable<Traveller>,java.io.Serializable {
    public String name;
    int age;
    public String transportation;
    public transient double current_lan;//Optional for business
    public transient double current_lon;
    private transient static int Ntravellers; 
    public String weather;
    public String museum;
    public String CafeRestaurantBar;
    public String landscape;
    public String cities;
    public String visit;
                                                   
    @Override
    public int compareTo(Traveller o) {                  
            return this.age - o.age;
    }
    
    public void Name_getter(Scanner input,Traveller o){
        int i=0;
        do {                    
            String name_traveller;
            System.out.println("What is your name?");
            Scanner scanner = new Scanner(System.in);
            name_traveller = input.next();
            if (!name_traveller.matches("[a-zA-Z_]+")) {
                System.out.println("Invalid name, please enter a correct one.");
            }else {
                 i++;
            }
            o.name = name_traveller;
        }while (i==0);        
    }
    
    public void Age_getter(Scanner input,Traveller o){
       int age_for_main;
        do {
            System.out.println("What is your age?");
            age_for_main =input.nextInt();
            if (age_for_main<=0 || age_for_main>100) {
                System.out.println("This is not an acceptable age, please give a correct one");
            }              
            o.age = age_for_main;
        }while (age_for_main<=0 || age_for_main>100);        
    }
    
    
    public Traveller(String name, int age, String transportation,double current_lan, double current_lon, String weather, String museum, String CafeRestaurantBar,String landscape, String cities,String visit) {
        this.name = name;
        this.age = age;
        this.transportation= transportation;
        this.current_lan = current_lan;
        this.current_lon = current_lon;
        this.weather = weather;
        this.museum = museum;
        this.CafeRestaurantBar = CafeRestaurantBar;
        this.landscape= landscape;
        this.cities=cities;
        this.visit=visit;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    Traveller() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double Similarity(City tempcity,ArrayList<String> criteria){
       double similarity=1;
       double sum_similarity ;
       
       //Transport                
        if(criteria.contains("Metro") ){
            try{
                if ( tempcity.city_transportation.contains("metro")){
                    similarity++;
                    
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
        } else if (criteria.contains("Bus")) {
            try{
                if ( tempcity.city_transportation.contains("bus")){
                    similarity++;                
       
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
           
        }else if(criteria.contains("Airport")){
            try{  
                if ( tempcity.city_transportation.contains("airport")){
                    similarity++;
               
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
        }        
        //WEATHER       
       if(criteria.contains("Clear") ){
            if ( tempcity.city_weather.contains("Clear")){
                similarity++;
            
                }else{
                    similarity--;
                }               
        } else if (criteria.contains("Clouds")) {            
                if ( tempcity.city_weather.contains("Clouds")){
                similarity++;
              
                }else{
                    similarity--;
                }                          
        }else if(criteria.contains("Snow")){             
                if ( tempcity.city_weather.contains("Snow")){
                similarity++;
           
                }else{
                    similarity--;
                }            
        }                          
               
        //MUSEUM
       if(criteria.contains("Archaeology museum") ){
            try{
                if ( tempcity.city_museum.contains("archaeology museum")){
                similarity++;
               
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
        } else if (criteria.contains("Art museum")) {
            try {
                if ( tempcity.city_museum.contains("art museum")){
                similarity++;
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
           
        }else if(criteria.contains("Military and war museum")){
            try{  
                if ( tempcity.city_museum.contains("military and war museum")){
                similarity++;
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
        }  
       
       //CAFERESTAURANTBAR
       if(criteria.contains("cafe") ){
            try{
                if ( tempcity.city_CafeRestaurantBar.contains("cafe")){
                similarity++;
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
        } else if (criteria.contains("restaurant")) {
            try{
                if ( tempcity.city_CafeRestaurantBar.contains("restaurant")){
                similarity++;
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
           
        }else if(criteria.contains("bar")){
            try{  
                if ( tempcity.city_CafeRestaurantBar.contains("bar")){
                similarity++;
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
        }  
        
       //LandScape
        if(criteria.contains("Valley") ){
            try{
                if ( tempcity.city_landscape.contains("valley")){
                similarity++;
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }
        } else if (criteria.contains("Mount")) {
            try{
                if ( tempcity.city_landscape.contains("Mount")){
                similarity++;
                }else{
                    similarity--;
                }
               
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);
            }
        }else if(criteria.contains("Desert")){
            try  {                
                if ( tempcity.city_landscape.contains("desert")){
                    similarity++;  
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);
            }
        } else if (criteria.contains("Forest")) {
            try{
                if ( tempcity.city_landscape.contains("forest")){
                similarity++;
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
           
        }else if(criteria.contains("Glacier")){
              
            try {
                if ( tempcity.city_landscape.contains("glacier")){
                similarity++;
                }else{
                    similarity--;
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name);            }    
        }         
        sum_similarity = similarity / (criteria.size());
        return sum_similarity;                   
    }

        public String CompareCities(ArrayList<City> cities,ArrayList<String> criteria){
        double similarity,user_max_similarity=-1;                      
        String tmp_city_string = null;
        for(int i=0;i < cities.size();i++){            
            similarity=Similarity(cities.get(i),criteria);
            if(user_max_similarity <= similarity){
                user_max_similarity =  similarity;
                tmp_city_string =  cities.get(i).city_name;                
            }            
        }        
        return  tmp_city_string;
        
    }
    
   public void CompareCities(Boolean weather){
       if(weather){
           System.out.printf("City contains rain and therefore is excluded from picking.\n");
       }else{             
           System.out.printf("The city has been added succesully to the list!\n");           
       }                      
    }    
    
    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMuseum() {
        return museum;
    }

    public void setMuseum(String museum) {
        this.museum = museum;
    }

    public  String getCafeRestaurantBar() {
        return CafeRestaurantBar;
    }

    public void setCafeRestaurantBar(String CafeRestaurantBar) {
        this.CafeRestaurantBar = CafeRestaurantBar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCurrent_lan() {
        return current_lan;
    }

    public void setCurrent_lan(double current_lan) {
        this.current_lan = current_lan;
    }

    public double getCurrent_lon() {
        return current_lon;
    }

    public void setCurrent_lon(double current_lon) {
        this.current_lon = current_lon;
    }
        
    public static int getNTravellers() {
        return Ntravellers;
    }

    public static void setNTravellers(int Ntravellers) {
        Traveller.Ntravellers = Ntravellers;
    }
    
    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }
    
    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }
    
    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }
    
    
    
    public ArrayList<String> returnCriteria(){
        ArrayList<String> criteria = new ArrayList<>();
        criteria.add(transportation);
        criteria.add(weather);
        criteria.add(museum);
        criteria.add(CafeRestaurantBar);
        criteria.add(landscape);
        return criteria;
    }

    @Override
    public String toString(){
        return "Traveller:\n "+"Name:" + name +"Age:" + age + "Transportation:"+transportation + "\n";
    }

}
