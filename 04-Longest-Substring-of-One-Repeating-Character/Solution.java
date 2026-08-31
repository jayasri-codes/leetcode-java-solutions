class Solution {
    static class Node {
        int len;
        char leftChar, rightChar;
        int pref, suff, best;

        Node() {}

        Node(char c) {
            len = 1;
            leftChar = rightChar = c;
            pref = suff = best = 1;
        }
    }

    Node[] tree;
    String s;

    private Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node res = new Node();
        res.len = a.len + b.len;
        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.pref = a.pref;
        if (a.pref == a.len && a.rightChar == b.leftChar) {
            res.pref = a.len + b.pref;
        }

        res.suff = b.suff;
        if (b.suff == b.len && a.rightChar == b.leftChar) {
            res.suff = b.len + a.suff;
        }

        res.best = Math.max(a.best, b.best);

        if (a.rightChar == b.leftChar) {
            res.best = Math.max(res.best, a.suff + b.pref);
        }

        return res;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(s.charAt(l));
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            tree[node] = new Node(c);
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, c);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {
        this.s = s;

        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] answer = new int[queryCharacters.length()];

        for (int i = 0; i < queryCharacters.length(); i++) {
            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            answer[i] = tree[1].best;
        }

        return answer;
    }
}
