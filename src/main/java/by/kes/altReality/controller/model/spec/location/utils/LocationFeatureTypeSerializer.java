package by.kes.altReality.controller.model.spec.location.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import by.kes.altReality.controller.model.spec.location.LocationFeatureType;

public class LocationFeatureTypeSerializer extends JsonSerializer<LocationFeatureType> {

  @Override
  public void serialize(LocationFeatureType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(value.getName());
  }
}
