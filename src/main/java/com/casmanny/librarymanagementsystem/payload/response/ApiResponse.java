package com.casmanny.librarymanagementsystem.payload.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse {
    private String message;
    private Boolean status;
}
