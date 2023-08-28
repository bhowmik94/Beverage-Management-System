package dsg.unibamberg.assignment1.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddToCartForm implements Serializable {
    private Long beverageId;
}
