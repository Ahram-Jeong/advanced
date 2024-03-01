package ash.advanced.trace.hellotrace;

import ash.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("Bonjour");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "WOODZ");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("Bonjour");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "WOODZ");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}