package io.github.mat3e.lang;

import io.github.mat3e.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class LangRepository {
    List<Lang> findAll(){
        var sesion = HibernateUtil.getSessionFactory().openSession();
        var transaction = sesion.beginTransaction();

        var result = sesion.createQuery("from  Lang", Lang.class).list();

        transaction.commit();
        sesion.close();

        return result;
    }

    public Optional<Lang> findById(Integer id){
        var sesion = HibernateUtil.getSessionFactory().openSession();
        var transaction = sesion.beginTransaction();

        var result = sesion.get(Lang.class, id);

        transaction.commit();
        sesion.close();

        return Optional.ofNullable(result);
    }
}
