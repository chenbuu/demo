package com.zjnu.bike.test.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zjnu.bike.Application;
import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.domain.User;
import com.zjnu.bike.enums.FileTypeEnum;
import com.zjnu.bike.enums.GenderEnum;
import com.zjnu.bike.enums.RoleEnum;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.FileInfoRepository;
import com.zjnu.bike.repository.UserRepository;
import com.zjnu.bike.util.RandUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据库数据模拟
 * @author ChenTao
 * @date 2015年11月19日下午9:42:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class UserTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Test
	//@Ignore
	public void save() throws Exception {
		System.out.println("---------------save----------------");
		List<User> uList = new ArrayList<User>();
		PageRequest pageRequest = new PageRequest(0, 40);
		List<FileInfo> fList = this.fileInfoRepository.findAll(pageRequest).getContent();
		for (FileInfo f : fList) {
			log.debug("{}", f);
		}
		List<FileInfo> bigImages = new ArrayList<FileInfo>();
		List<FileInfo> smallImages = new ArrayList<FileInfo>();
		for (FileInfo f : fList) {
			if (f.getFileType() == FileTypeEnum.BigImage) {
				bigImages.add(f);
			} else if (f.getFileType() == FileTypeEnum.SmallImage) {
				smallImages.add(f);
			}
		}
		for (int i = 0; i < 10; i++) {
			User u = new User();
			u.setUserName(RandUtil.getStringNumberRange(4, 10));
			u.setContactAddr(RandUtil.getStringChineseRange(8, 16));
			u.setCreateTime(RandUtil.getDate());
			u.setDescription(RandUtil.getStringChineseRange(8, 16));
			u.setEmail(RandUtil.getEmail());
			u.setGender(RandUtil.getEnum(GenderEnum.values()));
			u.setHeadPortraitId(RandUtil.getAttr(smallImages, "getDownload", String.class));
			u.setIdentityCard(RandUtil.getStringNumber(18));
			u.setJobNumber(RandUtil.getStringNumber(10));
			u.setPassword("1");
			u.setPhoneNumber(RandUtil.getStringNumber(13));
			u.setPhoneShorter(RandUtil.getStringNumber(6));
			u.setNickName(RandUtil.getStringChineseRange(2, 3));
			u.setRealName(RandUtil.getStringChineseRange(2, 3));
			u.setRole(RandUtil.getEnum(RoleEnum.values()));
			u.setStatus(RandUtil.getEnum(StatusEnum.values()));
			u.setZipcode(RandUtil.getStringNumber(6));
			uList.add(u);
		}
		this.userRepository.save(uList);
		uList.get(0).setUserName("1");
		this.userRepository.save(uList.get(0));
		System.out.println("-------------------------------");
	}

	@Test
	@Ignore
	public void findAll() throws Exception {
		System.out.println("--------------findAll-----------------");
		for (User model : this.userRepository.findAll()) {
			log.debug("{}", model);
		}
		System.out.println("-------------------------------");
	}

	@Test
	@Ignore
	public void addAttribute() throws Exception {
		System.out.println("--------------addAttribute-----------------");
		List<User> uList = this.userRepository.findAll();
		for (User model : uList) {
			model.setPhoneShorter(RandUtil.getStringNumber(6));
		}
		this.userRepository.save(uList);
		System.out.println("-------------------------------");
	}
}
