package learn.data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

// 全O(1)的数据结构
public class ALLO1 {

    // 测试链接 : https://leetcode.cn/problems/all-oone-data-structure/
    class AllOne {

        class Bucket {
            public HashSet<String> set;
            public int cnt; // 词频->桶的编号
            public Bucket last;
            public Bucket next;

            public Bucket(String s, int c) {
                set = new HashSet<>();
                set.add(s);
                cnt = c;
            }
        }

        // 桶的调整方法
        private void insert(Bucket cur, Bucket pos) {
            cur.next.last = pos;
            pos.next = cur.next;
            cur.next = pos;
            pos.last = cur;
        }

        private void remove(Bucket cur) {
            cur.last.next = cur.next;
            cur.next.last = cur.last;
        }

        Bucket head;

        Bucket tail;

        HashMap<String, Bucket> map; // AllO1结构

        public AllOne() {
            head = new Bucket("", 0);
            tail = new Bucket("", Integer.MAX_VALUE);
            // 初始化时把head和tail连接好
            head.next = tail;
            tail.last = head;
            map = new HashMap<>(); // key->字符串, value->对应bucket
        }

        // 添加字符串
        public void inc(String key) {
            // 第一次进入，因为要处理head
            if (!map.containsKey(key)) {
                if (head.next.cnt == 1) { // 存在编号为1的桶
                    map.put(key, head.next);
                    head.next.set.add(key);
                } else {
                    Bucket newBucket = new Bucket(key, 1); // 不存在编号为1的桶，new一个桶
                    map.put(key, newBucket);
                    insert(head, newBucket);
                }
            // 不是第一次进入
            } else {
                Bucket bucket = map.get(key); // 得到字符串原本所在桶
                if (bucket.next.cnt == bucket.cnt + 1) { // 下个桶词频刚好是cnt+1
                    map.put(key, bucket.next); // 在map里登记
                    bucket.next.set.add(key); // 往相应bucket里放字符串
                } else {
                    Bucket newBucket = new Bucket(key, bucket.cnt + 1); // 新建一个桶，字符串在初始化时放进去
                    map.put(key, newBucket);
                    insert(bucket, newBucket); // 在原来的桶bucket后插入新的桶
                }
                bucket.set.remove(key); // 移除原来的桶中的数据
                if (bucket.set.isEmpty()) { // 如果原来的桶空了，删除这个桶
                    remove(bucket);
                }
            }
        }

        public void dec(String key) {
            Bucket bucket = map.get(key);
            if (bucket.cnt == 1) {
                map.remove(key); // 只出现过一次，移除->直接删除对应记录
            } else {
                if (bucket.last.cnt == bucket.cnt - 1) { // 前一个桶刚好差1
                    map.put(key, bucket.last);
                    bucket.last.set.add(key);
                } else  {
                    Bucket newBucket = new Bucket(key, bucket.cnt - 1);
                    map.put(key, newBucket);
                    insert(bucket.last,  newBucket);
                }
            }
            bucket.set.remove(key); // 从当前桶中移除
            if (bucket.set.isEmpty()) {
                remove(bucket);
            }
        }

        public String getMaxKey() {
            return tail.last.set.iterator().next();
        }

        public String getMinKey() {
            return head.next.set.iterator().next();
        }
    }
}
