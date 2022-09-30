package test;

/**
 * @author zkx
 */
public class Lucas {
    int p;

    public static void main(String[] args) {
        int a = 10,b = 5,p = 30;
        Lucas lucas = new Lucas();
        lucas.p = p;
        int res = lucas._lucas(10, 5, p);
        System.out.println(res);

    }
    int _lucas(long a,long b,int p) {
        if (a < p && b < p) return C((int)a,(int)b);
        return C((int)a % p,(int)b % p) * _lucas(a / p,b / p,p);
    }
    int C(int a,int b) {
        if (a < b) return 0;
        int res = 1;
        for (int i = a, j = 1; i <= b ; i --,j ++) {
            res = res * j % p;
            res = res * qmi(a,p - 2);
        }
        return res;
    }
    int qmi (int a,int k) {
        int res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % p;
            a = a * a % p;
            k >>= 1;
        }
        return res;
    }

}
