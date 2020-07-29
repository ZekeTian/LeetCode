package array;


/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * 
 * 题目描述：给定一个排好序的数组 nums，在原数组中同删除重复元素，从而使得数组中每个元素最多出现两次，并且返回数组新的长度
 * 		     不要创建一个新的数组，必须要在原始输入的数组上进行修改，空间复杂度应该为 O(1)。另外，不需要考虑新数组长度之后的元素。
 */
public class _80_RemoveDuplicatesFromSortedArray2 {
	public static void main(String[] args) {
		_80Solution solution = new _80Solution();
		int[] nums = {0,0,1,1,1,1,1,2,3,3}; // {1,1,1,2,2,3};
		int len = solution.removeDuplicates(nums);
		
		for (int i = 0; i < len; ++i) {
			System.out.print(nums[i] + ", ");
		}
		
	}
}

class _80Solution {
    public int removeDuplicates(int[] nums) {
    	if (null == nums || 0 == nums.length) {
    		return 0;
    	}
    	
    	int r = 2; // 允许重复的次数
    	if (nums.length <= r) {
    		return nums.length;
    	}
    	
    	int k = r; // nums 中 [0, k) 之间，每个元素最多重复 r 次
    	
    	for (int i = r; i < nums.length; ++i) {
    		// 判断 nums[i] 是否可以放进去
    		// 因为每个元素可以重复 r 次，所以 nums[i] 可以与 nums[k-1] 相同(r>=2)，但是不能与 nums 的 [0,k-r] 之间的元素相同
    		// 为了确保 nums[i] 与 nums 的 [0,k-r] 之间的元素不相同，则需要保证 nums[i] > nums[k-r]。
    		// 因为数组有序，nums[i] > nums[k-r] 时，实际上 nums[i] 就会大于 nums 的 [0,k-r] 之间所有的元素
    		// 如果 r=1 ，则 nums[i] 也不能与 nums[k-1] 相同。此时，下面的代码同样适用，因为也可以通过  nums[i] > nums[k-r] 保证条件。
    		
    		if (nums[i] > nums[k-r]) {
    			nums[k] = nums[i];
    			++k; // 放进了 nums[i] 后， k 向后移动
    		}
    	}
    	
    	return k;
    }
}