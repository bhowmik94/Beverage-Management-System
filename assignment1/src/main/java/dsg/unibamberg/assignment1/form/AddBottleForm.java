package dsg.unibamberg.assignment1.form;

import com.google.gson.Gson;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBottleForm {

    @NotNull(message = "Name can not be null, please provide a value")
    @NotEmpty(message = "Name can't be empty. Please give a proper value")
    private String name;

//    private MultipartFile image;

    private String picture;

    @DecimalMin("0.0")
    private Double price = 0.0;

    @DecimalMin("0.0")
    private Double alcoholPercentage = 0.0;

    private Double volume;

    @NotNull(message = "Supplier Name can not be null, please provide a value")
    @NotEmpty(message = "Supplier Name can't be empty. Please give a proper value")
    private String supplier;

    @Min(value = 0)
    private Integer stock = 0;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
