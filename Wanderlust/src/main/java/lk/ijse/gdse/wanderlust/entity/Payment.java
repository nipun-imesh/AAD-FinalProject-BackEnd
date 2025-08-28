package lk.ijse.gdse.wanderlust.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PaymantId;
    private String PaymentDate;
    private String PaymentMethod;
    private double Amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
