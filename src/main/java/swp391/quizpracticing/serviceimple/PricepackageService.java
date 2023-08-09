/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.PricepackageDTO;
import swp391.quizpracticing.model.Pricepackage;
import swp391.quizpracticing.repository.IPricepackageRepository;
import swp391.quizpracticing.service.IPricepackageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Mosena
 */
@Service
public class PricepackageService implements IPricepackageService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IPricepackageRepository pricepackageRepository;


    private PricepackageDTO convertEntityToDTO(Pricepackage entity){
        return modelMapper.map(entity,PricepackageDTO.class);
    }

    @Override
    public PricepackageDTO getById(Integer id) {
        return convertEntityToDTO(
                pricepackageRepository.findById(id).orElse(null));
    }

    @Override
    public Pricepackage getPricepackageById(Integer id) {
        return pricepackageRepository.findPricepackageById(id);
    }

    @Override
    public List<PricepackageDTO> getBySubjectId(Integer id) {
        return pricepackageRepository.getPricepackageBySubjects(id)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PricepackageDTO> getAll() {
        return pricepackageRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pricepackage> listAll() {
        return null;
    }

    @Override
    public Pricepackage getPricepackageBySubId(Integer sid) {
        return pricepackageRepository.findById(sid).get();
    }

    @Override
    public List<Pricepackage> getPricepackageBySubject(Integer sid) {
        return pricepackageRepository.getPricepackageBySubjects(sid);
    }

    public void save(Pricepackage pricepackage) {
        pricepackageRepository.save(pricepackage);
    }

    public Pricepackage get(Integer id) throws Exception {
        Optional<Pricepackage> result = pricepackageRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new Exception("Could not find any pricepackages with ID " + id);
    }

    public void delete(Integer id) throws Exception {
        Long count = pricepackageRepository.countById(id);
        if (count == null || count == 0) {
            throw new Exception("Could not find any pricepackages with ID " + id);
        }
        pricepackageRepository.deleteById(id);
    }
}