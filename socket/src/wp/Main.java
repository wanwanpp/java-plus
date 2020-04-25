package wp;

import java.sql.Timestamp;

/**
 * Created by 王萍 on 2017/8/8 0008.
 */
public class Main {
    public static void main(String[] args) {
//        List<Timestamp> dateLinkedList = new ArrayList<>(100000000);
//        List<Timestamp> dateLinkedList = new LinkedList<>();
        Timestamp[] dateLinkedList = new Timestamp[100000000];
//        HashSet<Timestamp> dateLinkedList = new HashSet<>(100000000);
        long before = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        for (int i = 0; i < 100000000; i++) {
            //[List]
//            dateLinkedList.add(timestamp);
//            timestamp=new Timestamp(System.currentTimeMillis());
//            dateLinkedList.add(new Timestamp(System.currentTimeMillis()));

            //[Array]
            dateLinkedList[i] = timestamp;
            timestamp = new Timestamp(System.currentTimeMillis());

            //[HashSet]
//            dateLinkedList.add(new Timestamp(System.currentTimeMillis()));
        }

        System.out.println(System.currentTimeMillis() - before);
    }
}
