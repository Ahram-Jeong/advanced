package ash.advanced.app.v5;

import ash.advanced.trace.callback.TraceCallback;
import ash.advanced.trace.callback.TraceTemplate;
import ash.advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final TraceTemplate template; // 생성자를 통해 딱 한 번만 생성하기 위함

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace); // 한 번만 생성, 메소드마다 새로 생성하지 않아도 됨
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "OKAY";
            }
        });
    }
}
