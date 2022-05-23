package utils;

public class ScalingFactorDto {

  final double initX;
  final double initY;
  final double scaleX;
  final double scaleY;
  final double finishX;
  final double finishY;

  public ScalingFactorDto(double initX, double initY, double scaleX, double scaleY, double finishX,
      double finishY) {
    this.initX = initX;
    this.initY = initY;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.finishX = finishX;
    this.finishY = finishY;
  }

  public double getInitX() {
    return initX;
  }

  public double getInitY() {
    return initY;
  }

  public double getScaleX() {
    return scaleX;
  }

  public double getScaleY() {
    return scaleY;
  }

  public double getFinishX() {
    return finishX;
  }

  public double getFinishY() {
    return finishY;
  }
}


