package com.shinhansec.marketcapitalization.suggestion.controller;

import com.shinhansec.marketcapitalization.common.BaseException;
import com.shinhansec.marketcapitalization.common.BaseResponse;
import com.shinhansec.marketcapitalization.suggestion.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/suggestion")
public class SuggestionController {
    private final SuggestionService suggestionService;

    @PostMapping("")
    public BaseResponse<?> makeSuggestion(@RequestHeader("userId") Long userId,
                                          @RequestParam("stockId") Long stockId) {
        try {
            return new BaseResponse<>(suggestionService.saveNewSuggestion(userId, stockId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
