package com.erp.action;

import net.sourceforge.stripes.action.*;

import com.erp.exception.LoginException;
import com.erp.pojo.Traildate;

import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: Milind
 * Date: Feb 9, 2012
 * Time: 10:42:59 AM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class LoginActionBean extends BaseActionBean{
   @DefaultHandler
    public Resolution login()
    {

        try{

            getContext().setUser(userDao.authenticate(getUser()));
                }catch(LoginException le){
                           getContext().getMessages().add(new SimpleMessage("Invalid User"));

                   return new RedirectResolution("jsp/login.jsp");
                }

                return new ForwardResolution("jsp/main_page.jsp");


    }

     public Resolution trialPeriod()
    {



        if(datedao.checkPeriod(new Traildate())==1)
        {
         

            return login();

        }
           getContext().getMessages().add(new SimpleMessage("Trial period is over"));
            return new  ForwardResolution("/jsp/login.jsp");
    } 

    public Resolution logout(){
        getContext().logout();
        return new RedirectResolution("jsp/login.jsp");
    }
}
