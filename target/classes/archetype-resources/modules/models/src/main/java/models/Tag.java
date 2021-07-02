#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import ${package}.models.PostEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Tag
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-02T09:32:15.227062-04:00[America/New_York]")

@javax.persistence.Entity
@javax.persistence.Table(name = "tags")
public class Tag  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @javax.persistence.Id
  private UUID id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("posts")
  @com.fasterxml.jackson.annotation.JsonIgnore
  @javax.persistence.Transient
  @javax.persistence.ManyToMany(mappedBy = "tags")
  @Valid
  private List<PostEntity> posts = null;

  public Tag id(UUID id) {
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

  public Tag name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Tag posts(List<PostEntity> posts) {
    this.posts = posts;
    return this;
  }

  /**
   * Get posts
   * @return posts
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<PostEntity> getPosts() {
    return posts;
  }

  public void setPosts(List<PostEntity> posts) {
    this.posts = posts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tag tag = (Tag) o;
    return Objects.equals(this.id, tag.id) && 
        Objects.equals(this.name, tag.name) && 
        Objects.equals(this.posts, tag.posts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, posts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tag {${symbol_escape}n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("${symbol_escape}n");
    sb.append("    name: ").append(toIndentedString(name)).append("${symbol_escape}n");
    sb.append("    posts: ").append(toIndentedString(posts)).append("${symbol_escape}n");
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

