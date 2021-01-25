package Item54;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Item54_null이아닌빈컬렉션이나배열을반환하라 {

    public static void main(String[] args) {
        Cheese shop = new Cheese();
        List<Cheese> cheeses = shop.getCheess1();
        if(cheeses != null && cheeses.contains(Cheese.STILTON));{
            System.out.println("hello guys~");
        }
    }


    private static class Cheese {
        public static final String STILTON = "STILTON";
        private final List<Cheese> cheesesInStock = new ArrayList<>();
        /**
         *
         * @return 매장 안의 모든 치즈 목록을 반환한다.
         * 단, 재고가 하나도 없다면 null을 반환한다.
         */
        public List<Cheese> getCheess1(){
            // 절대 이렇게 처리하지말것! 서버와 클라이언트에서 모두 방어코드를 작성해야하는 불편함이 있다.
            return cheesesInStock.isEmpty() ? null : new ArrayList<>(cheesesInStock);
        }


        /**
         * 빈 컬렉션을 반환하는 올바른 예
         * @return
         */
        public List<Cheese> getCheess2(){
            return new ArrayList<>(cheesesInStock);
        }

        /**
         * 최적화 - 빈 컬렉션을 매번 새로 할당하지 않도록 하였다.
         * @return
         */
        public List<Cheese> getCheess3(){
            return cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
        }

        /**
         * 길이가 0일수도 있는 배열을 반환하는 올바른 방법
         * @return
         */
        public Cheese[] getCheess4(){
            return cheesesInStock.toArray(new Cheese[0]);
        }

        /**
         * 최적화 - 빈배열을 매번 새로 할당하지 않도록 처리
         * @return
         */
        private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];
        public Cheese[] getCheess5(){
            return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
        }

        /**
         * 나쁜예- 배열을 미리 할당하면 성능이 나빠진다.
         * @return
         */
        public Cheese[] getCheess6(){
            return cheesesInStock.toArray(new Cheese[cheesesInStock.size()]);
        }
    }
}