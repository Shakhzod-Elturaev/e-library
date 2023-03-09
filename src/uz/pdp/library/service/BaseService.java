package uz.pdp.library.service;

import java.util.UUID;

public interface BaseService<T> {
    void addElement(T t);
    T getById(UUID id);
}
