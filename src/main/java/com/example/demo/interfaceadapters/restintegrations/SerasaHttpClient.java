package com.example.demo.interfaceadapters.restintegrations;

import com.example.demo.businessrules.entities.Score;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "${serasa.name}", url = "${serasa.url:}")
public interface SerasaHttpClient {

    @RequestMapping(method = RequestMethod.GET, value = "/score/{document}",
            produces = APPLICATION_JSON_VALUE)
    Score getScore(@PathVariable(value = "document") final String document);

}
