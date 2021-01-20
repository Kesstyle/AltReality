package by.kes.altReality.data.domain;

import lombok.Data;

@Data
public abstract class TrackableObject<T> {

  private T id;
}
