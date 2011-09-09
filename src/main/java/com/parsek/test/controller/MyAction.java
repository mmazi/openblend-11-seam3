package com.parsek.test.controller;

import com.parsek.test.data.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * User: Matic<br/>
 * Date: 18.5.11<br/>
 */
@Stateless
public class MyAction {
    private static final Logger log = LoggerFactory.getLogger(MyAction.class);

    @Inject @MemberRepository private EntityManager em;

}
