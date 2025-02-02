package lk.ijse.gdse71.mrphone.dto.tm;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerTm {
    private String customer_id;
    private String name;
    private String phone_no;
    private String email;

}