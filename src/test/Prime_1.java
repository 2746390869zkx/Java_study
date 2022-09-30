package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author zkx
 */
public class Prime_1 {
    int[] prime;
    int cnt;
    boolean[] vis;
    Prime_1(int n) {
        prime = new int[n + 10];
        vis = new boolean[n + 10];
        cnt = 0;
    }
    public static void main(String[] args) {
        Prime_1.divisors(2100);
    }
    //求约数
    static void divisors(int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->a-b);
        for (int i = 2; i <= n / i; i ++) {
            if (n % i == 0) {
                queue.add(i);
                if (i != (n / i)) queue.add(n / i);
            }
        }
        while(!queue.isEmpty()) {
            int i = queue.poll();
            System.out.println(i);
        }
    }
    boolean isPrimes(int x) {
        if (x < 2) return false;
        for (int i = 2 ;i <= x / i; i ++) {
            if (x % i == 0) return false;
        }
        return true;
    }
    void divide(int x) {
        for (int i = 2; i <= x /i ; i ++) {
            int s = 0;
            if (x % i == 0) {
                while (x % i == 0) {
                    x /= i;
                    s ++;
                }
                System.out.println(i + " ");
            }
        }
        if (x > 1) System.out.println(x + " 1");
        else System.out.println("1");
    }
    //得到1-n之间的质数个数
    void get_primes(int n) {
        for (int i = 2;i <= n; i ++) {
            if (vis[i]) continue;
            prime[cnt++] = i;
            for (int j = i + i; j <= n ; j += i) {
                vis[j] = true;
            }
        }
    }
    //线性筛法
    void get_primes1(int n) {
        for (int i = 2 ;i <= n; i ++) {
            if (!vis[i]) prime[cnt++] = i;
            for (int j = 0; prime[j] <= n / i; j ++) {
                vis[prime[j] * i ] = true;
                if (i % prime[j] == 0) break;//此时prime[j]是i的最小质因子
            }
        }
    }
}
