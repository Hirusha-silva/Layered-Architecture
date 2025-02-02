package lk.ijse.gdse71.mrphone.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepairDto {
    private String repairing_id;
    private String customer_id;
    private String description;
    private Date date;
    private String status;
}
