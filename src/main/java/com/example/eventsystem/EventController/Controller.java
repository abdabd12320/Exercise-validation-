package com.example.eventsystem.EventController;

import com.example.eventsystem.EventApiResponse.ApiResponse;
import com.example.eventsystem.EventModel.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v/event")
public class Controller {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime localDateTime = LocalDateTime.now();
    ArrayList<Model> events = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getEvent()
    {
        return ResponseEntity.status(200).body(events);
    }
    @PostMapping("/add")
    public ResponseEntity postEvent(@Valid @RequestBody Model e, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(e);
        return ResponseEntity.status(200).body(new ApiResponse("event added",localDateTime.toLocalDate().toString()));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity putEvent(@PathVariable int index,@Valid @RequestBody Model e,Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index,e);
        return ResponseEntity.status(200).body(new ApiResponse("event updated",localDateTime.toLocalDate().toString()));
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index)
    {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("event deleted",localDateTime.toLocalDate().toString()));
    }
    @PutMapping("/change/{index}/{n}")
    public ResponseEntity changeEvent(@PathVariable int n,@PathVariable int index)
    {
        events.get(index).setCapacity(n);
        return ResponseEntity.status(200).body(new ApiResponse("capacity changed",localDateTime.toLocalDate().toString()));
    }

    @GetMapping("/search/{i}")
    public ResponseEntity searchEvent(@PathVariable int i)
    {
        return ResponseEntity.status(200).body(events.get(i));
    }
}
