package com.example.rm;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Member;
import com.example.rm.entity.Record;
import com.example.rm.enums.RoleType;
import com.example.rm.machine.repository.MachineRepository;
import com.example.rm.member.repository.MemberRepository;
import com.example.rm.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
class TestRecordRepository {

	@Autowired
	private RecordRepository recordRepository;
	@Autowired
	private MachineRepository machineRepository;
	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void A001_Record_데이터_삽입() {

		Member member = memberRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException("Member not found"));
		Machine machine = machineRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("Machine not found"));

		Record record = Record.builder()
				.member(member)
				.machine(machine)
				.count(15)
				.weight(15)
				.setCount(4)
				.build();

		recordRepository.save(record);
	}

	@Test
	public void A002_Record_전체_조회(){
		List<Record> recordList = recordRepository.findAll();
		if(recordList.isEmpty()){
			log.error("not found");
		} else {
			log.info(recordList.toString());
		}
	}

	@Test
	public void A003_Record_특정유저의_기록_전체_조회(){
		Member member = memberRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException("Member not found"));
		List<Record> recordList = recordRepository.findAllByMember(member);
		if(recordList.isEmpty()){
			log.error("not found");
		} else {
			log.info(recordList.toString());
		}
	}

}
