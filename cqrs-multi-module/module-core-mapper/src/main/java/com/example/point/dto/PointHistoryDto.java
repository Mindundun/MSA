package com.example.point.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PointHistoryDto {
    private Long id;
    private Long memberId;
    private Long amount;
    private String type;
    private LocalDateTime createdAt;
}
