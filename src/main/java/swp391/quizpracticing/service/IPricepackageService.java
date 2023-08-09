/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import swp391.quizpracticing.dto.PricepackageDTO;
import swp391.quizpracticing.model.Pricepackage;

import java.util.List;


/**
 *
 * @author Mosena
 */
public interface IPricepackageService {
    public List<PricepackageDTO> getAll();

    PricepackageDTO getById(Integer id);

    Pricepackage getPricepackageById(Integer id);

    List<PricepackageDTO> getBySubjectId(Integer id);

    public List<Pricepackage> listAll();

    public void save(Pricepackage pricepackage);

    Pricepackage getPricepackageBySubId(Integer sid);

    List<Pricepackage> getPricepackageBySubject(Integer sid);
}

