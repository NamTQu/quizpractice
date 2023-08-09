/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Blog;
import swp391.quizpracticing.model.Blogcategory;

import java.util.List;


/**
 *
 * @author Mosena
 */
@Repository
public interface IBlogRepository extends JpaRepository<Blog,Integer> {
//      List<Blog> findByFeaturing(boolean isFeatured);

//    @Query(value = "select * from iquiz.posts where featuring = ?1 order by updated_date desc limit ?2", nativeQuery = true)
//    List<Posts> findByFeaturingOrderByUpdatedDate(boolean isFeatured, int limit);
//
//    @Query(value = "select * from iquiz.blog order by updated_date desc limit ?1", nativeQuery = true)
//    List<Posts> findByUpdatedDate(int limit);

    List<Blog> findByTitleContainingIgnoreCase(String searchTerm);

//    List<Blog> findByCategoryIn(List<Blogcategory> categories);
    @Override
    List<Blog> findAll();

    @Override
    Blog getById(Integer integer);

    @Override
    List<Blog> findAllById(Iterable<Integer> integers);

    List<Blog> findByFeaturing(boolean isFeatured);

    @Query(value = "select * from iquiz.blog where featuring = ?1 order by last_updated desc limit ?2", nativeQuery = true)
    List<Blog> findByFeaturingOrderByUpdatedDate(boolean isFeatured, int limit);

    @Query(value = "select * from iquiz.blog order by last_updated desc limit ?1", nativeQuery = true)
    List<Blog> findByUpdatedDate(int limit);

    public Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query(value = "select distinct * from iquiz.blog b join iquiz.blog_category c on b.id = c.blog_id where c.category_id = ?1", nativeQuery = true)
    public List<Blog> findByCategories(int cate_id);

    public Long countById(Integer id);

    boolean existsBlogByBriefInfo(String title);
}
