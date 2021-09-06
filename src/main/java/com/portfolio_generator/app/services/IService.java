package com.portfolio_generator.app.services;

import java.util.List;

public interface IService<T, E extends Throwable>{

    List<T> findAll();

    T findById(Long id) throws E ;

    T save(T entity);

    void deleteById(Long id) throws E ;
}
