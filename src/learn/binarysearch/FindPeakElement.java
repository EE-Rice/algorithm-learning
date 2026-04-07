package learn.binarysearch;

public class FindPeakElement {

    // 测试链接：https://leetcode.cn/problems/find-peak-element/
    class Solution {

        public int findPeakElement(int[] arr) {
            int n = arr.length;
            // 小   小
            // -1 0 1
            if  (n == 1) return 0;

            // 数组长度>=2
            // 单独验证0位置是不是峰值点
            if (arr[0] > arr[1]) {
                return 0;
            }
            // 单独验证n-1位置是否为峰值点
            if(arr[n-1] > arr[n-2]) {
                return n-1;
            }

            // X   中间一定有峰值点    X
            // 0                    n-1
            // 中间：1 ~ n-2，一定有峰值点
            // 每一步l...r：一定有峰值点
            int l = 1, r = n-1, m = 0, ans = -1;
            while (l <= r) {
                m = l + (r - l) / 2;
                if (arr[m-1] > arr[m]) {
                    r  = m - 1;
                } else if (arr[m+1] > arr[m]) {
                    l = m + 1;
                } else  {
                    ans = m;
                    break;
                }
            }
            return ans;
        }
    }
}
