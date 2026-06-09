package learn.data_structure;

import java.util.ArrayList;
import java.util.HashMap;

// 最大频率栈
public class MaximumFrequencyStack {

    // 测试链接 : https://leetcode.cn/problems/maximum-frequency-stack/
    class FreqStack {

        // 出现的最大次数
        private int topTimes;
        // 每层节点
        private HashMap<Integer, ArrayList<Integer>> cntValues = new  HashMap<>();
        // 每个数字出现了几次
        private HashMap<Integer, Integer> valueTimes = new HashMap<>();

        public void push(int val) {
            valueTimes.put(val, valueTimes.getOrDefault(val, 0) + 1); // 更新insert的数出现次数
            int curTopTimes = valueTimes.get(val); // 得到待进栈的层数
            if (!cntValues.containsKey(curTopTimes)) { // 新建一层的情况（从0开始or新建一层最高层）
                cntValues.put(curTopTimes, new ArrayList<>());
            }
            ArrayList<Integer> curTimeValues = cntValues.get(curTopTimes);
            curTimeValues.add(val);
            topTimes = Math.max(topTimes, curTopTimes);
        }

        public int pop() {
            ArrayList<Integer> topTimeValues = cntValues.get(topTimes);
            int ans = topTimeValues.remove(topTimeValues.size() - 1); // 移除链表末尾元素（靠近栈顶）
            if (topTimeValues.size() == 0) { // 最高层空了
                cntValues.remove(topTimes--);
            }
            int times = valueTimes.get(ans);
            if (times == 1) {
                valueTimes.remove(ans); // 被pop后，留在栈里的相同值元素个数为0
            } else {
                valueTimes.put(ans, times - 1); // 更新valueTimes里的记录
            }
            return ans;
        }

    }
}
