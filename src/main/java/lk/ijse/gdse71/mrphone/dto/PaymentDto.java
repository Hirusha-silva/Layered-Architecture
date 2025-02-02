package lk.ijse.gdse71.mrphone.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDto {

    private String pay_id;
    private String order_id ;
    private String amount ;
    private String  method;
}
