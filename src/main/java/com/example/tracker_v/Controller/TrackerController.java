package com.example.tracker_v.Controller;


import com.example.tracker_v.ApiResponse.TrackerApiResponse;
import com.example.tracker_v.Model.TrackerModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

    @RestController
    @RequestMapping("/api/v1/tracker")
    public class TrackerController {

        LocalDateTime date = LocalDateTime.now();
        ArrayList<TrackerModel> trackers = new ArrayList<>();

        @GetMapping("/get")
        public ResponseEntity getTracker()
        {
            return ResponseEntity.status(200).body(trackers);
        }
        @PostMapping("/add")
        public ResponseEntity postTracker(@Valid @RequestBody TrackerModel tracker, Errors errors)
        {
            if(errors.hasErrors())
            {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            trackers.add(tracker);
            return ResponseEntity.status(200).body(new TrackerApiResponse("tracker added",date.toLocalDate().toString()));
        }
        @PutMapping("/update/{index}")
        public ResponseEntity putTracker(@PathVariable int index,@Valid @RequestBody TrackerModel tracker,Errors errors)
        {
            if(errors.hasErrors())
            {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            trackers.set(index,tracker);
            return ResponseEntity.status(200).body(new TrackerApiResponse("tracked updated",date.toLocalDate().toString()));
        }
        @DeleteMapping("/delete/{index}")
        public ResponseEntity deleteTracker(@PathVariable int index)
        {
            trackers.remove(index);
            return ResponseEntity.status(200).body(new TrackerApiResponse("tracker deleted",date.toLocalDate().toString()));
        }
        @PutMapping("/change/{index}")
        public ResponseEntity changeStatus(@PathVariable int index)
        {
            if(trackers.get(index).getStatus().equalsIgnoreCase("Not Started")) {
                trackers.get(index).setStatus("Progress");
            } else if (trackers.get(index).getStatus().equalsIgnoreCase("Progress")) {
                trackers.get(index).setStatus("Completed");
            }
            return ResponseEntity.status(200).body(new TrackerApiResponse("status changed",date.toLocalDate().toString()));
        }
        @GetMapping("/search/{s}")
        public ResponseEntity searchTracker(@PathVariable String s)
        {
            for (TrackerModel tracker : trackers) {
                if (tracker.getTitle().equals(s)) {
                    return ResponseEntity.status(200).body(tracker);
                }
            }
            return ResponseEntity.status(200).body(new TrackerApiResponse("is not found",date.toLocalDate().toString()));
        }
        @GetMapping("/get-company/{s}")
        public ResponseEntity getCompany(@PathVariable String s)
        {
            ArrayList<TrackerModel> newTrackerArrayList = new ArrayList<>();
            for (TrackerModel tracker: trackers)
            {
                if(tracker.getCompanyname().equalsIgnoreCase(s))
                {
                    newTrackerArrayList.add(tracker);
                }
            }
            return ResponseEntity.status(200).body(newTrackerArrayList);
        }
    }