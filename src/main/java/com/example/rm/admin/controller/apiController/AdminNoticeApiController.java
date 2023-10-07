package com.example.rm.admin.controller.apiController;

import com.example.rm.entity.Notice;
import com.example.rm.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminNoticeApiController {

    private final NoticeService noticeService;

    @RequestMapping(
            value = "/admin/notice",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> noticeInsert(@ModelAttribute Notice notice){
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
        notice.setId(Long.valueOf(id));
        noticeService.update(notice);
        return ResponseEntity.ok("Resource updated successfully");
    }


}
