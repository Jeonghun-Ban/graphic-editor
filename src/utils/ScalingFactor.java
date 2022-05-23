package utils;

import java.awt.Rectangle;
import java.util.function.Function;
import utils.dto.ScalingFactorDto;
import utils.dto.ScalingRequestDto;

public enum ScalingFactor {

  NW((request) -> {
    Rectangle bounds = request.getBounds();
    return new ScalingFactorDto(
        bounds.getMinX() + bounds.getWidth(),
        bounds.getMinY() + bounds.getHeight(),
        1 - computeDx(request),
        1 - computeDy(request),
        -(bounds.getMinX() + bounds.getWidth()),
        -(bounds.getMinY() + bounds.getHeight())
    );
  }),
  N((request) -> {
    Rectangle bounds = request.getBounds();
    return new ScalingFactorDto(
        0,
        bounds.getMinY() + bounds.getHeight(),
        1,
        1 - computeDy(request),
        0,
        -(bounds.getMinY() + bounds.getHeight())
    );
  }),
  NE((request) -> {
    Rectangle bounds = request.getBounds();
    return new ScalingFactorDto(
        bounds.getMinX(),
        bounds.getMinY() + bounds.getHeight(),
        1 + computeDx(request),
        1 - computeDy(request),
        -(bounds.getMinX()),
        -(bounds.getMinY() + bounds.getHeight())
    );
  }),
  E((request) -> {
    Rectangle bounds = request.getBounds();
    return new ScalingFactorDto(
        bounds.getMinX(),
        0,
        1 + computeDx(request),
        1,
        -bounds.getMinX(),
        0
    );
  }),
  SE((request) -> {
    Rectangle bounds = request.getBounds();
    return new ScalingFactorDto(
        bounds.getMinX(),
        bounds.getMinY(),
        1 + computeDx(request),
        1 + computeDy(request),
        -bounds.getMinX(),
        -bounds.getMinY()
    );
  }),
  S((request) -> {
    Rectangle bounds = request.getBounds();
    return new ScalingFactorDto(
        0,
        bounds.getMinY(),
        1,
        1 + computeDy(request),
        0,
        -bounds.getMinY()
    );
  }),
  SW((request) -> {
    Rectangle bounds = request.getBounds();
    return new ScalingFactorDto(
        bounds.getMinX() + bounds.getWidth(),
        bounds.getMinY(),
        1 - computeDx(request),
        1 + computeDy(request),
        -(bounds.getMinX() + bounds.getWidth()),
        -bounds.getMinY()
    );
  }),
  W((request) -> {
    Rectangle bounds = request.getBounds();
    return new ScalingFactorDto(
        bounds.getMinX() + bounds.getWidth(),
        0,
        1 - computeDx(request),
        1,
        -(bounds.getMinX() + bounds.getWidth()),
        0
    );
  }),
  ;

  private final Function<ScalingRequestDto, ScalingFactorDto> operator;

  ScalingFactor(Function<ScalingRequestDto, ScalingFactorDto> operator) {
    this.operator = operator;
  }

  private static double computeDx(ScalingRequestDto request) {
    return (request.getCurrentPoint().x - request.getStartPoint().x) / request.getBounds()
        .getWidth();
  }

  private static double computeDy(ScalingRequestDto request) {
    return (request.getCurrentPoint().y - request.getStartPoint().y) / request.getBounds()
        .getHeight();
  }

  public ScalingFactorDto compute(ScalingRequestDto request) {
    return this.operator.apply(request);
  }
}
