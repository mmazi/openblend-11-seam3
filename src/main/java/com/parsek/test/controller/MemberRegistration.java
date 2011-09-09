package com.parsek.test.controller;

import com.parsek.test.model.Member;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Stateful
@Model
public class MemberRegistration {
    @Inject private EntityManager em;

    private Member newMember;

    @Inject
    private Event<Member> memberEventSrc;

    @Produces @Named
    public Member getNewMember() {
        return newMember;
    }

    public void register() throws Exception {
        em.persist(newMember);
        memberEventSrc.fire(newMember);
        initNewMember();
    }

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
    }
}
