package by.kes.specification.location.domain;

import lombok.Data;

@Data
public abstract class GeometryElement<T> {

  private T coordinates;
}
