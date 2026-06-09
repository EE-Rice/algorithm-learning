package learn.data_structure;

import java.util.ArrayList;
import java.util.HashMap;

// 插入、删除和获取随机元素O(1)时间的结构
public class InsertDeleteRandom {

    // 测试链接 : https://leetcode.cn/problems/insert-delete-getrandom-o1/
    class RandomizedSet {

        // 用于去重和记录元素位置，key:元素值，value->位置
        public HashMap<Integer, Integer> map;

        // 用于存放集合元素
        public ArrayList<Integer> arr;

        public RandomizedSet() {
            map = new HashMap<>();
            arr = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, arr.size());
            arr.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int valIndex = map.get(val); // value在hashmap里的位置
            int endValue = arr.get(arr.size() - 1); // arr末尾元素
            map.put(endValue, valIndex); // 把集合末尾元素放到map中被移除的元素的位置
            arr.set(valIndex, endValue); // 把末尾元素endValue放到arr中被移除val的位置，相当于从arr里移除val
            // map和集合
            map.remove(val);
            arr.remove(arr.size() - 1); // 把末尾元素删除
            return true;
        }

        public int getRandom() {
            return arr.get((int) (Math.random() * arr.size()));
        }
    }



}
