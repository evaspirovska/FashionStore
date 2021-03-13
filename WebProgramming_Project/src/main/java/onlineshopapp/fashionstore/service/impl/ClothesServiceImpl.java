package onlineshopapp.fashionstore.service.impl;

import onlineshopapp.fashionstore.service.ClothesService;
import org.springframework.stereotype.Service;

<<<<<<< Updated upstream
@Service
public class ClothesServiceImpl implements ClothesService {
=======
import java.util.List;
import java.util.Optional;

@Service
public class ClothesServiceImpl implements ClothesService {
    private final ClothesRepository clothesRepository;

    public ClothesServiceImpl(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    @Override
    public List<Clothes> findAll() {
        return clothesRepository.findAll();
    }

    @Override
    public Optional<Clothes> findById(Long id) {
        return this.clothesRepository.findById(id);
    }
>>>>>>> Stashed changes
}
