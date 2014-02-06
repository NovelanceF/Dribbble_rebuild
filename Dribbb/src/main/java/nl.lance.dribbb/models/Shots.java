package nl.lance.dribbb.models;

/**
 * Created by Novelance on 2/6/14.
 */
public class Shots {

  private String shotsId;
  private String title;
  private String imageUrl;
  private String viewsCount;
  private String likesCount;
  private String commentsCount;
  private String username;
  private String name;
  private String avatarUrl;
  private String playerLikesCount;
  private String likesReceivdCount;
  private String followingCount;
  private String followersCount;
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getShotsId() {
    return shotsId;
  }

  public void setShotsId(String shotsId) {
    this.shotsId = shotsId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getViewsCount() {
    return viewsCount;
  }

  public void setViewsCount(String viewsCount) {
    this.viewsCount = viewsCount;
  }

  public String getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(String likesCount) {
    this.likesCount = likesCount;
  }

  public String getCommentsCount() {
    return commentsCount;
  }

  public void setCommentsCount(String commentsCount) {
    this.commentsCount = commentsCount;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getPlayerLikesCount() {
    return playerLikesCount;
  }

  public void setPlayerLikesCount(String playerLikesCount) {
    this.playerLikesCount = playerLikesCount;
  }

  public String getLikesReceivdCount() {
    return likesReceivdCount;
  }

  public void setLikesReceivdCount(String likesReceivdCount) {
    this.likesReceivdCount = likesReceivdCount;
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
}
