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


@RunWith(SpringRunner.class)
@Slf4j
@Commit
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
class TestRecordRepository {

	@Autowired
	private RecordRepository repository;


	@Test
	public void A001_Record_데이터_삽입() {

		Member member = Member.builder()
				.id(5L)
				.loginId("test")
				.password("123")
				.userName("test")
				.email("test")
				.gender("male")
				.phone("123")
				.role(RoleType.USER)
				.oauth("kakao")
				.build();

		Machine machine = new Machine();
		machine.setId(5L);
		machine.setMachineName("test");

		Record record = Record.builder()
				.member(member)
				.machine(machine)
				.count(15)
				.weight(15)
				.setCount(4)
				.build();

		repository.save(record);
	}

}
