package onlineshopapp.fashionstore.service;

import onlineshopapp.fashionstore.model.Clothes;
import onlineshopapp.fashionstore.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Clothes> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username, Long productId);

}
