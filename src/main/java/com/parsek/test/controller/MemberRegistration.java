package com.parsek.test.controller;

import com.parsek.test.data.MemberRepository;
import com.parsek.test.model.Member;
import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

// Adding the @Stateful annotation eliminates need for manual transaction demarcation
@javax.ejb.Stateful
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an EL name
// Read more about the @Model stereotype in this FAQ: http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberRegistration {
    @Inject
    @Category("demo")
    private Logger log;

    @Inject
    @MemberRepository
    private EntityManager em;

    @Inject
    private Event<Member> memberEventSrc;

    private Member newMember;

    @Produces
    @Named
    public Member getNewMember() {
        return newMember;
    }

    public void register() throws Exception {
        log.info("Registering " + newMember.getName());
        em.persist(newMember);
        memberEventSrc.fire(newMember);
        initNewMember();
    }

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
    }
}
