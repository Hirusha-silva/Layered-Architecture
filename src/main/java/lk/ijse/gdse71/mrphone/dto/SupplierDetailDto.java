package lk.ijse.gdse71.mrphone.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDetailDto {
    private String supplier_id;
    private String item_id;
    private int qty;
    private double price;
}
