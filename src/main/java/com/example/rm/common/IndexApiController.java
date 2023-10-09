package com.example.rm.common;

import com.example.rm.entity.Box;
import com.example.rm.entity.Machine;
import com.example.rm.entity.Member;
import com.example.rm.entity.Notice;
import com.example.rm.machine.service.MachineService;
import com.example.rm.member.service.MemberService;
import com.example.rm.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndexApiController {

    private final MemberService memberService;
    private final MachineService machineService;
    private final NoticeService noticeService;

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<Box> index(Authentication auth) {
        Box result = new Box();
        if(null != auth){
            String username = auth.getName();
            Member member = memberService.findByUsername(username);
            result.put("member",member);
        }
        PageRequest pageRequest = PageRequest.of(0,3);
        List<Machine> machineList = machineService.findAll(pageRequest);
        result.put("machineList",machineList);
        int machineCount = (int) machineService.getTotalCount();
        result.put("machineCount",machineCount);
        List<Notice> popupNoticeList = noticeService.findAllPopupNotice(pageRequest);
        result.put("popupNoticeList",popupNoticeList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
