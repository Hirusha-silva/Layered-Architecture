package lk.ijse.gdse71.mrphone.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemTm {
    private String item_id;
    private double price;
    private String brand;
    private int qty;
    private String description;

}
