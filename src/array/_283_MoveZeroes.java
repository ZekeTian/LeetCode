package array;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/move-zeroes/
 * 题目描述：给定一个数组，写一个函数将所有的 0 移到最后面，同时保持非 0 元素的相对顺序。
 * 
 * 要求：
 * 	尽量减少操作次数。
 * 	必须在原数组上操作，不能拷贝额外的数组。
 *
 */
public class _283_MoveZeroes {
	public static void main(String[] args) {
		_283Solution4 solution = new _283Solution4();
		int[] nums = {1, 2, 0, 4, 0, 5, 6};
		solution.moveZeroes(nums);
		for (int i = 0; i < nums.length; ++i) {
			System.out.println(nums[i]);
		}
	}
}

/**
 * 暴力求解
 */
class _283Solution1 {
	public void moveZeroes(int[] nums) {
		// 开辟一个新数组，存储非零元素
		ArrayList<Integer> noZero = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; ++i) {
			if (0 != nums[i]) {
				noZero.add(nums[i]);
			}
		}
		
		// 将非 0 元素放在 nums 中
		for (int i = 0; i < noZero.size(); ++i) {
			nums[i] = noZero.get(i);
		}
		// 在 nums 的最后面放剩余的 0
		for (int i = noZero.size(); i < nums.length; ++i) {
			nums[i] = 0;
		}
    }
}

/**
 * 双指针思路 1 ：将数组分成两部分，前面部分放非零元素，后面部分放 0。假设从 k 处开始划分，则 [0, k) 处放非零 0 元素，[k, num.length) 放 0
 * 具体思路：
 * 	（1）放置 nums 中的非零元素
 * 	遍历 nums 数组，将非零元素插入到前面部分（即非零部分）的后面（nums[k] 处），然后 k 向后移动，直至遍历完 nums 中所有元素。
 *  （2）放置 nums 中的 0 
 *  遍历完数组后，k 处即为 0 开始放置的地方
 * 
 */
class _283Solution2 {
	public void moveZeroes(int[] nums) {
		int k = 0; // [0, k) 区间内是非零元素
		for (int i = 0; i < nums.length; ++i) {
			if (0 != nums[i]) {
				nums[k] = nums[i]; // 遇到非 0 元素，则将其放在前面非 0 元素的后面，即 nums[k] 处
				++k; 
			}
		}
		
		// 后面从 k 处开始放 0 
		for (int i = k; i < nums.length; ++i) {
			nums[i] = 0;
		}
    }
}

/**
 * 双指针思路 2（遍历过程中将非零元素与 0 进行交换）：将数组分成两部分，前面部分放非零元素，后面部分放 0。
 * 具体思路：
 * 遍历数组， [0, k) 部分放非零元素，后面 [k, num.length) 部分中将第一个非零元素与 nums[k] 进行交换，nums[k] 之后一直是 0
 */ 
class _283Solution3 {
    public void moveZeroes(int[] nums) {
    	// 1, 2, 0, 4, 0, 5, 6
    	int k = 0;
    	for (int i = 0; i < nums.length; ++i) {
    		if (0 != nums[i]) { // 当 nums[i] = 0 时，k 不后移，此时 nums[k] = 0，并且 nums[k] 之后一直是 0
    			int tmp = nums[k];
    			nums[k] = nums[i];
    			nums[i] = tmp;
    			++k;
    		}
    	}
    }
}

/**
 * 双指针思路 2 优化版本：针对本题可以对双指针思路 2 的交换操作进行如下的优化
 * 	（1）减少不必要的交换
 * 		当数组中所有的元素非 0 时，原代码会进行 nums.length 次交换，实际上此时都是两个相同的数进行交换，可以省略交换操作
 *  （2）将两个数交换过程的三步缩减为两步（仅针对此题而言）
 *  	因为此思路中可以保证 nums[k] 始终为 0 ，所以将 nums[k] 与 nums[i] 进行交换时，将 nums[k] 赋值为 nums[i] 后可以直接将 nums[i] 赋值为 0，不需要中间变量 tmp
 */
class _283Solution4 {
	 public void moveZeroes(int[] nums) {
    	// 1, 2, 0, 4, 0, 5, 6
    	int k = 0;
    	for (int i = 0; i < nums.length; ++i) {
    		if (0 != nums[i]) {
    			// 当数组中全部为非零元素，则  nums[k] 不为 0，nums[k] 与 nums[i] 相等，则不需要交换；
    			// 当数组中含有 0 时，nums[k] = 0，此时才进行交换
    			if (nums[k] != nums[i]) {
    				nums[k] = nums[i];
        			nums[i] = 0;
    			}
    			++k;
    		}
    	}
	 }
}