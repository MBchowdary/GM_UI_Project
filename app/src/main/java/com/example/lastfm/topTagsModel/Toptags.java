package com.example.lastfm.topTagsModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Toptags{

	@SerializedName("@attr")
	private Attr attr;

	@SerializedName("tag")
	private List<TagItem> tag;

	public void setAttr(Attr attr){
		this.attr = attr;
	}

	public Attr getAttr(){
		return attr;
	}

	public void setTag(List<TagItem> tag){
		this.tag = tag;
	}

	public List<TagItem> getTag(){
		return tag;
	}

	@Override
 	public String toString(){
		return 
			"Toptags{" + 
			"@attr = '" + attr + '\'' + 
			",tag = '" + tag + '\'' + 
			"}";
		}
}