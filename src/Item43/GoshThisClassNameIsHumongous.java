package Item43;

import java.util.concurrent.Executor;
public class GoshThisClassNameIsHumongous {

    private static Executor service = null;
    public static void main(String[] args) {
        // 메서드 참조
        service.execute( GoshThisClassNameIsHumongous::action);

        // 람다
        service.execute(()->action());
    }

    private static void action() {

    }
}
