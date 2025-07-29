package lk.ijse.gdse.wanderlust.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    private int status;
    private String message;
    private Object data;
}