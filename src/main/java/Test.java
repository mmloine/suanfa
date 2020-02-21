import com.DoubleLinkedList1;

public class Test {
    public static void main(String[] args) {
        DoubleLinkedList1<Object> aa = new DoubleLinkedList1<>();
        boolean empty = aa.isEmpty();
        aa.add(0);
        aa.add(1);
        aa.add(2);
        aa.add(3);
        aa.add(4);
        aa.remove(0);

        System.out.println(aa);
        System.out.println(aa.TestElement());


    }
}
