package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemPreviewDTO;
import com.jeyofdev.shopping_krist.domain.category.dto.CategoryDTO;
import com.jeyofdev.shopping_krist.domain.comment.dto.CommentDTO;
import com.jeyofdev.shopping_krist.domain.notification.dto.NotificationDTO;
import com.jeyofdev.shopping_krist.domain.order.Order;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.product.Product;
import com.jeyofdev.shopping_krist.domain.product.dto.ProductDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.ProductPreviewDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfileDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfilePreviewDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.SaveProfileDTO;
import com.jeyofdev.shopping_krist.domain.profileSettings.dto.ProfileSettingsDTO;
import com.jeyofdev.shopping_krist.format.*;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper implements IDomainMapper<Profile, ProfileDTO, SaveProfileDTO> {

    @Override
    public ProfileDTO mapFromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                NameFormat.get(profile),
                profile.getPhone(),
                profile.getUser().getEmail(),
                profile.getAddress(),
                getDeliveryAddressListResponse(profile),
                getProfileSettingsResponse(profile),
                getNotificationListResponse(profile),
                getOrderListResponse(profile),
                getProductListResponse(profile)
        );
    }

    @Override
    public Profile mapToEntity(SaveProfileDTO saveProfileDTO) {
        return Profile.builder()
                .firstname(saveProfileDTO.firstname())
                .lastname(saveProfileDTO.lastname())
                .phone(saveProfileDTO.phone())
                .address(saveProfileDTO.address())
                .build();
    }

    private ListRelationFormat<AddressDTO> getDeliveryAddressListResponse(Profile profile) {
        return ListRelationFormat.get(profile.getDeliveryAddressList(), deliveryAddress -> new AddressDTO(
                deliveryAddress.getId(),
                deliveryAddress.getName(),
                deliveryAddress.getPhone(),
                AddressFormat.get(deliveryAddress)
        ));
    }

    private ProfileSettingsDTO getProfileSettingsResponse(Profile profile) {
        return new ProfileSettingsDTO(
                profile.getProfileSettings().getId(),
                profile.getProfileSettings().getAppearance(),
                profile.getProfileSettings().isPushNotification(),
                profile.getProfileSettings().isEmailNotification()
        );
    }

    private ListRelationFormat<NotificationDTO> getNotificationListResponse(Profile profile) {
        return ListRelationFormat.get(profile.getNotificationList(), notification -> new NotificationDTO(
                notification.getId(),
                notification.getTitle(),
                notification.getDescription(),
                notification.isRead()
        ));
    }

    private ListRelationFormat<ProductPreviewDTO> getProductListResponse(Profile profile) {
        return ListRelationFormat.get(profile.getProductList(), product -> new ProductPreviewDTO(
                product.getId(),
                product.getBrand(),
                product.getName(),
                product.getDescription(),
                PriceFormat.get(product),
                product.getStock(),
                product.getColor(),
                product.getSize(),
                product.getCommentList().size(),
                getCategoryListResponse(product)
        ));
    }

    private ListRelationFormat<CommentDTO> getCommentListResponse(Product product) {
        return ListRelationFormat.get(product.getCommentList(), comment -> new CommentDTO(
                comment.getId(),
                comment.getTitle(),
                comment.getReview(),
                comment.getRating(),
                comment.getCreatedAt()
        ));
    }

    private ListRelationFormat<CategoryDTO> getCategoryListResponse(Product product) {
        return ListRelationFormat.get(product.getCategoryList(), category -> new CategoryDTO(
                category.getId(),
                category.getName()
        ));
    }

    private ListRelationFormat<OrderDTO> getOrderListResponse(Profile profile) {
        return ListRelationFormat.get(profile.getOrderList(), order -> new OrderDTO(
                order.getId(),
                order.getCreatedAt(),
                order.getStatus(),
                getProfilPreviewResponse(order),
                getOrderAddressResponse(order),
                getOrderCartItemListResponse(order)
        ));
    }

    private ProfilePreviewDTO getProfilPreviewResponse(Order order) {
        ProfilePreviewFormat profilePreviewFormat = ProfilePreviewFormat.get(order.getProfile());
        return ProfilePreviewDTO.fromFormat(profilePreviewFormat);
    }

    private AddressDTO getOrderAddressResponse(Order order) {
        return new AddressDTO(
                order.getShippingAddress().getId(),
                order.getShippingAddress().getName(),
                order.getShippingAddress().getPhone(),
                AddressFormat.get(order.getShippingAddress())
        );
    }

    private ListRelationFormat<CartItemPreviewDTO> getOrderCartItemListResponse(Order order) {
        return ListRelationFormat.get(order.getCartItems(), cartItem -> new CartItemPreviewDTO(
                cartItem.getId(),
                cartItem.getQuantity(),
                ProductPreviewFormat.get(cartItem.getProduct())
        ));
    }
}
