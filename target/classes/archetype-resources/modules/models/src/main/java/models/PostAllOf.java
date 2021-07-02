#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PostAllOf
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-02T09:32:15.227062-04:00[America/New_York]")

public class PostAllOf  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("author")
  private String author;

  @JsonProperty("tags")
  private String tags;

  @JsonProperty("comments")
  private String comments;

  public PostAllOf author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
  */
  @ApiModelProperty(value = "")


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public PostAllOf tags(String tags) {
    this.tags = tags;
    return this;
  }

  /**
   * Get tags
   * @return tags
  */
  @ApiModelProperty(value = "")


  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public PostAllOf comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Get comments
   * @return comments
  */
  @ApiModelProperty(value = "")


  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostAllOf postAllOf = (PostAllOf) o;
    return Objects.equals(this.author, postAllOf.author) && 
        Objects.equals(this.tags, postAllOf.tags) && 
        Objects.equals(this.comments, postAllOf.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, tags, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostAllOf {${symbol_escape}n");
    
    sb.append("    author: ").append(toIndentedString(author)).append("${symbol_escape}n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("${symbol_escape}n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("${symbol_escape}n");
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

