package learn.heap;

import java.util.PriorityQueue;

// 将数组和减半的最少操作次数
// 测试链接 : https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/
public class MinimumOperationsToHalveArraySum {
    // 提交时将halveArray1改名为halveArray
    public static int halveArray1(int[] nums) {
        // 大根堆，每次取当前最大的数进行减半操作后放回堆
        PriorityQueue<Double> heap = new PriorityQueue<>((a, b) -> b.compareTo(a));
        double sum = 0;
        for (int num : nums) {
            heap.add((double)num);
            sum += num;
        }

        // sum：整体累加和 -> 要减少的目标!
        sum /= 2; // 待减少的总数
        int ans = 0; // 操作次数
        // minus：已经减少的幅度，循环条件minus < sum
        for (double minus = 0, cur; minus < sum; ans++, minus += cur) {
            cur = heap.poll() / 2;
            heap.add(cur);
        }
        return ans;
    }

    /// ///////////////////////////////////////////////////////////////////////////////////
    public static int MAXN = 100001;

    public static long[] heap = new long[MAXN];

    public static int size;

    // 提交时把halveArray2改名为halveArray
    public static int halveArray2(int[] nums) {
        size = nums.length;
        long sum = 0;
        for (int i = size - 1; i >= 0; i--) {
            heap[i] = (long)nums[i] << 20;
            sum += heap[i];
            heapify(i);
        }
        sum /= 2;
        int ans = 0;
        for (long minus = 0; minus < sum; ans++) {
            heap[0] /= 2;
            minus += heap[0];
            heapify(0);
        }
        return ans;
    }

    public static void heapify(int i) {
        int l = 2 * i + 1;
        while (l < size) {
            int best = l + 1 < size && heap[l + 1] > heap[l] ? l + 1 : l;
            best = heap[best] > heap[i] ? best : i;
            if (best == i) {
                return;
            }
            swap(i, best);
            i = best;
            l = 2 * i + 1;
        }
    }

    public static void swap(int i, int j) {
        long temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
