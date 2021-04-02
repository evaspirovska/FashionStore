package onlineshopapp.fashionstore.service;

import onlineshopapp.fashionstore.model.Clothes;

import onlineshopapp.fashionstore.model.ClothesGrade;

import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface ClothesService {
    Page<Clothes> findAll(int pageNumber, String sortField, String sortDir);

    Clothes findById(Long id);

    Clothes create( String name, String description, String image, String image1, String image2, String image3, double price, double grade, int quantitySizeS, int quantitySizeM, int quantitySizeL, int quantitySizeXL);

    Clothes update(Long id, String name, String description, String image, String image1, String image2, String image3, double price, double grade, int quantitySizeS, int quantitySizeM, int quantitySizeL, int quantitySizeXL);

    Clothes delete(Long id);

    Optional<Clothes> findOptionalById(Long id);


    void updateFinalGrade(Clothes clothes, List<ClothesGrade> clothesGrades);

    List<Clothes> listProductsByName(String name);

    List<Clothes> sortDescendingByDate();

    List<Clothes> sortAscendingAlphabetic();

    List<Clothes> sortDescendingByGrade();

    List<Clothes> listAll();

}
