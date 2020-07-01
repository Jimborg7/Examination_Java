/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;



import java.util.ArrayList;

public class Tourist extends Traveller {    
    public Tourist(String name, int age, String transportation, double current_lan, double current_lon, String weather, String museum, String CafeRestaurantBar, String landscape, String cities,String visit) {
        super(name, age, transportation, current_lan, current_lon, weather, museum, CafeRestaurantBar, landscape,cities,visit);
    }
    
   
    /*H Similarity του Tourist υπολογίζεται με βάση το πλήθος των κοινών όρων επί την
συχνότητα εμφάνισης κάθε όρου στο αντικείμενο city.*/
    @Override
    public double Similarity(City tempcity,ArrayList<String> criteria){
        double similarity = 1;
        double sum_similarity = 0;
        int word,i;
        int count=0; 
        
       //Transport      
        if(criteria.contains("Metro") ){
            try{
                if ( tempcity.city_transportation.contains("Metro")){
                word =tempcity.city_transportation.indexOf("Metro");                
                for(i = 0; i < tempcity.city_transportation.length(); i++){
                    count++;
                    tempcity.city_transportation=tempcity.city_transportation.substring(word + 1);
                    word = tempcity.city_transportation.indexOf("Metro");
                }
                similarity = (similarity + (count/i));
            }
          }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
          }  
        } else if (criteria.contains("Bus")) {
           try{
            if ( tempcity.city_transportation.contains("Bus")){
                word =tempcity.city_transportation.indexOf("Bus");                
                for(i = 0; i < tempcity.city_transportation.length(); i++){
                    count++;
                    tempcity.city_transportation=tempcity.city_transportation.substring(word + 1);
                    word = tempcity.city_transportation.indexOf("Bus");
                }
                similarity = (similarity + (count/i));
            }
           }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
          }  
        }else if(criteria.contains("Airport")){
            try{  
                if ( tempcity.city_transportation.contains("Airport")){
                    word =tempcity.city_transportation.indexOf("Airport");                
                    for(i = 0; i < tempcity.city_transportation.length(); i++){
                        count++;
                        tempcity.city_transportation=tempcity.city_transportation.substring(word + 1);
                        word = tempcity.city_transportation.indexOf("Airport");
                    }
                    similarity = (similarity + (count/i));
                }        
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
          } 
        }
        //WEATHER       
       if(criteria.contains("Clear") ){
            try{ 
                if ( tempcity.city_weather.contains("Clear"))
                similarity++;
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }  
        } else if (criteria.contains("Clouds")) {
            try{
                if ( tempcity.city_weather.contains("Clouds"))
                similarity++;
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }  
           
        }else if(criteria.contains("Snow")){
           try{
            if ( tempcity.city_weather.contains("Snow"))
                similarity++;
          }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }  
        }                          
               
        //MUSEUM
       if(criteria.contains("Archaeology museum") ){
            try{
                    if ( tempcity.city_museum.contains("Archaeology museum")){
                    word =tempcity.city_museum.indexOf("Archaeology museum");                
                    for(i = 0; i < tempcity.city_museum.length(); i++){
                        count++;
                        tempcity.city_museum=tempcity.city_museum.substring(word + 1);
                        word = tempcity.city_museum.indexOf("Archaeology museum");
                    }
                    similarity = (similarity + (count/i));
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }  
        } else if (criteria.contains("Art museum")) {
            try{
                if ( tempcity.city_museum.contains("Art museum")){
                    word =tempcity.city_museum.indexOf("Art museum");                
                    for(i = 0; i < tempcity.city_museum.length(); i++){
                        count++;
                        tempcity.city_museum=tempcity.city_museum.substring(word + 1);
                        word = tempcity.city_museum.indexOf("Art museum");
                    }
                    similarity = (similarity + (count/i));
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
          }  
        }else if(criteria.contains("Military and war museum")){              
            try{
                if ( tempcity.city_museum.contains("Military and war museum")){
                    word =tempcity.city_museum.indexOf("Military and war museum");                
                    for(i = 0; i < tempcity.city_museum.length(); i++){
                        count++;
                        tempcity.city_museum=tempcity.city_museum.substring(word + 1);
                        word = tempcity.city_museum.indexOf("Military and war museum");
                    }
                    similarity = (similarity + (count/i));
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
          }  
        }    
       
       //CAFERESTAURANTBAR
       if(criteria.contains("cafe") ){
            try{
                    if ( tempcity.city_CafeRestaurantBar.contains("cafe")){
                    word =tempcity.city_CafeRestaurantBar.indexOf("cafe");                
                    for(i = 0; i < tempcity.city_CafeRestaurantBar.length(); i++){
                        count++;
                        tempcity.city_CafeRestaurantBar=tempcity.city_CafeRestaurantBar.substring(word + 1);
                        word = tempcity.city_CafeRestaurantBar.indexOf("cafe");
                    }
                    similarity = (similarity + (count/i));
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }         
        } else if (criteria.contains("restaurant")) {
            try{
                if ( tempcity.city_CafeRestaurantBar.contains("restaurant")){
                    word =tempcity.city_CafeRestaurantBar.indexOf("restaurant");                
                    for(i = 0; i < tempcity.city_CafeRestaurantBar.length(); i++){
                        count++;
                        tempcity.city_CafeRestaurantBar=tempcity.city_CafeRestaurantBar.substring(word + 1);
                        word = tempcity.city_CafeRestaurantBar.indexOf("restaurant");
                    }
                    similarity = (similarity + (count/i));
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }  
        }else if(criteria.contains("bar")){             
            if ( tempcity.city_CafeRestaurantBar.contains("bar")){
                word =tempcity.city_CafeRestaurantBar.indexOf("bar");                
                for(i = 0; i < tempcity.city_CafeRestaurantBar.length(); i++){
                    count++;
                    tempcity.city_CafeRestaurantBar=tempcity.city_CafeRestaurantBar.substring(word + 1);
                    word = tempcity.city_CafeRestaurantBar.indexOf("bar");
                }
                similarity = (similarity + (count/i));
            }           
        }  
        
       //LandScape
        if(criteria.contains("Valley") ){
            try{
                if ( tempcity.city_landscape.contains("Valley")){
                    word =tempcity.city_landscape.indexOf("Valley");                
                    for(i = 0; i < tempcity.city_landscape.length(); i++){
                        count++;
                        tempcity.city_landscape=tempcity.city_landscape.substring(word + 1);
                        word = tempcity.city_landscape.indexOf("Valley");
                    }
                    similarity = (similarity + (count/i));
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }  
        } else if (criteria.contains("Mount")) {
            try{
                if ( tempcity.city_landscape.contains("Mount")){
                    word =tempcity.city_landscape.indexOf("Mount");                
                    for(i = 0; i < tempcity.city_landscape.length(); i++){
                        count++;
                        tempcity.city_landscape=tempcity.city_landscape.substring(word + 1);
                        word = tempcity.city_landscape.indexOf("Mount");
                    }
                    similarity = (similarity + (count/i));
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }  
        }else if(criteria.contains("Desert")){
          try{    
            if ( tempcity.city_landscape.contains("Desert")){
                word =tempcity.city_landscape.indexOf("Desert");                
                for(i = 0; i < tempcity.city_landscape.length(); i++){
                    count++;
                    tempcity.city_landscape=tempcity.city_landscape.substring(word + 1);
                    word = tempcity.city_landscape.indexOf("Desert");
                }
                similarity = (similarity + (count/i));
            }
          }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }        
        } else if (criteria.contains("Forest")) {
            try {
                if ( tempcity.city_landscape.contains("Forest")){
                    word =tempcity.city_landscape.indexOf("Forest");                
                    for(i = 0; i < tempcity.city_landscape.length(); i++){
                        count++;
                        tempcity.city_landscape=tempcity.city_landscape.substring(word + 1);
                        word = tempcity.city_landscape.indexOf("Forest");
                    }
                    similarity = (similarity + (count/i));
                }
            }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
            }  
           
        }else if(criteria.contains("Glacier")){
            try{   
                if ( tempcity.city_landscape.contains("Glacier")){
                    word =tempcity.city_landscape.indexOf("Glacier");                
                    for(i = 0; i < tempcity.city_landscape.length(); i++){
                        count++;
                        tempcity.city_landscape=tempcity.city_landscape.substring(word + 1);
                        word = tempcity.city_landscape.indexOf("Glacier");
                        
                    similarity = (similarity + (count/i));
                    }           
                }  
           }catch(NullPointerException e){
                System.out.print("Could not find all the given criteria in :" +tempcity.city_name); 
           }
        }    
        sum_similarity = similarity / (criteria.size());
        System.out.println("Similarity for Tourist:"+sum_similarity+"\n");        
        return sum_similarity; 
             
                
    }
}
