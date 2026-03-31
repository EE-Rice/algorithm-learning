package learn.sort;

public class insertionSort {
    public static void main(String[] args) {
        int [] arr = new int[] {3,4,5,6,1,2};
        for (int num : arr) {
            System.out.print(num);
        }
        insertionSort(arr);
        System.out.println();
        for (int num : arr) {
            System.out.print(num);
        }
    }

    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            // 0~1
            // ...
            // 0~n-1
            // 0~i-1 有序了！新来的数[i]，向左看
            // 当前数j+1, j是前一个数
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j-- ) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i,  int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
