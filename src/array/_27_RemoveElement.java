package array;


/**
 * https://leetcode.com/problems/remove-element/
 * 题目描述：给定一个数组和一个值，在原数组中将所有给定值删除并且返回新的长度。
 * 		    不要创建一个新的数组，你必须要以 O(1) 的空间复杂度来修改输入的原数组。元素的顺序可以发生改变，可以不用考虑新数组长度之后的元素。
 */
public class _27_RemoveElement {
	public static void main(String[] args) {
		_27Solution2 solution = new _27Solution2();
//		int[] nums = {0,1,2,2,3,0,4,2};
//		int[] nums = {4,2,3,5,1};
		int[] nums = {4,4};
		int val = 4;
		int len = solution.removeElement(nums, val);
		
		for (int i = 0; i < len; ++i) {
			System.out.println(nums[i]);
		}
	}
}

/**
 * 同向双指针
 * 本题采用 283 号问题的思路。将待删除值 val 移到数组的最后面（或者说是将非 val 的值移到数组的前面），然后返回新数组的长度，从而相当于在原始数组中将 val 删除。   
 * 同时由于本题中不需要考虑新数组长度之后的元素，所以最终不需要将 val 放到数组的最后面
 */
class _27Solution1 {
    public int removeElement(int[] nums, int val) {
    	int k = 0; // [0, k) 中不出现 val, 如果 nums 中有 val ，则 k 指针指向第一个 val 
    	
    	for (int i = 0; i < nums.length; ++i) {
    		if (val != nums[i]) {
    			nums[k] = nums[i];
    			++k;
    		}
    	}
    	
    	return k;
    }
}

/**
 * 碰撞指针
 * 当数组中待删除的元素较少时，Solution1 会进行大量的 nums[k] = nums[i] 操作。
 * 由于本题中不需要考虑数组中的元素顺序，因此可以采用碰撞指针的思路来减少 nums[k] = nums[i] 的操作次数。
 * 
 * 思路：设置 i、k 两个指针，指针 i 向左移动，指针 k 向右移动寻找待删除值 val 
 * （1）k 指针向右移动，遇到非 val 时继续移到，直到找到 val。
 * （2）当 k 指针找到 val 时，k 指针不移动，将 nums[i] 放到 nums[k] 处，i 向左移动
 *  
 */
class _27Solution2 {
	public int removeElement(int[] nums, int val) {
		// [4,2,3,5,1], val = 4
		int k = 0; 
		int i = nums.length;
		while (k < i) {
			if (val == nums[k]) {
				// nums[k] 为 val 时，则将 nums[i-1] 放置到 nums[k] 处
				// 如果 nums[i-1] 也为 val ，则指针 k 保持不动，等待指针 i 找到一个非 val 值
				nums[k] = nums[i - 1];
				--i; // 此语句包含两种含义：（1）找到一个 val，则数组的长度减 1 （2）指针 i 向左移动
			} else {
				++k;
			}
		}
		
		return i;
	}
}