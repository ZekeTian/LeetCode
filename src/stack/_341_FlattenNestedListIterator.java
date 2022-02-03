package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * 
 * 题目描述：给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。
 *        请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
 *        实现扁平迭代器类 NestedIterator ：
 *          NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
 *          int next() 返回嵌套列表的下一个整数。
 *          boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
 *       
 *        你的代码将会用下述伪代码检测：
 *          initialize iterator with nestedList
 *          res = []
 *          while iterator.hasNext()
 *              append iterator.next() to the end of res
 *          return res
 *          
 *        如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
 *        
 * 限制条件：
 *  （1）1 <= nestedList.length <= 500
 *  （2）嵌套列表中的整数值在范围 [-10^6, 10^6] 内
 *  
 * 示例：
 *  输入：nestedList = [[1,1],2,[1,1]]
 *  输出：[1,1,2,1,1]
 *  解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 *        
 */
public class _341_FlattenNestedListIterator {

    public static void main(String[] args) {
        List<NestedInteger> list = new ArrayList<NestedInteger>();
        list.add(new NestedInteger(Arrays.asList(1, 1)));
        list.add(new NestedInteger(2));
        list.add(new NestedInteger(Arrays.asList(1, 1)));
        
        NestedIterator nestedIterator = new NestedIterator(list);
        
        while (nestedIterator.hasNext()) {
            System.out.print(nestedIterator.next() + " ");
        }
    }
}

class NestedInteger {
    
    private List<NestedInteger> list = null;
    private Integer num = 0;
    
    public NestedInteger(int num) {
        this.num = num;
    }
    
    public NestedInteger(List<Integer> data) {
        list = new ArrayList<NestedInteger>();
        for (int i : data) {
            list.add(new NestedInteger(i));
        }
    }
 
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return null == list;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return num;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }

}

class NestedIterator implements Iterator<Integer> {

    private List<Integer> data = null;
    private Iterator<Integer> iterator = null;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        data = new ArrayList<>();
        flatten(nestedList);
        iterator = data.iterator();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
    
    private void flatten(List<NestedInteger> nestedList) {
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) { // n 是普通的数字，则直接加入到 data 列表中即可
                data.add(n.getInteger());
            } else { // n 是嵌套的列表，则继续递归，将其扁平化
                flatten(n.getList());
            }
        }
    }
}
