package backToBackSwe.recursion;

import java.util.Map;
import java.util.Stack;

public class B2BIterativeSolution {
    private static class JumpNode<T> {
        T data;
        int order = -1;
        JumpNode next;
        JumpNode randomJump;

        public JumpNode(T data) {
            this.data = data;
        }

        public JumpNode(T data, JumpNode<T> next, JumpNode<T> randomJump) {
            this.data = data;
            this.randomJump = randomJump;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        } // Clever. Use the Object's toString() instead. Incase its a class with its own .toString() method.
    }

    public static JumpNode setJumpOrder(JumpNode head) {
    /*
      We can model the Depth First Search with our own stack
      (instead of the call stack).
    */

        Stack<JumpNode> stack = new Stack<>();

        int currentOrder = 0;
        stack.push(head);

        while (!stack.isEmpty()) {
            JumpNode node = stack.pop();

            if (node != null && node.order == -1) { // TODO: We can push null to DSs. Order is to know if we have visited before.
                node.order = currentOrder;

                currentOrder += 1;

                // Priority goes to the jump node, we push it last.
                // It will be popped first next iteration to be searched on.
                stack.push(node.next);
                stack.push(node.randomJump);
            }
        }

        return head;
    }

    public static void main(String[] args) {
        JumpNode<Character> A = new JumpNode<Character>('a');
        JumpNode<Character> B = new JumpNode<Character>('b');
        JumpNode<Character> C = new JumpNode<Character>('c');
        JumpNode<Character> D = new JumpNode<Character>('d');

        A.next = B; A.randomJump = B;
        B.next = C; B.randomJump = D;
        C.next = D; C.randomJump = B;

        JumpNode result = setJumpOrder(A);
        System.out.println(result);

        JumpNode<Character> E = new JumpNode<Character>('e');
        JumpNode<Character> F = new JumpNode<Character>('f');
        JumpNode<Character> G = new JumpNode<Character>('g');
        JumpNode<Character> H = new JumpNode<Character>('h');
        JumpNode<Character> I = new JumpNode<Character>('i');
        JumpNode<Character> J = new JumpNode<Character>('j');
        JumpNode<Character> K = new JumpNode<Character>('k');

        E.next = F; E.randomJump = G;
        F.next = G; G.randomJump = I;
        G.next = H; I.randomJump = K;
        H.next = I;
        I.next = J;
        J.next = K;

        JumpNode result2 = setJumpOrder(E);
        System.out.println(result2);
    }
}
