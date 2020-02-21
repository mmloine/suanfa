import com.DoubleLinkedList;
import com.List;
import model.Person;

import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        DoubleLinkedList<Object> aa = new DoubleLinkedList<>();
        boolean empty = aa.isEmpty();
        aa.add(0);
        aa.add(2);
        aa.add(4);
        aa.add(45);
        aa.add(41);

        System.out.println(aa);
        aa.TestElement();

    }
}
