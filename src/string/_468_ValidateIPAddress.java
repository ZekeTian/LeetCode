package string;

/**
 * https://leetcode.com/problems/validate-ip-address/
 * 
 * 题目描述：给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；
 *         如果不是上述类型的 IP 地址，返回 "Neither" 。
 *         有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。
 *         例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; 
 *              “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 *              
 *         一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 *         （1）1 <= xi.length <= 4
 *         （2）xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 *         （3）在 xi 中允许前导零
 *         例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，
 *         而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 *
 * 限制条件：queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 * 
 * 示例：
 *  示例 1
 *      输入：queryIP = "172.16.254.1"
 *      输出："IPv4"
 *      解释：有效的 IPv4 地址，返回 "IPv4"
 *      
 *  示例 2
 *      输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 *      输出："IPv6"
 *      解释：有效的 IPv6 地址，返回 "IPv6"
 * 
 *  示例 3
 *      输入：queryIP = "256.256.256.256"
 *      输出："Neither"
 *      解释：既不是 IPv4 地址，又不是 IPv6 地址
 * 
 */
public class _468_ValidateIPAddress {

    public static void main(String[] args) {
        // test case1, output: IPv4
//        String queryIP = "172.16.254.1";
        
        // test case2, output: IPv6
//        String queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        
        // test case3, output: Neither
//        String queryIP = "256.256.256.256";
        
        // test case4, output: Neither
//        String queryIP = "172.16.254.1.";
        
        // test case5, output: Neither
        String queryIP = "172.016.254.1";
        
        
        _468Solution solution = new _468Solution();
        
        
        System.out.println(solution.validIPAddress(queryIP));
    }
}


/**
 * 将字符串分割成多段，然后按照 IP 的规则去检查。
 */
class _468Solution {
    
    private boolean isDigital(char ch) {
        return (ch >= '0' && ch <= '9');
    }
    
    // 因为 IPv6 中只能存在 A-F 或 a-f 之间的字母，所以此时也只需要在这个区间之内判断即可
    private boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
    }
    
    private boolean isIPv4(String queryIP) {
        String[] splits = queryIP.split("\\.", -1); // 后面添加参数 -1，是为了避免丢失最后的空串，如：“122.22.2.2.” 中最后一个 “.” 会产生一个空串
        if (splits.length != 4) {
            return false; // 分段长度不够
        }
        
        for (String s : splits) {
            if (s.length() == 0 || s.length() > 3) {
                return false; // 长度不符合规则 
            }
            if (s.length() > 1 && s.charAt(0) == '0') {
                return false; // 含有前导 0
            }
            int num = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (!isDigital(s.charAt(i))) {
                    return false; // 含有非数字的字符
                }
                num = num * 10 + s.charAt(i) - '0';
            }
            if (num > 255) {
                return false; // 超过数字范围
            }
        }
        
        return true;
    }

    private boolean isIPv6(String queryIP) {
        String[] splits = queryIP.split(":", -1);
        
        if (splits.length != 8) {
            return false; // 分段长度不够
        }
        
        for (String s : splits) {
            if (s.length() > 4 || s.length() < 1) {
                return false; // 长度不符合规则 
            }
            for (int i = 0; i < s.length(); ++i) {
                if (!isDigital(s.charAt(i)) && !isLetter(s.charAt(i))) {
                    return false; // 含有非法的字符
                }
            }
        }
        
        return true;
    }
    
    public String validIPAddress(String queryIP) {
        if (isIPv4(queryIP)) {
            return "IPv4";
        }
        
        if (isIPv6(queryIP)) {
            return "IPv6";
        }
        
        return "Neither";
    }

}
