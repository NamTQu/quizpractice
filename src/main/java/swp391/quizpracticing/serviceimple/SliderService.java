/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import jakarta.persistence.metamodel.SingularAttribute;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.dto.SliderDTO;
import swp391.quizpracticing.model.Slider;
import swp391.quizpracticing.repository.ISliderRepository;
import swp391.quizpracticing.service.ISliderService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Mosena
 */
@Service
public class SliderService implements ISliderService {

    public static final int SLIDERS_PER_PAGE = 4;

    //
//    private SliderDTO convertEntityToDTO(Slider entity){
//        return modelMapper.map(entity,SliderDTO.class);
//    }
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ISliderRepository iSliderRepository;

    @Override
    public List<SliderDTO> getAllSlider() {
        List<SliderDTO> results = new ArrayList<>();
        List<Slider> sliders = iSliderRepository.findAll();
        for(Slider slider : sliders) {
            SliderDTO sliderDTO = new SliderDTO();
            sliderDTO.sliderHomePage(slider);
            results.add(sliderDTO);
        }
        return results;
    }

//    @Override
//    public List<SliderDTO> getFeaturedSlider(boolean isFeatured) {
//        List<Slider> featuredSlider = iSliderRepository.findByFeaturing(isFeatured);
//        List<SliderDTO> results = new ArrayList<>();
//        for(Slider slider : featuredSlider) {
//            SliderDTO sliderDTO = new SliderDTO();
//            sliderDTO.sliderHomePage(slider);
//            results.add(sliderDTO);
//        }
//        return results;
//    }

    @Override
    public Page<Slider> getAllSlidersWithPagination(int pageNo) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Slider> sliders = iSliderRepository.findAll(PageRequest.of(pageNo, SLIDERS_PER_PAGE));

        return sliders;
    }

    @Override
    public List<SliderDTO> searchSliderByTitle(String searchTerm) {
        List<SliderDTO> results = new ArrayList<>();
        List<Slider> searchedSlider = iSliderRepository.findByTitleContainingIgnoreCase(searchTerm);
        for(Slider slider : searchedSlider) {
            SliderDTO sliderDTO = new SliderDTO();
            sliderDTO.sliderHomePage(slider);
            results.add(sliderDTO);
        }
        return results;
    }

    //    @Override
//    public List<PostsDTO> getFilteredPosts(List<PostCategory> categories) {
//        List<Posts> filteredPosts = iBlogRepository.findByPostCategoryIn(categories);
//        List<PostsDTO> results = new ArrayList<>();
//        for(Posts post : filteredPosts) {
//            PostsDTO postsDTO = new PostsDTO();
//            postsDTO.postsHomePage(post);
//            results.add(postsDTO);
//        }
//
//        return results;
//    }
    @Override
    public Slider getSliderById(Integer id) {
        return iSliderRepository.findById(id).get();
    }
    private SliderDTO convertEntityToDTO(Slider entity){
        return modelMapper.map(entity,SliderDTO.class);
    }

    @Override
    public List<SliderDTO> getAllSlidersForHomepage() {
        List<Slider> sliders = iSliderRepository.findAll();
        List<SliderDTO> results = new ArrayList<>();
        for (Slider slider : sliders) {
            SliderDTO sliderDTO = new SliderDTO();
            sliderDTO = convertEntityToDTO(slider);
            results.add(sliderDTO);
        }
        return results;
    }

    public void save(Slider slider) {
        iSliderRepository.save(slider);
    }

    public Slider get(Integer id) throws Exception {
        Optional<Slider> result = iSliderRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new Exception("Could not find any sliders with ID " + id);
    }

    @Override
    public Boolean checkIfSliderExistByTitle(String title) {
        return iSliderRepository.existsSliderByTitle(title);
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public Slider saveSlider(Slider slider) {
        return iSliderRepository.save(slider);
    }

}