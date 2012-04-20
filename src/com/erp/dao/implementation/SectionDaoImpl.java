package com.erp.dao.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import com.erp.utils.HibernateUtils;
import com.erp.dao.BaseDao;
import com.erp.dao.SectionDao;
import com.erp.pojo.Uom;
import com.erp.pojo.Section;
import com.wideplay.warp.persist.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 14, 2012
 * Time: 12:42:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SectionDaoImpl extends BaseDao implements SectionDao{
 
    @Transactional
      public List getSection(){

         String hql="from Section where deleted='1'";
         try {

                   

         }catch (Exception e){
             e.printStackTrace();

         }finally{
           //  s.close();
         }
          return getSession().createQuery(hql).list();
     }

    @Transactional
      public boolean SaveSection(Section section)

    {
     try {

                    
            if(section!=null){
               getSession().save(section);
                
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
    public void delete(Section section){
        try{
        
            section.setDeleted(0);
               getSession().update(section);
                


        }catch (Exception e){
            e.printStackTrace();
          
        }finally{
          //  getSession().close();
        }
    }
                                @Transactional
    public Section findById(Long id){
         Section ud=null;
           try {
                                 

        return (Section)getSession().createQuery("from Section where id='"+id+"'").uniqueResult();
           }catch (Exception e){

           }
        return null;
    }
                                              @Transactional
      public void update(Section section){
        try{
        
               getSession().update(section);
                


        }catch (Exception e){
            e.printStackTrace();

        }finally{
          //  getSession().close();
        }
    }
 }
