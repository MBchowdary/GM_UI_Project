package com.example.lastfm.topTagsModel;

import com.google.gson.annotations.SerializedName;

public class TopTagsResponse {

	@SerializedName("toptags")
	private Toptags toptags;

	public void setToptags(Toptags toptags){
		this.toptags = toptags;
	}

	public Toptags getToptags(){
		return toptags;
	}

	@Override
 	public String toString(){
		return 
			"TopTagsResponse{" +
			"toptags = '" + toptags + '\'' + 
			"}";
		}
}