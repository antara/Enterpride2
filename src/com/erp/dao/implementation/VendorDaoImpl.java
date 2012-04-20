package com.erp.dao.implementation;

import org.hibernate.Transaction;

import com.erp.pojo.Vendor;
import com.erp.dao.BaseDao;
import com.erp.dao.VendorDao;
import com.wideplay.warp.persist.Transactional;

import java.util.List;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 17, 2012
 * Time: 3:38:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class VendorDaoImpl extends BaseDao implements VendorDao{

    @Transactional
           public List<Vendor> searchByName(String name) {

           try {
                   

           }catch (Exception e){

           }
         return (List<Vendor>)getSession().createQuery("FROM Vendor WHERE name='"+name+"'").list();


    }

        //save
    @Transactional
          public boolean SaveVendor(Vendor vendor)

        {
         try {

                        
                if(vendor!=null){
				 if (vendor.getCreateDate() == null) vendor.setCreateDate(new Date());
                   getSession().save(vendor);
                    
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
        //delete
    @Transactional
           public void delete(Vendor vendor)  {
            try{
            
                              vendor.setDeleted(0);
                    getSession().update(vendor);
                    


            }catch (Exception e){
                e.printStackTrace();

            }finally{
              //  getSession().close();
            }
        }
      //find by id
    @Transactional
        public Vendor findById(Long id){
             Vendor ud=null;
               try {
                                     
            return (Vendor)getSession().createQuery("from Vendor where id='"+id+"'").uniqueResult();
               }catch (Exception e){

               }
            return null;
        }

        //get all list

    @Transactional
         public List getVendor(){

             String hql="from Vendor where deleted='1'";
            // List list=null;
             try {

                     


             }catch (Exception e){
                 e.printStackTrace();

             }finally{
               //  s.close();
             }
              return getSession().createQuery(hql).list();
         }
          //update
    @Transactional
          public void update(Vendor vendor)  {
            try{
            

                   getSession().update(vendor);
                    


            }catch (Exception e){
                e.printStackTrace();

            }finally{
              //  getSession().close();
            }
        }
    @Transactional
    public List<Vendor> findByName(String fname) {
                   List<Vendor> l=null;
                   try {
                                         

               return (List<Vendor>)getSession().createQuery("from Vendor where name='"+fname+"'").list();

                   }catch (Exception e){

                   }
                return null;

        }
                  @Transactional
         public List<String> allVendor() {
               String hql="Select name from Vendor where deleted='1'";
            // List list=null;
             try {

                     


             }catch (Exception e){
                 e.printStackTrace();

             }finally{
               //  s.close();
             }
              return getSession().createQuery(hql).list();
         }
}


