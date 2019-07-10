package io.github.mat3e;

import java.util.Optional;

public class LangRepository {

    Optional<Lang> findById (Integer id){
        var sesion = HibernateUtil.getSessionFactory().openSession();
        var transaction = sesion.beginTransaction();

        var result = sesion.get(Lang.class, id);

        transaction.commit();
        sesion.close();

        return Optional.ofNullable(result);
    }
}
