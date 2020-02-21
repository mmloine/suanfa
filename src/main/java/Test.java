import com.ArrayList1;
import com.DoubleLinkedList1;
import com.List1;
import com.SingleLinkedList1;

import java.lang.reflect.Array;

public class Test {
    public static void main(String[] args) {
        List1 aa = new ArrayList1();
        for(int a = 1;a< 200;a++) {
            aa.add(a);
        }
        for(int a = 1;a< 200;a++) {
            aa.removeByelement(a);//参数 如果是int 就不能分辨是索引还是元素
        }
        System.out.println(aa);
    }
}
