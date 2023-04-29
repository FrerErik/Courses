
import com.a4.GraphADT.Edge;

public class EdgeMST extends Edge {

  public EdgeMST(int v, int w, float weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  public int either() {
    return this.v;
  }

  public int other(int v) {
    if (v == this.v) {
      return this.w;
    } else {
      return this.v;
    }
  }

}
