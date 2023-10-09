package com.example.rm.admin.controller.apiController;

import com.example.rm.entity.Member;
import com.example.rm.entity.Notice;
import com.example.rm.entity.Reply;
import com.example.rm.member.service.MemberService;
import com.example.rm.notice.service.NoticeService;
import com.example.rm.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminReplyApiController {

    private final ReplyService replyService;



    @RequestMapping(
            value = "/admin/reply",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> replyInsert(@ModelAttribute Reply reply){
        replyService.save(reply);
        return ResponseEntity.ok("Resource saved successfully");
    }

    @RequestMapping(
            value = "/admin/reply/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String>  replyDelete(
            @PathVariable("id") int id
    ){
        replyService.delete(Long.valueOf(id));
        return ResponseEntity.ok("Resource deleted successfully");
    }

    @RequestMapping(
            value = "/admin/reply/{id}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<String>  replyUpdate(
            @PathVariable int id, @ModelAttribute Reply reply
    ) {
        Reply resultReply = replyService.findById(reply.getId());
        resultReply.setTextContent(reply.getTextContent());
        replyService.update(resultReply);
        return ResponseEntity.ok("Resource updated successfully");
    }


}
