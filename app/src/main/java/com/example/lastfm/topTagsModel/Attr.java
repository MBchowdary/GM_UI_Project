package com.example.lastfm.topTagsModel;

import com.google.gson.annotations.SerializedName;

public class Attr{

	@SerializedName("total")
	private int total;

	@SerializedName("offset")
	private int offset;

	@SerializedName("num_res")
	private int numRes;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setNumRes(int numRes){
		this.numRes = numRes;
	}

	public int getNumRes(){
		return numRes;
	}

	@Override
 	public String toString(){
		return 
			"Attr{" + 
			"total = '" + total + '\'' + 
			",offset = '" + offset + '\'' + 
			",num_res = '" + numRes + '\'' + 
			"}";
		}
}