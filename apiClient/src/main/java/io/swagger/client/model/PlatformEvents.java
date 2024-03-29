/*
 * Core API v2.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v2.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.client.model.PlatformEvent;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * PlatformEvents
 */


public class PlatformEvents implements Parcelable {
  @SerializedName("events")
  private List<PlatformEvent> events = null;

  public PlatformEvents() {
  }
  public PlatformEvents events(List<PlatformEvent> events) {
    this.events = events;
    return this;
  }

  public PlatformEvents addEventsItem(PlatformEvent eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<PlatformEvent>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Get events
   * @return events
  **/
  @Schema(description = "")
  public List<PlatformEvent> getEvents() {
    return events;
  }

  public void setEvents(List<PlatformEvent> events) {
    this.events = events;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlatformEvents platformEvents = (PlatformEvents) o;
    return Objects.equals(this.events, platformEvents.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(events);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformEvents {\n");
    
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
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
    return o.toString().replace("\n", "\n    ");
  }


  public void writeToParcel(Parcel out, int flags) {
    out.writeValue(events);
  }

  PlatformEvents(Parcel in) {
    events = (List<PlatformEvent>)in.readValue(PlatformEvent.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<PlatformEvents> CREATOR = new Parcelable.Creator<PlatformEvents>() {
    public PlatformEvents createFromParcel(Parcel in) {
      return new PlatformEvents(in);
    }
    public PlatformEvents[] newArray(int size) {
      return new PlatformEvents[size];
    }
  };
}
