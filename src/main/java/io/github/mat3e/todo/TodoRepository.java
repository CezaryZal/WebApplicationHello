package io.github.mat3e.todo;

import io.github.mat3e.HibernateUtil;
import io.github.mat3e.lang.Lang;

import java.util.List;
import java.util.Optional;

class TodoRepository {

    List<Todo> findAll (){
        var sesion = HibernateUtil.getSessionFactory().openSession();
        var transaction = sesion.beginTransaction();

        var result = sesion.createQuery("from  Todo", Todo.class).list();

        transaction.commit();
        sesion.close();

        return result;
    }

    Todo toggleTodo(Integer id){
        var sesion = HibernateUtil.getSessionFactory().openSession();
        var transaction = sesion.beginTransaction();

        var result = sesion.get(Todo.class, id);
        result.setDone(!result.isDone());

        transaction.commit();
        sesion.close();

        return result;
    }

    Todo addTodo (Todo newTodo){
        var sesion = HibernateUtil.getSessionFactory().openSession();
        var transaction = sesion.beginTransaction();

        sesion.persist(newTodo);

        transaction.commit();
        sesion.close();
        return newTodo;
    }
}
