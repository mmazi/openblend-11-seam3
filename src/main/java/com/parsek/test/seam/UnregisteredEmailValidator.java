package com.parsek.test.seam;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Matic<br/>
 * Date: 1.7.11<br/>
 */
@FacesValidator("unregisteredEmailValidator")
@Named
@RequestScoped
public class UnregisteredEmailValidator implements Validator {
    @PersistenceContext private EntityManager entityManager;
    @Inject private Messages messages;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Number result = entityManager
                .createQuery("select count(m) from Member m where m.email = :email", Number.class)
                .setParameter("email", o)
                .getSingleResult();
        if (result.intValue() > 0) {
            throw new ValidatorException(messages.createError("email.already.registered"));
        }
    }
}
