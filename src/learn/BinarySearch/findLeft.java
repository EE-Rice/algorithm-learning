package learn.BinarySearch;

import java.util.Arrays;

public class findLeft {
    // 保证数组有序，才能使用
    public static int findLeft(int[] arr, int num) {
        int l = 0, r = arr.length - 1, m = 0;
        int ans = -1;
        while (l <= r) {
            // m = (l + r) / 2;
            // 等效写法
            m = l + ((r - l) >> 1); // 右移一位等效于除2
            // m = l + (r - 1) / 2 // 防止溢出：数组很长的时候，(l + r) 的运算有溢出风险
            if (arr[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

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
            if (findLeft(arr, num) != right(arr, num)) {
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

    public static int right(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
    }
}
