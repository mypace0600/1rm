package com.example.rm;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Member;
import com.example.rm.entity.Record;
import com.example.rm.enums.RoleType;
import com.example.rm.machine.repository.MachineRepository;
import com.example.rm.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@Slf4j
@Commit
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RequiredArgsConstructor
class TestRecordRepository {

	private final RecordRepository repository;

	@Test
	public void A001_Record_데이터_삽입() {
		Machine machine = Machine.builder()
				.machineName("test3")
				.machineType("test")
				.imgUrl("testtest")
				.thumbImgUrl("testtesttest")
				.videoUrl("testtesttesttest")
				.stimulatePoint("test")
				.build();

		Member member = Member.builder()
				.loginId("test")
				.userName("test")
				.password("123")
				.gender("male")
				.role(RoleType.USER)
				.email("test@test.com")
				.phone("010-0000-0000")
				.build();

		Record record = Record.builder()
				.machine(machine)
				.member(member)
				.count(15)
				.weight(15)
				.setCount(4)
				.build();

		repository.save(record);
	}

}
