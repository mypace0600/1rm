package com.example.rm;

import com.example.rm.entity.*;
import com.example.rm.machine.repository.MachineRepository;
import com.example.rm.member.repository.MemberRepository;
import com.example.rm.notice.repository.NoticeRepository;
import com.example.rm.record.repository.RecordRepository;
import com.example.rm.reply.repository.ReplyRepository;
import com.example.rm.reply.repository.ReplyRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringRunner.class)
@Slf4j
@Commit
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
class TestNoticeRepository {

	@Autowired
	private RecordRepository recordRepository;
	@Autowired
	private MachineRepository machineRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private ReplyRepositoryCustom replyRepositoryCustom;

	@Test
	public void A001_Notice_Reply() {

		Notice notice = noticeRepository.findById(2L).orElseThrow(()->{
			return new IllegalArgumentException("해당 공지는 없습니다.");
		});
		PageRequest pageRequest = PageRequest.of(0,10);
		List<Reply> replies = replyRepositoryCustom.findAllByNoticeId(pageRequest,notice);
		log.info("@@@@@@@ replies :{}",replies.toString());

	}

	@Test
	public void A002_Notice_Reply_add(){

		Notice notice = noticeRepository.findById(1L).orElseThrow(()->{
			return new IllegalArgumentException("해당 공지는 없습니다.");
		});
		Member member = memberRepository.findByUsername("admin").orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자는 없습니다.");
		});
		for(int i=0;i<4;i++){
			Reply reply = Reply.builder()
					.notice(notice)
					.textContent("test")
					.likeCount(0)
					.member(member)
					.build();
			replyRepository.save(reply);
		}
	}

	@Test
	public void A003_Record_id_조회(){
		Record record = recordRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException("@@@@@@@@@@@@ not found"));
		log.info("@@@@@@@@@@@@@@ record :{}", record.toString());
	}

	@Test
	public void A004_Record_특정유저의_전체기록_조회(){
		Member member = memberRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException("Member not found"));
		List<Record> recordList = recordRepository.findAllByMember(member);
		if(recordList.isEmpty()){
			log.error("@@@@@@@@@@@@@@@@ not found");
		} else {
			log.info(recordList.toString());
		}
	}

	@Test
	public void A005_Record_특정유저의_전체기록_삭제(){
		Member member = memberRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException("Member not found"));
		recordRepository.deleteAllByMember(member);
		List<Record> recordList = recordRepository.findAllByMember(member);
		if(recordList.isEmpty()){
			log.info("@@@@@@@@@@@@@@@@ okay done");
		} else {
			log.info("@@@@@@@@@@@@@@@@ fail");
			log.info(recordList.toString());
		}
	}


	@Test
	public void A006_Record_특정기구의_전체기록_삭제(){
		Machine machine = machineRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("Machine not found"));
		recordRepository.deleteAllByMachine(machine);
		List<Record> recordList = recordRepository.findAllByMachine(machine);
		if(recordList.isEmpty()){
			log.info("@@@@@@@@@@@@@@@@ okay done");
		} else {
			log.info("@@@@@@@@@@@@@@@@ fail");
			log.info(recordList.toString());
		}
	}

	@Test
	public void A007_Record_특정유저의_특정기구의_전체기록_삭제(){
		Member member = memberRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException("Member not found"));
		Machine machine = machineRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("Machine not found"));
		recordRepository.deleteAllByMemberAndMachine(member,machine);
		List<Record> recordList = recordRepository.findAllByMemberAndMachine(member,machine);
		if(recordList.isEmpty()){
			log.info("@@@@@@@@@@@@@@@@ okay done");
		} else {
			log.info("@@@@@@@@@@@@@@@@ fail");
			log.info(recordList.toString());
		}
	}

}
