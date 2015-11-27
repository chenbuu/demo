package com.zjnu.bike.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjnu.bike.domain.User;
import com.zjnu.bike.dto.UserDto;
import com.zjnu.bike.enums.RoleEnum;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.UserRepository;
import com.zjnu.bike.security.SessionSecurity;
import com.zjnu.bike.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	/**
	 * 登录
	 * 必要参数userName password
	 * @author ChenTao
	 * @date 2015年11月22日下午3:46:57
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public Map<String, Object> login(User user, String securityCode, HttpSession session, ModelMap map) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		log.debug("{}", user);
		if (user == null || StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())) {
			throw new Exception("user为空");
		}
		List<User> uList = this.userService.findAllByUserNameAndPassword(user.getUserName(), user.getPassword());
		if (uList.size() != 1) {
			throw new Exception("user错误");
		}
		User u = uList.get(0);
		session.setAttribute("user", u);
		dataMap.put("user", new UserDto(u));
		dataMap.put("success", true);
		return dataMap;
	}

	/**
	 * 注册
	 * 必要参数userName password
	 * 可选参数headPortraitId email realName gender phoneNumber identityCard jobNumber contactAddr zipcode description
	 * @author ChenTao
	 * @date 2015年11月22日下午3:46:57
	 */
	@ResponseBody
	@RequestMapping(value = "/register")
	public Map<String, Object> register(User user, HttpSession session, ModelMap map) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		log.debug("{}", user);
		if (user == null || StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())) {
			throw new Exception("user为空");
		}
		List<User> uList = this.userService.findAllByUserName(user.getUserName());
		if (uList.size() != 0) {
			throw new Exception("用户名已经存在");
		}
		user.setCreateTime(new Date());
		user.setRole(RoleEnum.Student);
		user.setStatus(StatusEnum.Use);
		user.setId(null);
		this.userService.save(user);
		session.setAttribute("user", user);
		dataMap.put("user", new UserDto(user));
		dataMap.put("success", true);
		return dataMap;
	}

	/**
	 * 表格分页查询
	 * 可选参数：除了id和password都可选
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listUser")
	public Page<User> listUser(User user, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", user);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.userService.findAll(user, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findUser")
	public UserDto findUser(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return new UserDto(this.userService.findOne(id));
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUser")
	public UserDto deleteUser(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			User old = this.userService.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return new UserDto(this.userService.save(old));
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertUser")
	public UserDto insertUser(User user, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", user);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (user != null) {
			return new UserDto(this.userService.insert(user));
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveUser")
	public UserDto saveUser(User user, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", user);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (user != null) {
			return new UserDto(this.userService.save(user));
		}
		return null;
	}

}
