package backToBackSwe.recursion;

public class B2BRecursiveSolution {

    private static class JumpNode<T> {
        T data;
        int order = -1; // Seems this is where Moore got some of the -1 inspiration from as well.
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

    private static class IntegerWrapper {
        int value;

        IntegerWrapper(int value) {
            this.value = value;
        }
    }

    public static JumpNode setJumpOrder(JumpNode head) {
        IntegerWrapper order = new IntegerWrapper(0); // start ordering at 0

        setJumpOrderRecursiveHelper(head, order);

        return head;
    }

        /*
          A "jump-first" search prioritizes the jump field in search. We go
          deep into searching on the jump field to populate the 'order' field,
          come back, and then go deep into searching on the next field.
        */
        private static void setJumpOrderRecursiveHelper(JumpNode node, IntegerWrapper currentOrder) {
            if (node == null || node.order != -1) {
                return;
            }

            node.order = currentOrder.value;

            // Increment the counter
            currentOrder.value += 1;

            // First we recurse deeply into the 'jump' pointer
            setJumpOrderRecursiveHelper(node.randomJump, currentOrder);

            // Then we recurse deeply into the 'next' pointer
            setJumpOrderRecursiveHelper(node.next, currentOrder);
        }

    // TODO: Questions
        /*
            1. what recursive framework is used here??
            2. I can see a private recursive helper which is then used by a public method to access it.
            3. So it seems to be helper method recursion, the helper calls itself with the new right arguments.
            4. Why IntegerWrapper?? maybe so we keep the actual reference?? Instead of summing up.
        */
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
