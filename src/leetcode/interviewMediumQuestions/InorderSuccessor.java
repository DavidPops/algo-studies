package leetcode.interviewMediumQuestions;

public class InorderSuccessor {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public String toString() {
            return data + "";
        }
    }

    public static Node root;

    public static void main(String[] args) {

        root = new Node(6);
        root.left = new Node(4);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(5);
        root.right.right = new Node(12);
        root.right.left = new Node(8);
        root.right.left.left = new Node(7);
        root.right.left.right = new Node(9);

        /*for any rightmost node of a tree which is also the greatest
         * node in a tree, Inorder successor can not exist as it is
         * the very last node to get visited in the inorder*/
        System.out.println(getInsucc(5));

    }

    public static int getInsucc(int data) {

        /*find the object of the Node having data equal to given data */
        Node node = find(root, data);

        /* if the right child of the node whose inorder successor
         * is to be found is not null, then it means its inorder
         * successor will lie in the right side only having actual
         * value equal to smallest node in its right subtree*/
        if (node.right != null) {
            return rightsmallest(node).data;
        } else {
            /* if if the right child of the node whose inorder successor
             * is to be found is null, then we need to run an efficient
             * traversal starting from root looking for a value just greater
             * than given node */
            nextGreaterEfficient(root, data);
            return inSuc.data;
        }

    }

    public static Node find(Node node, int data) {
        /*negative basecase : if node is null, return null*/
        if (node == null) {
            return null;
        }
        /*positive basecase : node found*/
        if (node.data == data) {
            return node;
        }

        /* result of recursive call to left subtree*/
        Node lr = find(node.left, data);

        /* result of recursive call to left subtree*/
        Node rr = find(node.right, data);

        /*if left subtree result and right subtree result, both
         * are null, this means, node does not lie in either of
         * the subtree of current node, and hence return null*/
        if (lr == null && rr == null) {
            return null;
            /*if only one side is null. then node was found in
             * the other subtree and hence return the result of
             * ecursive call to that subtree*/
        } else if (rr == null) {
            return lr;

            /*we have to have atleast one of either left result
             * or right result to be null, as a node can not be
             * present in both left and right subtree of any node*/
        } else {
            return rr;
        }
    }

    public static Node rightsmallest(Node node) {
        /* firstly, shift the current node to right child of the
         * current node. This can never lead to a null pointer
         * exception as we call this function only when we ensure
         * that the node has a right child*/
        Node curr = node.right;

        /* keep on shifting the current node to left, until we
         * find a node with no left child*/
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public static Node inSuc = null;
    public static void nextGreaterEfficient(Node node, int target)
    {
        if(node==null)
        {
            return;
        }
        /* when we find a node having value greater than the target
         * node, then there is no point in going right side,
         * as we will only find nodes having value greater than this
         * node, therefore we update the final answer and move to left
         * subtree*/
        if(node.data > target)
        {
            inSuc = node;
            nextGreaterEfficient(node.left, target);
        }else {
            nextGreaterEfficient(node.right, target);
        }
    }

}