package com.example.lastfm.topAlbumsModel;

import com.google.gson.annotations.SerializedName;

public class Attr{

	@SerializedName("rank")
	private String rank;

	public void setRank(String rank){
		this.rank = rank;
	}

	public String getRank(){
		return rank;
	}

	@Override
 	public String toString(){
		return 
			"Attr{" + 
			"rank = '" + rank + '\'' + 
			"}";
		}
}