package com.dto;

import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class UserDTO {

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String age;

	@NotNull
	private String department;

	public static UserDTO of(String firstName, String lastName, String age, String department) {
		UserDTO user = new UserDTO();
		user.firstName = firstName;
		user.lastName = lastName;

		user.age = age;
		user.department = department;

		return user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
