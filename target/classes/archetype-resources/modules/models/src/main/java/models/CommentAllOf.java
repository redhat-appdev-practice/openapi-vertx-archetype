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
 * CommentAllOf
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-02T09:32:15.227062-04:00[America/New_York]")

public class CommentAllOf  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("post")
  private JsonNullable<Object> post = JsonNullable.undefined();

  public CommentAllOf post(Object post) {
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
    CommentAllOf commentAllOf = (CommentAllOf) o;
    return Objects.equals(this.post, commentAllOf.post);
  }

  @Override
  public int hashCode() {
    return Objects.hash(post);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentAllOf {${symbol_escape}n");
    
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

