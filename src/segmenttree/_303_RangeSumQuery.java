package segmenttree;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 *
 * 给定一个整数数组 nums，求出数组中 [left, right] 之间的元素和（left <= right），给定的 nums 数组中的元素不会发生变化
 * 要求：
 *  实现 NumArray 类，各个方法的功能描述：
 *  NumArray(int[] nums): 用给定的数组 nums 初始化 NumArray 的对象
 *  int sumRange(int left, int right): 返回数组中 [left, right] 之间的元素的和 
 *  
 * 示例：
 *  Input
 *      ["NumArray", "sumRange", "sumRange", "sumRange"]
 *      [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 *  Output
 *      [null, 1, -1, -3]
 * 
 * 示例数据范围：
 *  1 <= nums.length <= 10^4
 *  -10^5 <= nums[i] <= 10^5
 *  0 <= left <= right < nums.length
 *  At most 10^4 calls will be made to sumRang
 */
public class _303_RangeSumQuery {

    public static void main(String[] args) {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        NumArray4 numArray = new NumArray4(nums);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}

/**
 * 利用线段树实现
 */
class NumArray {

    public class SegmentTree<E> {
        private E[] tree;
        private E[] data;
        private Merger<E> merger;

        public SegmentTree(E[] arr, Merger<E> merger) {
            data = (E[]) new Object[arr.length];
            this.merger = merger;
            for (int i = 0; i < arr.length; ++i) {
                data[i] = arr[i];
            }

            tree = (E[]) new Object[4 * arr.length];

            buildSegmentTree(0, 0, arr.length - 1);
        }

        public E query(int queryLeft, int queryRight) {
            if (queryLeft < 0 || queryRight >= data.length || queryLeft > queryRight) {
                throw new IllegalArgumentException("Illegal query range!");
            }

            return query(0, 0, data.length - 1, queryLeft, queryRight);
        }

        public void update(int index, E e) {
            if (index < 0 || index >= data.length) {
                throw new IllegalArgumentException("Illegal index");
            }

            // 更新数组数组指定索引处的元素
            data[index] = e;

            // 更新线段树数组
            update(0, 0, data.length - 1, index, e);
        }

        public int size() {
            return data.length;
        }

        public E get(int index) {
            if (index < 0 || index >= data.length) {
                throw new IllegalArgumentException("Illegal index!");
            }
            return data[index];
        }

        private void buildSegmentTree(int root, int left, int right) {
            // 当区间内只有一个元素时，终止递归
            if (left == right) {
                tree[root] = data[left];
                return;
            }

            // 计算区间的中间值，将区间一分为二，然后分别构建左右子树
            int mid = left + (right - left) / 2;
            int leftChildIndex = leftChild(root);
            int rightChildIndex = rightChild(root);
            buildSegmentTree(leftChildIndex, left, mid);
            buildSegmentTree(rightChildIndex, mid + 1, right);

            // 左右子树构建完之后，将左右子树代表的区间内的值合并，从而得到当前节点的值
            tree[root] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
        }

        private E query(int root, int left, int right, int queryLeft, int queryRight) {
            // 待查询的区间与当前线段树所表示的区间一样，则直接返回当前线段树的值即可
            if (left == queryLeft && right == queryRight) {
                return tree[root];
            }

            // 计算当前线段树区间的中间点，确定查询的方向
            // 查询的方向有三种，分别为：只在左子树中查找，只在右子树中查找，在左右子树中都要查找
            int mid = left + (right - left) / 2;
            int leftChildIndex = leftChild(root);
            int rightChildIndex = rightChild(root);

            if (queryRight <= mid) { // 只在左子树中查找
                return query(leftChildIndex, left, mid, queryLeft, queryRight);
            } else if (queryLeft >= mid + 1) { // 只在右子树中查找
                return query(rightChildIndex, mid + 1, right, queryLeft, queryRight);
            }

            // 在左右子树中都进行查找，需要将查询区间分为左右两部分，分别在左右子树中进行查找
            E leftResult = query(leftChildIndex, left, mid, queryLeft, mid);
            E rightResult = query(rightChildIndex, mid + 1, right, mid + 1, queryRight);

            // 将左右子树的结果合并并返回
            return merger.merge(leftResult, rightResult);
        }

        private void update(int root, int left, int right, int index, E e) {
            // 当区间中只有一个元素，且该区间对应的索引是 index 时，终止递归。（更新 index 处的元素，相当于更新 [index, index] 区间的元素）
            if (left == right && left == index) {
                tree[root] = e; // 当前线段树的根顶点即为数据数组中 index 处的元素，因此需要将根顶点的元素进行更新
                return;
            }

            // 确定 index 所在的子树（可能在左子树，也可能在右子树），并对该子树进行更新
            int mid = left + (right - left) / 2;
            int leftChildIndex = leftChild(root);
            int rightChildIndex = rightChild(root);
            if (index <= mid) { // 在左子树中
                update(leftChildIndex, left, mid, index, e);
            } else { // 在右子树中
                update(rightChildIndex, mid + 1, right, index, e);
            }

            tree[root] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
        }

        private int parent(int i) {
            // 下标从 1 开始， parent(i) = i/2; leftChild(i) = 2*i; rightChild(i) = 2*i + 1 
            // 下标从 0 开始， parent(i) = (i-1)/2; leftChild(i) = 2*i + 1; rightChild(i) = 2*i + 2 
            return (i - 1) / 2;
        }

        private int leftChild(int i) {
            return 2 * i + 1;
        }

        private int rightChild(int i) {
            return 2 * i + 2;
        }
    }

    interface Merger<E> {

        public E merge(E e1, E e2);
    }

    SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            arr[i] = nums[i];
        }

        segmentTree = new SegmentTree<Integer>(arr, (a, b) -> a + b);
    }

    public int sumRange(int left, int right) {
        return segmentTree.query(left, right);
    }
}

