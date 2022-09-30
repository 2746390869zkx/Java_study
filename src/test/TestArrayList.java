package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @author zkx
 */
public class TestArrayList {
    public static void main(String[] args) {
        Integer[] ints = new Integer[]{1,2,3,4};
        List<Integer> ints1 = Arrays.asList(ints);
        ListIterator<Integer> iterator = ints1.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
            System.out.println(i);
        }
    }
}
