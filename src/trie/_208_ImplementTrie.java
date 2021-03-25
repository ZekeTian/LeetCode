package trie;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * 
 * 实现 Trie 类，包含如下几个方法：
 * Trie()：初始化对象
 * void insert(String word)：向 trie 中插入一个字符串 word
 * boolean search(String word)：如果字符串 word 在 trie 中，则返回 true；否则，返回 false
 * boolean startsWith(String prefix)：如果 trie 之前插入的字符串 word 含有前缀 prefix，则返回 true；否则，返回 false
 */
public class _208_ImplementTrie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple")); // return True
        System.out.println(trie.search("app")); // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app")); // return True
    }
}

class Trie {

    private class Node {
        boolean isWord; // 标记当前节点是否是一个单词的结尾字符。如果是，则为 true，表明能构成一个单词；否则，为 false
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

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
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

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        // 遍历 word 中的字符，从根节点出发，根据 word 中的字符选择子节点方向，然后向下继续寻找
        Node current = root; // Trie 中当前所处的节点位置
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (current.next.containsKey(c)) {
                // 如果根据字符能够找到对应的子节点，则继续向下寻找
                current = current.next.get(c);
            } else {
                // 如果根据字符未能找到对应的子节点，则说明 Trie 中不包含该单词，直接返回 false
                return false;
            }
        }

        return current.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        // 遍历 prefix 中的字符，从根节点出发，根据 prefix 中的字符选择子节点的方向，从而继续向下走
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (current.next.containsKey(c)) {
                current = current.next.get(c);
            } else {
                // 无法找到当前字符 c 对应的子节点，说明 Trie 中不包含该字符，直接 return false
                return false;
            }
        }
        // 逐个遍历 prefix 中的字符，在 Trie 中可以一直走下去，说明 Trie 包含 prefix 中所有字符 
        return true;
    }
}