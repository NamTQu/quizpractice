package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.BlogDTO;
import swp391.quizpracticing.dto.BlogcategoryDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.Blog;
import swp391.quizpracticing.model.Blogcategory;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.service.IBlogService;
import swp391.quizpracticing.service.IBlogcategoryService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private IBlogcategoryService iBlogCategoryService;

    @GetMapping("/blog/all")
    public String listByPage(@RequestParam(name = "page", required = false) Integer page, Model model, HttpSession session) {

        //Loggedin User (using Session)
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        System.out.println("pageNum="+page);

        //Get latest blogs
        List<BlogDTO> latestBlogs = iBlogService.getLatestBlog(2);

        //Get all categories
        List<BlogcategoryDTO> postCategories = iBlogCategoryService.getAllCategories();

        //Get Paginated Blogs with corresponding categories

        //Add to model
        model.addAttribute("lastestBlogs", latestBlogs);
        model.addAttribute("postCategories", postCategories);
        model.addAttribute("searchTerm", "");
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("paginationLinks", null);

        if (page == null) {
            //Get All post with their corresponding categories (with pagination)
            Page<Blog> postsWithPagination = iBlogService.getAllBlogsWithPagination(0);
            model.addAttribute("posts", postsWithPagination);
            model.addAttribute("page", 1);
            return "blogs_list/blogs_list";
        } else {
            Page<Blog> postsWithPagination = iBlogService.getAllBlogsWithPagination(page - 1);
            model.addAttribute("posts", postsWithPagination);
            model.addAttribute("page", page);
        }

        return "blogs_list/blogs_list";
    }

    @GetMapping("/blog/all/search")
    public String searchPosts(@RequestParam(name = "searchTerm", defaultValue = "") String searchTerm, @RequestParam(name = "page", required = false) Integer page, Model model, HttpSession session) {

        System.out.println("Call searchPosts() method!!!");

        //Loggedin User (using Session)
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        if(searchTerm.isEmpty()) {
            return "redirect:/blog/all";
        }

        //Get all categories
        List<BlogcategoryDTO> postCategories = iBlogCategoryService.getAllCategories();

        //Get latest blogs
        List<BlogDTO> latestBlogs = iBlogService.getLatestBlog(2);

        //Add to model
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("lastestBlogs", latestBlogs);
        model.addAttribute("postCategories", postCategories);
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("paginationLinks", null);

        if (page == null) {
            //Get All post with their corresponding categories (with pagination)
            Page<Blog> postsWithPagination = iBlogService.searchBlogByName(0, searchTerm);
            model.addAttribute("posts", postsWithPagination);
            model.addAttribute("page", 1);
            return "blogs_list/blogs_list";
        } else {
            Page<Blog> postsWithPagination = iBlogService.searchBlogByName(page - 1, searchTerm);
            model.addAttribute("posts", postsWithPagination);
            model.addAttribute("page", page);
        }

        return "blogs_list/blogs_list";
    }


    @GetMapping("/blog/all/filter")
    public String filterPosts(@RequestParam("selectedCategories") List<String> selectedCategories, @RequestParam(name = "page", defaultValue = "0") Integer page, Model model, HttpSession session) {

        System.out.println("Filter function is called");

        if(selectedCategories.contains("-1")) {
            return "redirect:/blog/all";
        }

        System.out.println("selectedCategories: " + selectedCategories.toString());

        Page<Blog> blogWithPagination;

        //Find Selected Post Category by id
        List<Blogcategory> filteredCategories = new ArrayList<>();
        for(String selectedCategory : selectedCategories) {
            Blogcategory postCategory = new Blogcategory();
            postCategory = iBlogCategoryService.findByPostCategoryId(Integer.parseInt(selectedCategory));
            filteredCategories.add(postCategory);
        }

        //Get Filtered Posts by Selected Post Categories
        List<Blog> filteredPosts = iBlogService.getFilteredBlog(selectedCategories);

        System.out.println("Filtered Blogs size: " + filteredPosts.size());
        System.out.println("Filtered Blogs: ");
        for(Blog blog : filteredPosts) {
            System.out.println(blog.getTitle());
        }

        int totalBlogs = filteredPosts.size();

        if (page != 0) {
            page--;
        }
        System.out.println("page: " + page);

        List<Blog> filteredBlogsPagination = new ArrayList<>();
        int startItem = page*4;
        int toIndex = Math.min(startItem + 4, filteredPosts.size());
        System.out.println("startIndex: " + startItem + ", toIndex: " + toIndex);
        filteredBlogsPagination = filteredPosts.subList(startItem, toIndex);
        System.out.println("filteredBlogsPagination: ");
        for(Blog blog : filteredBlogsPagination) {
            System.out.println(blog.getTitle());
        }

        blogWithPagination = new PageImpl<>(filteredBlogsPagination, PageRequest.of(page, 4).withSort(Sort.by(Sort.Direction.ASC, "id")), totalBlogs);

        StringBuilder pagination_links = new StringBuilder(selectedCategories.get(0));
        for(int i = 1; i < selectedCategories.size(); i++) {
            pagination_links.append(",").append(selectedCategories.get(i));
        }

        //Get all categories
        List<BlogcategoryDTO> postCategories = iBlogCategoryService.getAllCategories();

        //Get latest blogs
        List<BlogDTO> latestBlogs = iBlogService.getLatestBlog(2);

        model.addAttribute("posts", blogWithPagination);
        model.addAttribute("postCategories", postCategories);
        model.addAttribute("lastestBlogs", latestBlogs);
        model.addAttribute("searchTerm", "");
        model.addAttribute("paginationLinks", pagination_links.toString());

        model.addAttribute("userSession", session.getAttribute("user"));
        return "blogs_list/blogs_list";
    }

}
