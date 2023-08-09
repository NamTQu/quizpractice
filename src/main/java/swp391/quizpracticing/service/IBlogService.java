/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.dto.BlogDTO;
import swp391.quizpracticing.model.Blog;
import swp391.quizpracticing.model.Blogcategory;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mosena
 */
public interface IBlogService {
    List<BlogDTO> getAllBlog();

    Page<Blog> getAllBlogsWithPagination(int pageNo);

    List<BlogDTO> searchBlogByTitle(String searchTerm);

    Blog getBlogById(Integer id);

    List<BlogDTO> getFeaturedBlog(boolean isFeatured);

    List<BlogDTO> getFeaturedLatestBlog(boolean isFeatured, int limit);

    List<BlogDTO> getLatestBlog(int limit);

    List<Blog> getFilteredBlog(List<String> category_id);

    Page<Blog> searchBlogByName(int page, String name);

    public Boolean checkIfBlogExistByBriefInfo(String title);

    public String uploadImage(MultipartFile file) throws IOException;

    Blog saveBlog(Blog blog);
}