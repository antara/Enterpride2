package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.RoleDao;
import com.erp.pojo.Role;
import com.wideplay.warp.persist.Transactional;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: Feb 24, 2012
 * Time: 3:17:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoleDaoImpl extends BaseDao implements RoleDao {


    public List getRole(){
        return getSession().createQuery("from Role where deleted='1' and id <> 1").list();
    }
    @Transactional
    public boolean SaveRole(Role role)

    {
        try {


            if(role!=null){
                getSession().save(role);

                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }



    public Role findById(Long id){
        Role ud=null;

        return (Role)getSession().createQuery("from Role where id='"+id+"'").uniqueResult();
    }
    @Transactional
    public void delete(Role role) {
        try{
            role.setDeleted(0);
            getSession().update(role);
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    @Transactional
    public void update(Role role) {
        try{
            getSession().update(role);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
