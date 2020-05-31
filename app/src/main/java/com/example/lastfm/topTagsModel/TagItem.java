package com.example.lastfm.topTagsModel;

import com.google.gson.annotations.SerializedName;

public class TagItem{

	@SerializedName("reach")
	private int reach;

	@SerializedName("name")
	private String name;

	@SerializedName("count")
	private int count;

	public void setReach(int reach){
		this.reach = reach;
	}

	public int getReach(){
		return reach;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	@Override
 	public String toString(){
		return 
			"TagItem{" + 
			"reach = '" + reach + '\'' + 
			",name = '" + name + '\'' + 
			",count = '" + count + '\'' + 
			"}";
		}
}