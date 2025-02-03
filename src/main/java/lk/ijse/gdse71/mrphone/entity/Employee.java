package lk.ijse.gdse71.mrphone.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String employee_id;
    private String name;
    private String phone_no;
    private String address;
}