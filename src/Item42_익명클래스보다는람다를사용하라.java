import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.DoubleBinaryOperator;

import static java.util.Comparator.comparingInt;
/*
comment 핵심정리

자바가 8로 판올림되면서 작은 함수 객체를 구현하는데 적합한 람다가 도입되었습니다.
익명 클래스는 함수형 인터페이스가 아닌 타입의 인스턴스를 만들때만 사용합니다. 람다는 작은 함수 객체를 아주 쉽게 표현할 수 있어서 함수형 프로그래밍의 지평을 열었습니다.
 */


// comment 람다는 이름이 없고 문서화도 못한다 따라서 코드 자체로 동작이 명확히 설명 되지 않거나 코드 줄 수가 많아지면 람다를 쓰지말자.

public class Item42_익명클래스보다는람다를사용하라 {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("1"); words.add("12");
        words.add("123"); words.add("1234");


        // comment 익명 클래스의 인스턴스를 함수 객체로 사용 - 낡은 기법!!!!
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        for(String s : words){
            System.out.println(s);
        }
        // comment 람다식을 함수 객체로 사용 - 익명 클래스 대체
        // comment 타입을 명시해야 코드가 더 명확할때마 제외하고는 람다의 모든 매개변수 타입은 생략하자
        Collections.sort(words, (s1,s2) -> Integer.compare(s1.length(), s2.length()));
        for(String s : words){
            System.out.println(s);
        }

        // comment 더 간결하게 람다식으로 만들어보자

        Collections.sort(words, comparingInt(String::length));
        for(String s : words){
            System.out.println(s);
        }
        words.sort(comparingInt(String::length));



    }
    // comment 상수별 클래스 몸체와 데이터를 사용한 열거 타입
    public enum Operation{

        PLUS("+"){
            public double apply(double x, double y){
                return x + y;
            }
        },
        MINUS("-"){
            public double apply(double x, double y){
                return x - y;
            }
        },
        TIMES("*"){
            public double apply(double x, double y){
                return x * y;
            }
        },
        DIVIDE("/"){
            public double apply(double x, double y){
                return x / y;
            }
        };

        private final String symbol;
        Operation(String symbol){
            this.symbol = symbol;
        }

        @Override
        public String toString(){
            return symbol;
        }

        public abstract double apply(double x,double y);
    }

    // comment 함수 객체 람다를 인스턴스 필드에 저장해 상수별 동작을 구현한 열거타입
    // comment 열거 타입 상수의 동작을 표현한 람다를 DoubleBinaryOperator 인터페이스 변수에 할당하였습니다.
    // comment java.util.function 패키지가 제공하는 다양한 함수 인터페이스(아이템 44) 중 하나 인수를 받아서 결과를 반환시켜준다(double)

    public enum Operation_lambda{
        PLUS("+", (x,y) -> x+y),
        MINUS("-", (x,y) -> x-y),
        TIMES("*", (x,y) -> x*y),
        DIVIDE("/", (x,y) -> x/y);

        private final String symbol;
        private final DoubleBinaryOperator op;

        Operation_lambda(String symbol, DoubleBinaryOperator op){
            this.symbol = symbol;
            this.op = op;
        }

        @Override
        public String toString(){
            return symbol;
        }

        public double apply(double x, double y){
            return op.applyAsDouble(x,y);
        }
    }
}
