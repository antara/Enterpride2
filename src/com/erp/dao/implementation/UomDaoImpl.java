package com.erp.dao.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import com.erp.utils.HibernateUtils;
import com.erp.dao.BaseDao;
import com.erp.dao.UomDao;

import com.erp.pojo.Uom;
import com.erp.pojo.Section;
import com.wideplay.warp.persist.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 14, 2012
 * Time: 12:44:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class UomDaoImpl extends BaseDao implements UomDao{

    @Transactional
    public List getUom(){

        return getSession().createQuery("from Uom where deleted='1'").list();
    }
    @Transactional
    public boolean SaveUom(Uom uom)

    {
        try {


            if(uom!=null){
                getSession().save(uom);

                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally{
//  getSession().close();
        }

    }

    @Transactional
    public void delete(Uom uom){
        try{

            uom.setDeleted(0);
            getSession().update(uom);



        }catch (Exception e){
            e.printStackTrace();

        }finally{
//  getSession().close();
        }
    }
    @Transactional
    public Uom findById(Long id){
        return (Uom)getSession().createQuery("from Uom where id='"+id+"'").uniqueResult();

    }
    @Transactional
    public void update(Uom uom){
        try{
            getSession().update(uom);
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}


