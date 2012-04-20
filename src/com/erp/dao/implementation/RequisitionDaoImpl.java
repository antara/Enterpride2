package com.erp.dao.implementation;

import com.erp.dao.RequisitionDao;
import com.erp.dao.BaseDao;
import com.erp.pojo.Requisition;
import com.erp.pojo.RequisitionDetail;
import com.erp.pojo.PurchaseOrder;
import com.wideplay.warp.persist.Transactional;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Created by IntelliJ IDEA.
 * User: Milind
 * Date: Feb 23, 2012
 * Time: 2:08:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequisitionDaoImpl extends BaseDao implements RequisitionDao {

    @Transactional
    public boolean SaveRequisition(Requisition requisition, List<RequisitionDetail> requisitiondetail) {
     try {

         for(Iterator<RequisitionDetail> i=requisitiondetail.iterator();i.hasNext();){
             RequisitionDetail r=(RequisitionDetail)i.next();

             if(r==null){
                 i.remove();

             }else if(r.getRequiredQty()==null){
                 i.remove();
             }
         }
            
            if(requisition!=null){
                 if (requisition.getRequisitionDate() == null) requisition.setRequisitionDate(new Date());
               Long q=(Long)getSession().createQuery("select max(id) from Requisition").uniqueResult();
                        //int mId=m.getMemberId();

                        if(q==null)
                        {
                          requisition.setId(1000l);
                        }
                        else
                        {

                           requisition.setId(q+1);

                        }
                requisition.setRequisitiondetailarray(requisitiondetail);
               getSession().save(requisition);
                
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
    public void update(Requisition requisition, List<RequisitionDetail> requisitiondetail) {
        try{
                    for(Iterator<RequisitionDetail> i=requisitiondetail.iterator();i.hasNext();){
                        RequisitionDetail g=(RequisitionDetail)i.next();

                        if(g==null){
                            i.remove();

                        }else if(g.getRequiredQty()==null){
                            i.remove();
                        }
                    }
        
                requisition.setRequisitiondetailarray(requisitiondetail);
               getSession().update(requisition);
                


        }catch (Exception e){
            e.printStackTrace();

        }finally{
          /*if(getSession().isOpen()){
              getSession().flush();
              getSession().close();
          }*/
        }
    }

    @Transactional
    public List getRequisition() {
             String hql="from Requisition";
            // List list=null;
             try {

                     


             }catch (Exception e){
                 e.printStackTrace();

             }finally{
               //  s.close();
             }
              return getSession().createQuery(hql).list();

            }

    
                                              @Transactional
    public Requisition findById(Long id) {

        return (Requisition)getSession().createCriteria(Requisition.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

      public  Requisition latestrequisition()
      {
          String hql="from Requisition where id=(select max(id) from Requisition)";
                 try {




                  }catch (Exception e){
                      e.printStackTrace();

                  }finally{
                    //  s.close();
                  }
                   return (Requisition)getSession().createQuery(hql).uniqueResult();

             }


      }

