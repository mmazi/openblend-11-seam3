package com.parsek.test;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful @Model
public class MemberRegistration {
    @PersistenceContext private EntityManager em;
    @Inject private Event<Member> memberEventSrc;

    private Member newMember;

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
    }

    @Produces @Named
    public Member getNewMember() {
        return newMember;
    }

    public void register() throws Exception {
        em.persist(newMember);
        memberEventSrc.fire(newMember);
        initNewMember();
    }

    public void delete(Member m) {
        em.remove(em.getReference(Member.class, m.getId()));
        memberEventSrc.fire(m);
    }
}
