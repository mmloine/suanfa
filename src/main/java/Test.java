import com.LinkedList;
import com.List;
import model.Person;

public class Test {
    public static void main(String[] args) {
        List<Person> arrayList = new LinkedList<>();
        arrayList.add(new Person(21,"asd"));
        arrayList.add(new Person(21,"sad"));
        arrayList.add(new Person(21,"aaad"));
        arrayList.add(new Person(21,"dsg"));
        System.out.println(arrayList.set(0,new Person(21,"dsg")));
        System.out.println(arrayList);



    }
}
