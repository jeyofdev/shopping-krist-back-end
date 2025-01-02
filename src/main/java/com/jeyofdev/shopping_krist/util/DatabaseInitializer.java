package com.jeyofdev.shopping_krist.util;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;
import com.jeyofdev.shopping_krist.domain.cart.CartDomainMapper;
import com.jeyofdev.shopping_krist.domain.cart.CartService;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItemDomainMapper;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItemService;
import com.jeyofdev.shopping_krist.domain.product.Product;
import com.jeyofdev.shopping_krist.domain.product.ProductDomainMapper;
import com.jeyofdev.shopping_krist.domain.product.ProductService;
import com.jeyofdev.shopping_krist.domain.product.dto.SaveProductDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final ProductService productService;
    private final ProductDomainMapper productMapper;
    private final CartService cartService;
    private final CartDomainMapper cartMapper;
    private final CartItemService cartItemService;
    private final CartItemDomainMapper cartItemMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Database initialization started...");
        this.createDatas();
    }

    public static void initializeDatabase(String jdbcUrl, String user, String password, String dbName) {
        String createDbQuery = MessageFormat.format("CREATE DATABASE {0}", dbName);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
             Statement statement = connection.createStatement()) {

            // If the database does not exist, create it
            statement.executeUpdate(createDbQuery);
            System.out.println("Database created successfully!");
        } catch (SQLException e) {
            if (e.getSQLState().equals("42P04")) { // Code error for "Database already exists"
                System.out.println("The database already exists.");
            } else {
                System.err.println(MessageFormat.format("Error creating database : {0}", e.getMessage()));
                throw new RuntimeException(e);
            }
        }
    }

    private void createDatas() throws IOException {
        this.createProducts();
       /*this.createCarts();
       this.createCartItems();*/
    }

    private void createProducts() {
        Product productA = productMapper.mapToEntity(new SaveProductDTO(
                "adidas",
                "men red t-shirt",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus viverra ligula nec sapien imperdiet, eu mattis enim cursus. Donec tristique arcu eget dolor blandit, posuere porta mi rhoncus. Nulla non urna metus. Sed ullamcorper non quam id euismod. Pellentesque ac dolor et ante tincidunt dignissim. Ut cursus mi eu odio convallis, vitae accumsan ipsum luctus. Aenean massa sapien, pretium egestas sollicitudin nec, vulputate a diam. Vestibulum at lobortis urna.",
                11.99,
                15.99,
                23,
                Color.BLUE,
                Size.L
        ));

        Product productB = productMapper.mapToEntity(new SaveProductDTO(
                "nike",
                "short white",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus viverra ligula nec sapien imperdiet, eu mattis enim cursus. Donec tristique arcu eget dolor blandit, posuere porta mi rhoncus. Nulla non urna metus. Sed ullamcorper non quam id euismod. Pellentesque ac dolor et ante tincidunt dignissim. Ut cursus mi eu odio convallis, vitae accumsan ipsum luctus. Aenean massa sapien, pretium egestas sollicitudin nec, vulputate a diam. Vestibulum at lobortis urna.",
                25.99,
                35.99,
                2,
                Color.WHITE,
                Size.XL
        ));
        productService.save(productA);
        productService.save(productB);
    }

    /*private void createCarts() {
        Cart cartA = cartMapper.mapToEntity(new SaveCartDTO());
        Cart cartB = cartMapper.mapToEntity(new SaveCartDTO());

        cartService.save(cartA);
        cartService.save(cartB);
    }*/

    /*private void createCartItems() {
        CartItem cartItemA = cartItemMapper.mapToEntity(new SaveCartItemDTO(5));
        CartItem cartItemB = cartItemMapper.mapToEntity(new SaveCartItemDTO(2));

        UUID firstProductId = productService.findAll().getFirst().getId();
        UUID secondProductId = productService.findAll().get(1).getId();
        UUID firstCartId = cartService.findAll().getFirst().getId();

        cartItemService.save(cartItemA, firstProductId, firstCartId);
        cartItemService.save(cartItemB, secondProductId, firstCartId);
    }*/
}
