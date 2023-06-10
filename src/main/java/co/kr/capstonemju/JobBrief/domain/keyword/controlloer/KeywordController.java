package co.kr.capstonemju.JobBrief.domain.keyword.controlloer;

import co.kr.capstonemju.JobBrief.domain.keyword.service.KeywordService;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListForMemberDto;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keyword")
@RequiredArgsConstructor
public class KeywordController {
    private final KeywordService keywordService;

    @GetMapping("/search")
    public NewsListForMemberDto searchNewsListByKeyword(@CurrentUser Member member, @RequestParam String keyword, @RequestParam(defaultValue = "1") int page){
        return keywordService.searchNewsListByKeyword(member, keyword, page);
    }

}
