package learn.sort.radix;

// 基数排序，acm练习风格
// 测试链接 : https://www.luogu.com.cn/problem/P1177
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.*;
import java.util.Arrays;

public class RadixSort {

    // 可以设置进制，不一定10进制，随你设置
    public static int BASE = 10;

    public static int MAXN = 100001;

    public static int[] arr = new int[MAXN];

    public static int[] help =  new int[MAXN]; // 辅助数组

    public static int[] cnts=  new int[BASE]; // 词频统计

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        for  (int i = 0; i < n; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
        }
        sort();
        for (int i = 0; i < n - 1; i++) {
            out.print(arr[i] + " ");
        }
        out.print(arr[n - 1]);
        out.flush();
        out.close();
        br.close();
    }

    public static void sort() {
        // 如果会溢出，那么要改用long类型数组来排序
        // 找到数组中的最小值
        int min = arr[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, arr[i]);
        }
        int max = arr[0];
        for (int i = 0; i < n; i++) {
            // 数组中的每个数字，减去数组中的最小值，就把arr转成了非负数组
            arr[i] -= min;
            // 记录数组中的最大值
            max = Math.max(max, arr[i]);
        }

        // 根据最大值在BASE进制下的位数，决定基数排序做多少轮
        radixSort(bits(max));
        // 数组中所有数都减去了最小值，所以最后不要忘了还原
        for (int i = 0; i < n; i++) {
            arr[i] += min;
        }

    }

    // 返回number在BASE进制有几位
    // 词频统计循环的次数
    public static int bits(int number) {
        int ans = 0;
        while (number > 0) {
            ans++;
            number /= BASE;
        }
        return ans;
    }

    // 基数排序核心代码
    // arr内要保证没有负数
    // m是arr中最大值在BASE进制下有几位
    public static void radixSort(int bits) {
        // 理解的时候可以假设BASE = 10
        for (int offset = 1; bits > 0; offset *= BASE, bits--) {
            Arrays.fill(cnts, 0);
            // 词频统计，cnts[i]表示当前位=i的数字有几个
            for (int i = 0; i < n; i++) {
                // 数字提取某一位的技巧
                cnts[(arr[i] / offset) % BASE]++;
            }
            // 从=cnts[i]转变为<=cnts[i]的词频统计
            for (int i = 1; i < BASE; i++) {
                cnts[i] = cnts[i] + cnts[i - 1];
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
