/**
 * Created by benja on 20.05.2017.
 */
public class Lcm {

    public static void main(String[] args) {


        System.out.println(lcm(2, 6, 14));

    }

    public static Integer lcm(int n, int m, int r) {
        if (r % n == 0 && r % m == 0 && r == n * m) {
            System.out.println(r + " " + " IST LCM VON N UND M");
        } else {
            System.out.println(r + " " + " IST nicht LCM VON N UND M");
        }

        return null;
    }
}