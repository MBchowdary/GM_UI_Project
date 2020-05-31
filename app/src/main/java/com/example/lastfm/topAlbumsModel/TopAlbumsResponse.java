package com.example.lastfm.topAlbumsModel;

import com.google.gson.annotations.SerializedName;

public class TopAlbumsResponse {

	@SerializedName("albums")
	private Albums albums;

	public void setAlbums(Albums albums){
		this.albums = albums;
	}

	public Albums getAlbums(){
		return albums;
	}

	@Override
 	public String toString(){
		return 
			"TopAlbumsResponse{" +
			"albums = '" + albums + '\'' + 
			"}";
		}
}