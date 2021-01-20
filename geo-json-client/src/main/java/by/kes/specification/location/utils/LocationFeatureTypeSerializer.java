package by.kes.specification.location.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import by.kes.specification.location.domain.LocationFeatureType;

public class LocationFeatureTypeSerializer extends JsonSerializer<LocationFeatureType> {

  @Override
  public void serialize(LocationFeatureType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(value.getName());
  }
}
