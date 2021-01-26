package Item55;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class Item55_컬렉션에서최댓값을구하는코드_컬렉션이비었으면예외처리 {

    // Run Configuration -> program arguement설정
    public static void main(String[] args) {
        List<String> words = Arrays.asList(args);
        System.out.println(max(words));
        System.out.println(max1(words));
        System.out.println(max2(words));

        // 코드 55-4 옵셔널 활용 1 - 기본값을 정해둘 수 있다. (328쪽)
        String lastWordInLexicon = max2(words).orElse("단어 없음...");
        // 원하는 예외값을 던질 수 있습니다.
//        Toy myToy = max2(toys).orElseThrow(words).orElseThrow(TemperTantrumException::new);

//        Element lastNobleGas = max2(Elements.NOBLE_GASES).get();

        // 자바9 ProcessHandle ifPresent() 메서드

        ProcessHandle ph = new ProcessHandle() {
            @Override
            public long pid() {
                return 0;
            }

            @Override
            public Optional<ProcessHandle> parent() {
                return Optional.empty();
            }

            @Override
            public Stream<ProcessHandle> children() {
                return null;
            }

            @Override
            public Stream<ProcessHandle> descendants() {
                return null;
            }

            @Override
            public Info info() {
                return null;
            }

            @Override
            public CompletableFuture<ProcessHandle> onExit() {
                return null;
            }

            @Override
            public boolean supportsNormalTermination() {
                return false;
            }

            @Override
            public boolean destroy() {
                return false;
            }

            @Override
            public boolean destroyForcibly() {
                return false;
            }

            @Override
            public boolean isAlive() {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public boolean equals(Object other) {
                return false;
            }

            @Override
            public int compareTo(ProcessHandle other) {
                return 0;
            }
        };
        Optional<ProcessHandle> parentProcess = ph.parent();
        System.out.println("부모 PID" + (parentProcess.isPresent() ? String.valueOf(parentProcess.get().pid()) : "N/A"));
        System.out.println("부모 PID" + (ph.parent().map(h -> String.valueOf(h.pid())).orElse("N/A")));

        // 자바8 구현
//        streamOfOptionals
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//
//        streamOfOptionals
//                .flatMap(Optional::stream)
    }
    // 컬렉션에서 최댓값을 구한다 컬렉션이 비었으면 예외를 던진다.
    public static <E extends Comparable<E>> E max(Collection<E> c){

        if(c.isEmpty())
            throw new IllegalArgumentException("empty Collection");
        E result = null;
        for (E e : c) {
            if(result == null || e.compareTo(result) > 0){
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    // 컬렉션에서 최댓값을 구해 Optional<E>로 반환합니다.

    public static <E extends Comparable<E>> Optional<E> max1(Collection<E> c){
        if(c.isEmpty()){
            return Optional.empty();
        }
        E result = null;
        for (E e : c) {
            if(result == null || e.compareTo(result)>0){
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);
    }

    // 컬렉션에서 최댓값을 구해 Optional<E>로 반환합니다. (Stream)
    public static <E extends Comparable<E>> Optional<E> max2(Collection<E> c){
        return c.stream().max(Comparator.naturalOrder());
    }
}
