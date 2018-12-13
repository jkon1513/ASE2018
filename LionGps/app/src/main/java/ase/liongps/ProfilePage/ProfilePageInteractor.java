package ase.liongps.ProfilePage;

import java.util.HashSet;

public class ProfilePageInteractor {
	HashSet<String> schedule;

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




}
