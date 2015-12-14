package com.zjnu.bike.config;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.domain.User;
import com.zjnu.bike.enums.FileTypeEnum;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.gridfs.GridFSRepository;
import com.zjnu.bike.repository.FileInfoRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

/**
 * 上传
 * @author ChenTao
 * @date 2015年11月19日下午8:40:56
 */
@Controller
@Slf4j
public class Upload {

	@Autowired
	public MVCConfig config;

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Autowired
	private GridFSRepository gridFSRepository;

	/**
	 * 单个文件上传
	 * @author ChenTao
	 * @date 2015年11月19日下午8:41:04
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public FileInfo upload(MultipartFile file, FileTypeEnum fileType, HttpServletRequest request, HttpSession session, ModelMap map) throws Exception {
		if (file == null) {
			throw new Exception("file为空");
		}
		log.debug("{}", file.getOriginalFilename());
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		User user = (User) session.getAttribute("user");
		return upload(file.getInputStream(), file.getOriginalFilename(), request.getContentType(), fileType, user);
	}

	/**
	 * 
	 * @author ChenTao
	 * @date 2015年12月13日下午1:41:51
	 */
	public FileInfo upload(InputStream in, String originalFilename, String contentType, FileTypeEnum fileType, User user) throws Exception {
		FileInfo fileInfo = gridFSRepository.saveFile(in, originalFilename, contentType);
		if (fileInfo == null || StringUtils.isBlank(fileInfo.getDownload())) {
			throw new Exception("保存错误");
		}
		fileInfo.setStatus(StatusEnum.Use);
		if (fileType == null) {
			fileInfo.setFileType(FileTypeEnum.Unknow);
		} else {
			fileInfo.setFileType(fileType);
		}
		fileInfo.setOperatorId(user == null ? null : user.getId());
		fileInfoRepository.save(fileInfo);
		return fileInfo;
	}

	/**
	 * 图片上传
	 * @author ChenTao
	 * @date 2015年11月19日下午8:41:04
	 */
	@ResponseBody
	@RequestMapping("/uploadImage")
	public List<FileInfo> uploadImage(MultipartFile file, HttpServletRequest request, HttpSession session, ModelMap map) throws Exception {
		if (file == null) {
			throw new Exception("file为空");
		}
		log.debug("{}", file.getOriginalFilename());
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		User user = (User) session.getAttribute("user");
		return uploadImage(file.getInputStream(), null, file.getOriginalFilename(), request.getContentType(), user);
	}

	/**
	 * 
	 * @author ChenTao
	 * @date 2015年12月13日下午1:52:22
	 */
	public List<FileInfo> uploadImage(InputStream originalIn, BufferedImage bufferedImage, String originalFilename, String contentType, User user)
			throws Exception {
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FileInfo fileInfo1 = new FileInfo();
		if (bufferedImage != null && bufferedImage.getWidth() > 1) {
			fileInfo1 = upload(originalIn, originalFilename, contentType, FileTypeEnum.BigImage, user);
			fileInfos.add(fileInfo1);
			Thumbnails.of(bufferedImage).size(128, 128).outputFormat("jpg").outputQuality(0.2).toOutputStream(out);
		} else {
			//复制输入流
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = originalIn.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
			InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
			fileInfo1 = upload(stream1, originalFilename, contentType, FileTypeEnum.BigImage, user);
			fileInfos.add(fileInfo1);
			Thumbnails.of(stream2).size(128, 128).outputFormat("jpg").outputQuality(0.2).toOutputStream(out);
		}
		out.flush();
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		String firstName = originalFilename;
		if (firstName.indexOf(".") > -1) {
			firstName = firstName.substring(0, firstName.lastIndexOf("."));
		}
		FileInfo fileInfo2 = gridFSRepository.saveFile(in, "samll_" + firstName + ".jpg", contentType);
		if (fileInfo2 == null || StringUtils.isBlank(fileInfo2.getDownload())) {
			throw new Exception("保存错误");
		}
		fileInfo2.setStatus(StatusEnum.Use);
		fileInfo2.setFileType(FileTypeEnum.SmallImage);
		fileInfo2.setOperatorId(fileInfo1.getOperatorId());
		fileInfoRepository.save(fileInfo2);
		fileInfos.add(fileInfo2);
		log.debug("{}", fileInfos);
		return fileInfos;
	}

	/**
	 * 单个文件上传
	 * 不推荐使用
	 * @author ChenTao
	 * @date 2015年11月19日下午8:41:04
	 */
	/*@ResponseBody
	@RequestMapping("/upload")
	public Map<String, Object> upload(MultipartFile file, HttpSession session, ModelMap map) throws Exception {
		if (file == null) {
			throw new Exception("file错误");
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		log.debug("{}", file.getOriginalFilename());
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		User user = (User) session.getAttribute("user");
		FileInfo fileInfo = new FileInfo();
		fileInfo = gridFSRepository.saveFile(file.getInputStream());
		fileInfo.setStatus(StatusEnum.Use);
		fileInfo.setOperatorId(user == null ? null : user.getId());
	
		//打算存放文件的路径
		String fileUrl = DateFormat.getUploadDateStr() + "/";
		String filepath = config.getUploadPath() + fileUrl;
		fileInfo.setFileUrl(fileUrl);
		this.fileInfoRepository.save(fileInfo);
		//查看该路径存在与否，遇过不存在，创建路径
		if (!new File(filepath).isDirectory()) {
			new File(filepath).mkdirs();
		}
		try (InputStream is = file.getInputStream()) {
			Files.copy(is, Paths.get(filepath, fileInfo.getId().toString()));
		}
		dataMap.put("success", true);
		return dataMap;
	}*/

	/**
	 * 单个文件上传
	 * 不推荐使用
	 * @author ChenTao
	 * @date 2015年11月19日下午8:41:04
	 */
	//@RequestMapping("/uploadold")
	/*public void uploadold(MultipartFile file) throws IOException {
		try (InputStream is = file.getInputStream()) {
			Files.copy(is, Paths.get(config.getUploadPath(), file.getOriginalFilename()));
		}
	}*/

	/**
	 * 多个文件上传
	 * 不推荐使用
	 * @author ChenTao
	 * @date 2015年11月19日下午8:41:12
	 */
	//@RequestMapping("/uploads")
	/*public void uploads(ArrayList<MultipartFile> file) throws IOException {
		for (MultipartFile multipartFile : file) {
			try (InputStream inputStream = multipartFile.getInputStream()) {
				Files.copy(inputStream, Paths.get(config.getUploadPath(), multipartFile.getOriginalFilename()));
			}
		}
	}*/

}
