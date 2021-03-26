package trie;

import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 *
 * 设计一个数据结构，该数据结构需要实现如下方法：
 * WordDictionary()：初始化对象
 * void addWord(word)：添加一个新字符串，该字符串之后可以被匹配
 * bool search(word)：如果 word 能够匹配到已添加的字符串，则返回 true；否则，返回 false。word 中可能包含 “.”，它表示可以匹配任意字母。
 */
public class _211_DesignAddAndSearchWordsDataSructure {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }
}

class WordDictionary {

    private class Node {
        boolean isWord;
        TreeMap<Character, Node> next;

        public Node() {
            this(false);
        }

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<Character, Node>();
        }
    }

    private Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        // 遍历 word 中的字符，从根节点出发，根据 word 中的字符选择子节点方向，然后向下继续寻找
        Node current = root; // Trie 中当前所处的节点位置
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (current.next.containsKey(c)) {
                current = current.next.get(c);
            } else {
                // 如果未找到字符对应的子节点，则创建该子节点
                Node child = new Node();
                current.next.put(c, child);
                current = child;
            }
        }

        current.isWord = true;
    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node root, String word, int index) {
        // 当 word 中所有的字符都匹配完，则终止递归。能否匹配到 word 这个单词，取决于匹配到的最后一个节点是否代表一个单词
        if (index == word.length()) {
            return root.isWord;
        }

        // 获取当前正在匹配的字符
        char c = word.charAt(index);
        if ('.' == c) {
            // 如果当前字符是 “.”，则所有子节点都需要进行匹配
            Set<Entry<Character, Node>> childNodes = root.next.entrySet();
            for (Entry<Character, Node> child : childNodes) {
                if (match(child.getValue(), word, index + 1)) {
                    return true; // 只要有一个子节点后面能够匹配完 word 剩余的字符，则说明 Trie 中包含 word，直接返回 true，无需对剩余的子节点进行匹配
                }
            }
            // 所有的子节点都不能继续匹配 word，则以 root 为根节点的 Trie 无法匹配到 word
            return false;
        } else {
            // 如果当前字符是一般字母，则判断能否根据当前字符找到对应的子节点
            // 如果根据当前字符未能找到对应的子节点，则 return false
            if (!root.next.containsKey(c)) {
                return false;
            }

            // 如果能找到对应的子节点，则以该子节点为根节点，继续匹配
            return match(root.next.get(c), word, index + 1);
        }
    }
}