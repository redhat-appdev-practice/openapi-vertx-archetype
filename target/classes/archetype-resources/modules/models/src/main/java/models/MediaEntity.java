#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MediaEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-02T09:32:15.227062-04:00[America/New_York]")

@javax.persistence.Entity
@javax.persistence.Table(name = "media")
public class MediaEntity  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @javax.persistence.Id
  private UUID id;

  @JsonProperty("mimeType")
  private String mimeType;

  @JsonProperty("fileName")
  private String fileName;

  @JsonProperty("sizeInBytes")
  private Integer sizeInBytes;

  @JsonProperty("created")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime created;

  @JsonProperty("modified")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime modified;

  @JsonProperty("reviewed")
  private Boolean reviewed = false;

  @JsonProperty("owner")
  @javax.persistence.ManyToOne(targetEntity = UserEntity.class, fetch = javax.persistence.FetchType.EAGER)
  @javax.persistence.JoinColumn(name = "owner", referencedColumnName = "id")
  private UUID owner;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    AVATAR("avatar"),
    
    PHOTO("photo"),
    
    BANNER("banner"),
    
    BACKGROUND("background"),
    
    ICON("icon"),
    
    VIDEO("video"),
    
    AUDIO("audio");

    private String value;

    TypeEnum(String value) {
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
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("type")
  private TypeEnum type = TypeEnum.PHOTO;

  public MediaEntity id(UUID id) {
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

  public MediaEntity mimeType(String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

  /**
   * Get mimeType
   * @return mimeType
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public MediaEntity fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * @return fileName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public MediaEntity sizeInBytes(Integer sizeInBytes) {
    this.sizeInBytes = sizeInBytes;
    return this;
  }

  /**
   * Get sizeInBytes
   * @return sizeInBytes
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getSizeInBytes() {
    return sizeInBytes;
  }

  public void setSizeInBytes(Integer sizeInBytes) {
    this.sizeInBytes = sizeInBytes;
  }

  public MediaEntity created(LocalDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public MediaEntity modified(LocalDateTime modified) {
    this.modified = modified;
    return this;
  }

  /**
   * Get modified
   * @return modified
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDateTime getModified() {
    return modified;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public MediaEntity reviewed(Boolean reviewed) {
    this.reviewed = reviewed;
    return this;
  }

  /**
   * Get reviewed
   * @return reviewed
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getReviewed() {
    return reviewed;
  }

  public void setReviewed(Boolean reviewed) {
    this.reviewed = reviewed;
  }

  public MediaEntity owner(UUID owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UUID getOwner() {
    return owner;
  }

  public void setOwner(UUID owner) {
    this.owner = owner;
  }

  public MediaEntity type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediaEntity mediaEntity = (MediaEntity) o;
    return Objects.equals(this.id, mediaEntity.id) && 
        Objects.equals(this.mimeType, mediaEntity.mimeType) && 
        Objects.equals(this.fileName, mediaEntity.fileName) && 
        Objects.equals(this.sizeInBytes, mediaEntity.sizeInBytes) && 
        Objects.equals(this.created, mediaEntity.created) && 
        Objects.equals(this.modified, mediaEntity.modified) && 
        Objects.equals(this.reviewed, mediaEntity.reviewed) && 
        Objects.equals(this.owner, mediaEntity.owner) && 
        Objects.equals(this.type, mediaEntity.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, mimeType, fileName, sizeInBytes, created, modified, reviewed, owner, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediaEntity {${symbol_escape}n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("${symbol_escape}n");
    sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("${symbol_escape}n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("${symbol_escape}n");
    sb.append("    sizeInBytes: ").append(toIndentedString(sizeInBytes)).append("${symbol_escape}n");
    sb.append("    created: ").append(toIndentedString(created)).append("${symbol_escape}n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("${symbol_escape}n");
    sb.append("    reviewed: ").append(toIndentedString(reviewed)).append("${symbol_escape}n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("${symbol_escape}n");
    sb.append("    type: ").append(toIndentedString(type)).append("${symbol_escape}n");
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

