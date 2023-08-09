/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import java.util.List;

import swp391.quizpracticing.dto.DimensionDTO;
import swp391.quizpracticing.model.Dimension;


/**
 *
 * @author Mosena
 */
public interface IDimensionService {
    List<DimensionDTO> getAllDimension();

    Dimension getDimensionById(Integer id);

    List<Dimension> listAll();
}
