package com.lance.dribbb.network;

public class DribbbleAPI {
  
  public final static String SHOTS_POPULAR = "http://api.dribbble.com/shots/popular/?page=";
  
  public final static String SHOTS_DEBUTS = "http://api.dribbble.com/shots/debuts/?page=";
  
  public final static String SHOTS_EVERYONE = "http://api.dribbble.com/shots/everyone/?page=";
  
  public static String getUserFollowingUrl(String username) {
    return "http://api.dribbble.com/players/" + username + "/shots/following/?page=";
  }
  
  public static String getuserLikesUel(String username) {
    return "http://api.dribbble.com/players/" + username + "/shots/likes/?page=";
  }
  
  public static String getUserUrl(String username) {
    return "http://api.dribbble.com/players/" + username;
  }

}
