package com.example.lastfm.topAlbumsModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Albums{

	@SerializedName("@attr")
	private Attr attr;

	@SerializedName("album")
	private List<AlbumItem> album;

	public void setAttr(Attr attr){
		this.attr = attr;
	}

	public Attr getAttr(){
		return attr;
	}

	public void setAlbum(List<AlbumItem> album){
		this.album = album;
	}

	public List<AlbumItem> getAlbum(){
		return album;
	}

	@Override
 	public String toString(){
		return 
			"Albums{" + 
			"@attr = '" + attr + '\'' + 
			",album = '" + album + '\'' + 
			"}";
		}
}