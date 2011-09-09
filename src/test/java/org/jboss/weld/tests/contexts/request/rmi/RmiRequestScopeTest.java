package org.jboss.weld.tests.contexts.request.rmi;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.naming.InitialContext;
import javax.naming.NamingException;

@RunWith(Arquillian.class)
public class RmiRequestScopeTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Bridge.class, BridgeBean.class, Config.class, Manager.class, My.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private static Bridge getBridge()
    {
        try
        {
            Object serverObject = new InitialContext().lookup("BridgeBean/remote");
            return (Bridge) serverObject;
        }
        catch (NamingException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRmiRequestScopeActive()
    {
        System.out.println(getBridge().doSomething());
    }
}
