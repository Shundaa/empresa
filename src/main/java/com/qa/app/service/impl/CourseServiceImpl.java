package com.qa.app.service.impl;

import com.qa.app.dao.request.NewChapterRequest;
import com.qa.app.dao.request.NewChapterToCourseRequest;
import com.qa.app.dao.request.NewCourseRequest;
import com.qa.app.dao.response.CourseDao;
import com.qa.app.entities.Chapter;
import com.qa.app.entities.Course;
import com.qa.app.exception.ResourceNotFoundException;
import com.qa.app.repository.ChapterRepository;
import com.qa.app.repository.CourseRepository;
import com.qa.app.service.CourseService;
import com.qa.app.utils.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final ChapterRepository chapterRepository;

    @Override
    public List<CourseDao> get() {
        return ObjectMapperUtils.mapAll(courseRepository.findAll(), CourseDao.class);
    }

    @Override
    public CourseDao get(Long id) {
        return ObjectMapperUtils.map(getById(id), CourseDao.class);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDao edit(Long id, NewCourseRequest newLessonRequest) {
        Course course = getById(id);
        if (Objects.nonNull(newLessonRequest.getName()) && !newLessonRequest.getName().isEmpty()) {
            course.setName(newLessonRequest.getName());
        }
        if (Objects.nonNull(newLessonRequest.getOwner())) {
            course.setOwner(newLessonRequest.getOwner());
        }
        if (Objects.nonNull(newLessonRequest.getDescription())) {
            course.setDescription(newLessonRequest.getDescription());
        }
        return ObjectMapperUtils.map(courseRepository.save(course), CourseDao.class);
    }

    @Override
    public CourseDao newLesson(NewCourseRequest newCourseRequest) {
        Course course = Course.builder()
                .name(newCourseRequest.getName())
                .owner(newCourseRequest.getOwner())
                .description(newCourseRequest.getDescription())
                .build();
        return ObjectMapperUtils.map(courseRepository.save(course), CourseDao.class);
    }

    @Override
    public CourseDao addNewChapterToCourse(Long id, NewChapterToCourseRequest addNew) {
        Course course = getById(id);
        List<Chapter> chapters = course.getChapters();
        addNew.getChaptersId().forEach(chapterId -> {
            Chapter Chapter = chapterRepository.findById(chapterId).orElseThrow(
                    () -> new ResourceNotFoundException("Chapter with id: " + chapterId + " Not Found"));
            Chapter.setCourse(course);
            chapters.add(Chapter);
        });
        return ObjectMapperUtils.map(courseRepository.save(course), CourseDao.class);
    }

    @Override
    public CourseDao createNewChapterToCourse(Long id, NewChapterRequest createNew) {
        Course course = getById(id);
        Chapter chapter = new Chapter();
        chapter.setName(createNew.getName());
        chapter.setCourse(course);
        List<Chapter> chapters = course.getChapters();
        chapters.add(chapter);
        return ObjectMapperUtils.map(courseRepository.save(course), CourseDao.class);
    }

    @Override
    public List<CourseDao> searchCourses(String name) {
        return ObjectMapperUtils.mapAll(courseRepository.findCoursesByNameContainingIgnoreCase(name), CourseDao.class);
    }

    private Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Course with id: " + id + " Not Found"));
    }
}
