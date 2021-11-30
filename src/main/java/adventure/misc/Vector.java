package adventure.misc;

public class Vector {
  public int x;
  public int y;

  public Vector(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Vector){
      Vector other = (Vector) obj;
      return this.x == other.x && this.y == other.y;
    }
    return false;
  }

  public double getVectorDistance(Vector other){
    double a = this.x - other.x;
    double b = this.y - other.y;
    return Math.sqrt(a * a + b * b);
  }

  @Override
  public String toString() {
    return "Vector{" +
            "x= " + x +
            ", y= " + y +
            '}';
  }
}