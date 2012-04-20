package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.GrnDao;
import com.erp.pojo.*;
import com.erp.utils.BaseUtils;
import com.wideplay.warp.persist.Transactional;

import java.util.*;
import java.math.BigInteger;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 21, 2012
 * Time: 11:08:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class GrnDaoImpl extends BaseDao implements GrnDao{
    @Transactional
    public void setPaymentStatusSuccess(Long id) {
        System.out.println("in method");
         String sql="UPDATE Grn SET paymentStatus='Success' WHERE id="+id+"";
        // List list=null;
         try {
                    getSession().createQuery(sql);
                     Query query = getSession().createQuery(sql);
                    int result = query.executeUpdate();
             System.out.println("RESULT :"+result);
         }catch (Exception e){
             e.printStackTrace();
         }finally{
           //  s.close();
         }
    }

    @Transactional
    public void setVerifiedToY(Long gn) {
        System.out.println("in method");
         String sql="UPDATE Grn SET verified='Y' WHERE id="+gn+"";
        // List list=null;
         try {
                    getSession().createQuery(sql);
                     Query query = getSession().createQuery(sql);
                    int result = query.executeUpdate();
             System.out.println("RESULT :"+result);
         }catch (Exception e){
             e.printStackTrace();
         }finally{
           //  s.close();
         }
    }

    Date today=new Date();
    //save

    @Transactional
      public boolean SaveGrn(Grn grn, List<GrnDetail> grndetail)

    {
     try {
                   GrnDetail gr=null;

         for(Iterator<GrnDetail> i=grndetail.iterator();i.hasNext();){
              gr=(GrnDetail)i.next();

             if(gr==null){
                 i.remove();
                continue;        
             }else if(gr.getAcceptedQty()==null){
                 i.remove();
                 continue;
             }

                Query strQuery = getSession().createSQLQuery("Select id from daily_stock where item_id='"+gr.getItem().getId()+"'");
				List<?> lst = strQuery.list();
				if (lst != null && lst.size() <= 0) {
                   DailyStockRecord dailystock=new DailyStockRecord();
                    dailystock.setOpenQuantity(0);
                    dailystock.setReceivedQuantity(gr.getAcceptedQty());
                    dailystock.setIssuedQuantity(0);
                        dailystock.setItem(gr.getItem());
                        dailystock.setClosingQuantity(gr.getAcceptedQty());
                           if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().save(dailystock);
                  
                }  else{
                       Query strQuerydb = getSession().createSQLQuery("Select id,closing_quantity,issued_quantity,open_quantity,received_quantity,create_date from daily_stock where item_id='"+gr.getItem().getId()+"'");
          				List<?> lst1 = strQuerydb.list();
                    Iterator idb=lst1.iterator();
                    BigInteger id=null;
                     double closing_quantity=0,issued_quantity=0,open_quantity=0,received_quantity=0;
                   // Date datedb=null;
                      String sst = null,datedb=null;
                    while (idb.hasNext()){
                        Object[] rowData=(Object[])idb.next();
                           id=(BigInteger)rowData[0];
                             closing_quantity=(Double)rowData[1];
                         issued_quantity=(Double)rowData[2];
                         open_quantity=(Double)rowData[3];
                         received_quantity=(Double)rowData[4];
                    			datedb = (rowData[5].toString()).substring(0,10);
                    }

                         if(BaseUtils.getCurrentTimestamp().equals(datedb)){

                             String idstg=id.toString();
                           long id1ong=Long.parseLong(idstg);
                          DailyStockRecord dailystock = (DailyStockRecord) getSession().get(DailyStockRecord.class, id1ong);

                      dailystock.setOpenQuantity(open_quantity);
                       dailystock.setReceivedQuantity(received_quantity+gr.getAcceptedQty());
                        dailystock.setIssuedQuantity(issued_quantity);
                        dailystock.setItem(gr.getItem());
                        dailystock.setClosingQuantity(open_quantity+received_quantity+gr.getAcceptedQty());
                           if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().update(dailystock);

                        }
                        else{

                              DailyStockRecord dailystock=new DailyStockRecord();
                      dailystock.setOpenQuantity(closing_quantity);
                       dailystock.setReceivedQuantity(gr.getAcceptedQty());
                        dailystock.setIssuedQuantity(0);
                        dailystock.setItem(gr.getItem());
                        dailystock.setClosingQuantity(closing_quantity+gr.getAcceptedQty());
                           if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().save(dailystock);
                        }
                }

         }

            
            if(grn!=null){

                 if (grn.getCreateDate() == null) grn.setCreateDate(new Date());
                Long q=(Long)getSession().createQuery("select max(id) from Grn").uniqueResult();
                           if(q==null)
                        {
                         grn.setId(1000L);
                        }
                        else
                        {

                           grn.setId(q+1);

                        }
                grn.setGrndetailarray(grndetail);
               getSession().save(grn);
                 if(grn.getStatus().equals("close")){
                         grn.setVerified("N");
                    PurchaseOrder purchase = (PurchaseOrder) getSession().get(PurchaseOrder.class, grn.getPurchaseOrder().getId());
                purchase.setStatus("close");
                
                getSession().update(purchase);
                 }

               // purchase.
                
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

     //get all list
    @Transactional
     public List getGrn(){
         String hql="from Grn ";
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
       public List getTodayGrn()  {
          
         String hql="from Grn where createDate like '"+ BaseUtils.getCurrentTimestamp()+"%'";
        // List list=null;
         try {
                 
         }catch (Exception e){
             e.printStackTrace();
         }finally{
           //  s.close();
         }
          return getSession().createQuery(hql).list();
     }
    //find by id
    @Transactional
    public Grn findById(Long id){
           try {
              
        return (Grn)getSession().createCriteria(Grn.class).add(Restrictions.eq("id",id)).uniqueResult();
           }catch (Exception e){
                e.printStackTrace();
           }
        return null;
    }

      //update
    @Transactional
      public void update(Grn grn,List<GrnDetail> grndetail)  {
        try{
                    for(Iterator<GrnDetail> i=grndetail.iterator();i.hasNext();){
                        GrnDetail g=(GrnDetail)i.next();

                        if(g==null){
                            i.remove();
                            continue;

                        }else if(g.getAcceptedQty()==null){
                            i.remove();
                            continue;
                        }
                         Query strQuery = getSession().createSQLQuery("Select id from daily_stock where item_id='"+g.getItem().getId()+"'");
				List<?> lst = strQuery.list();
				if (lst != null && lst.size() <= 0) {
                   DailyStockRecord dailystock=new DailyStockRecord();
                    dailystock.setOpenQuantity(0);
                    dailystock.setReceivedQuantity(g.getAcceptedQty());
                    dailystock.setIssuedQuantity(0);
                        dailystock.setItem(g.getItem());
                        dailystock.setClosingQuantity(g.getAcceptedQty());
                           if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().save(dailystock);

                }  else{
                       Query strQuerydb = getSession().createSQLQuery("Select id,closing_quantity,issued_quantity,open_quantity,received_quantity,create_date from daily_stock where item_id='"+g.getItem().getId()+"'");
          				List<?> lst1 = strQuerydb.list();
                    Iterator idb=lst1.iterator();
                    BigInteger id=null;
                     double closing_quantity=0,issued_quantity=0,open_quantity=0,received_quantity=0;
                   // Date datedb=null;
                      String sst = null,datedb=null;
                    while (idb.hasNext()){
                        Object[] rowData=(Object[])idb.next();
                           id=(BigInteger)rowData[0];
                             closing_quantity=(Double)rowData[1];
                         issued_quantity=(Double)rowData[2];
                         open_quantity=(Double)rowData[3];
                         received_quantity=(Double)rowData[4];
                    			datedb = (rowData[5].toString()).substring(0,10);
                    }
                    if(BaseUtils.getCurrentTimestamp().equals(datedb)){
                       Grn strQuerydb1 =(Grn) getSession().createQuery("from Grn where id='"+grn.getId()+"'").uniqueResult();
                    GrnDetail valuefrmdb=null;
          		     for(Iterator<GrnDetail> iterate=strQuerydb1.getGrndetailarray().iterator();iterate.hasNext();){
                              valuefrmdb=(GrnDetail)iterate.next();
                     }
                      String idstg=id.toString();
                           long id1ong=Long.parseLong(idstg);
                   DailyStockRecord dailystock = (DailyStockRecord) getSession().get(DailyStockRecord.class, id1ong);

                      dailystock.setOpenQuantity(open_quantity);
                       dailystock.setReceivedQuantity((received_quantity+g.getAcceptedQty())-valuefrmdb.getAcceptedQty());
                        dailystock.setIssuedQuantity(issued_quantity);
                        dailystock.setItem(g.getItem());
                        dailystock.setClosingQuantity((open_quantity+received_quantity+g.getAcceptedQty())-valuefrmdb.getAcceptedQty());
                           if (dailystock.getDate() == null) dailystock.setDate(new Date());
                                   getSession().update(dailystock);
                    }else{
                             DailyStockRecord dailystock=new DailyStockRecord();
                      dailystock.setOpenQuantity(closing_quantity);
                       dailystock.setReceivedQuantity(g.getAcceptedQty());
                        dailystock.setIssuedQuantity(issued_quantity);
                        dailystock.setItem(g.getItem());
                        dailystock.setClosingQuantity(closing_quantity+g.getAcceptedQty());
                           if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().save(dailystock);
                    }
                    }
                    }

        
             if (grn.getCreateDate() == null) grn.setCreateDate(new Date());
                grn.setGrndetailarray(grndetail);
               getSession().merge(grn);
                

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
public List<Long> allGrnno() {
            String hql="Select id from Grn";
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
     public List<Grn> searchByName(Long n) {

          try {
                   

           }catch (Exception e){

           }
         return (List<Grn>)getSession().createQuery("FROM Grn WHERE id='"+n+"'").list();
    }

    @Transactional
    public List<Grn> unverifiedGrnList() {
        return (List<Grn>)getSession().createQuery("FROM Grn WHERE verified='N' and status='close'").list();
    }

    @Transactional
    public List<Grn> verifiedGrnList() {
        return (List<Grn>)getSession().createQuery("FROM Grn WHERE verified='Y'  and paymentStatus='Requested'").list();
    }
    
        
}
