package lk.ijse.gdse71.mrphone.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {

    private String pay_id;
    private String order_id ;
    private String amount ;
    private String  method;
}