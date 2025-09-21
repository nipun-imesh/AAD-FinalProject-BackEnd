package lk.ijse.gdse.wanderlust.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Long PaymantId;
    private String PaymentDate;
    private String PaymentMethod;
    private double Amount;

}
