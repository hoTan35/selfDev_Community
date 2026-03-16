package com.example.selfdev.repository;

import com.example.selfdev.entity.Post;
import com.example.selfdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthor(User author);
}
