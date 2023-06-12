package co.kr.capstonemju.JobBrief.domain.scrap.controller;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListForMemberDto;
import co.kr.capstonemju.JobBrief.domain.scrap.controller.dto.ScrapCreateRequest;
import co.kr.capstonemju.JobBrief.domain.scrap.service.ScrapService;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scrap")
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapService scrapService;

    @PostMapping("/new/{id}")
    @PreAuthorize("hasRole('MEMBER')")
    public void saveScrap (@CurrentUser Member member, @PathVariable Long id, @RequestBody @Valid ScrapCreateRequest scrapCreateRequest){
        scrapService.saveScrap(member, id, scrapCreateRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MEMBER')")
    public NewsListForMemberDto getAllScrap(@CurrentUser Member member, @RequestParam(defaultValue = "1") int page){
        return scrapService.getAllScrap(member, page);
    }

}
