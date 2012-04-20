package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.PurchaseOrderDao;
import com.erp.pojo.PurchaseOrder;
import com.erp.pojo.PurchaseOrderDetail;
import com.wideplay.warp.persist.Transactional;

import java.util.List;
import java.util.Iterator;
import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: Mar 1, 2012
 * Time: 12:18:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderDaoImpl extends BaseDao implements PurchaseOrderDao  {

    @Transactional
     public boolean SavePurchaseOrder(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchasedetail){
         try {
                   PurchaseOrderDetail g=null;

         for(Iterator<PurchaseOrderDetail> i=purchasedetail.iterator();i.hasNext();){
              g=(PurchaseOrderDetail)i.next();

             if(g==null){
                 i.remove();
                continue;
             }else if(g.getOrderedQty()==null){
                 i.remove();
                 continue;
             }

         }

            
            if(purchaseOrder!=null){
                 if (purchaseOrder.getCreateDate() == null) purchaseOrder.setCreateDate(new Date());
                Query q=getSession().createQuery("select max(id) from PurchaseOrder");
                    
                        List list=q.list();
                String a=null;
                        if(list.contains(null))
                        {
                             a="po1000";

                        }
                        else
                        {
                        PurchaseOrder ponumber=(PurchaseOrder)getSession().createQuery("from PurchaseOrder where id=(select  max(id) from PurchaseOrder)").uniqueResult();

                            String orderno=ponumber.getPurchaseOrderNo();
                            int i=Integer.parseInt(orderno.substring(2));
                            i++;

                             a="po"+i;
                       

                        }
              
		              purchaseOrder.setGeneratedPO(0);  
                    purchaseOrder.setPurchaseOrderNo(a);
                purchaseOrder.setPurchasedetailarray(purchasedetail);

               getSession().save(purchaseOrder);



                
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
       public List<PurchaseOrder> getPurchaseOrder(){
           String hql="from PurchaseOrder where generatedPO=0";
        // List list=null;
         try {

                 


         }catch (Exception e){
             e.printStackTrace();

         }finally{
           //  s.close();
         }
          return (List<PurchaseOrder>)getSession().createQuery(hql).list();
       }
                                @Transactional
    public void update(PurchaseOrder purchaseOrder,List<PurchaseOrderDetail> purchasedetail){
          PurchaseOrderDetail g=null;
             try{
                    for(Iterator<PurchaseOrderDetail> i=purchasedetail.iterator();i.hasNext();){
                       g =(PurchaseOrderDetail)i.next();

                        if(g==null){
                            i.remove();

                        }else if(g.getOrderedQty()==null){
                            i.remove();
                        }
                    }

        

                  purchaseOrder.setGeneratedPO(0);  
             if (purchaseOrder.getCreateDate() == null) purchaseOrder.setCreateDate(new Date());
                purchaseOrder.setPurchasedetailarray(purchasedetail);
               getSession().merge(purchaseOrder);
                

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
     public PurchaseOrder findById(Long id){
        PurchaseOrder ud=null;
               try {
                        
                     
               }catch (Exception e){

               }
                return (PurchaseOrder)getSession().createQuery("from PurchaseOrder where id='"+id+"'").uniqueResult();
     }
                                                            @Transactional
    public void deletePO( PurchaseOrder purchaseOrder)  {
        try{
        
                          purchaseOrder.setGeneratedPO(1);
                getSession().update(purchaseOrder);

                

        }catch (Exception e){
            e.printStackTrace();

        }finally{
          //  getSession().close();
        }
    }
                                                                          @Transactional
    public PurchaseOrder latestPurchaseOrder() {
        String hql="from PurchaseOrder where id=(select max(id) from PurchaseOrder)";
        try {

                 


         }catch (Exception e){
             e.printStackTrace();

         }finally{
           //  s.close();
         }
          return (PurchaseOrder)getSession().createQuery(hql).uniqueResult();

    }
                                                                                        @Transactional
    public void generatePurchaseOrder(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchasedetail) {
      try{
                    for(Iterator<PurchaseOrderDetail> i=purchasedetail.iterator();i.hasNext();){
                        PurchaseOrderDetail g=(PurchaseOrderDetail)i.next();

                        if(g==null){
                            i.remove();

                        }else if(g.getOrderedQty()==null){
                            i.remove();
                        }
                    }
        

                  purchaseOrder.setGeneratedPO(1);  
             if (purchaseOrder.getCreateDate() == null) purchaseOrder.setCreateDate(new Date());
                purchaseOrder.setPurchasedetailarray(purchasedetail);
               getSession().update(purchaseOrder);
                

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
    public List<String> getAllPurchaseOrderNo() {
       String hql="Select purchase_order_no from purchase_order";
            // List list=null;
        
             try {

                     


             }catch (Exception e){
                 e.printStackTrace();

             }finally{
               //  s.close();
             }
              return getSession().createSQLQuery(hql).list();
         }
                  @Transactional
    public PurchaseOrder findByLastUpdate() {

               try {
                        
                       return (PurchaseOrder)getSession().createQuery("from PurchaseOrder order by id desc").uniqueResult();
               }catch (Exception e){

               }
                return null;//(PurchaseOrder)getSession().createQuery("from PurchaseOrder where id='"+id+"'").uniqueResult();
     }
                                @Transactional
    public List<PurchaseOrder> getPoByStatus(){
             String hql="from PurchaseOrder where status='open' and generatedPO='1' and deleted='1'";
          // List list=null;
           try {

                   


           }catch (Exception e){
               e.printStackTrace();

           }finally{
             //  s.close();
           }
            return (List<PurchaseOrder>)getSession().createQuery(hql).list();
         }
    @Transactional
     public List<String> allPOnoForSearch() {
                  String hql="Select id from PurchaseOrder";
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
      public List<PurchaseOrder> searchByName(String  n){
        try {



                  }catch (Exception e){

                  }
               return getSession().createQuery("FROM PurchaseOrder WHERE purchaseOrderNo='"+n+"'").list();
           }
}
