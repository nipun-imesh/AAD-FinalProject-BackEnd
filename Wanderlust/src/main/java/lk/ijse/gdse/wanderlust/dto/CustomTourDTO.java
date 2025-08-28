package lk.ijse.gdse.wanderlust.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomTourDTO {

    private Integer id;
    private String country;
    private String city;
    private String date;
    private String duration;
    private int price;
    private String description;
    private String image;
    private String status;
}
