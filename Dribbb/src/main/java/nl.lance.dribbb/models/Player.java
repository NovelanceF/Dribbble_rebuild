package nl.lance.dribbb.models;

/**
 * Created by Novelance on 2/6/14.
 */
public class Player {

  private String username;
  private String avatarUrl;
  private String name;
  private String likesCount;
  private String likesReceivedCount;
  private String followingCount;
  private String followersCount;
  private int id;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(String likesCount) {
    this.likesCount = likesCount;
  }

  public String getLikesReceivedCount() {
    return likesReceivedCount;
  }

  public void setLikesReceivedCount(String likesReceivedCount) {
    this.likesReceivedCount = likesReceivedCount;
  }

  public String getFollowingCount() {
    return followingCount;
  }

  public void setFollowingCount(String followingCount) {
    this.followingCount = followingCount;
  }

  public String getFollowersCount() {
    return followersCount;
  }

  public void setFollowersCount(String followersCount) {
    this.followersCount = followersCount;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
