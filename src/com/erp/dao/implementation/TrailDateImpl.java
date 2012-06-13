package com.erp.dao.implementation;

import com.erp.dao.DateDao;
import com.erp.dao.BaseDao;
import com.erp.pojo.Traildate;
import com.wideplay.warp.persist.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;

/**
 * Created by IntelliJ IDEA.
 * User: Atul
 * Date: Apr 18, 2012
 * Time: 3:52:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrailDateImpl extends BaseDao implements DateDao
{
    @Transactional
    public int checkPeriod(Traildate traildate)
    {
        Traildate td=new Traildate();
        try
        {
            Query qry=getSession().createQuery("from Traildate");
            List l=qry.list();
            Iterator it=l.iterator();
            if(it.hasNext()==false)
            {
                Date d=new Date();
                Date d1=new Date();
                d1.setDate(d.getDate()+15);
                traildate.setDate1(d);
                traildate.setDate2(d1);
                getSession().save(traildate);
                return 1;
            }
            else
            {
                td=(Traildate)getSession().load(Traildate.class,1);
                Date d=new Date();
                if(d.compareTo(td.getDate1())==-1)
                {
                    return 0;
                }
                else
                if(d.compareTo(td.getDate2())==1)
                {
                    return 0;
                }
                else{
                    return 1;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }


}
