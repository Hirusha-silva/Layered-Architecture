package lk.ijse.gdse71.mrphone.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {
    private String supplier_id;
    private String name;
    private String phone_no;
    private String company;
}
