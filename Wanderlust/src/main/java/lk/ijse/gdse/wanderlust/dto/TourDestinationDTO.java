package lk.ijse.gdse.wanderlust.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourDestinationDTO {

    private long id;
    private String country;
    private String city;
    private String description;
    private String destinationimage;
    private String status;
    private double longitude;
    private double latitude;
}
