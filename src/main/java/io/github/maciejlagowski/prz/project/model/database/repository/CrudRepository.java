package io.github.maciejlagowski.prz.project.model.database.repository;

import java.util.List;

public interface CrudRepository <T> {

    void createRecord(T object);
    List readAllRecords();
    T findRecordById(Long id);
    void updateRecord(T object);
    void deleteRecordById(Long id);
    void deleteRecord(T object);
    void deleteAllRecords();
}
