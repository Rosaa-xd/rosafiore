package eu.rosafiore.blogbackend.blogpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController()
public class BlogPostController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping
    public void createPost() {
        BlogPost blogPost = new BlogPost();
        blogPost.setEndPoint("/test");
        blogPost.setPublishDate(new Date());
        blogPost.setCategories(Arrays.asList("TestCategory"));
        blogPost.setTags(Arrays.asList("TestTag"));
        blogPost.setArticle("testPost.md");

        mongoTemplate.save(blogPost, "blogposts");
    }

    @GetMapping
    public List<BlogPost> getPost() {
        Query query = new Query();
        query.addCriteria(Criteria.where("endPoint").is("/test"));
        return mongoTemplate.find(query, BlogPost.class);
    }
}
