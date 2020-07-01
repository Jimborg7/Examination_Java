package opii_21815_21873;

import java.io.IOException;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

/**City description and weather information using OpenData with Jackson JSON processor.
* @since 29-2-2020
* @version 1.0
* @author John Violos */
public class OpenData {
    Map<String,ArrayList<String>> map = new HashMap(); 
    ArrayList <String> str = new ArrayList(); 
    ArrayList <String> str2 = new ArrayList();         
    String city,country;
    String appid;

    ObjectMapper mapper = new ObjectMapper();
/**Retrieves weather information, geotag (lan, lon) and a Wikipedia article for a given city.
* @param city_na The Wikipedia article and OpenWeatherMap city. 
* @param country_co The country initials (i.e. gr, it, de). 
* @param Appid Your API key of the OpenWeatherMap. 
     * @return  
     * @throws java.io.IOException*/ 
    
 public Map RetrieveData(String city_na,String country_co,String Appid) throws IOException {	          
	city = city_na;
        System.out.println(city);
        country = country_co;      
        System.out.println(country);
        appid = Appid;
        System.out.println(appid);
        Thread weather = new Thread(() -> {
            String open_city, open_counrty,open_appid;
            boolean finished = false;
            OpenWeatherMap weather_obj = null;   
            open_city = city;
            open_counrty=country;
            open_appid=appid;
            try {
                weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+city_na+","+country_co+"&APPID="+Appid+""), OpenWeatherMap.class);
            }catch(FileNotFoundException e){
                Logger.getLogger(OpenData.class.getName()).log(Level.SEVERE, null, e);       
            }catch (MalformedURLException ex) {
                Logger.getLogger(OpenData.class.getName()).log(Level.SEVERE, null, ex);                
            }catch (IOException ex) {
                Logger.getLogger(OpenData.class.getName()).log(Level.SEVERE, null, ex);                
            } 
            str.add(weather_obj.getWeather().get(0).getMain()) ;
            str.add(weather_obj.getCoord().getLat().toString());
            str.add(weather_obj.getCoord().getLon().toString());
            
            //Όπως είχαμε κάνει και στη εργασία του εξαμήνου παίρνουμε τα στοιχεία μας από το OpenWeathe και καλούμε την main            
            //με βάση το JSON αρχείο που εμφανίζει το Postman. Οι μεταβλητές θα είναι ακέραιοι αριθμοί και για την πίεση και για τυν υγρασία
            //Τα μετατρέπουμε σε κείμενο γιατί έτσι τα περνάμε στην κλάση City
            str.add(weather_obj.getMain().getPressure().toString());
            str.add(weather_obj.getMain().getHumidity().toString());
            finished = true;                                                                  
        });
        
        Thread wikipedia = new Thread(() -> {
             boolean finished = false;
             
                
            try {
                MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2"),MediaWiki.class);
                int tmp =mediaWiki_obj.getQuery().getPages().get(0).getExtract().length();
                str2.add("Size of Article:"+tmp);
                str2.add(city+" Wikipedia article: "+mediaWiki_obj.getQuery().getPages().get(0).getExtract());
                
            } catch (MalformedURLException ex) {
                Logger.getLogger(OpenData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OpenData.class.getName()).log(Level.SEVERE, null, ex);            
            }
            finished = true;                                                                                  
        });

        weather.start();       
        wikipedia.run();
       
        map.put("WeatherAPI", str);
        map.put("Wikipedia", str2);
        return map;
         
   }               
}