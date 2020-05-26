package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description:
 * @author: Kevin
 * @createDate: 2020/2/27
 * @version: 1.0
 */
public class LastSubOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sn = scanner.nextLine();
        String string = scanner.nextLine();
        int n = Integer.parseInt(sn);
        String[] ssarr = string.split(" ");
        int an = ssarr.length;
        if (n==0||an == 0||n!= an){
            throw new IllegalArgumentException("input error!");
        }
        Integer[] sarr = new Integer[an];
        for (int i = 0; i < an; i++) {
            sarr[i] = Integer.parseInt(ssarr[i]);
        }
        int res =1 ;
        for (int i = 0; i < n-2; i++) {
            // 同趋势
            if ((sarr[i].compareTo(sarr[i + 1])) * (sarr[i+1].compareTo(sarr[i + 2])) >= 0) {

            }else { // 有变化
                ++res;
                i = i+1;
            }
        }

        System.out.println(res);
    }
}
