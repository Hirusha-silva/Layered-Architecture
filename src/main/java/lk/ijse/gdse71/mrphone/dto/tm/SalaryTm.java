package lk.ijse.gdse71.mrphone.dto.tm;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaryTm {
    private String salary_id;
    private String employee_id;
    private double amount;
    private Date date;
}
