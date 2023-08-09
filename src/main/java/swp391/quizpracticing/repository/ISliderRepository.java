/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Slider;

import java.util.List;

/**
 *
 * @author Mosena
 */
@Repository
public interface ISliderRepository extends JpaRepository<Slider,Integer> {
    //   List<Slider> findByFeaturing(boolean isFeatured);

//
//    @Query(value = "select * from iquiz.posts where featuring = ?1 order by updated_date desc limit ?2", nativeQuery = true)
//    List<Posts> findByFeaturingOrderByUpdatedDate(boolean isFeatured, int limit);
//
//    @Query(value = "select * from iquiz.posts order by updated_date desc limit ?1", nativeQuery = true)
//    List<Posts> findByUpdatedDate(int limit);

    List<Slider> findByTitleContainingIgnoreCase(String searchTerm);
    @Override
    List<Slider> findAll();

    @Override
    Slider getById(Integer integer);

    @Override
    List<Slider> findAllById(Iterable<Integer> integers);

    boolean existsSliderByTitle(String title);
}
