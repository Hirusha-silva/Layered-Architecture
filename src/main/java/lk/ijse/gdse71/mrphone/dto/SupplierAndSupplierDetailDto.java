package lk.ijse.gdse71.mrphone.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierAndSupplierDetailDto {
    private String supplier_id;
    private String item_id;
    private String name;
    private String phone_no;
    private String company;
    private int qty;
    private double price;

}
