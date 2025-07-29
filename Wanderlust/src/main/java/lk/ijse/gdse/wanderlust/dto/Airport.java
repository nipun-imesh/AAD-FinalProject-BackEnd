package lk.ijse.gdse.wanderlust.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Airport {
    private String airport_name;
    private String iata_code;
    private String icao_code;
    private String country_name;

}
