package lk.ijse.gdse71.mrphone.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {
    private String orderId;
    private String itemId;
    private int qty;
    private double price;
}