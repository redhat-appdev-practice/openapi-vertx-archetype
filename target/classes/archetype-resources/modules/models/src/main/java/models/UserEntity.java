#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import ${package}.models.CommentEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-02T09:32:15.227062-04:00[America/New_York]")

@javax.persistence.Entity
@javax.persistence.Table(name = "users")
public class UserEntity  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("username")
  private String username;

  @JsonProperty("email")
  private String email;

  @JsonProperty("givenName")
  private String givenName;

  @JsonProperty("familyName")
  private String familyName;

  @JsonProperty("id")
  @javax.persistence.Id
  private UUID id;

  @JsonProperty("joined")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime joined;

  @JsonProperty("publicName")
  private String publicName;

  @JsonProperty("avatar")
  private String avatar;

  @JsonProperty("verified")
  private Boolean verified = false;

  @JsonProperty("userComments")
  @com.fasterxml.jackson.annotation.JsonIgnore
  @javax.persistence.Transient
  @javax.persistence.OneToMany(targetEntity = CommentEntity.class, fetch = javax.persistence.FetchType.LAZY)
  @Valid
  private List<CommentEntity> userComments = null;

  @JsonProperty("userMedia")
  @com.fasterxml.jackson.annotation.JsonIgnore
  @javax.persistence.Transient
  @javax.persistence.OneToMany(targetEntity = MediaEntity.class, fetch = javax.persistence.FetchType.LAZY)
  @Valid
  private List<CommentEntity> userMedia = null;

  @JsonProperty("userPosts")
  @com.fasterxml.jackson.annotation.JsonIgnore
  @javax.persistence.Transient
  @javax.persistence.OneToMany(targetEntity = PostEntity.class, fetch = javax.persistence.FetchType.LAZY)
  @Valid
  private List<CommentEntity> userPosts = null;

  public UserEntity username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserEntity email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@javax.validation.constraints.Email
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserEntity givenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

  /**
   * Get givenName
   * @return givenName
  */
  @ApiModelProperty(value = "")


  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public UserEntity familyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

  /**
   * Get familyName
   * @return familyName
  */
  @ApiModelProperty(value = "")


  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public UserEntity id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UserEntity joined(LocalDateTime joined) {
    this.joined = joined;
    return this;
  }

  /**
   * Get joined
   * @return joined
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDateTime getJoined() {
    return joined;
  }

  public void setJoined(LocalDateTime joined) {
    this.joined = joined;
  }

  public UserEntity publicName(String publicName) {
    this.publicName = publicName;
    return this;
  }

  /**
   * Get publicName
   * @return publicName
  */
  @ApiModelProperty(value = "")


  public String getPublicName() {
    return publicName;
  }

  public void setPublicName(String publicName) {
    this.publicName = publicName;
  }

  public UserEntity avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  /**
   * Get avatar
   * @return avatar
  */
  @ApiModelProperty(value = "")


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public UserEntity verified(Boolean verified) {
    this.verified = verified;
    return this;
  }

  /**
   * Get verified
   * @return verified
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getVerified() {
    return verified;
  }

  public void setVerified(Boolean verified) {
    this.verified = verified;
  }

  public UserEntity userComments(List<CommentEntity> userComments) {
    this.userComments = userComments;
    return this;
  }

  /**
   * Get userComments
   * @return userComments
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<CommentEntity> getUserComments() {
    return userComments;
  }

  public void setUserComments(List<CommentEntity> userComments) {
    this.userComments = userComments;
  }

  public UserEntity userMedia(List<CommentEntity> userMedia) {
    this.userMedia = userMedia;
    return this;
  }

  /**
   * Get userMedia
   * @return userMedia
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<CommentEntity> getUserMedia() {
    return userMedia;
  }

  public void setUserMedia(List<CommentEntity> userMedia) {
    this.userMedia = userMedia;
  }

  public UserEntity userPosts(List<CommentEntity> userPosts) {
    this.userPosts = userPosts;
    return this;
  }

  /**
   * Get userPosts
   * @return userPosts
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<CommentEntity> getUserPosts() {
    return userPosts;
  }

  public void setUserPosts(List<CommentEntity> userPosts) {
    this.userPosts = userPosts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserEntity userEntity = (UserEntity) o;
    return Objects.equals(this.username, userEntity.username) && 
        Objects.equals(this.email, userEntity.email) && 
        Objects.equals(this.givenName, userEntity.givenName) && 
        Objects.equals(this.familyName, userEntity.familyName) && 
        Objects.equals(this.id, userEntity.id) && 
        Objects.equals(this.joined, userEntity.joined) && 
        Objects.equals(this.publicName, userEntity.publicName) && 
        Objects.equals(this.avatar, userEntity.avatar) && 
        Objects.equals(this.verified, userEntity.verified) && 
        Objects.equals(this.userComments, userEntity.userComments) && 
        Objects.equals(this.userMedia, userEntity.userMedia) && 
        Objects.equals(this.userPosts, userEntity.userPosts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, email, givenName, familyName, id, joined, publicName, avatar, verified, userComments, userMedia, userPosts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserEntity {${symbol_escape}n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("${symbol_escape}n");
    sb.append("    email: ").append(toIndentedString(email)).append("${symbol_escape}n");
    sb.append("    givenName: ").append(toIndentedString(givenName)).append("${symbol_escape}n");
    sb.append("    familyName: ").append(toIndentedString(familyName)).append("${symbol_escape}n");
    sb.append("    id: ").append(toIndentedString(id)).append("${symbol_escape}n");
    sb.append("    joined: ").append(toIndentedString(joined)).append("${symbol_escape}n");
    sb.append("    publicName: ").append(toIndentedString(publicName)).append("${symbol_escape}n");
    sb.append("    avatar: ").append(toIndentedString(avatar)).append("${symbol_escape}n");
    sb.append("    verified: ").append(toIndentedString(verified)).append("${symbol_escape}n");
    sb.append("    userComments: ").append(toIndentedString(userComments)).append("${symbol_escape}n");
    sb.append("    userMedia: ").append(toIndentedString(userMedia)).append("${symbol_escape}n");
    sb.append("    userPosts: ").append(toIndentedString(userPosts)).append("${symbol_escape}n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("${symbol_escape}n", "${symbol_escape}n    ");
  }
}

