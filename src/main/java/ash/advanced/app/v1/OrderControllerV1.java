package ash.advanced.app.v1;

import ash.advanced.trace.TraceStatus;
import ash.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);

            return "OKAY";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 정상 흐름으로 처리되는 것을 방지하기 위해 예외를 꼭 던져주어야 함
        }
    }
}
