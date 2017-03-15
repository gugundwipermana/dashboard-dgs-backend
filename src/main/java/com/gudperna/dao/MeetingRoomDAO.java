package com.gudperna.dao;

import com.gudperna.model.MeetingRoom;
import java.util.ArrayList;

public interface MeetingRoomDAO {
	public ArrayList<MeetingRoom> getAll();
	public MeetingRoom getById(int id);
	public void insert(MeetingRoom meetingRoom);
	public void edit(MeetingRoom meetingRoom);
	public void delete(int id);
}