package onlineshopapp.fashionstore.service;

<<<<<<< Updated upstream
public interface ClothesService {
=======
import onlineshopapp.fashionstore.model.Clothes;

import java.util.List;
import java.util.Optional;

public interface ClothesService {

    List<Clothes> findAll();

    Optional<Clothes> findById(Long id);
>>>>>>> Stashed changes
}
