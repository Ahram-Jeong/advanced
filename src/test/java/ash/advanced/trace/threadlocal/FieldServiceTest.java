package ash.advanced.trace.threadlocal;

import ash.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        // 스레드 생성
        Runnable userA = () -> {
            fieldService.logic("WOODZ");
        };
        Runnable userB = () -> {
            fieldService.logic("MOODZ");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000); // 동시성 문제 발생X
        sleep(100); // 동시성 문제 발생O
        threadB.start();

        sleep(3000); // 메인 스레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
