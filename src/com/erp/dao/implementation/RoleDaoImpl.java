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

    @Transactional
         public List getRole(){

            String hql="from Role where deleted='1' and id <> 1";
            try {

                      

            }catch (Exception e){
                e.printStackTrace();

            }finally{
              //  s.close();
            }
             return getSession().createQuery(hql).list();
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
           }finally{
             //  getSession().close();
           }

       }

      
                  @Transactional
       public Role findById(Long id){
            Role ud=null;
              try {
                                    

           return (Role)getSession().createQuery("from Role where id='"+id+"'").uniqueResult();
              }catch (Exception e){

              }
           return null;
       }
                                @Transactional
    public void delete(Role role) {
       try{
        
            role.setDeleted(0);
               getSession().update(role);
                


        }catch (Exception e){
            e.printStackTrace();

        }finally{
          //  getSession().close();
        }
    }
                                              @Transactional
    public void update(Role role) {
        try{
        
               getSession().update(role);
                


        }catch (Exception e){
            e.printStackTrace();

        }finally{
          //  getSession().close();
        }
    }


}
