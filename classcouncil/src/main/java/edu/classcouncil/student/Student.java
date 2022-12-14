package edu.classcouncil.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student implements Comparable<Student> {

	String name;
	String email;
	String id; // String of 3 digits
	String pairedStudentsString;
	String pairOfTheWeek; // String of ID number (3 digits)
	String[] pairedStudentsIds;

	public Student(String id, String name, String email, String pairOfTheWeek, String pairedStudentsString) {
		setId(id);
		setName(name);
		setEmail(email);
		setPairOfTheWeek(pairOfTheWeek);
		setPairedStudentsString(pairedStudentsString);
		setPairedStudents();

	}

	public String getPairOfTheWeek() {
		return pairOfTheWeek;
	}

	public void setPairOfTheWeek(String pairOfTheWeek) {
		this.pairOfTheWeek = pairOfTheWeek;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPairedStudentsString(String pairedStudentsString) {
		this.pairedStudentsString = pairedStudentsString;
	}

	public void setPairedStudents() {
		String temp = pairedStudentsString.substring(1);
		pairedStudentsIds = temp.split("-");
		// System.out.println(pairedStudentsIds.length);

	}

	public String[] getPairedStudents() {
		return pairedStudentsIds;
	}

	public String toString() {
		String temp = "";

		for (int i = 0; i < pairedStudentsIds.length; i++) {
			temp += pairedStudentsIds[i] + " ";
		}
		return id + " " + name + " " + email + " " + temp;
	}

	public String[] toStringArray() {
		String[] temp = new String[] { id, name, email, pairOfTheWeek, pairedStudentsString };
		return temp;
	}

	public void updateNewPair(String newPairOfTheWeek) {
		pairOfTheWeek = newPairOfTheWeek;
	}

	public void updatePairedStudents() {
		// System.out.println(pairedStudentsString);
		pairedStudentsString = pairedStudentsString + "-" + pairOfTheWeek;
		// System.out.println(pairedStudentsString);
		int len = pairedStudentsIds.length;
		String arr[] = new String[len + 1];
		for (int i = 0; i < len; i++) {
			arr[i] = pairedStudentsIds[i];
		}
		arr[len] = pairOfTheWeek;
		pairedStudentsIds = arr;

	}

	public int compareTo(Student comparestu) {
		int compareage = Integer.parseInt(comparestu.getId());
		return Integer.parseInt(this.getId()) - compareage;

	}

}
