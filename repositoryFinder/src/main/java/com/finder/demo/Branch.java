package com.finder.demo;

import lombok.Data;
import lombok.Getter;

@Data
public class Branch {
    private String name;
    private Commit commit;

    @Data
    public static class Commit {
        private String sha;

    }
}
