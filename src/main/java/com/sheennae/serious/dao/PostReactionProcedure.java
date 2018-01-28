package com.sheennae.serious.dao;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;

@Repository
public class PostReactionProcedure {

    @PersistenceContext
    private EntityManager entityManager;

    public void reactPost(Date reactedTime, int postId, int postReactionId, int userId) {

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("post_reaction");
        storedProcedure.registerStoredProcedureParameter("reactedTime", Date.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("postId", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("postReactionId", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN);

        storedProcedure.setParameter("reactedTime", reactedTime);
        storedProcedure.setParameter("postId", postId);
        storedProcedure.setParameter("postReactionId", postReactionId);
        storedProcedure.setParameter("userId", userId);

        storedProcedure.execute();

    }
}
