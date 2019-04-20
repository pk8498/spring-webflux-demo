package tv.anypoint.demo.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
@Service
public class FluxTestService {

    public static final String DUMMY_DATA_01 = "dummy01";
    public static final String DUMMY_DATA_02 = "dummy02";
    public static final String DUMMY_DATA_03 = "dummy03";


    public Flux<String> emptyFlux() {
        return Flux.empty();
    }

    public Flux<String> getFluxFromValues() {
        return Flux.just(DUMMY_DATA_01, DUMMY_DATA_02, DUMMY_DATA_03);
    }

    public Flux<String> getFluxFromList() {
        return Flux.fromIterable(Lists.newArrayList(DUMMY_DATA_01, DUMMY_DATA_02, DUMMY_DATA_03));
    }

    public Flux<String> errorFlux() {
        return Flux.error(RuntimeException::new);
    }

    /**
     * 1분 마다 0~9 까지 데이터 방출
     */
    public Flux<Long> counter() {
        return Flux.interval(Duration.ofMinutes(1)).take(10L);
    }

}
