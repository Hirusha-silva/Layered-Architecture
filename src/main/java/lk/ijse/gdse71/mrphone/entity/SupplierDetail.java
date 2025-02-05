package lk.ijse.gdse71.mrphone.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDetail {
    private String supplier_id;
    private String item_id;
    private int qty;
    private double price;
    private String brand;
}