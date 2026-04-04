package learn.BinarySearch;

import java.util.Arrays;

public class findNumber {

    // 对数器
    public static void main(String[] args) {
        int N = 100;
        int V = 1000;
        int testTime = 500000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N);
            int arr[] = randomArray(n, V);
            Arrays.sort(arr);
            int num = (int)(Math.random() * N);
            if (right(arr, num) != exsit(arr, num)) {
                System.out.println("出错了！");
                for (int j = 0; j < arr.length; j++)
                    System.out.print(arr[j] + " ");
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 生成指定范围内的随机数组
     * @param n 元素个数
     * @param v 元素大小[1, v]上等概率
     * @return
     */
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            // Math.random() -> double -> [0, 1)上的一个小数，0.34782358912
            // Math.random() * v -> double -> [0, v)一个小数，依旧等概率
            // (int)(Math.random() * v) -> 0 1 2 3 ... v-1，等概率！
            arr[i] = (int) (Math.random() * v) + 1; // -> int -> 1 2 3 ... v，等概率
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static boolean sameArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /// ///////////////////////////////////////////////////////////////////////////////////////
    ///
    // arr有序才能用这个方法
    public static boolean exsit(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int l = 0, r = arr.length - 1, m = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] == num) {
                return true;
            } else if (arr[m] < num) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    public static boolean right(int[] arr, int num) {
        for (int cur: arr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
    }

}
