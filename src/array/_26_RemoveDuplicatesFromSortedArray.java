package array;


/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * 
 * 题目描述：给定一个排好序的数组 nums，在原数组中同删除重复元素，从而使得数组中每个元素只出现一次，并且返回数组新的长度
 * 		     不要创建一个新的数组，必须要在原始输入的数组上进行修改，空间复杂度应该为 O(1)。另外，不需要考虑新数组长度之后的元素。
 */
public class _26_RemoveDuplicatesFromSortedArray {
	
	public static void main(String[] args) {
		_26Solution2 solution = new _26Solution2();
		int[] nums = {1,1,2,2}; // {1, 1, 2, 2, 3}; // {0,0,1,1,1,2,2,3,3,4}; 
		int len = solution.removeDuplicates(nums);
		
		for (int i = 0; i < len; ++i) {
			System.out.println(nums[i]);
		}
	}
	
}


/**
 * 本题要求对数组去重，本质实际上也就是在数组中删除元素，从而解题思路可以借鉴 26 号问题。不过需要注意是，每次删除的元素可能不一样。
 *
 */
class _26Solution1 {
    public int removeDuplicates(int[] nums) {
    	if (null == nums || 0 == nums.length) {
    		return 0;
    	}
    	
        int deleteVal = nums[0]; // 待删除元素
        int k = 1; // [0, k) 内为不重复的元素，如果数组有重复的元素，则 k 指向第一个出现的重复元素。
    	
        // 1,1,2,2,3
        // i = 1, k = 1, del = 1, num = [1, 1, 2, 2, 3]
        // i = 2, k = 1, del = 1, num = [1, 2, 2, 2, 3]
        // i = 3, k = 2, del = 2, num = [1, 2, 2, 2, 3]
        // i = 4, k = 2, del = 2, num = [1, 2, 3, 2, 3]
        
        for (int i = 1; i < nums.length; ++i) {
        	if (nums[i] != deleteVal) { // nums[i] 与 deleteVal  不等，则说明 nums[i] 是一个新的值，与前面 [0, k) 部分的元素不重复
        		nums[k] = nums[i]; // 如果数组中有重复的值，则 k 指向第一个重复的值，将新值 nums[i] 放在 nums[k] 处
        		 // 因为 nums 是有序的，相同的重复元素在原数组中是一起出现的。
        		// 因此当出现新值 nums[i] 时，则说明数组中 nums[i] 的后面不可能再出现 deleteVal，但是可能会重复出现 nums[i]，因此 deleteVal 应该标记为 nums[i]
        		deleteVal = nums[i];
        		++k;
        	}
        }
    	
    	return k;
    }
}

class _26Solution2 {
	public int removeDuplicates(int[] nums) {
		if (null == nums || 0 == nums.length) {
			return 0;
		}

		int k = 1; // nums 中的 [0,k) 之间无重复元素
		
		for (int i = k; i < nums.length; ++i) {
			// 判断 nums[i] 是否可以放进去（即是否可以放在 nums[k] 处）
			// 当 nums[i] 与 nums 中 [0,k) 之间的元素全都不相同时，才能将 nums[i] 放进去
			// 因为数组有序（题目给定的是升序），则当 nums[i] > nums[k-1] 时，即可保证 nums[i] 大于 nums 中 [0,k) 之间所有的元素（即保证 nums[i] 不会与前面的重复）
			if (nums[i] > nums[k-1]) {
				nums[k] = nums[i];
				++k;
			}
		}
		
		return k;
	}
}