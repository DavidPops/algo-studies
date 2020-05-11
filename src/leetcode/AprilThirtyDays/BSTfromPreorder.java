package leetcode.AprilThirtyDays;

//import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class StackSolution {
    public TreeNode bstFromPreorder(int[] preorder) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        s.add(root);
        for(int i=1;i<preorder.length;i++){
            TreeNode x = null ;
            while(!s.isEmpty() && preorder[i] > s.peek().val){
                x = s.pop();
            }
            if(x != null){
                x.right = new TreeNode(preorder[i]);
                s.push(x.right);
            }else{
                s.peek().left = new TreeNode(preorder[i]);
                s.push(s.peek().left);
            }
        }
        return root ;
    }

    public static void main(String[] args) {
        StackSolution stackSolution = new StackSolution();
        stackSolution.bstFromPreorder(new int[]{8,5,1,7,10,12});
    }
}

class RecursiveSolution { // coded this in python with Moore
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int value : preorder) {
            root = insert(root, value);
        }
        return root;
    }

    public TreeNode insert(TreeNode tn, int n) {
        if (tn == null)
            return new TreeNode(n);
        if (tn.val > n) {
            tn.left = insert(tn.left, n);
        } else {
            tn.right = insert(tn.right, n);
        }
        return tn;
    }
    public static void main(String[] args) {
        RecursiveSolution recursiveSolution = new RecursiveSolution();
        recursiveSolution.bstFromPreorder(new int[]{8,5,1,7,10,12});
    }
}

class MagpoMagoSolution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0){
            return null;
        }

        return util(preorder, 0, preorder.length-1);
    }

    private TreeNode util(int[] preorder, int start, int end){
        if(start == end){
            return new TreeNode(preorder[start]);
        }

        if(start > end){
            return null;
        }


        final TreeNode node = new TreeNode(preorder[start]);
        final int index = getIndex(preorder, start+1, end, preorder[start]);
        node.left = util(preorder, start+1, index-1);
        node.right = util(preorder, index, end);
        return node;
    }

    private int getIndex(int[] preorder, int start, int end, int num){
        while(start <= end && num > preorder[start] ){
            start++;
        }

        return start;
    }
}

class RangeSolution {
    public TreeNode bstFromPreorder(int[] preorder) {

        return helper(preorder,0,preorder.length-1);
    }

    private TreeNode helper(int[] preorder, int start, int end){
        if(start>end)
            return null;

        TreeNode root = new TreeNode(preorder[start]);

        if(start==end)
            return root;


        //get the range for left sub tree
        int leftTreeEndIndex=start+1;
        while(leftTreeEndIndex<=end && preorder[leftTreeEndIndex]<preorder[start])
            leftTreeEndIndex++;

        root.left = helper(preorder, start+1, leftTreeEndIndex-1);
        root.right = helper(preorder, leftTreeEndIndex, end);

        return root;


    }
}

class MooreSolution {
    int idx = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return go(preorder, Integer.MAX_VALUE);
    }

    public TreeNode go(int[] preorder, int parent) {
        if (idx == preorder.length || preorder[idx] > parent) return null;
        int curr = preorder[idx++];
        TreeNode node = new TreeNode(curr);

        node.left = go(preorder, curr);
        node.right = go(preorder, parent);

        return node;
    }

    public static void main(String[] args) {
        MooreSolution s1 = new MooreSolution();
        s1.bstFromPreorder(new int[]{8,5,1,7,10,12});
    }
}

class ILikeSolution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0)
            return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        for (int i = 1; i < preorder.length; i++) {
            TreeNode cur = new TreeNode(preorder[i]);
            if (!stack.isEmpty() && cur.val < stack.peek().val)
                stack.peek().left = cur;
            else {
                TreeNode parent = null;
                while (!stack.isEmpty() && stack.peek().val < cur.val)
                    parent = stack.pop();
                parent.right = cur;
            }
            stack.push(cur);
        }

        return root;
    }

    public static void main(String[] args) {
        ILikeSolution s1 = new ILikeSolution();
        s1.bstFromPreorder(new int[]{8,5,1,7,10,12});
    }
}

class RecursiveSolution2 {
    int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, Integer.MAX_VALUE);
    }

    private TreeNode buildTree(int[] preorder, int parent) {
        TreeNode temp = new TreeNode(preorder[index++]);
        if (index >= preorder.length)
            return temp;
        if (preorder[index] < temp.val) {
            temp.left = buildTree(preorder, temp.val);
        }
        if (index >= preorder.length)
            return temp;
        if (preorder[index] > temp.val && preorder[index] < parent) {
            temp.right = buildTree(preorder, parent);
        }
        return temp;
    }

    public static void main(String[] args) {
        RecursiveSolution2 s1 = new RecursiveSolution2();
        s1.bstFromPreorder(new int[]{8,5,1,7,10,12});
    }
}