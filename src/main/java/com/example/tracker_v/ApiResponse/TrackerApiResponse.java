package com.example.tracker_v.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

    @Data
    @AllArgsConstructor
    public class TrackerApiResponse {

        private String message;
        private String theDate;
    }