package com.qa.app.controller;

import com.qa.app.dao.request.NewCourseRequest;
import com.qa.app.dao.request.UpdatePassword;
import com.qa.app.dao.request.UserRegistrationRequest;
import com.qa.app.dao.response.ClassRegistrationResponse;
import com.qa.app.dao.response.UserDao;
import com.qa.app.entities.User;
import com.qa.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PutMapping("/update")
    public ResponseEntity<User> getAllUsers(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @GetMapping("/{id}/subscriptions/all")
    public ResponseEntity<List<ClassRegistrationResponse>> getAllCoursesOfUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAllCoursesOfUser(id));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<UserDao> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody UpdatePassword changePassword) {
        return ResponseEntity.ok(userService.updatePassword(id, changePassword));
    }

    @PostMapping("/{id}/subscribe/course")
    public ResponseEntity<User> addNewCourse(@PathVariable Long id, @RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.ok(userService.addRegistration(id, userRegistrationRequest));
    }

    @PostMapping("/{id}/unSubscribe/{idCourse}")
    public ResponseEntity<User> addNewCourse(@PathVariable Long id, @PathVariable Long idCourse) {
        return ResponseEntity.ok(userService.deleteCourse(id, idCourse));
    }

    @PostMapping("/{id}/create/course")
    public ResponseEntity<User> createNewCourse(@PathVariable Long id, @RequestBody NewCourseRequest request) {
        return ResponseEntity.ok(userService.createRegistration(id, request));
    }

    @GetMapping("/{id}/add/")
    public ResponseEntity<UserDao> addXp(@PathVariable Long id, @RequestParam("xp") Long xp) {
        return ResponseEntity.ok(userService.addXp(id, xp));
    }

}
