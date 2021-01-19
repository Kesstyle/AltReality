package by.kes.altReality.controller.model.spec.location.domain;

import lombok.Data;

@Data
public abstract class GeometryElement<T> {

  private T coordinates;
}
