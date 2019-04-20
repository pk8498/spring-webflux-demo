package com.hyeyoung.demo.web;

import com.hyeyoung.demo.service.FluxTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.hyeyoung.demo.service.MonoTestService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FluxTestService fluxTestService;

    @Autowired
    private MonoTestService monoTestService;

    @GetMapping("/flux/empty")
    public Flux<String> emptyFlux() {
        return fluxTestService.emptyFlux();
    }

    @GetMapping("/flux/value")
    public Flux<String> getFluxFromValues() {
        return fluxTestService.getFluxFromValues();
    }

    @GetMapping("/flux/list")
    public Flux<String> getFluxFromList() {
        return fluxTestService.getFluxFromList();
    }

    @GetMapping("/flux/error")
    public Flux<String> errorFlux() {
        return fluxTestService.errorFlux();
    }

    @GetMapping("/flux/counter")
    public Flux<Long> counter() {
        return fluxTestService.counter();
    }

    @GetMapping("/mono/empty")
    public Mono<String> emptyMono() {
        return monoTestService.emptyMono();
    }

    @GetMapping("/mono/value")
    public Mono<String> getMonoFromValue() {
        return monoTestService.getMonoFromValue();
    }

    @GetMapping("/mono/noSignal")
    public Mono<String> monoWithNoSignal() {
        return monoTestService.monoWithNoSignal();
    }

    @GetMapping("/mono/error")
    public Mono<String> errorMono() {
        return monoTestService.errorMono();
    }

}
