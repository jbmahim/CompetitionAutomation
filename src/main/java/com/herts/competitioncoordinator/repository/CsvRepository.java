package com.herts.competitioncoordinator.repository;

import com.herts.competitioncoordinator.exception.CustomException;

import java.util.List;
import java.util.Optional;

public interface CsvRepository<T, ID> {
    List<T> findAll() throws CustomException;
    Optional<T> findById(ID id) throws CustomException;
    T save(T entity) throws CustomException;
    void deleteById(ID id) throws CustomException;
}
