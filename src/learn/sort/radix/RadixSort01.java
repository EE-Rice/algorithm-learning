package learn.sort.radix;

import java.util.Arrays;

// 基数排序
// 测试链接 : https://leetcode.cn/problems/sort-an-array/
public class RadixSort01 {
    // 可以设置进制，不一定10进制，随你设置
    public static int BASE = 10;

    public static int MAXN = 50001;

    public static int[] help = new int[MAXN];

    public static int[] cnts = new int[BASE];

    public static int[] sortArray(int[] arr) {
        if (arr.length > 1) {
            // 如果溢出，要改用long类型数组进行排序
            int n = arr.length;
            // 找到数组中的最小值
            int min = arr[0];
            for (int i = 1; i < n; i++) {
                min = Math.min(min, arr[i]);
            }
            int max = 0;
            for (int i = 0; i < n; i++) {
                // 数组中的每个数字，减去数组中的最小值，就把arr转成了非负数组
                arr[i] -= min;
                // 记录最大值
                max = Math.max(max, arr[i]);
            }

            // 根据最大值在BASE进制下的位数，决定基数排序做多少轮
            radixSort(arr, n, bits(max));
            // 数组中所有数都减去了最小值，所以最后不要忘了还原
            for (int i = 0; i < n; i++) {
                arr[i] += min;
            }
        }
        return arr;
    }

    /// 返回number在BASE进制下有几位
    public static int bits(int number) {
        int ans = 0;
        while (number > 0) {
            ans++;
            number /= BASE;
        }
        return ans;
    }

    /// 基数排序核心代码
    // arr内要保证没有负数
    // n是arr的长度
    // bits是arr中最大值在BASE进制下有几位
    public static void  radixSort(int[] arr, int n, int bits) {
        for (int offset = 1; bits > 0; offset *= BASE, bits--) {
            Arrays.fill(cnts, 0);
            // 词频统计，cnts[i]表示当前位=i的数字有几个
            for (int i = 0; i < n; i++) {
                cnts[(arr[i] / offset) % BASE]++;
            }
            // 从=cnts[i]转变为<=cnts[i]的词频统计
            for (int i = 1; i < BASE; i++) {
                cnts[i] += cnts[i - 1];
            }
            // 从右往左
            // 每一轮排序都是稳定排序
            for (int i = n - 1; i >= 0; i--) {
                // 前缀数量分区的技巧
                // 数字提取某一位的技巧
                // cnts[d] 表示当前位等于 d 的数字应该放在 help 数组的哪个位置
                help[--cnts[(arr[i] / offset) % BASE]] = arr[i];
            }
            for (int i = 0; i < n; i++) {
                arr[i] = help[i];
            }
        }
    }
}
