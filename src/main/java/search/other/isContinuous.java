package search.other;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @description:
 * @author: Kevin
 * @createDate: 2020/3/4
 * @version: 1.0
 */
public class isContinuous {

    public static void main(String[] args) {
        isContinuous solution = new isContinuous();
        int[] nums = {1, 3, 0, 0, 5};
        long st = System.nanoTime();
        boolean isCon = solution.isContinuous(nums);
        long et = System.nanoTime();
        System.out.println("is:"+isCon+"\t time1:"+(et - st));


        long st2 = System.nanoTime();
        boolean isCon2 = solution.isContinuousBit(nums);
        long et2 = System.nanoTime();
        System.out.println("is:"+isCon2+"\t time2:"+(et2 - st2));
    }

    public boolean isContinuous(int [] numbers) {
        if(numbers==null || numbers.length==0 || numbers.length>5){
            return false;
        }
        int[] d = new int[14];
        //d[0]=0;
        int min = 14,max = -1;
        for(int i = 0;i<numbers.length;i++){
            d[numbers[i]]++;
            if(numbers[i]==0){
                continue;
            }
            if(d[numbers[i]]>1){
                return false;
            }
            if(numbers[i]>max){
                max = numbers[i];
            }
            if(numbers[i]<min){
                min = numbers[i];
            }
        }
        if(max - min <5){
            return true;
        }else{
            return false;
        }
    }

    public boolean isContinuousBit(int [] numbers) {
        if(numbers.length != 5) return false;
        int min = 14;
        int max = -1;
        int flag = 0;
        for(int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if(number < 0 || number > 13) return false;
            if(number == 0) continue;
            if(((flag >> number) & 1) == 1)
                return false;
            flag |= (1 << number);
            if(number > max) max = number;
            if(number < min) min = number;
            if(max - min >= 5) return false;
        }
        return true;
    }
}
