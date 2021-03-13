package onlineshopapp.fashionstore.service.impl;

import onlineshopapp.fashionstore.model.Clothes;
import onlineshopapp.fashionstore.model.ShoppingCart;
import onlineshopapp.fashionstore.model.User;
import onlineshopapp.fashionstore.model.enumerations.ShoppingCartStatus;
import onlineshopapp.fashionstore.model.exceptions.ProductAlreadyInShoppingCartException;
import onlineshopapp.fashionstore.model.exceptions.ProductNotFoundException;
import onlineshopapp.fashionstore.model.exceptions.ShoppingCartNotFoundException;
import onlineshopapp.fashionstore.model.exceptions.UserNotFoundException;
import onlineshopapp.fashionstore.repository.ShoppingCartRepository;
import onlineshopapp.fashionstore.repository.UserRepository;
import onlineshopapp.fashionstore.service.ClothesService;
import onlineshopapp.fashionstore.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ClothesService clothesService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ClothesService clothesService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.clothesService = clothesService;
    }

    @Override
    public List<Clothes> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getClothes();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Clothes product = this.clothesService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if(shoppingCart.getClothes()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getClothes().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}

