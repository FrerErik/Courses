import java.util.Random;

public class Test {
  public static void main(String[] args) {
//    for(int i = 0; i<=9; i++) {
//      Random rng = new Random();
//      double number = rng.nextDouble();
//      System.out.println(number);
//    }
    int [] arr = {1,2,3,4};
    //产生0-(arr.length-1)的整数值,也是数组的索引
    int index=(int)(Math.random()*arr.length);
    int rand = arr[index];
    System.out.println(rand);
  }
}
