package lk.ijse.gdse71.mrphone.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTm {
    private String itemId;
    private String itemName;
    private int cartQty;
    private double unitPrice;
    private double total;
    private Button removeBtn;

}
