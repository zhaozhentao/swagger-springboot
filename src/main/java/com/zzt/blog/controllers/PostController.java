package com.zzt.blog.controllers;

import com.zzt.blog.models.Post;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by zhaotao on 2017/4/3.
 */

@RestController
@RequestMapping("posts")
@Api(description = "posts api", tags = "Posts", consumes = "application/x-www-form-urlencoded")
public class PostController {

    @RequestMapping(value = "/{id}", method = GET)
    @ApiOperation(value = "获取post", notes = "根据post的id获取post详情", response = Post.class)
    public Post show(@PathVariable(name = "id") int id) {
        return new Post(id, "content", Post.PostType.教程);
    }

    @RequestMapping(value = "", method = GET)
    @ApiOperation(value = "获取posts", notes = "根据page获取posts", response = Post.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", value = "页码", paramType = "query", dataType = "int")
    })
    public ArrayList<Post> index(@RequestParam(name = "page") int page) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "content", Post.PostType.教程));
        posts.add(new Post(2, "content", Post.PostType.小说));
        return posts;
    }

    @RequestMapping(value = "", method = POST)
    @ApiResponse(code = 200, message = "创建成功")
    @ApiOperation(value = "创建post", notes = "创建一个post", response = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, value = "token", paramType = "header"),
            @ApiImplicitParam(name = "content", defaultValue = "defaultValue", value = "内容", paramType = "form"),
            @ApiImplicitParam(name = "type", allowableValues = "教程,小说", defaultValue = "教程", value = "类型", paramType = "form", dataType = "string")
    })
    public Post store(@RequestHeader String Authorization, @RequestParam String content, @RequestParam Post.PostType type) {
        return new Post(1, content, type);
    }

    @RequestMapping(value = "/{id}", method = PATCH)
    @ApiOperation(value = "更新post", notes = "更新post", response = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", defaultValue = "defaultValue", value = "内容", paramType = "form")
    })
    public Post update(@PathVariable(name = "id") int id, @RequestParam String content) {
        return new Post(id, content, Post.PostType.教程);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ApiOperation(value = "删除post", notes = "根据post id删除一条post", response = String.class)
    public HashMap<String, String> destroy(@PathVariable(name = "id") int id) {
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "ok");
        return message;
    }
}
