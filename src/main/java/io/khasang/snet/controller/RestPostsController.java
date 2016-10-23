package io.khasang.snet.controller;

import io.khasang.snet.entity.Post;
import io.khasang.snet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class RestPostsController {

    @Autowired
    PostService postService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getPost(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            Long postId = Long.valueOf(inputId);
            Post post = postService.getPostById(postId);
            if (post != null) {
                return post;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "Post with id: " + postId + " not found.";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad Post id format: " + inputId;
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Post> getAllPost() {
        return postService.getQuetionList();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addPost(@RequestBody Post post, HttpServletResponse response) {
        try {
            postService.addPost(post);
            return post;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error adding Post: " + e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Object updatePost(@RequestBody Post post, HttpServletResponse response) {
        try {
            postService.updatePost(post);
            return post;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error updating Post: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deletePost(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            Long postId = Long.valueOf(inputId);
            Post post = postService.getPostById(postId);
            if (post != null) {
                postService.deletePost(post);
                return "Post #" + postId + " successfully deleted.";
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "Post with id: " + postId + " not found.";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad Post id format: " + inputId;
        }
    }
}
