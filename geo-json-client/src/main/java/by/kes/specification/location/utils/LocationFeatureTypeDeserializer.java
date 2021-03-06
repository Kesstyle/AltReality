package by.kes.specification.location.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import by.kes.specification.location.domain.LocationFeatureType;

public class LocationFeatureTypeDeserializer extends JsonDeserializer<LocationFeatureType> {

  @Override
  public LocationFeatureType deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    return LocationFeatureType.getByName(parser.getText());
  }
}
