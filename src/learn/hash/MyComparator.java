package learn.hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class MyComparator {
    public static class Employee {
        public int company;
        public int age;

        public Employee(int c, int a) {
            company = c;
            age = a;
        }
    }

    public static class EmployeeComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
//            // 谁年龄小，谁优先级高
//            if (o1.age < o2.age) {
//                return -1;
//            } else if (o1.age > o2.age) {
//                return 1;
//            } else {
//                return 0;
//            }

            // 任何比较器都默认
            // 如果返回负数认为o1的优先级更高
            // 如果返回正数认为o2的优先级更高
            // 任何比较器都是这样，所以利用这个设定，可以定制优先级怎么确定，也就是怎么比较
            // 不再有大小的概念，就是优先级的概念
            return o1.age - o2.age;
        }

    }

    public static void main(String[] args) {
        Employee s1 = new Employee(2, 27);
        Employee s2 = new Employee(1, 60);
        Employee s3 = new Employee(4, 19);
        Employee s4 = new Employee(3, 23);
        Employee s5 = new Employee(1, 35);
        Employee s6 = new Employee(3, 55);
        Employee[] arr = { s1, s2, s3, s4, s5, s6 };

        // 使用定制的比较器进行排序
        Arrays.sort(arr, new EmployeeComparator());
        for (Employee e : arr) {
            System.out.println(e.company + " , " + e.age);
        }

        /// /////////////////////////////////////////////////////////////////////////////////////
        // lamda表达式定制比较器
        System.out.println("=====");
        // (a, b)比较的对象，比较的内容a.age, b.age
        // 返回负数：a优先级更高；返回正数：b优先级更高
        Arrays.sort(arr, (a, b) -> b.age - a.age);
        for (Employee e : arr) {
            System.out.println(e.company + " , " + e.age);
        }

        // 更复杂的定制比较器
        System.out.println("=====");
        // 所有员工，先按照谁的公司编号小，谁在前；如果公司编号一样，谁年龄小谁在前
        Arrays.sort(arr, (a, b) -> a.company != b.company ? (a.company - b.company) : (a.age - b.age));
        for (Employee e : arr) {
            System.out.println(e.company + " , " + e.age);
        }

        /// /////////////////////////////////////////////////////////////////////////////////////
        // 有序表
        TreeSet<Employee> treeSet1 = new TreeSet<>(new EmployeeComparator());
        for (Employee e : arr) {
            treeSet1.add(e);
        }
        System.out.println(treeSet1.size());

        // 会去重
        // 因为比较器：EmployeeComparator()返回o1.age - o2.age作为比较依据
        treeSet1.add(new Employee(2, 27));
        System.out.println(treeSet1.size());

        System.out.println("===");

        // 如果不想去重，就需要增加更多的比较
        // 比如对象的内存地址、或者如果对象有数组下标之类的独特信息
        TreeSet<Employee> treeSet2 = new TreeSet<>(
                (a, b) ->
                a.company != b.company
                ? (a.company - b.company)
                : a.age != b.age ? (a.age - b.age)
                    : a.toString().compareTo(b.toString()) // 这里可以理解为对象的内存地址
        );
        for (Employee e : arr) {
            treeSet2.add(e);
        }
        System.out.println(treeSet2.size());

        // 不会去重
        treeSet2.add(new Employee(2, 27));
        System.out.println(treeSet2.size());

        System.out.println("===");

        // PriorityQueue不会去重，不再展示了

        // 字典序
        // 长度相等的字符串：看作26进制进行比较，返回首位非0差
        // 长度不等：把较短的字符串用0补全低位，和长字符串一样长
        String str1 = "abcde";
        String str2 = "ks";
        System.out.println(str1.compareTo(str2)); // -10
        System.out.println(str2.compareTo(str1)); // 10
    }

}
