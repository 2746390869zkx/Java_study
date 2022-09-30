package test;

/**
 * @author zkx
 */
public class Test {

    public static void main(String[] args) {
        int n = 10;
//        int res = Test.phi(n);
//        System.out.printf("在1到%d中与%d互质的个数为%d",n,n,res);
        Test test = new Test(n);
        test.get_phi(n);
        for (int i = 1; i <= n ; i ++) {
            System.out.println((i) + "的互质数为" + test.phi[i]);
        }
    }

    //求最大公约数
    static int gcd(int a,int b) {
        return b == 0 ? a : gcd (b, a % b);
    }
    //求欧拉函数,得到1-n之间与n互质的书的个数

//    static int phi(int n) {
//        int res = n;
//        for (int i = 2; i <= n / i; i ++) {
//            if (n % i == 0) {
//                res = res / i * (i - 1);
//                while (n % i == 0) n /= i;
//            }
//        }
//        if (n > 1) res = res / n * (n - 1);
//        return res;
//    }
    int[] primes;
    boolean[] vis;
    int[] phi;
    int cnt;
    Test(int n) {
        primes = new int[n + 10];
        vis = new boolean[n + 10];
        phi = new int[n + 10];
    }
    int get_phi(int n) {
        int res = 10;
        phi[1] = 1;
        for (int i = 2;i <= n; i++) {
            if (!vis[i]) {
                primes[cnt++] = i;
                phi[i] = i - 1;//如果是质数，那么前面与他互质得数就是1-n-1的个数
            }
            for (int j = 0; primes[j] <= n / i; j ++) {
                int t = primes[j] * i;
                vis[t] = true;
                if (i % primes[j] == 0) {
                    phi[t] = primes[j] * phi[i];
                    break;
                }
                phi[t] = phi[i] * (primes[j] - 1);
            }
        }
        return res;

    }
}
