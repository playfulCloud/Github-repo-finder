package com.finder.demo.util;

import lombok.Data;

@Data
public class Repo {

    private String name;
    private Owner owner;
    private Branch[] branches;
    private boolean fork;

    public boolean isFork() {
        return fork;
    }


    @Data
    public static class Owner {
        private String login;

    }

}
