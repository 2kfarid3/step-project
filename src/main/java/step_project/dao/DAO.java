package step_project.dao;

public interface DAO<T> {
    T save(T t);
    //void delete(T t);
}
