package io.khasang.snet.service;

import io.khasang.snet.repository.PostDAO;
import io.khasang.snet.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    private PostDAO postDAO;

    @Autowired
    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public void addPost(Post post) {
       postDAO.addPost(post);
    }

    public Post getPostById(Long id) {
        return postDAO.getPostById(id);
    }

    public List<Post> getQuetionList() {
        return postDAO.getPostList();
    }

    public void updatePost(Post post) {
       postDAO.updatePost(post);
    }

    public void deletePost(Post post) {
       postDAO.deletePost(post);
    }

}
