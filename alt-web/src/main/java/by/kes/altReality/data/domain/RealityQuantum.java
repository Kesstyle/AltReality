package by.kes.altReality.data.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

import by.kes.altReality.controller.utils.LocalDateTimeDeserializer;
import by.kes.altReality.controller.utils.LocalDateTimeSerializer;
import by.kes.specification.location.domain.FeaturedLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RealityQuantum {

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime moment;

  private FeaturedLocation location;
}
