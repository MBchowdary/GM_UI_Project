package com.example.lastfm.topAlbumsModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AlbumItem{

	@SerializedName("image")
	private List<ImageItem> image;

	@SerializedName("@attr")
	private Attr attr;

	@SerializedName("mbid")
	private String mbid;

	@SerializedName("artist")
	private Artist artist;

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private String url;

	public void setImage(List<ImageItem> image){
		this.image = image;
	}

	public List<ImageItem> getImage(){
		return image;
	}

	public void setAttr(Attr attr){
		this.attr = attr;
	}

	public Attr getAttr(){
		return attr;
	}

	public void setMbid(String mbid){
		this.mbid = mbid;
	}

	public String getMbid(){
		return mbid;
	}

	public void setArtist(Artist artist){
		this.artist = artist;
	}

	public Artist getArtist(){
		return artist;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"AlbumItem{" + 
			"image = '" + image + '\'' + 
			",@attr = '" + attr + '\'' + 
			",mbid = '" + mbid + '\'' + 
			",artist = '" + artist + '\'' + 
			",name = '" + name + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}