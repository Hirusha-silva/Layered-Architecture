package lk.ijse.gdse71.mrphone.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierAndSupplierDetail {
    private String supplier_id;
    private String item_id;
    private String name;
    private String phone_no;
    private String company;
    private int qty;
    private double price;

}