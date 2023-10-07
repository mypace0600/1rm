package com.example.rm.admin.controller.apiController;

import com.example.rm.entity.Member;
import com.example.rm.entity.Notice;
import com.example.rm.member.service.MemberService;
import com.example.rm.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminNoticeApiController {

    private final NoticeService noticeService;
    private final MemberService memberService;

    @RequestMapping(
            value = "/admin/notice",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> noticeInsert(@ModelAttribute Notice notice){
        Member member = memberService.findById(notice.getMemberId());
        notice.setMember(member);
        noticeService.save(notice);
        return ResponseEntity.ok("Resource saved successfully");
    }

    @RequestMapping(
            value = "/admin/notice/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String>  noticeDelete(
            @PathVariable("id") int id
    ){
        noticeService.delete(Long.valueOf(id));
        return ResponseEntity.ok("Resource deleted successfully");
    }

    @RequestMapping(
            value = "/admin/notice/{id}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<String>  noticeUpdate(
            @PathVariable int id, @ModelAttribute Notice notice
    ) {
        Member member = memberService.findById(notice.getMemberId());
        Notice resultNotice = noticeService.findById(notice.getId());
        resultNotice.setTitle(notice.getTitle());
        resultNotice.setTextContent(notice.getTextContent());
        resultNotice.setIsImportant(notice.getIsImportant());
        resultNotice.setIsPopup(notice.getIsPopup());
        LocalDateTime editNow = LocalDateTime.now();
        resultNotice.setEditedDate(Timestamp.valueOf(editNow));
        resultNotice.setMember(member);
        noticeService.update(resultNotice);
        return ResponseEntity.ok("Resource updated successfully");
    }


}
