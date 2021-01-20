package by.kes.specification.location.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Polygon extends GeometryElement<List<Point>> {
}
