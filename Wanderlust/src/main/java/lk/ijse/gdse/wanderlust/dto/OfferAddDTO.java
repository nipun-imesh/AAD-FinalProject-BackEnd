package lk.ijse.gdse.wanderlust.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OfferAddDTO {

    private String id;
    private String imageUrl;
    private String title;
    private String description;
    private String discountPrice;
    private String actualPrice;
}
