package com.zjnu.bike.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.zjnu.bike.webclient.CampusNewsClient;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务具体实现类(在这里写你要执行的内容)
 * @author ChenTao
 * @date 2015年7月22日下午9:18:19
 */
@Service
@Slf4j
public class MySchedule {

	@Autowired
	private CampusNewsClient campusNewsClient;

	//每5秒执行一次
	//@Scheduled(cron = "0/5 * * * * ?")
	//每天早上2：00触发
	//@Scheduled(cron = "0 0 2 * * ?")
	public void run() {
		try {
			// 信息
			log.debug("信息: 任务定时器开始执行");
			log.debug("info: schedule start");
			// 在这里写你要执行的内容
			System.out.println("在这里写你要执行的内容");
			// 信息
			log.debug("info: schedule end");
			log.debug("信息: 任务定时器执行结束");
		} catch (Exception e) {
			log.error("------定时任务发生异常------/");
			log.error("------schedule error------/" + e);
		}
	}

	//每6小时执行一次
	@Scheduled(cron = "0 0 0/6 * * ?")
	public void getCampusNews() {
		try {
			log.debug("信息: 任务定时器开始执行");
			log.debug("info: getCampusNews start");
			campusNewsClient.detailHandler();
			campusNewsClient.outDetailHandler();
			log.debug("info: getCampusNews end");
			log.debug("信息: 任务定时器执行结束");
		} catch (Exception e) {
			log.error("------定时任务发生异常------/");
			log.error("------schedule error------/" + e);
		}
	}

}