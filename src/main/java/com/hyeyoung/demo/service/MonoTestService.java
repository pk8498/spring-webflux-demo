package com.hyeyoung.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MonoTestService {

    public static final String DUMMY_DATA_01 = "dummy01";

    public Mono<String> emptyMono() {
        return Mono.empty();
    }

    public Mono<String> getMonoFromValue() {
        return Mono.just(DUMMY_DATA_01);
    }

    /**
     * 어떤 데이터도 방출하지 않음
     */
    public Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

    public Mono<String> errorMono() {
        return Mono.error(RuntimeException::new);
    }


}
