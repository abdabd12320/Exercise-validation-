package com.example.eventsystem.EventModel;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Model {

    @NotEmpty(message = "id should be not empty")
    @Size(min = 3,message = "id should be more than 2")
    private String id;
    @NotEmpty(message = "description should be not empty")
    @Size(min = 16,message = "description should be more than 15")
    private String description;
    @NotNull(message = "capacity should be not null")
    @PositiveOrZero(message = "capacity should be positive ot zero number")
    @Min(value = 26,message = "capacity should be more than 25")
    @Digits(message = "capacity should be numbers", integer = 10, fraction = 0)
    private int capacity;
    @NotEmpty(message = "startDate should be not empty")
    private String startDate;
    @NotEmpty(message = "endDate should be not empty")
    private String endDate;
}