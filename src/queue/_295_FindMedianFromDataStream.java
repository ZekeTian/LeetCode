package queue;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * 
 * 题目描述：中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *         例如，[2,3,4] 的中位数是 3
 *              [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *         设计一个支持以下两种操作的数据结构：
 *          （1）void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 *          （2）double findMedian() - 返回目前所有元素的中位数。
 *          
 * 进阶：
 *  （1）如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 *  （2）如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 
 * 示例：
 *  addNum(1)
 *  addNum(2)
 *  findMedian() -> 1.5
 *  addNum(3) 
 *  findMedian() -> 2
 * 
 */
public class _295_FindMedianFromDataStream {

    public static void main(String[] args) {
        MedianFinder median = new MedianFinder();
        
        median.addNum(1);
        median.addNum(2);
        System.out.println(median.findMedian()); // 1.5
        median.addNum(3);
        System.out.println(median.findMedian()); // 2
    }
}

/**
 * 使用堆解决。
 * 将数字一分为二，左边是最大堆 maxHeap，右边是最小堆 minHeap。在添加数字时，始终保持 “0 <= maxHeap.size() - minHeap.size() <= 1”。
 * 相当于如下的示意图所示。
 *        /
 *       / minHeap 的堆顶元素
 * 
 *     / maxHeap 的堆顶元素
 *    /
 * 因为满足关系 “0 <= maxHeap.size() - minHeap.size() <= 1”，所以两个堆顶元素处于中心位置。
 * 当 minHeap.size() = maxHeap.size() 时，总个数为偶数，则中位数是中间两个数（即两个堆顶元素）的平均数；
 * 当 maxHeap.size() > minHeap.size() 时，总个数是奇数，则中位数即为 maxHeap 中的最大值
 *
 */
class MedianFinder {

    private PriorityQueue<Integer> maxHeap = null;
    private PriorityQueue<Integer> minHeap = null;
    
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((x, y) -> (y - x));
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }
        
        if (maxHeap.size() > minHeap.size()) {
            // 此时，maxHeap.size() = minHeap.size() + 1，优先向 minHeap 中放
            if (num >= maxHeap.peek()) {
                minHeap.add(num);
            } else {
                // num 必须要放进 maxHeap 中，为了维持 0 <= maxHeap.size() - minHeap.size() <= 1，
                // 必须要将 maxHeap 中最大值移入 minHeap 中，从而在 maxHeap 中腾出一个位置，然后再将 num 放进 maxHeap 中。
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            }
            return;
        }
        
        // maxHeap.size() = minHeap.size()，则此时优先放进 maxHeap 中
        if (num <= minHeap.peek()) {
            maxHeap.add(num);
        } else {
            // num 必须要放进 minHeap 中，为了维持 0 <= maxHeap.size() - minHeap.size() <= 1，
            // 必须要将 minHeap 中的最小值移入 maxHeap 中，从而在 minHeap 中腾出一个位置，然后再将 num 放进 minHeap 中。
            maxHeap.add(minHeap.poll());
            minHeap.add(num);
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // 总个数为偶数，则中位数是中间两个数（即两个堆顶元素）的平均数
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        // 当 maxHeap.size() > minHeap.size() 时，总个数是奇数，则中位数即为 maxHeap 中的最大值。
        return maxHeap.peek();
    }
}