package com.example.point.mapper;

import java.util.List;
import com.example.point.dto.PointHistoryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointHistoryMapper {
    List<PointHistoryDto> findByMemberId(Long memberId);
}
