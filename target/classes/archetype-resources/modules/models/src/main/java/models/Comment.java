#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import ${package}.models.CommentAllOf;
import ${package}.models.CommentEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Comment
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-02T09:32:15.227062-04:00[America/New_York]")

public class Comment  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @javax.persistence.Id
  private UUID id;

  @JsonProperty("author")
  @javax.persistence.ManyToOne(targetEntity = UserEntity.class, fetch = javax.persistence.FetchType.EAGER)
  @javax.persistence.JoinColumn(name = "author", referencedColumnName = "id")
  private UUID author;

  @JsonProperty("created")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime created;

  @JsonProperty("modified")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime modified;

  @JsonProperty("content")
  private String content;

  @JsonProperty("post")
  private JsonNullable<Object> post = JsonNullable.undefined();

  public Comment id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Comment author(UUID author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
  */
  @ApiModelProperty(value = "")

  @Valid

  public UUID getAuthor() {
    return author;
  }

  public void setAuthor(UUID author) {
    this.author = author;
  }

  public Comment created(LocalDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public Comment modified(LocalDateTime modified) {
    this.modified = modified;
    return this;
  }

  /**
   * Get modified
   * @return modified
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDateTime getModified() {
    return modified;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public Comment content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @ApiModelProperty(value = "")


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Comment post(Object post) {
    this.post = JsonNullable.of(post);
    return this;
  }

  /**
   * Get post
   * @return post
  */
  @ApiModelProperty(value = "")


  public JsonNullable<Object> getPost() {
    return post;
  }

  public void setPost(JsonNullable<Object> post) {
    this.post = post;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(this.id, comment.id) && 
        Objects.equals(this.author, comment.author) && 
        Objects.equals(this.created, comment.created) && 
        Objects.equals(this.modified, comment.modified) && 
        Objects.equals(this.content, comment.content) && 
        Objects.equals(this.post, comment.post);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, author, created, modified, content, post);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {${symbol_escape}n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("${symbol_escape}n");
    sb.append("    author: ").append(toIndentedString(author)).append("${symbol_escape}n");
    sb.append("    created: ").append(toIndentedString(created)).append("${symbol_escape}n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("${symbol_escape}n");
    sb.append("    content: ").append(toIndentedString(content)).append("${symbol_escape}n");
    sb.append("    post: ").append(toIndentedString(post)).append("${symbol_escape}n");
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

