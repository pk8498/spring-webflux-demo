package hyeyoung.demo

import lombok.extern.slf4j.Slf4j
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import spock.lang.Specification
import hyeyoung.demo.service.FluxTestService

@Slf4j
class StepVerifierTest extends Specification {

    FluxTestService fluxTestService = new FluxTestService()

    def "getFluxFromValues - 데이터를 방출하고 정상 종료되는지 테스트"() {

        when:
        Flux<String> flux = fluxTestService.getFluxFromValues()

        then:
        StepVerifier.create(flux)
                    .expectNext(fluxTestService.DUMMY_DATA_01
                                ,fluxTestService.DUMMY_DATA_02
                                ,fluxTestService.DUMMY_DATA_03)
                    .verifyComplete()
    }

    def "getFluxFromValues - 데이터를 순차적으로 방출하고 정상 종료되는지 테스트"() {

        when:
        Flux<String> flux = fluxTestService.getFluxFromValues()

        then:
        StepVerifier.create(flux)
                    .expectNextMatches({data -> fluxTestService.DUMMY_DATA_01.equals(data)})
                    .expectNextMatches({data -> fluxTestService.DUMMY_DATA_02.equals(data)})
                    .expectNextMatches({data -> fluxTestService.DUMMY_DATA_03.equals(data)})
                    .verifyComplete()
    }

    def "errorFlux - 에러 발생 테스트"() {

        when:
        Flux<String> flux = fluxTestService.errorFlux()

        then:
        StepVerifier.create(flux)
                    .expectError(RuntimeException.class)
    }


}
