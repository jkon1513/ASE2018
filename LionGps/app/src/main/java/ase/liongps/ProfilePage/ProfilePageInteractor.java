package ase.liongps.ProfilePage;

import java.util.HashSet;
import java.util.Set;

public class ProfilePageInteractor {
	private HashSet<String> schedule;

	public ProfilePageInteractor(){
		schedule = new HashSet<>();
	}

	public boolean addToSchedule(String courseEntry){
		if(courseEntry == null){
			return false;
		}

		schedule.add(courseEntry);
		return true;
	}

	public HashSet<String> getSchedule() {
		return schedule;
	}

	public boolean loadSchedule(Set<String> courseList) {
		if(courseList != null) {
			schedule = new HashSet<>(courseList);
			return true;
		}

		return false;
	}




}
