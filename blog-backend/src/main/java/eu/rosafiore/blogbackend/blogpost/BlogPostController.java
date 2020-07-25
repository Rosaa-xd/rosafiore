package eu.rosafiore.blogbackend.blogpost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController(value = "/blogPost")
public class BlogPostController {

    @PostMapping()
    public boolean createPost() {
        BlogPost blogPost = new BlogPost();
        blogPost.setEndPoint("/test");
        blogPost.setPublishDate(new Date());
        blogPost.setCategories(Arrays.asList("TestCategory"));
        blogPost.setTags(Arrays.asList("TestTag"));
        blogPost.setArticle("testPost.md");

        return false;
    }
}
