package algorithm.树.字典树;

import java.util.ArrayList;
import java.util.List;

public class 字典序排数 {
    public static void main(String[] args) {
        // [1,10,11,12,13,2,3,4,5,6,7,8,9]
        List<Integer> res = lexicalOrder(100);
        System.out.println(res);
    }

    public static List<Integer> lexicalOrder(int n) {
        Trie trie = new Trie();
        for (int i = 1; i <= n; i++) {
            trie.insert(i);
        }
        return trie.gen();
    }

    static class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            this.children = new Trie[10];
        }

        public void insert(int val) {
            Trie cur = this;
            String target = String.valueOf(val);
            char[] array = target.toCharArray();
            for (int i = 0; i < array.length; i++) {
                int index = array[i] - '0';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
            }
            cur.isEnd = true;
        }

        public List<Integer> gen() {
            List<Integer> result = new ArrayList<>();
            search(this, 0, result);
            return result;
        }

        // 深度优先遍历当前的节点，每个元素都加入到result中
        // prefix就是当前节点的上一个节点的值，从而方便计算当前节点的值
        public void search(Trie root, int prefix, List<Integer> result) {
            for (int i = 0; i < 10; i++) {
                if (root.children[i] != null) {
                    int now = prefix * 10 + i;
                    result.add(now);
                    search(root.children[i], now, result);
                }
            }
        }
    }
}
