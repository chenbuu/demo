package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.StudyGuide;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.StudyGuideRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class StudyGuideService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private StudyGuideRepository studyGuideRepository;

	public StudyGuide findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, StudyGuide.class)) {
			return redisRepository.getObject(id, StudyGuide.class);
		}
		StudyGuide studyGuide = studyGuideRepository.findOne(id);
		redisRepository.setObject(studyGuide);
		return studyGuide;
	}

	public StudyGuide save(StudyGuide studyGuide) throws Exception {
		studyGuideRepository.save(studyGuide);
		redisRepository.setObject(studyGuide);
		return studyGuide;
	}

	public StudyGuide insert(StudyGuide studyGuide) throws Exception {
		studyGuideRepository.insert(studyGuide);
		redisRepository.setObject(studyGuide);
		return studyGuide;
	}

	public Page<StudyGuide> findAll(@Valid StudyGuide mod, Pageable pageable) throws Exception {
		Page<StudyGuide> page = studyGuideRepository.findAll(mod, pageable);
		List<StudyGuide> studyGuides = page.getContent();
		redisRepository.setList(studyGuides);
		return page;
	}

	public Page<StudyGuide> findAll(Pageable pageable) throws Exception {
		Page<StudyGuide> page = studyGuideRepository.findAll(pageable);
		List<StudyGuide> studyGuides = page.getContent();
		redisRepository.setList(studyGuides);
		return page;
	}

}
