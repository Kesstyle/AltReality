package by.kes.altReality.controller.model.spec.location.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class MultiPolygon extends GeometryElement<List<Polygon>> {
}
