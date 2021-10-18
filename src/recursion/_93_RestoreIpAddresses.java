package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 * 
 * 题目描述：给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *        有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *        例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *        
 * 条件限制：
 *  （1）0 <= s.length <= 3000
 *  （2）s 仅由数字组成
 * 
 * 示例：
 *  示例 1：
 *  输入：s = "25525511135"
 *  输出：["255.255.11.135","255.255.111.35"]
 *  
 *  示例 2：
 *  输入：s = "0000"
 *  输出：["0.0.0.0"]
 *  
 *  示例 3：
 *  输入：s = "101023"
 *  输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 */
public class _93_RestoreIpAddresses {

    public static void main(String[] args) {
//        String s = "0000";
        String s = "25525511135";
        
        _93Solution solution = new _93Solution();
        
        System.out.println(solution.restoreIpAddresses(s));
    }
}

/**
 * 使用回溯的方式枚举出所有可能的 ip 组成。不过，在枚举时需要根据 ip 组成的特点，进行剪枝。
 */
class _93Solution {
    private String inputIp = null;
    private List<String> resultList = new ArrayList<>();

    // ip：存储分割的四段字符串，start：inpuIp 开始分割的下标
    private void restore(List<String> ip, int start) {
        if (4 == ip.size() && start < inputIp.length()) {
            // ip 已经满足四段，但是 inpuIp 没有分割完，则当前次分割失效
            return;
        }

        if (4 == ip.size() && start == inputIp.length()) {
            // ip 已经满足四段，并且 inpuIp 分割完，则当前次分割有效，将其存储到 ip list 中。
            StringJoiner joiner = new StringJoiner(".");
            for (String s : ip) {
                joiner.add(s);
            }
            resultList.add(joiner.toString());
            return;
        }

        if ('0' == inputIp.charAt(start)) {
            // 如果当前这个位置是 0，则当前次只能分割出 0 这一个数（保证无前导 0）
            ip.add("0");
            restore(ip, start + 1);
            ip.remove(ip.size() - 1);
            return;
        }

        // inputIp.length() - (4 - ip.size()) 是为了让 inputIp 留够空余的字符，保证最终的 ip 有四段
        for (int i = start; i <= inputIp.length() - (4 - ip.size()); ++i) {
            String subStr = inputIp.substring(start, i + 1);
            if (Integer.parseInt(subStr) > 255) { // 当前段大于 255 ，则此次分割无效
                break;
            }
            ip.add(subStr);
            restore(ip, i + 1);
            ip.remove(ip.size() - 1);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        inputIp = s;

        if (s.length() >= 4 && s.length() <= 12) { // 检查输入 ip 字符串的长度是否有效
            restore(new ArrayList<>(), 0);
        }

        return resultList;
    }
}
