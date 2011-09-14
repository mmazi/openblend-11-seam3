package com.parsek.test;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class MemberListProducer {
    @PersistenceContext private EntityManager em;
    private List<Member> members;

    @Produces @Named
    public List<Member> getMembers() {
        return members;
    }

    public void onMemberListChanged(@Observes Member member) {
        retrieveAllMembers();
    }

    @PostConstruct
    public void retrieveAllMembers() {
        members = em.createQuery("select m from Member m order by m.name", Member.class).getResultList();
/*
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        criteria.select(member).orderBy(cb.asc(member.get("name")));
        members = em.createQuery(criteria).getResultList();
*/
    }
}
