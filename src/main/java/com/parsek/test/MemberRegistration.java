package com.parsek.test;

import com.parsek.test.model.Member;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Stateful @Model
public class MemberRegistration {
    @Inject private EntityManager em;
    @Inject private Event<Member> memberEventSrc;

    private Member newMember;

    @Produces @Named public Member getNewMember() { return newMember; }

    @PostConstruct public void initNewMember() { newMember = new Member(); }

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
