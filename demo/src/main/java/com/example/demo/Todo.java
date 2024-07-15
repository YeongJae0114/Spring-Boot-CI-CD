package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Todo {
    private long id;
    private String content;

    public Todo(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
