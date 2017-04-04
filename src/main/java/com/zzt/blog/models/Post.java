package com.zzt.blog.models;

/**
 * Created by zhaotao on 2017/4/3.
 */
public class Post {

    public long id;

    public String content;

    public enum PostType {
        教程,
        小说,
    }

    public PostType type;

    public Post(long id, String content, PostType t) {
        this.id = id;
        this.content = content;
        this.type = t;
    }
}
