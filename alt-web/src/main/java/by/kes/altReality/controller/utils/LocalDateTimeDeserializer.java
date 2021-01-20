package by.kes.altReality.controller.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

  @Override
  public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    return LocalDateTime.parse(parser.getText(), formatter);
  }
}
