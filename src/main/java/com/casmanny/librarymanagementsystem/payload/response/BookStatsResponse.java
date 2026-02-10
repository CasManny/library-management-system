package com.casmanny.librarymanagementsystem.payload.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookStatsResponse {
    private long totalActive;
    private long totalAvailable;
}
