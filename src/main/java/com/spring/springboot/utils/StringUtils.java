package com.spring.springboot.utils;

public final class StringUtils {
	
	private StringUtils() {}
	
	public static final String NOT_FOUND_ID = "%s of %s %s has not been found.";
	public static final String NOT_FOUND_NAME = "%s of %s %s has not been found.";
	public static final String NOT_FOUND_EXAM = "Exam %s of student %n has not been found.";
	public static final String STUDENT = "Student";
	public static final String COURSE = "Course";
	public static final String INVALID_UPDATE_OP = "Error, you are trying to do an update, but you have to do an insert";
	public static final String INVALID_INSERT_OP = "Error, you are trying to do an insert, but you have to do an update";
	public static final String EMPTY_LIST = "No %s were found.";
	public static final String EXAM_ALREADY_REGISTERED = "Exam %s for student %s is already registered";
	public static final String EXAM_UNEXISTING = "Exam %s do not exists for this student, cannot upload or delete";
	public static final String EXAM = "Exam";
	public static final String ACCESS_DENIED = "Access Denied";
	public static final String UNAUTHORIZED = "Not Authorized";
	public static final String IMAGE = "Image";
	public static final String IMAGE_INSERTION_ERROR = "Some errors occured during the image insertion/update";

}
