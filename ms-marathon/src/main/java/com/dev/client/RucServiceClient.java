package com.dev.client;

import com.dev.domain.PersonaJuridica;
import com.dev.domain.response.PersonaJuridicaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ruc-service", url = "http://wsruc.com")
public interface RucServiceClient {

    @GetMapping("/Ruc2WS_JSON.php")
    PersonaJuridicaResponse obtenerInformacionPersonaJuridica(
            @RequestParam("tipo") Integer tipo,
            @RequestParam("ruc") String ruc,
            @RequestParam("token") String token
    );
}
