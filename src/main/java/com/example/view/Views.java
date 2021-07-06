package com.example.view;

/**
 * 可以通过端点和实体中JsonView注解的配合决定返回哪些字段
 */
public class Views {

    /**
     * 面向公共的
     */
    public static class Public {
    }

    /**
     * 面向私有的
     */
    public static class Private extends Public {
    }
}
