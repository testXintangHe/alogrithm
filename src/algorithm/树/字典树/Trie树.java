package algorithm.树.字典树;


// 字典树就是一个头节点，下面有一个子节点数组，每个节点标识一个特定的字符 / 数字等，从而快速的进行检索

// 初始状态  头

// 插入abc       头
// 插入a        a
// 插入b       b
// 插入c      c

// 插入bc        头
// 插入b        a  b
// 插入c       b    c
//           c

// 插入ab，在插入a的时候发现已经存在，插入b的时候发现也存在，就给b节点打上 isEnd 就行
public class Trie树 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }

    static class Trie {
        // 当前节点的值
        public char val;
        // 当前节点后面的子节点，因为只有26个字母，所以长度是26
        public Trie[] children;
        // 当前节点是否作为单词的结尾
        public boolean isEnd;

        public Trie() {
            children = new Trie[26];
        }

        public void insert(String word) {
            // this就是开头的空节点，这样所有的节点都挂在这个空节点下面，从而方便后面的检索
            Trie cur = this;
            char[] array = word.toCharArray();
            for (int i = 0; i < array.length; i++) {
                // 如果 cur.children[index] == null，说明当前节点没有插入过，那么就插入
                int index = array[i] - 'a';
                if (cur.children[index] == null) {
                    Trie now = new Trie();
                    now.val = array[i];
                    cur.children[index] = now;
                }
                // 节点后移，并判断是否是最后一个节点，是的话就打上 isEnd 标识
                cur = cur.children[index];
                if (i == array.length - 1) {
                    cur.isEnd = true;
                }
            }
        }

        public boolean search(String word) {
            Trie node = prefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie node = prefix(prefix);
            return node != null;
        }

        // 从头开始遍历，查看当前前缀是否存在，存在就返回前缀的最后一个节点
        public Trie prefix(String prefix) {
            Trie cur = this;
            char[] array = prefix.toCharArray();
            for (int i = 0; i < array.length; i++) {
                int index = array[i] - 'a';
                if (cur.children[index] != null) {
                    cur = cur.children[index];
                } else {
                    return null;
                }
            }
            return cur;
        }
    }
}
