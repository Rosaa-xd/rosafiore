package eu.rosafiore.blogbackend.blogpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/blog")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        blogPostService.CreateBlogPost(blogPost);
    }
}
