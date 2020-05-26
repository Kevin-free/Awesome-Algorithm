package math;

/**
 * @description: 约瑟夫环问题
 * @author: Kevin
 * @createDate: 2020/3/6
 * @version: 1.0
 */
public class LastRemaining_Solution {
    public static void main(String[] args) {
        int n = 6,m=3;
        LastRemaining_Solution solution = new LastRemaining_Solution();
        int res = solution.f(n, m);
        System.out.println(res);

    }

    int f(int n, int m){
        if(n == 1)   return n;
        int res = (f(n - 1, m) + m - 1) % n + 1;
        return res;
    }


    public int LastRemaining_Solution(int n, int m) {
        if (n == 0)     /* 特殊输入的处理 */
            return -1;
        if (n == 1)     /* 递归返回条件 */
            return 0;
        return (LastRemaining_Solution(n - 1, m) + m) % n;
    }
}
