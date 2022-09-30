package test;

/**
 * @author zkx
 */
public class Test3 {
    //求在a中取b的概率
    int[] fact;//存放fact[n] = n!
    int[] infact;//存放逆元infact[n] = 1 / n!
    int N = 10010;//a最大为2000
    int mod = (int)1e9 + 7;

    public static void main(String[] args) {
//        Test3.sum();
        System.out.println();
    }
    void sum(int n) {
        fact = new int[N];
        infact = new int[N];
        fact[0] = infact[0] = 1;
        for (int i = 1; i < N; i++) {
            fact[i] = fact[i - 1] * i % mod;
            infact[i] = infact[i - 1] * qmi(i,mod - 2,mod) % mod;
        }

    }
    int qmi(int a,int m,int p) {
        int res = 1;
        while(m > 0) {
            if ((m & 1) != 0) res =  res * a % p;
            a = a * a % p;
            m >>= 1;
        }
        return res;
    }
}
