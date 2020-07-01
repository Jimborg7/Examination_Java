/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;

/**
 *
 * @author jimge
 */

public class RecommendedCity {
	public RecommendedCity(String city, int rank) {
		super();
		City = city;
		this.rank = rank;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	String City;
	int rank;
}

