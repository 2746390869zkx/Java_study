package test;

import java.util.HashMap;

/**
 * @author zkx
 */
public class Divisors {
    public static void main(String[] args) {
        Divisors.get_divisors(new int[]{2,6,8});
    }
    //将这些数的乘积得到1e9 +_ 7取模，返回约数个数
    static void get_divisors(int[] arr) {
        int mod = (int)1e9 + 7;
        //第一步进行对每一个进行分解
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int x : arr) {
            for (int i = 2;i <= x / i; i ++) {
                //将质数i加进map中
                Integer n = null;
                while (x % i == 0) {
                    n = map.get(i);
                    x /= i;
                    if (n == null) {
                        map.put(i,1);
                    } else {
                        map.replace(i,n + 1);
                    }
                }
            }
            if (x > 1) {
                Integer m = map.get(x);
                if (m == null) map.put(x , 1);
                else map.replace(x, m + 1);
            }
        }
        int res = 1;
        for (Integer i : map.keySet()) {
            System.out.print(i + "----");
            System.out.println(map.get(i));
        }
        for (Integer i : map.keySet()) {
            int a = map.get(i);
            int pk = 0;
            int b = 1;
            for (int j = 0;j <= a;j++) {
                pk += b;
                b *= i;
            }
                res = res * pk % mod;
        }
        System.out.println("约数之和为" + res);
    }
}
