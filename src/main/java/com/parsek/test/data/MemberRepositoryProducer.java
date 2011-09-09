package com.parsek.test.data;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MemberRepositoryProducer {
    @Produces @PersistenceContext private EntityManager em;
}
