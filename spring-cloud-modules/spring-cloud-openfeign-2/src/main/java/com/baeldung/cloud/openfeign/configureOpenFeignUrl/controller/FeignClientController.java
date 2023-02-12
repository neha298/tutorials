package com.baeldung.cloud.openfeign.configureOpenFeignUrl.controller;

import com.baeldung.cloud.openfeign.configureOpenFeignUrl.clients.AlbumClient;
import com.baeldung.cloud.openfeign.configureOpenFeignUrl.clients.PostClient;
import com.baeldung.cloud.openfeign.configureOpenFeignUrl.clients.TodoClient;
import com.baeldung.cloud.openfeign.configureOpenFeignUrl.dto.Album;
import com.baeldung.cloud.openfeign.configureOpenFeignUrl.dto.Post;
import com.baeldung.cloud.openfeign.configureOpenFeignUrl.dto.Todo;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Import(FeignClientsConfiguration.class)
public class FeignClientController {
    private final AlbumClient albumClient;
    private final PostClient postClient;

    private final TodoClient todoClient;

    public FeignClientController(AlbumClient albumClient,
                                 PostClient postClient,
                                 Decoder decoder,
                                 Encoder encoder) {
        this.albumClient = albumClient;
        this.postClient = postClient;
        this.todoClient = Feign.builder().encoder(encoder).decoder(decoder).target(Target.EmptyTarget.create(TodoClient.class));
    }

    @GetMapping(value = "albums/{id}")
    public Album getAlbumById(@PathVariable(value = "id") Integer id) {
        return albumClient.getAlbumById(id);
    }

    @GetMapping(value = "posts/{id}")
    public Post getPostById(@PathVariable(value = "id") Integer id) {
        return postClient.getPostById(id);
    }

    @GetMapping(value = "todos/{id}")
    public Todo getTodoById(@PathVariable(value = "id") Integer id) {
        return todoClient.getTodoById(URI.create("https://jsonplaceholder.typicode.com/todos/" + id));
    }

}
