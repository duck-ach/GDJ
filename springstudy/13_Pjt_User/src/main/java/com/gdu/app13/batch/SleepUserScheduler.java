package com.gdu.app13.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app13.service.UserService;

@EnableScheduling // 이거 있어야 스케줄러 동작함
@Component
public class SleepUserScheduler {
	
	@Autowired
	private UserService userService;
	
	// 매일 새벽 1시. 6개 맞추면됨 (초 분 시 일 요일 달)
	@Scheduled(cron="0 0/1 * * * *")
	public void execute() {
		userService.sleepUserHandle();
	}
	
}
