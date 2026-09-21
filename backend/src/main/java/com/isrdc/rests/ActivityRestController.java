package com.isrdc.rests;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isrdc.dtos.ActivityLogDto;
import com.isrdc.services.ActivityService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ActivityRestController {

	@Autowired
	private ActivityService serv;
	@GetMapping("/activities/{userId}")
	public ResponseEntity<?> getActivities(@PathVariable Integer userId) {
		System.out.println("activties api accessed.....");
		ArrayList<ActivityLogDto>activities =serv.findAllActivitesByUser(userId);
		System.out.println(activities);
		return ResponseEntity.ok(Map.of(
				"status","successfull",
				"activities",activities
				));
	}

}
