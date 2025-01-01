package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.order.dto.SaveOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAllOrder() {
        List<Order> orderList = orderService.findAll();
        List<OrderDTO> orderDTOList = orderList.stream().map(orderMapper::mapFromEntity).toList();

        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable("orderId") UUID orderId) {
        Order order = orderService.findById(orderId);
        OrderDTO orderDTO = orderMapper.mapFromEntity(order);

        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @PostMapping("profile/{profileId}")
    public ResponseEntity<OrderDTO> saveOrder(
            @RequestBody SaveOrderDTO saveOrderDTO,
            @PathVariable("profileId") UUID profileId
    ) {
        Order order = orderMapper.mapToEntity(saveOrderDTO);
        Order newOrder = orderService.save(order, profileId);
        OrderDTO newOrderDTO = orderMapper.mapFromEntity(newOrder);

        return new ResponseEntity<>(newOrderDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrderById(
            @PathVariable("orderId") UUID orderId,
            @RequestBody SaveOrderDTO saveOrderDTO
    ) {
        Order order = orderMapper.mapToEntity(saveOrderDTO);
        Order updateOrder = orderService.updateById(orderId, order);
        OrderDTO updateOrderDTO = orderMapper.mapFromEntity(updateOrder);

        return new ResponseEntity<>(updateOrderDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("orderId") UUID orderId) {
       orderService.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
