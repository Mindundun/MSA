package com.example.welfare.service;

import com.example.point.dto.PointHistoryDto;
import com.example.member.repository.MemberRepository;
import com.example.member.entity.Member;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.point.mapper.PointHistoryMapper;
import org.springframework.transaction.annotation.Transactional;
    
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WelfareService {
    private final MemberRepository memberRepository;
    private final PointHistoryMapper pointHistoryMapper;

    public Long getBalance(Long memberId) {
        Member member = memberRepository.getMemberById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        return member.getBalance();
    }

    public List<PointHistoryDto> getHistory(Long memberId) {
        List<PointHistoryDto> historyList = pointHistoryMapper.findByMemberId(memberId);
        if (historyList.isEmpty()) {
            throw new IllegalArgumentException("No history found for memberId: " + memberId);
        }
        return historyList;
    }
      
}
