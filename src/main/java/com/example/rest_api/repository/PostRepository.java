package com.example.rest_api.repository;

import com.example.rest_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(
        value = "SELECT category, count(*) as count FROM posts GROUP BY category ORDER BY 2",
        nativeQuery = true
    )
    List<Object[]> getAggregatedPostsByCategory();
}
