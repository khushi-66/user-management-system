package com.isrdc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.ActivityLogDto;
import com.isrdc.entities.ActivityLog;
import com.isrdc.repos.ActivityLogRepo;

@Service
public class ActivityService {

	@Autowired
	private ActivityLogRepo repo;
	public ArrayList<ActivityLogDto>  findAllActivitesByUser(Integer id) {
		ArrayList<ActivityLogDto>activites=new ArrayList<>();
		List<ActivityLog>act=repo.findAllByUserUserIdOrderByTimeDesc(id);
		
		for(int i=0;i<act.size();i++) {
			ActivityLog a=act.get(i);
			ActivityLogDto dto=new ActivityLogDto();
			BeanUtils.copyProperties(a, dto);
			activites.add(dto);
		}
		return activites;
		
	}

}
