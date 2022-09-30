package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zkx
 */
public class Sort {


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4};
        int i = Sort.find(arr, 1, 0, 3);
        System.out.println(i);
    }




    //快排
    public static void sort() {
            BufferedReader br = new
                    BufferedReader(new InputStreamReader(System.in));
            System.out.println("数据");
        String data = null;
        try {
            data = br.readLine();
            String[] arrS = data.split(" ");
            int [] arr = new int[arrS.length];
            for (int i = 0; i < arr.length;i++) {
                arr[i] = Integer.parseInt(arrS[i]);
            }
            //排序
//            quickSort(arr,0,arr.length - 1);
            sort(arr,0,arr.length - 1);
            for (int i : arr) {
                System.out.print(i + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static int find(int[] nums, int target, int l, int r) {
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        System.out.println(l);
        return (nums[l] == target) ? l : -1;//表示没有找到
    }

    //排序排序
    public static void quickSort(int [] arr,int l,int r) {

        if (l >= r) return;

        int i = l - 1;
        int j = r + 1;
        //先随机取一个值，
        int x = arr[(i + j) >> 1];
        while (i < j) {
            do {
                i++;
            } while (arr[i] < x);
            do {
                j--;
            } while (arr[j] > x);
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //递归
        quickSort(arr,l,j);
        quickSort(arr,j+1,r);
    }

    //归并排序
    public static void sort(int[] arr1,int l,int r) {



        if (l >= r) return;

        int mid = (l + r) >> 1;
        //递归,直到不能再分治
        sort(arr1,l,mid);
        sort(arr1,mid + 1,r);

        //归并排序的逻辑
        int i = l;
        int j = mid + 1;
        //k记入的是临时表的下标
        int k = 0;
        int[] tmp = new int[r - l + 1];
        //这里就是合并比较两个数组
        while (i <= mid && j <= r) {
            if (arr1[i] <= arr1[j]) tmp[k ++] = arr1[i ++ ];
            else tmp[k ++ ] = arr1[j ++ ];
        }
        //将最后的放到最后面
        while (i <= mid) tmp[k ++ ] = arr1[i++];
        while (j <= r) tmp[k ++ ] = arr1[j++];
        //复制临时表数据进入数组
        for (i = l, j = 0; i <= r; i ++, j ++ ) arr1[i] = tmp[j];

    }

}
