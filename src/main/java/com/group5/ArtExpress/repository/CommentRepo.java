package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
