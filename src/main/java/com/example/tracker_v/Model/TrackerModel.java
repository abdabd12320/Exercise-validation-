package com.example.tracker_v.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackerModel {

    @NotEmpty(message = "id should be not empty")
    @Size(min = 3,max = 4,message = "id should be between 3 and 4")
    private String id;
    @NotEmpty(message = "title should be not empty")
    @Size(min = 9,message = "title should be more than 8")
    private String title;
    @NotEmpty(message = "description should be not empty")
    @Size(min = 16,message = "description should be more than 15")
    private String description;
    @NotEmpty(message = "status should be not empty")
    @Pattern(regexp = "Not Started|Progress|Completed",message = "status should be Not Started, Progress and Completed")
    private String status;
    @NotEmpty(message = "companyName should be not empty")
    @Size(min = 7,message = "companyName should be more than 6")
    private String companyname;
}
