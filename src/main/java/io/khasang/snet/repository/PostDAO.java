package io.khasang.snet.repository;

import io.khasang.snet.entity.Post;
import java.util.List;

public interface PostDAO {
    void addPost(Post post);

    void updatePost(Post post);

    void deletePost(Post post);

    Post getPostById(Long id);

    Post getPostByName(String title);

    List<Post> getPostList();

}

