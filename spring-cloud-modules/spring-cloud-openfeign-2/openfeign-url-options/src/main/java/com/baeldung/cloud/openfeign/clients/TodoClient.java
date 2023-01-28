package com.baeldung.cloud.openfeign.clients;

import com.baeldung.cloud.openfeign.dto.Todo;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

@FeignClient(name = "todoClient")
public interface TodoClient {
    @RequestLine(value = "GET")
    Todo getPlaceholderObjectById(URI uri);
}
