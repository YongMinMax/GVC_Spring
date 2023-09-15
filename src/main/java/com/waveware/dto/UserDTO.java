package com.waveware.dto;

import java.sql.Date;

public class UserDTO{
	
	private int user_idx;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_role;
	private Date user_joindate;
	private Date user_update;
	private Date user_deletdate;	
	
	
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public Date getUser_joindate() {
		return user_joindate;
	}
	public void setUser_joindate(Date user_joindate) {
		this.user_joindate = user_joindate;
	}
	public Date getUser_update() {
		return user_update;
	}
	public void setUser_update(Date user_update) {
		this.user_update = user_update;
	}
	public Date getUser_deletdate() {
		return user_deletdate;
	}
	public void setUser_deletdate(Date user_deletdate) {
		this.user_deletdate = user_deletdate;
	}
	@Override
	public String toString() {
		return "UserDTO [user_idx=" + user_idx + ", user_id=" + user_id + ", user_pw=" + user_pw + ", user_name="
				+ user_name + ", user_role=" + user_role + ", user_joindate=" + user_joindate + ", user_update="
				+ user_update + ", user_deletdate=" + user_deletdate + "]";
	}
	
	
}
