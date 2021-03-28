package segmenttree;

/**
 * https://leetcode.com/problems/range-sum-query-mutable/
 * 
 * 给定一个整数数组 nums，求出数组中 [left, right] 之间的元素和（left <= right），给定的 nums 数组中的元素可能会发生变化。
 * 要求：
 *  实现 NumArray 类，各个方法的功能描述：
 *  NumArray(int[] nums): 用给定的数组 nums 初始化 NumArray 的对象
 *  void update(int index, int val)：将  nums[index] 的值更新为 val
 *  int sumRange(int left, int right): 返回数组中 [left, right] 之间的元素的和 
 * 
 */
public class _307RangeSumQueryMutable {
    
    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        _307NumArray numArray = new _307NumArray(nums);
        System.out.println(numArray.sumRange(0, 2)); // return 9 = sum([1,3,5])
        numArray.update(1, 2);   // nums = [1,2,5]
        System.out.println(numArray.sumRange(0, 2)); // return 8 = sum([1,2,5])
    }
}

class _307NumArray {

    private class SegmentTree {
        private int[] data;
        private int[] tree;
        
        public SegmentTree(int[] nums) {
            this.data = new int[nums.length];
            for (int i = 0; i < nums.length; ++i) {
                this.data[i] = nums[i];
            }
            
            tree = new int[4 * nums.length];
            
            buildSegemetTree(0, 0, data.length - 1);
        }
        
        // 将 index 处的元素的值更新为 value
        public void update(int index, int value) {
            if (index < 0 || index >= data.length) {
                throw new IllegalArgumentException("Illegal index!");
            }
            // 更新 data 中的值 
            data[index] = value;
            // 更新线段树中的值
            update(0, 0, data.length - 1, index, value);
        }
        
        // 返回 [left, right] 区间内的元素和
        public int query(int queryLeft, int queryRight) {
            if (queryLeft > queryRight || queryLeft < 0 || queryRight >= data.length) {
                throw new IllegalArgumentException("Illegal Range!");
            }
            
            return query(0, 0, data.length - 1, queryLeft, queryRight);
        }
        
        // 构建以 root 为根顶点的线段树，其对应的区间为 [left, right]，并返回该区间的元素和
        private int buildSegemetTree(int root, int left, int right) {
            if (left == right) {
                tree[root] = data[left];
                return tree[root];
            }
            
            int mid = left + (right - left) / 2;
            int leftChildIndex = leftChild(root);
            int rightChildIndex = rightChild(root);
            
            int leftResult = buildSegemetTree(leftChildIndex, left, mid);
            int rightResult = buildSegemetTree(rightChildIndex, mid + 1, right);
            
            tree[root] = leftResult + rightResult;
            return tree[root];
        }
        
        private void update(int root, int left, int right, int index, int value) {
            if (left == right && left == index) {
                tree[root] = value;
                return;
            }
            
            int mid = left + (right - left) / 2 ;
            int leftChildIndex = leftChild(root);
            int rightChildIndex = rightChild(root);
            if (index <= mid) { // 在左子树中
                update(leftChildIndex, left, mid, index, value);
            } else { // 在右子树中
                update(rightChildIndex, mid + 1, right, index, value);
            }
            
            tree[root] = tree[leftChildIndex] + tree[rightChildIndex];
        }
        
        // 在以 root 为根顶点的线段树中，查询 [queryLeft, queryRight] 之间的元素和
        private int query(int root, int left, int right, int queryLeft, int queryRight) {
            if (left == queryLeft && right == queryRight) {
                return tree[root];
            }
            
            int mid = left + (right - left) / 2;
            int leftChildIndex = leftChild(root);
            int rightChildIndex = rightChild(root);
            if (queryRight <= mid) { // 只在左子树中
                return query(leftChildIndex, left, mid, queryLeft, queryRight);
            }
            if (queryLeft >= mid + 1) { // 只在右子树中
                return query(rightChildIndex, mid + 1, right, queryLeft, queryRight);
            }
            
            int leftResult = query(leftChildIndex, left, mid, queryLeft, mid);
            int rightResult = query(rightChildIndex, mid + 1, right, mid + 1, queryRight);
            
            return leftResult + rightResult;
        }
        
        // 获取索引为 index 的元素的左孩子的索引
        private int leftChild(int index) {
            return 2 * index  + 1;
        }
        
        // 获取索引为 index 的元素的右孩子的索引
        private int rightChild(int index) {
            return 2 * index + 2;
        }
    }

    private SegmentTree segmentTree;
    
    public _307NumArray(int[] nums) {
        segmentTree = new SegmentTree(nums);
    }
    
    public void update(int index, int val) {
        segmentTree.update(index, val);
    }
    
    public int sumRange(int left, int right) {
        return segmentTree.query(left, right);
    }
}
