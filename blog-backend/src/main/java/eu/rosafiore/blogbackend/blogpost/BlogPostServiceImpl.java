package eu.rosafiore.blogbackend.blogpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void CreateBlogPost(BlogPost blogPost) {
        mongoTemplate.save(blogPost, "blogposts");
    }
}
