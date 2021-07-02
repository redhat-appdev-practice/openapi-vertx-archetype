#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * The database entity from which a Post is generated
 */
@ApiModel(description = "The database entity from which a Post is generated")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-02T09:32:15.227062-04:00[America/New_York]")

@javax.persistence.Entity
@javax.persistence.Table(name = "posts")
public class PostEntity  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @javax.persistence.Id
  private UUID id;

  @JsonProperty("title")
  private String title;

  /**
   * The status of the article as an enumeration of possible statuses
   */
  public enum StatusEnum {
    DRAFT("draft"),
    
    PUBLISHED("published"),
    
    DELETED("deleted"),
    
    REVIEW("review"),
    
    HELD("held");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("status")
  private StatusEnum status = StatusEnum.DRAFT;

  @JsonProperty("content")
  private String content;

  @JsonProperty("author")
  @javax.persistence.ManyToOne(targetEntity = UserEntity.class, fetch = javax.persistence.FetchType.EAGER)
  @javax.persistence.JoinColumn(name = "owner", referencedColumnName = "id")
  private UUID author;

  @JsonProperty("created")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime created;

  @JsonProperty("modified")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime modified;

  @JsonProperty("meta")
  private String meta;

  @JsonProperty("comments")
  @javax.persistence.Transient
  @com.fasterxml.jackson.annotation.JsonIgnore
  @javax.persistence.OneToMany(targetEntity = CommentEntity.class, fetch = javax.persistence.FetchType.EAGER)
  @javax.persistence.JoinColumn(name = "comments", referencedColumnName = "id")
  @Valid
  private List<CommentEntity> comments = null;

  @JsonProperty("tags")
  @com.fasterxml.jackson.annotation.JsonIgnore
  @javax.persistence.Transient
  @javax.persistence.ManyToMany(targetEntity = Tag.class, cascade = { javax.persistence.CascadeType.DETACH })
  @javax.persistence.JoinTable(
    name = "post_tags",
    joinColumns = {
        @javax.persistence.JoinColumn(name = "tag_id")
    },
    inverseJoinColumns = {
        @javax.persistence.JoinColumn(name = "post_id")
    }
)
  @Valid
  private List<String> tags = null;

  public PostEntity id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * The Post ID (UUID)
   * @return id
  */
  @ApiModelProperty(required = true, value = "The Post ID (UUID)")
  @NotNull

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public PostEntity title(String title) {
    this.title = title;
    return this;
  }

  /**
   * The title of the article in Markdown
   * @return title
  */
  @ApiModelProperty(required = true, value = "The title of the article in Markdown")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public PostEntity status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * The status of the article as an enumeration of possible statuses
   * @return status
  */
  @ApiModelProperty(required = true, value = "The status of the article as an enumeration of possible statuses")
  @NotNull


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public PostEntity content(String content) {
    this.content = content;
    return this;
  }

  /**
   * The markdown encoded content
   * @return content
  */
  @ApiModelProperty(value = "The markdown encoded content")


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public PostEntity author(UUID author) {
    this.author = author;
    return this;
  }

  /**
   * The author's user ID (UUID)
   * @return author
  */
  @ApiModelProperty(required = true, value = "The author's user ID (UUID)")
  @NotNull

  @Valid

  public UUID getAuthor() {
    return author;
  }

  public void setAuthor(UUID author) {
    this.author = author;
  }

  public PostEntity created(LocalDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * The date+time when this articles was first created in GMT
   * @return created
  */
  @ApiModelProperty(required = true, value = "The date+time when this articles was first created in GMT")
  @NotNull

  @Valid

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public PostEntity modified(LocalDateTime modified) {
    this.modified = modified;
    return this;
  }

  /**
   * The date+time when the article was last modified in GMT
   * @return modified
  */
  @ApiModelProperty(required = true, value = "The date+time when the article was last modified in GMT")
  @NotNull

  @Valid

  public LocalDateTime getModified() {
    return modified;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public PostEntity meta(String meta) {
    this.meta = meta;
    return this;
  }

  /**
   * A short summary to be used in meta tags
   * @return meta
  */
  @ApiModelProperty(value = "A short summary to be used in meta tags")


  public String getMeta() {
    return meta;
  }

  public void setMeta(String meta) {
    this.meta = meta;
  }

  public PostEntity comments(List<CommentEntity> comments) {
    this.comments = comments;
    return this;
  }

  /**
   * The comments associated with this post
   * @return comments
  */
  @ApiModelProperty(value = "The comments associated with this post")

  @Valid

  public List<CommentEntity> getComments() {
    return comments;
  }

  public void setComments(List<CommentEntity> comments) {
    this.comments = comments;
  }

  public PostEntity tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  /**
   * Tags to help classify this post
   * @return tags
  */
  @ApiModelProperty(value = "Tags to help classify this post")


  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostEntity postEntity = (PostEntity) o;
    return Objects.equals(this.id, postEntity.id) && 
        Objects.equals(this.title, postEntity.title) && 
        Objects.equals(this.status, postEntity.status) && 
        Objects.equals(this.content, postEntity.content) && 
        Objects.equals(this.author, postEntity.author) && 
        Objects.equals(this.created, postEntity.created) && 
        Objects.equals(this.modified, postEntity.modified) && 
        Objects.equals(this.meta, postEntity.meta) && 
        Objects.equals(this.comments, postEntity.comments) && 
        Objects.equals(this.tags, postEntity.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, status, content, author, created, modified, meta, comments, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostEntity {${symbol_escape}n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("${symbol_escape}n");
    sb.append("    title: ").append(toIndentedString(title)).append("${symbol_escape}n");
    sb.append("    status: ").append(toIndentedString(status)).append("${symbol_escape}n");
    sb.append("    content: ").append(toIndentedString(content)).append("${symbol_escape}n");
    sb.append("    author: ").append(toIndentedString(author)).append("${symbol_escape}n");
    sb.append("    created: ").append(toIndentedString(created)).append("${symbol_escape}n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("${symbol_escape}n");
    sb.append("    meta: ").append(toIndentedString(meta)).append("${symbol_escape}n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("${symbol_escape}n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("${symbol_escape}n");
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

