package com.baeldung.cloud.openfeign.configureOpenFeignUrl.clients;

import com.baeldung.cloud.openfeign.configureOpenFeignUrl.dto.Todo;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

@FeignClient(name = "todoClient")
public interface TodoClient {
    @RequestLine(value = "GET")
    Todo getTodoById(URI uri);
}
