import com.ArrayList1;
import com.CircleSingleLinkedList;
import com.List1;

public class Test {
    public static void main(String[] args) {
       /* List1 aa = new ArrayList1();
        for(int a = 1;a< 200;a++) {
            aa.add(a);
        }
        for(int a = 1;a< 200;a++) {
            aa.removeByelement(a);//参数 如果是int 就不能分辨是索引还是元素
        }
        System.out.println(aa);*/
       /* CircleSingleLinkedList<Integer> bb = new CircleSingleLinkedList<>();

        System.out.println(bb);*/
       List1 ll = new CircleSingleLinkedList<Integer>();
       for(int a = 0 ;a <10 ; a++){
           ll.add(a);
       }
    }
}
