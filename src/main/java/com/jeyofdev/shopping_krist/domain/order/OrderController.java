package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.order.dto.SaveOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.ORDER)
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceBase orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<DomainSuccessResponse<List<OrderDTO>>> findAllOrder() {
        List<Order> orderList = orderService.findAll();
        List<OrderDTO> orderDTOList = orderList.stream().map(orderMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, orderDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<DomainSuccessResponse<OrderDTO>> findOrderById(@PathVariable("orderId") UUID orderId) {
        Order order = orderService.findById(orderId);
        OrderDTO orderDTO = orderMapper.mapFromEntity(order);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, orderDTO),
                HttpStatus.OK
        );
    }

    @PostMapping(ApiRoutes.PROFILE + "/{profileId}" + ApiRoutes.ADDRESS + "/{addressId}")
    public ResponseEntity<DomainSuccessResponse<OrderDTO>> saveOrder(
            @RequestBody SaveOrderDTO saveOrderDTO,
            @PathVariable("profileId") UUID profileId,
            @PathVariable("addressId") UUID addressId
    ) {
        Order order = orderMapper.mapToEntity(saveOrderDTO, null);
        Order newOrder = orderService.save(order, profileId, addressId);
        OrderDTO newOrderDTO = orderMapper.mapFromEntity(newOrder);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newOrderDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<DomainSuccessResponse<OrderDTO>> updateOrderById(
            @PathVariable("orderId") UUID orderId,
            @RequestBody SaveOrderDTO saveOrderDTO
    ) {
        Order existingOrder = orderService.findById(orderId);
        Order order = orderMapper.mapToEntity(saveOrderDTO, existingOrder);
        Order updateOrder = orderService.updateById(orderId, order);
        OrderDTO updateOrderDTO = orderMapper.mapFromEntity(updateOrder);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateOrderDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteOrderById(@PathVariable("orderId") UUID orderId) {
        String message = orderService.deleteById(orderId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
