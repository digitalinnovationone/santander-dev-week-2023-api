package me.dio.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "adviceSlipClient", url = "https://api.adviceslip.com")
public interface AiFeignClient {

    @GetMapping("/advice")
    AdviceResponse getAdvice();

    class AdviceResponse {
        private Slip slip;

        public Slip getSlip() {
            return slip;
        }

        public void setSlip(Slip slip) {
            this.slip = slip;
        }

        public static class Slip {
            private Long id;
            private String advice;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getAdvice() {
                return advice;
            }

            public void setAdvice(String advice) {
                this.advice = advice;
            }
        }
    }
}
