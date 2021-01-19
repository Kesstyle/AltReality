package by.kes.altReality.controller.model.spec.location.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import by.kes.altReality.controller.model.spec.location.LocationFeatureType;

public class LocationFeatureTypeDeserializer extends JsonDeserializer<LocationFeatureType> {

  @Override
  public LocationFeatureType deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    return LocationFeatureType.getByName(parser.getText());
  }
}
