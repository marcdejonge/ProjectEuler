package euler.collection;

public class IntLinkedList {
    public static IntLinkedList create(int[] values) {
        IntLinkedList node = null;
        for (int ix = values.length - 1; ix >= 0; ix--) {
            node = new IntLinkedList(values[ix], node);
        }
        return node;
    }

    private final int value;
    private final IntLinkedList next;

    private IntLinkedList(int value, IntLinkedList next) {
        this.value = value;
        this.next = next;
    }

    public IntLinkedList add(int value) {
        return new IntLinkedList(this.value, next == null ? new IntLinkedList(value, null) : next.add(value));
    }

    public int getValue() {
        return value;
    }

    public IntLinkedList tail() {
        return next;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append('[').append(value);
        for (IntLinkedList node = next; node != null; node = node.next) {
            sb.append(", ").append(node.value);
        }
        sb.append(']');
        return sb.toString();
    }
}
