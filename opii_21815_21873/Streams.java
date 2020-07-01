/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author jimge
 */
public class Streams extends Listeners{
    public String streamsLamda(List<Traveller> CollectionListofTravellers, Traveller traveller , ArrayList<String> criteriaListeners){        
        Traveller candidateTraveller = new Traveller("Traveller_Candidate",traveller.age,traveller.transportation,traveller.current_lan,traveller.current_lon,
                traveller.weather,traveller.museum,traveller.CafeRestaurantBar,traveller.landscape,traveller.cities,"City_"+traveller.visit);  		
        ArrayList<String> candidateTravellerCriteria = candidateTraveller.returnCriteria();
       
        
	Map <String,Integer> cityToRank = CollectionListofTravellers.stream().collect(
                                          Collectors.toMap(
                                                  i->i.getVisit(),i->innerDot(i.returnCriteria(),candidateTravellerCriteria),(city1,city2)->
                                                  { return city1; }));
	cityToRank.forEach((k,v)->System.out.println("city:"+k+" rank: "+v));		
	Optional<RecommendedCity> recommendedCity=CollectionListofTravellers.stream().map(i-> new RecommendedCity(
                i.getVisit(),innerDot(i.returnCriteria(),candidateTravellerCriteria))).max(Comparator.comparingInt(RecommendedCity::getRank));	
        
	System.out.println("The Recommended City:"+recommendedCity.get().getCity());                                               
        return recommendedCity.get().City;
    }    
    
    public int innerDot(ArrayList<String>  criteria , ArrayList<String> candidateTravellerCriteria){                
        int summary=0;
        for (int i=0; i<criteria.size();i++){          
            try{
               
                if(criteria.get(i).equals(candidateTravellerCriteria.get(i))){
                    summary += 1;
                }
            }catch(NullPointerException e){                
                System.err.println("Some travellers have not filled all of the criteria." );
            }    
        }
        return summary;        
    }
    
    
    
    
}

      	