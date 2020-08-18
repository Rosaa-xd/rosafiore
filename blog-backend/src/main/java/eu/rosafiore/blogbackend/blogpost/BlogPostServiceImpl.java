package eu.rosafiore.blogbackend.blogpost;

import eu.rosafiore.blogbackend.exception.web.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void CreateBlogPost(BlogPost blogPost) {
        String[] endPoint = new String[] {blogPost.getEndPoint()};

        Query query = new Query();
        query.addCriteria(Criteria.where("endPoint").in(endPoint));

        boolean postExistsAlready = mongoTemplate.exists(query, BlogPost.class);

        if(!postExistsAlready) {
            mongoTemplate.save(blogPost, "blogposts");
        }
        else {
            throw new ResourceAlreadyExistsException("endpoint", blogPost.getEndPoint());
        }
    }
}
