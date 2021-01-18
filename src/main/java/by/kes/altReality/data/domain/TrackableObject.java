package by.kes.altReality.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public abstract class TrackableObject<T> {

  private T id;
}
