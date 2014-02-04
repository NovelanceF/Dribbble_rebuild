package nl.lance.dribbb.network;

public class DribbbleAPI {
  
  public final static String SHOTS_POPULAR = "http://api.dribbble.com/shots/popular/?page=";
  
  public final static String SHOTS_DEBUTS = "http://api.dribbble.com/shots/debuts/?page=";
  
  public final static String SHOTS_EVERYONE = "http://api.dribbble.com/shots/everyone/?page=";

  public final static String tagsShots[] = {"id", "title", "image_url", "image_teaser_url", "views_count", "likes_count",
          "comments_count"};

  public final static String tagPlayer[] = {"name", "username", "avatar_url", "followers_count", "following_count",
          "likes_count", "likes_received_count"};

  public final static  String tagBundleShots[] = {"id", "title", "image_url", "views_count", "likes_count", "comments_count",
          "username", "name", "avatar_url", "likes_count", "likes_received_count", "following_count", "followers_count" };

  public final static String tagBundlePlayerInfo[] = {"username", "avatar_url", "name", "likes_count", "likes_received_count", "following_count", "followers_count"};
  
  public static String getUserFollowingUrl(String username) {
    return "http://api.dribbble.com/players/" + username + "/shots/following/?page=";
  }
  
  public static String getuserLikesUel(String username) {
    return "http://api.dribbble.com/players/" + username + "/shots/likes/?page=";
  }
  
  public static String getUserUrl(String username) {
    return "http://api.dribbble.com/players/" + username;
  }

  public static String getCommentsUrl(String id) {
    return "http://api.dribbble.com/shots/" + id + "/comments/?page=";
  }

  public static  String getReboundUrl(String id) {
    return "http://api.dribbble.com/shots/" + id + "/rebounds";
  }

  public static  String getPlayersShotUrl (String username) {
    return "http://api.dribbble.com/players/" + username + "/shots/?page=";
  }

}
