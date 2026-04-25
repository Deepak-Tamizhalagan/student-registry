package com.example.studentregistry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    /**
     * Returns a map of students.
     * @Cacheable means: on first call, execute the method and store the result in cache "students".
     * On subsequent calls, skip the method entirely and return from cache.
     */
    @Cacheable(value = "students")
    public Map<Integer, String> getStudents() {
        log.info(">>> Cache MISS - Building student map from scratch...");

        // Simulating a small delay to make it obvious when cache is NOT used
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Map<Integer, String> students = new LinkedHashMap<>();
        students.put(1, "Alice Johnson");
        students.put(2, "Bob Smith");
        students.put(3, "Charlie Brown");
        students.put(4, "Diana Prince");
        students.put(5, "Edward Norton");

        log.info(">>> Student map built and stored in cache.");
        return students;
    }

    /**
     * Clears the "students" cache.
     * @CacheEvict means: remove the cached entry so the next GET call rebuilds it.
     */
    @CacheEvict(value = "students", allEntries = true)
    public void clearCache() {
        log.info(">>> Cache CLEARED - Next GET call will rebuild the student map.");
    }
}