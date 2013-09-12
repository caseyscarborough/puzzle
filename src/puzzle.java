public class puzzle {

  public static void main(String[] args) {
    int[] p = { 1, 4, 5, 7, 6, 2, 3, 8 };
    if(isSolvable(p)) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
    }
  }

  public static boolean isSolvable(int [] p) {
    int inversions = 0;

    for(int i = 0; i < p.length - 1; i++) {
      for(int j = i + 1; j < p.length; j++) {
        if(p[i] > p[j]) {
          System.out.printf("(%d, %d)\n", p[i], p[j]);
          inversions++;
        }
      }
    }

    System.out.println("Number of inversions are: " + inversions);
    return !((inversions > 0) && (inversions % 2 == 0));
  }

}