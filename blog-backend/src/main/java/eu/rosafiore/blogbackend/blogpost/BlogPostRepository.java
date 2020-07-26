package eu.rosafiore.blogbackend.blogpost;

import eu.rosafiore.blogbackend.blogpost.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends MongoRepository <BlogPost, Long> {
}
