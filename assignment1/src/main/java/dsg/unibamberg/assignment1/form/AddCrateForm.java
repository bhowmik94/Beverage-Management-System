package dsg.unibamberg.assignment1.form;

import com.google.gson.Gson;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AddCrateForm {
    @NotNull(message = "You must select a bottle")
    private Long bottleId;


    @NotNull(message = "Name can not be null, please provide a value")
    @NotEmpty(message = "Name can't be empty. Please give a proper value")
    private String name;


    private String beveragePic;

    @DecimalMin("0.0")
    private Double price = 0.0;

    @DecimalMin("0.0")
    private Double alcoholPercentage = 0.0;

    @Min(value = 1, message = "No of bottles must be greater than zero")
    private Integer noOfBottles = 0;

    @Min(value = 0)
    private Integer stock = 0;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
