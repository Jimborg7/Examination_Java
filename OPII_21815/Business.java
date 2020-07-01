/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;

import java.util.ArrayList;

public class Business extends Traveller {
    
    /*Οι κλάσεις Business και Tourist θα πρέπει να εφαρμόζουν υπέρβαση στην μέθοδο
    Similarity().*/    
    
   
    public Business(String name, int age, String transportation, double current_lan, double current_lon, String weather, String museum, String CafeRestaurantBar, String landscape, String cities,String visit){
        super(name, age, transportation, current_lan, current_lon, weather, museum, CafeRestaurantBar, landscape , cities,visit);
    }
    
    
    @Override
     public double Similarity(City temp_city,ArrayList<String> criteria)/*(double lat, double lon, double city_lat, double city_lon, String unit)*/ {
        double similarity = 0.0;       
        String unit = criteria.get(0);
        
        if ((current_lan == temp_city.city_lat) && (current_lon == temp_city.city_lon)) {
		return 0;
	}
	else {
        	double theta = current_lon - temp_city.city_lon;
		double dist = Math.sin(Math.toRadians(current_lan )) * Math.sin(Math.toRadians(temp_city.city_lat)) + Math.cos(Math.toRadians(current_lan )) * Math.cos(Math.toRadians(temp_city.city_lat)) * Math.cos(Math.toRadians(theta));
		dist = Math.acos(dist);
		dist = Math.toDegrees(dist);
		dist = dist * 60 * 1.1515;
		if (unit.equals("K")) {
			dist = dist * 1.609344;                        
		} else if (unit.equals("N")) {
			dist = dist * 0.8684;
		}
                if(dist == 0.0){
                    System.out.print("The distance was calculated and found that it is equal to zero.\n");
                    similarity =0;                             
                }else{
		similarity = 1 /dist;
                
                }
               
                return similarity;
        }                
    }      
}
