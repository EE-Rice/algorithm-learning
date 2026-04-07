package learn.complexity;

import java.util.ArrayList;

public class Complexity {

    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        int n = arr.length;
        int end = n - 1, i = 0;
        while (end > 0){
            if (arr[i] > arr[i+1]){
                swap(arr, i, i + 1);
            } if (i < end - 1) {
                i++;
            } else  {
                end--;
                i = 0;
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // 随机生成长度为n
        // 值在 0 ~ v-1 之间
        // 且任意相邻两数不相等的数组
        int n = 10;
        int v = 4;
        int[] arr1 = new int[n];
        arr1[0] = (int) (Math.random() * v);
        for (int i = 1; i < n; i++) {
            do {
                arr1[i] = (int) (Math.random() * v);
            } while (arr1[i] == arr1[i-1]);
        }
        for (int num : arr1) {
            System.out.println(num + " ");
        }
        System.out.println();
        System.out.println("============");

        // java中的动态数组是ArrayList
        // 不同语言中动态数组的初始大小和实际扩容因子可能会变化，均摊都是O(1)
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr2.add(5);
        arr2.add(4);
        arr2.add(9);
        arr2.set(1, 6);
        System.out.println(arr2.get(1));
        System.out.println("============");

        int[] arr = {32, 45, 74, 9, 14, 5, 7};
        bubbleSort(arr);
        for (int num : arr) {
            System.out.println(num + " ");
        }
        System.out.println();
        System.out.println("============");


    }
}
