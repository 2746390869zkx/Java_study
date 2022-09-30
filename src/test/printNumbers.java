package test;

/**
 * @author zkx
 */
public class printNumbers {

    String[] nums = new String[]{"0","1","2","3","4","5","6","7","8","9"};
    int N;
    String num;
    printNumbers(int n) {
        N = n;
        num = "";
    }
    //打印出不超过n位的1-n之间的数字。
    public static void main(String[] args) {
        int n = 2;
        printNumbers printNumbers = new printNumbers(n);
        printNumbers.print();
    }

    void print() {
        if (N < 1) return;
        dfs(0);
    }
    void dfs(int n) {
        if (n == N) {
            int cnt = 0;
            for (int i = 0 ; i < n; i++) {
                if (num.charAt(i) == '0') cnt ++;
                else break;
            }
            System.out.println(num.substring(cnt));
            return;//结束条件
        }
        for (int i = 0 ; i < 10; i ++) {
            if (N == 1 && i == 0) continue;
            num = num + nums[i];
            dfs(n + 1);
            //恢复
            num = num.substring(0,n);
        }
    }

}
