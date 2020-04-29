package backToBackSwe.recursion;


import java.util.*;

public class jumpReferencesSLLIterative {
    LinkedList<Integer> list = new LinkedList<Integer>();

    private static class JumpNode<T> { // seems no need for static. We need it.
        T data;
        JumpNode<T> next, randomJump;

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

    public static Map<Integer, JumpNode> sortJumpOrder(JumpNode head) {
        int counter = 0;
        Map<Integer, JumpNode> annotationMap = new HashMap<Integer, JumpNode>();
        ArrayDeque<JumpNode> nextJumps = new ArrayDeque<>();
        ArrayDeque<JumpNode> nextNexts = new ArrayDeque<>();

        nextJumps.add(head);

        while (!nextJumps.isEmpty()) {
            JumpNode current = nextJumps.getFirst();
            JumpNode nextJump = current.randomJump;
            JumpNode nextNext = current.next;

            if (nextJump != null) nextJumps.add(nextJump);
            if (nextJump != nextNext && nextJump != null) nextNexts.add(nextNext);
            nextJumps.poll();
            // counter++;
            annotationMap.put(++counter, current);
        }

        while (!nextNexts.isEmpty()) {
            JumpNode current = nextNexts.getFirst();
            annotationMap.put(++counter, current);
            nextNexts.poll();
        }

        return annotationMap;
    }

    public static void main(String[] args) {
        JumpNode<Character> A = new JumpNode<Character>('a');
        JumpNode<Character> B = new JumpNode<Character>('b');
        JumpNode<Character> C = new JumpNode<Character>('c');
        JumpNode<Character> D = new JumpNode<Character>('d');

        A.next = B; A.randomJump = B;
        B.next = C; B.randomJump = D;
        C.next = D; C.randomJump = B;

        Map<Integer, JumpNode> result = sortJumpOrder(A);
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

        Map<Integer, JumpNode> result2 = sortJumpOrder(E);
        System.out.println(result2);
    }
}
