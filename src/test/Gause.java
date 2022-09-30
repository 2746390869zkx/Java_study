package test;

import java.util.Scanner;

/**
 * 高斯消元法
 * @author zkx
 */
public class Gause {
    double[] x;
    double eps = 1e-6;
    int m,n;
    Gause(int m,int n) {
        this.m = m;
        this.n = n;
        x = new double[n];
    }

    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        float[][] arr = new float[n][m];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0;i < n;i ++) {
            for (int j = 0; j < m;j ++) {
                arr[i][j] = scanner.nextFloat();
            }
        }
        Gause g = new Gause(m,n);
        int res = g.gause(arr);
        for (int i = 0 ;i < n ; i ++ ) {
            for (int j = 0 ; j < m ; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        if (res == 0) {
            System.out.println("该方程组无解");
        } else if (res == 1) {
            int cnt = 1;
            for (float[] i : arr) {
                System.out.println("x" + (cnt ++) + " = " + i[n]);
            }
        } else {
            System.out.println("该方程有无数个解");
        }
    }

    //0 - 无解 1 - 唯一解 2 - 无穷解
    int gause(float[][] a) {
        int c = 0;
        int r = 0;//当前操作的行数
        for (;c < n; c ++) {
            int t = r;
            //第一步找到绝对值最大的一行,
            for (int i = 0; i < n ;i ++)
                if (Math.abs(a[i][c]) > Math.abs(a[t][c]))
                    t = i;

            if (Math.abs(a[t][c]) < eps) continue;

            //将其换到最前面
            for (int i = c ;i <= n; i ++) swap(r,i,t,i,a);
            //将改行的第一个不为0的数字变成1
            for (int i = n; i >= c; i --) a[r][i] /= a[r][c];
            //将下面的所有航第c列变成0
            for (int i = r + 1; i < n; i ++) {
                //不为0的时候
                if (Math.abs(a[i][c]) > eps)
                    for (int j = n ; j >= c ; j --)
                        a[i][j] -= a[r][j] * a[i][c];
            }

            r ++;
        }

        if (r < n) {
            for (int i = r + 1; i < n ; i ++)
                if (a[i][n] > eps)
                    return 0;
            return 2;
        }
        //华为最简行阶梯型
        for (int i = n - 1; i >= 0; i -- )
            for (int j = i + 1; j < n; j ++ )
                a[i][n] -= a[i][j] * a[j][n];

        return 1;
    }
    void swap(int a1,int b1,int a2,int b2,float[][] arr) {
        float t = arr[a1][b1];
        arr[a1][b1] = arr[a2][b2];
        arr[a2][b2] = t;
    }

}
