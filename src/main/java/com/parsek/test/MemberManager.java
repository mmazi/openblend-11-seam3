package com.parsek.test;

import com.parsek.test.model.Member;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Named @Stateless
public class MemberManager {
    @Inject private EntityManager em;
    private List<Member> members;

    @Produces @Named
    public List<Member> getMembers() { return members; }

    public void onMemberListChanged(@Observes final Member member) {
        retrieveAllMembersOrderedByName();
    }

    public void deleteMember(Member m) {
        em.remove(em.find(Member.class, m.getId()));
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        criteria.select(member).orderBy(cb.asc(member.get("name")));
        members = em.createQuery(criteria).getResultList();
    }
}
