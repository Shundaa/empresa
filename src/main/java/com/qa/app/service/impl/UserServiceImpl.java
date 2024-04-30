package com.qa.app.service.impl;

import com.qa.app.dao.request.NewCourseRequest;
import com.qa.app.dao.request.UpdatePassword;
import com.qa.app.dao.request.UserRegistrationRequest;
import com.qa.app.dao.response.ClassRegistrationResponse;
import com.qa.app.dao.response.UserDao;
import com.qa.app.entities.ClassRegistration;
import com.qa.app.entities.Course;
import com.qa.app.entities.User;
import com.qa.app.exception.ResourceNotFoundException;
import com.qa.app.repository.ClassRegistrationRepository;
import com.qa.app.repository.CourseRepository;
import com.qa.app.repository.UserRepository;
import com.qa.app.service.UserService;
import com.qa.app.utils.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ClassRegistrationRepository classRegistrationRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        userList.forEach(a -> a.setPassword(a.getPassword()));
        return userList;
    }

    @Override
    public UserDao getUser(Long id) {
        User user = findById(id);
        return ObjectMapperUtils.map(userRepository.save(user), UserDao.class);
    }

    @Override
    public UserDao addXp(Long id, Long xp) {
        User user = findById(id);
        Long newXpValue = user.getXp() + xp;
        if (Objects.nonNull(user.getLevel()) && newXpValue > 100L) {
            user.setXp(newXpValue - 100L);
            user.setLevel(user.getLevel() + 1L);
        } else if (Objects.isNull(user.getLevel()) && newXpValue > 100L) {
            user.setLevel(1L);
            user.setXp(newXpValue - 100L);
        } else {
            user.setXp(newXpValue);
        }
        return ObjectMapperUtils.map(userRepository.save(user), UserDao.class);
    }

    @Override
    public User addRegistration(Long id, UserRegistrationRequest userRegistrationRequest) {
        User user = findById(id);
        List<ClassRegistration> classRegistrations = user.getRegistrations();
        ClassRegistration classRegistration = classRegistrationRepository.findById(userRegistrationRequest.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Course with id: " + userRegistrationRequest.getCourseId() + " Not Found"));
        classRegistration.setRegisteredAt(LocalDateTime.now());
        classRegistration.setUser(user);
        classRegistrations.add(classRegistration);
        user.setRegistrations(classRegistrations);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User deleteCourse(Long id, Long courseId) {
        User user = findById(id);
        List<ClassRegistration> newList = user.getRegistrations()
                .stream().filter(classRegistration -> !classRegistration.getCourse().getId().equals(courseId)).toList();
        classRegistrationRepository.deleteAll(user.getRegistrations()
                .stream().filter(classRegistration -> classRegistration.getCourse().getId().equals(courseId)).toList());
        user.setRegistrations(newList);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(Long id, UpdatePassword password) {
        User user = findById(id);
        user.setPassword(passwordEncoder.encode(password.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User createRegistration(Long id, NewCourseRequest request) {
        User user = findById(id);
        Course course = Course.builder()
                .name(request.getName())
                .owner(request.getOwner())
                .description(request.getDescription())
                .build();
        Course courseSaved = courseRepository.save(course);
        List<ClassRegistration> classRegistrations = user.getRegistrations();
        ClassRegistration classRegistration = ClassRegistration.builder()
                .user(user)
                .course(courseSaved)
                .registeredAt(LocalDateTime.now())
                .build();
        classRegistrations.add(classRegistration);
        user.setRegistrations(classRegistrations);
        return userRepository.save(user);
    }

    @Override
    public List<ClassRegistrationResponse> getAllCoursesOfUser(Long id) {
        User user = findById(id);
        return ObjectMapperUtils.mapAll(user.getRegistrations(), ClassRegistrationResponse.class);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id: " + id + " Not Found"));
    }
}
