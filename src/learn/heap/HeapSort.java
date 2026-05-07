package learn.heap;

// 堆结构和堆排序，acm练习风格
// 测试链接 : https://www.luogu.com.cn/problem/P1177
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.*;

public class HeapSort {
    public static int MAXN = 100001;

    public static int[] arr = new int[MAXN];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer  in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        for (int i = 0; i < n; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
        }

        // heapSort1();
        heapSort2();

        for (int i = 0; i < n - 1; i++) {
            out.print(arr[i] + " ");
        }
        out.println(arr[n - 1]);
        out.flush();
        out.close();
        br.close();
    }

    // 堆用数组实现，以大根堆为例
    // 数组前缀范围和完全二叉树对应，大小由单独的变量size来控制
    // 堆的调整：heapInsert（向上调整）、heapify（向下调整）
    // 单次调用heapInsert、heapify，时间复杂度O(log n)，由完全二叉树的结构决定的

    // i位置的数，向上调整大根堆
    // 父节点下标：(i -1) / 2
    public static void heapInsert(int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(i, (i -1) / 2);
            i = (i - 1) / 2;
        }
    }

    // i位置的数变小了，想要维持大根堆结构
    // i位置的数，向下调整大根堆
    // 当前堆的大小为size
    public static void heapify(int i, int size) {
        int l = 2 * i + 1;

        // 有左孩子: l
        // 右孩子：l + 1
        while (l < size) {
            // 选择最强的孩子（大根堆：值较大）的下标
            int best = l + 1 < size && arr[l] < arr[l + 1] ? l + 1 : l;
            // 最强的孩子和当前数，最强的元素的下标
            best = arr[best] > arr[i] ? best : i;
            if (best == i) {
                break;
            }
            swap(best, i);

            i = best;
            l = 2 * i + 1;
        }
    }

    public static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /// 最经典的堆排序
    /// 从顶到底建立大根堆，O(n * logn)
    /// 依次弹出堆内最大值并排好序，O(n * logn)
    /// 整体时间复杂度O(n * logn)
    public static void heapSort1() {
        for (int i = 0; i < n; i++) {
            heapInsert(i); // 从顶到底构建堆
        }
        int size = n;
        while (size > 1) {
            swap(0, --size);
            heapify(0, size);
        }
    }

    // 从底到顶建立大根堆，O(n)
    // 依次弹出堆内最大值并排好序，O(n * logn)
    // 整体时间复杂度O(n * logn)
    public static void heapSort2() {
        for (int i = n - 1; i >= 0; i--) {
            heapify(i, n);
        }
        int size = n;
        while (size > 1) {
            swap(0, --size);
            heapify(0, size);
        }
    }
}
