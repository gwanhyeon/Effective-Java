package Item43;

import java.util.HashMap;
import java.util.Map;

public class Item43_람다보다는메서드참조를사용하라 {
    public static void main(String[] args) {


        //comment 람다보다도 더 간결하게 만드는 방법은 -> 메서드 참조
        Map<String,Integer> m = new HashMap<>();

        //comment java 8 추가된 Map merge 메서드를 사용하기 (키가 없다면 {키,쌍} 그대로저장, 있다면 현재값과 주어진값에 적용후 그 결과로 현재값을 덮어쓴다
        m.merge("1",1,(count, increment)->count+increment);
        System.out.println(m.get("1")); // 1
        m.merge("1",1,(count, increment)->count+increment);
        System.out.println(m.get("1")); // 2

        // comment 위와 같은 merge기능은 count, increment가 공간을 꽤나 차지하기때문에 이것을 더 쉽게 람다로 바꾸어보자.
        m.merge("1", 1, Integer::sum);
        System.out.println(m.get("1")); // 3

        // comment 항상 람다가 정답은 아니다. 메소드 참조가 더 나은경우도 있다.


    }

}
