package com.erp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.erp.utils.HibernateUtils;
import com.google.inject.Provider;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 14, 2012
 * Time: 12:25:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseDao{

    @Inject
    private Provider<Session> sessionProvider;


    public BaseDao() {
//        session=HibernateUtils.getSession();
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public Session getSession() {
        return getSessionProvider().get();
    }

    

/*
    public void startSessionAndTransaction(){
        if (ManagedSessionContext.hasBind(sf)) {
            System.out.println("start session and transaction no bind");
       return;
     }
        sf=HibernateUtils.getSessionFactory();

        session= sf.openSession();
        session.setFlushMode(FlushMode.MANUAL);
        ManagedSessionContext.bind((org.hibernate.classic.Session)session);
        session.beginTransaction();
        System.out.println("new session");
                
    }

    protected void commitTransaction() {


        if (!ManagedSessionContext.hasBind(sf)) {
            System.out.println("no value returned");
       return;
     }

       try{

       session.flush();
           System.out.println("session flushed");
       session.getTransaction().commit();
           System.out.println("transaction commited");
       session.close();
           System.out.println("session closed");
    }finally {
           ManagedSessionContext.unbind(sf);
       }
       }
*/

}
