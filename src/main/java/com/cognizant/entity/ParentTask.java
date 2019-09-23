package com.cognizant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARENTTASK")
public class ParentTask implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "parent_task_id")
	private long parentTaskId;
	@Column(name = "parent_task")
	private String parentTask;

	public long getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	public ParentTask() {

	}

	public ParentTask(long parentTaskId, String parentTask) {
		super();
		this.parentTaskId = parentTaskId;
		this.parentTask = parentTask;
	}

	@Override
	public String toString() {
		return "Task [id=" + parentTaskId + ", parentTask=" + parentTask + "]";
	}
}
