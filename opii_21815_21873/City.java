/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 *
 * @author jimge
 */
public class City {
    String city_name;
    String city_countrycode;
    String city_transportation;
    String city_museum;
    String city_CafeRestaurantBar;
    String city_weather;
    double city_lat;
    double city_lon;
    String city_landscape;  
    int city_pressure;
    int city_humidity;
    
    
    public City(String city_name,String city_countrycode, String city_transportation, String city_museum, String city_CafeRestaurantBar, String city_weather, double city_lat, double city_lon, String city_landscape,
                int pressure,int humidity) {
        this.city_name = city_name;
        this.city_countrycode = city_countrycode;
        this.city_transportation = city_transportation;
        this.city_museum = city_museum;
        this.city_CafeRestaurantBar = city_CafeRestaurantBar;
        this.city_weather = city_weather;
        this.city_lat = city_lat;
        this.city_lon = city_lon;
        this.city_landscape = city_landscape;
        this.city_pressure = pressure;
        this.city_humidity=humidity; 
        
    }
    
    
    @Override
    public boolean equals(Object o) { 
  
        if (o == this) { 
            return true; 
        } 
        
        if (!(o instanceof City)) { 
            return false; 
        } 
        City c =(City) o;
        
        return this.city_name.equalsIgnoreCase(c.city_name);             
    }            

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.city_name);
        return hash;
    }
    
    public void ExtractData(String cityname,String countrycode ) throws IOException{
        final String appid = "e27f01b38bd82529d49eaf427bb6d307";

        Map<String,ArrayList<String>> data = new HashMap();
        ArrayList<String> STR  = new ArrayList<>();
        ArrayList<String> STR2 = new ArrayList<>();
        
        OpenData opd = new OpenData();
        STR.clear();  
        System.out.print("Please wait while we try to retreive the necessary information...\n ");                    
        opd.RetrieveData(cityname,countrycode,appid);         
        data = opd.map;
        
        STR = data.get("WeatherAPI");
        STR2 = data.get("Wikipedia");
        
        if( STR==null || STR2 == null ){
            System.out.print("Failed to retreive the necessary data for given city.\n");
            return;        
        }
        
        this.city_name=cityname;
        this.city_weather = STR.get(0);        
        this.city_lat = Double.parseDouble(STR.get(1));
        this.city_lon = Double.parseDouble(STR.get(2));   
        //Προσθέτουμε πάνω στην δήλωση των μεταβλητών τις δύο νεες μεταβλητές και τις συπεριλαμβάνουμε και στον constructor.
        //Τους δίνουμε τις τιμές τους χρησιμοποιώντας την πληροφορία που προηγουμένως παραλάβαμε από το διαδίκτυο.
        //Επιδή η πληροφορία ήταν σε μορφή κειμένου απλώς την μετατρέπουμε σε ακέραιους αριθμούς
        this.city_pressure=Integer.parseInt(STR.get(3));
        this.city_humidity=Integer.parseInt(STR.get(4));
         
      
        if (STR2.get(1).contains("Culture")){
            this.city_museum = STR2.get(1).substring(STR2.get(1).indexOf("Museum"),STR2.get(1).lastIndexOf("Museum"));
        }else if(STR2.get(1).contains("Museums")){
            this.city_museum = STR2.get(1).substring(STR2.get(1).indexOf("museums"));
        }else if(STR2.get(1).contains("landmarks")){
             this.city_museum = STR2.get(1).substring(STR2.get(1).indexOf("museum"));
        }else{
            System.out.print("There is no option for museums\n");
            this.city_museum = null;
        } 
                    
        if (STR2.get(1).contains("Transportartion")){
            this.city_transportation = STR2.get(1).substring(STR2.get(1).indexOf("Transportation"),STR2.get(1).lastIndexOf("airport"));
        }else if(STR2.get(1).contains("Transport")){
            this.city_transportation = STR2.get(1).substring(STR2.get(1).indexOf("Transport"));
        }else{
            System.out.print("There is no option for Transportation\n");
            this.city_transportation = null;
        } 
         
        if(STR2.get(1).contains("reastaurant")){
            this.city_CafeRestaurantBar = STR2.get(1).substring(STR2.get(1).indexOf("restaurants")/*,STR.get(4).lastIndexOf("bars")*/);            
        }else if(STR2.get(1).contains("cafe")){
            this.city_CafeRestaurantBar = STR2.get(1).substring(STR2.get(1).indexOf("cafe")/*,STR.get(4).lastIndexOf("bars")*/);            
        }else if(STR2.get(1).contains("bar")){
            this.city_CafeRestaurantBar = STR2.get(1).substring(STR2.get(1).indexOf("bar")/*,STR.get(4).lastIndexOf("bars")*/);                                        
        }else{
            System.out.print("There is no option for cafes or restaurants or bars\n");
            this.city_CafeRestaurantBar = null;
        }
          
        if(STR2.get(1).contains("Geography")){
            this.city_landscape = STR2.get(1).substring(STR2.get(1).indexOf("Geography"),STR2.get(1).lastIndexOf("Climate")); 
        }else if(STR2.get(1).contains("Plain")){
            this.city_landscape = STR2.get(1).substring(STR2.get(1).indexOf("Plain"),STR2.get(1).lastIndexOf("Climate"));                                          
        }else if(STR2.get(1).contains("Topography")){
            this.city_landscape = STR2.get(1).substring(STR2.get(1).indexOf("Topography"),STR2.get(1).lastIndexOf("Climate"));                                          
        }else{
            System.out.print("There is no option for landscapes\n");
            this.city_landscape = null;
        }  
        System.out.println ("Success, city was found and data were retreived!");                                                                                         
    }    
        
    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_Transportation() {
        return city_transportation;
    }

    public void setCity_Transportation(String city_transportation) {
        this.city_transportation = city_transportation;
    }

    public String getCity_museum() {
        return city_museum;
    }

    public void setCity_museum(String city_museum) {
        this.city_museum = city_museum;
    }

    public String getCity_CafeRestaurantBar() {
        return city_CafeRestaurantBar;
    }

    public void setCity_CafeRestaurantBar(String city_CafeRestaurantBar) {
        this.city_CafeRestaurantBar = city_CafeRestaurantBar;
    }

    public String getCity_weather() {
        return city_weather;
    }

    public void setCity_weather(String city_weather) {
        this.city_weather = city_weather;
    }

    public double getCity_lat() {
        return city_lat;
    }

    public void setCity_lat(double city_lat) {
        this.city_lat = city_lat;
    }

    public double getCity_lon() {
        return city_lon;
    }

    public void setCity_lon(double city_lon) {
        this.city_lon = city_lon;
    }

    public String getCity_landscape() {
        return city_landscape;
    }

    public void setCity_landscape(String city_landscape) {
        this.city_landscape = city_landscape;
    }

    public String getCity_countrycode() {
        return city_countrycode;
    }

    public void setCity_countrycode(String city_countrycode) {
        this.city_countrycode = city_countrycode;
    }                                          
    
    public int getCity_pressure() {
        return city_pressure;
    }

    public void setCity_pressure(int city_pressure) {
        this.city_pressure = city_pressure;
    }

    public int getCity_humidity() {
        return city_humidity;
    }

    public void setCity_humidity(int city_humidity) {
        this.city_humidity = city_humidity;
    }
}
