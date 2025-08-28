package lk.ijse.gdse.wanderlust.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class CustomTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String city;
    private String date;
    private String duration;
    private String description;
    private int price;
    private String image;
    private String status;

    @OneToMany(mappedBy = "customTour")
    private List<TicketBooking> bookings;
}