/**
 * 利用数组提前存储计算的和（方式一，既存储 sum 又存储 nums，sum 从下标 0 处开始存储值）
 */
class NumArray2 {
    private int[] sum; // sum[i] 存储着 nums 中[0,i] 之间元素的和
    private int[] nums;

    public NumArray2(int[] nums) {
        sum = new int[nums.length];
        this.nums = new int[nums.length];
        sum[0] = nums[0];
        this.nums[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            sum[i] = sum[i - 1] + nums[i];
            this.nums[i] = nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return sum[right] - sum[left] + nums[left]; // 因为包含左边界的值，所以需要将左边界的值加上去
    }
}

/**
 * 利用数组提前存储计算的和（方式二，只存储 sum，sum 从下标 0 处开始存储值）
 * 在方式一中， sumRange 函数中如果直接使用 “sum[right] - sum[left]” 会丢失左边界的值。
 * 为了返回正确的结果，需要特意保存 nums 的值，从而确保左边界值可以加上去。但是这样做造成存储的浪费，实际上 nums 的值不是必须要保存的。
 * 如在 sumRange 函数中用 “sum[right] - sum[left - 1]” 就不会丢失左边界的值，但是需要注意的是：当 left = 0 时，会发生下标越界。
 * 因此，在采取这种方式时，虽然不需要存储 nums，但是需要对 left = 0 进行特殊处理。
 */
class NumArray3 {
    private int[] sum; // sum[i] 存储着 nums 中[0,i] 之间元素的和

    public NumArray3(int[] nums) {
        sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (0 == left) {
            return sum[right];
        }
        // left >= 1，无需担心 left - 1 会越界
        return sum[right] - sum[left - 1];
    }
}

/**
 * 利用数组提前存储计算的和（方式三，只存储 sum，sum 从下标 1 处开始存储值）
 */
class NumArray4 {
    private int[] sum; // sum[i] 存储着 nums 中前 i 个元素的和（即 [0,i-1] 之间）

    public NumArray4(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0; // sum 中第一个元素不存储实际值
        for (int i = 0; i < nums.length; ++i) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return sum[right + 1] - sum[left];
    }
}
