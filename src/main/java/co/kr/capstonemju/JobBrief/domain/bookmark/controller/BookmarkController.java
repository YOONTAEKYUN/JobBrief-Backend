package co.kr.capstonemju.JobBrief.domain.bookmark.controller;

import co.kr.capstonemju.JobBrief.domain.bookmark.controller.dto.BookmarkDto;
import co.kr.capstonemju.JobBrief.domain.bookmark.service.BookmarkService;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/bookmark")
@RestController
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping("/")
    @PreAuthorize("hasRole('MEMBER')")
    public void addBookmark(@RequestBody BookmarkDto bookmarkDto, @CurrentUser Member member) {
        bookmarkService.clickBookmark(bookmarkDto, member);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MEMBER')")
    public NewsListDto getBookmarkList(@CurrentUser Member member,@RequestParam(defaultValue = "1") int page){
        return bookmarkService.getBookmarkList(member, page);
    }


}
