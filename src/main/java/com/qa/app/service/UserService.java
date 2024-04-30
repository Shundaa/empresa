package com.qa.app.service;

import com.qa.app.dao.request.NewCourseRequest;
import com.qa.app.dao.request.UpdatePassword;
import com.qa.app.dao.request.UserRegistrationRequest;
import com.qa.app.dao.response.ClassRegistrationResponse;
import com.qa.app.dao.response.UserDao;
import com.qa.app.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    UserDao getUser(Long id);

    UserDao addXp(Long id, Long xp);

    User addRegistration(Long id, UserRegistrationRequest userRegistrationRequest);

    User updateUser(User user);

    User deleteCourse(Long id, Long courseId);

    User updatePassword(Long id, UpdatePassword user);

    User createRegistration(Long id, NewCourseRequest request);

    List<ClassRegistrationResponse> getAllCoursesOfUser(Long id);
}
