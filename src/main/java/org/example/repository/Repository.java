package org.example.repository;

import java.util.List;

public interface Repository<T>{
    List<T> Listar();
    T porid(Long id);
    void guardar(T t);
    void eliminar(long id);

}
