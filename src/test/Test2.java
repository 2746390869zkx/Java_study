package test;

/**
 * @author zkx
 */
public class Test2 {

    static int x = 0;
    static int y = 0;

    /**
     * a * x + b * y = d
     * d为(a,b)的最大公约数
     * x,y为凑成d的系数
     * @param args
     */
    public static void main(String[] args) {
        int a = 15;
        int b = 6;
        int t = Test2.exgcd(a,b);
        System.out.printf("%dx + %dy = %d\n",a,b,t);
        System.out.printf("x = %d,y = %d",x,y);
    }
    //扩展欧几里得算法
    static int exgcd(int a,int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        int d = exgcd(b,a % b); //
        int t = x;
        x = y;
        y = t - a / b * y;//将x与y的数值交换，然后x不变,y变成(y - a/ b * x)
        return d;
    }
}
