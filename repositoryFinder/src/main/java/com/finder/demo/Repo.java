package com.finder.demo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
public class Repo {

    private String name;
    private Owner owner;
    private Branch[] branches;


    @Data
    public static class Owner {
        private String login;

    }

}
