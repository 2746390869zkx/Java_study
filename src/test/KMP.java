package test;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author zkx
 * 测试kmp
 */
public class KMP {

    private int[] next;

    public static void main(String[] args) {
        String s = "ababababc";
        String p = "abc";
        KMP kmp = new KMP();
        kmp.get_next(p);
        int res = kmp.kmp(s, p);
        if (res == -1) {
            System.out.println("匹配失败");
        } else {
            System.out.println("匹配成功，下标为" + res);
        }
    }
    public int kmp(String s,String p) {
        //返回是否匹配成功，成功返回开始下标，否则返回-1
        int sLen = s.length();
        int pLen = p.length();
        for (int i = 0,j = 0;i < sLen;i++) {
            while (j != 0 && (s.charAt(i) != p.charAt(j))) j = next[j];
            if (s.charAt(i) == p.charAt(j)) j++;
            if (j == pLen) {
                System.out.println("i = " + i);
                System.out.println("j = " + j);
                return i - j + 1;
            }
        }
        return -1;
    }
    public void get_next(String p) {
        int len = p.length();
        next = new int[len];
        for (int i = 1,j = 0;i < len;i++) {
            while (j!=0 && (p.charAt(i) != p.charAt(j))) j = next[j];
            if (p.charAt(i) == p.charAt(j)) j++;
            next[i] = j;
        }
    }
}
