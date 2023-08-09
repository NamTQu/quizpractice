/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.dto.BlogDTO;
import swp391.quizpracticing.model.Blog;
import swp391.quizpracticing.model.Blogcategory;
import swp391.quizpracticing.repository.IBlogRepository;
import swp391.quizpracticing.service.IBlogService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Mosena
 */
@Service
public class BlogService implements IBlogService {
    public static final int BLOGS_PER_PAGE = 4;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IBlogRepository iBlogRepository;
    private BlogDTO convertEntityToDTO(Blog entity){
        return modelMapper.map(entity,BlogDTO.class);
    }

    @Override
    public List<BlogDTO> getAllBlog() {
        List<Blog> blogs = iBlogRepository.findAll();
        return blogs
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<BlogDTO> getFeaturedBlog(boolean isFeatured) {
        List<Blog> featuredBlog = iBlogRepository.findByFeaturing(isFeatured);
        return featuredBlog
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Blog> getAllBlogsWithPagination(int pageNo) {
        Page<Blog> blogs = iBlogRepository.findAll(PageRequest.of(pageNo, BLOGS_PER_PAGE));
        return blogs;
    }

    @Override
    public List<BlogDTO> searchBlogByTitle(String searchTerm) {
        List<Blog> searchedBlog = iBlogRepository.findByTitleContainingIgnoreCase(searchTerm);
        return searchedBlog
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Blog getBlogById(Integer id) {
        return iBlogRepository.findById(id).get();

    }

    @Override
    public List<BlogDTO> getFeaturedLatestBlog(boolean isFeatured, int limit) {
        List<Blog> latestBlog = iBlogRepository.findByFeaturingOrderByUpdatedDate(isFeatured, limit);
        return latestBlog
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<BlogDTO> getLatestBlog(int limit) {
        List<Blog> latestBlog = iBlogRepository.findByUpdatedDate(limit);
        return latestBlog
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Blog> getFilteredBlog(List<String> category_id) {
        List<Blog> filteredBlogs = new ArrayList<>();

        for(String id : category_id) {
            List<Blog> blogs = iBlogRepository.findByCategories(Integer.parseInt(id));
            filteredBlogs.addAll(blogs);
        }

        return filteredBlogs;
    }

    @Override
    public Page<Blog> searchBlogByName(int page, String name) {
        return iBlogRepository.findByTitleContainingIgnoreCase(name, PageRequest.of(page, 4));
    }

    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }

    public Blog get(Integer id) throws Exception {
        Optional<Blog> result = iBlogRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new Exception("Could not find any blogs with ID " + id);
    }

    public void delete(Integer id) throws Exception {
        Long count = iBlogRepository.countById(id);
        if (count == null || count == 0) {
            throw new Exception("Could not find any blogs with ID " + id);
        }
        iBlogRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfBlogExistByBriefInfo(String title) {
        return iBlogRepository.existsBlogByBriefInfo(title);
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return iBlogRepository.save(blog);
    }

}
