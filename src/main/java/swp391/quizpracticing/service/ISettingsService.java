/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import java.util.List;
import org.springframework.data.domain.Page;
import swp391.quizpracticing.dto.SettingsDTO;

/**
 *
 * @author Mosena
 */
public interface ISettingsService {
    public void addSetting(String type,Integer order, String value,  String description);
    public Page<SettingsDTO> getSettings(int pageNo, int pageSize,
            String type,Boolean status, 
            String sortBy, String order, String value);
    public List<String> findTypes();
    public SettingsDTO findById(Integer id);
    public void updateSettings(Integer id, String value, Integer order, Boolean status, String description);
}
