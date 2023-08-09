/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Settings;

/**
 *
 * @author Mosena
 */
@Repository
public interface ISettingsRepository extends JpaRepository<Settings,Integer>, 
        JpaSpecificationExecutor<Settings> {
    @Override
    public Page<Settings> findAll(Specification spec,Pageable pageable);
    
    @Override
    public Settings save(Settings s);
    
    @Query(value = "select distinct type from settings;", nativeQuery = true)
    public List<String> findAllType();
    
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Transactional
    @Query("UPDATE Settings s SET s.value = :value, s.order = :order,"
            + " s.status = :status, s.description = :description "
            + "WHERE s.id = :settingsId")
    public int settingsUpdate(@Param("settingsId") Integer settingsId,
            @Param("value") String value, @Param("order") Integer order, 
            @Param("status") Boolean status, @Param("description") String description);
    
}
