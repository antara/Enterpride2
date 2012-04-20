
package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.StoreIssueDao;
import com.erp.pojo.*;
import com.erp.utils.BaseUtils;
import com.wideplay.warp.persist.Transactional;

import java.util.List;
import java.util.Iterator;
import java.util.Date;
import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import org.hibernate.Criteria;

/**
 * Created by IntelliJ IDEA.
 * User: Milind
 * Date: Feb 28, 2012
 * Time: 12:34:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class StoreIssueDaoImpl extends BaseDao implements StoreIssueDao{

    @Transactional
    public void update(StoreIssue storeissue,List<StoreIssueDetail> storeissuedetailarray) {
        try{
               for(Iterator<StoreIssueDetail> i=storeissuedetailarray.iterator();i.hasNext();){
                        StoreIssueDetail s=(StoreIssueDetail)i.next();
                        if(s==null){
                            i.remove();
                        }
                        else if(s.getIssueQty()==0){
                            i.remove();
                        }


             }

                storeissue.setStoreissuedetailarray(storeissuedetailarray);
                
                getSession().update(storeissue);
                
            }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{

        }
   }
    @Transactional
    public List getRequisition(){
             String hql="from Requisition";
             try {
                     
             }catch (Exception e){
                 e.printStackTrace();

             }finally{

             }
              return getSession().createQuery(hql).list();
            }

    @Transactional
   public boolean SaveStoreIssue(StoreIssue storeissue, List<StoreIssueDetail> storeissuedetail) {
     try {
           for(Iterator<StoreIssueDetail> i=storeissuedetail.iterator();i.hasNext();){
             StoreIssueDetail r=(StoreIssueDetail)i.next();
               if(r==null){
                 i.remove();
                   continue;
               }else if(r.getIssueQty()==0){
                 i.remove();
                   continue;
             }

               Query strQuerydb = getSession().createSQLQuery("Select id,closing_quantity,issued_quantity,open_quantity,received_quantity,create_date from daily_stock where item_id='"+r.getItem().getId()+"'");
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
               dailystock.setReceivedQuantity(received_quantity);
                dailystock.setIssuedQuantity(issued_quantity+r.getIssueQty());
                dailystock.setItem(r.getItem());
                dailystock.setClosingQuantity(closing_quantity-r.getIssueQty());
                   if (dailystock.getDate() == null) dailystock.setDate(new Date());
                getSession().update(dailystock);

                }
                else{

                      DailyStockRecord dailystock=new DailyStockRecord();
              dailystock.setOpenQuantity(closing_quantity);
               dailystock.setReceivedQuantity(0);
                dailystock.setIssuedQuantity(r.getIssueQty());
                dailystock.setItem(r.getItem());
                dailystock.setClosingQuantity(closing_quantity-r.getIssueQty());
                   if (dailystock.getDate() == null) dailystock.setDate(new Date());
                getSession().save(dailystock);
                }
               
         }
            
            if(storeissue!=null){
                 if (storeissue.getIssueDate() == null) storeissue.setIssueDate(new Date());
                // StoreIssue q=(StoreIssue)getSession().createQuery("select max(id) from StoreIssue").uniqueResult();
                   Long q=(Long)getSession().createQuery("select max(id) from StoreIssue").uniqueResult();
                        //int mId=m.getMemberId();

                        if(q==null)
                        {
                         storeissue.setId(1000l);
                        }
                        else
                        {

                           storeissue.setId(q+1);

                        }
                        storeissue.setStoreissuedetailarray(storeissuedetail);
                getSession().save(storeissue);
                
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
    public StoreIssue findById(long id) {
          StoreIssue ud=null;
               try {
                        
                        return (StoreIssue)getSession().createQuery("from StoreIssue where requisition_id='"+id+"'").uniqueResult();
               }catch (Exception e){

               }
            return null;
    }

    @Transactional
    public List requisitionIds() {
        List l=null;
        String hql="SELECT r.id FROM requisition r LEFT OUTER JOIN storeissue s on r.id=s.requisition_id WHERE s.requisition_id IS NULL";
        try {
                    
                    l=getSession().createSQLQuery(hql).list();
            
                    return l;
        }catch (Exception e){
                    e.printStackTrace();
        }
        finally{
        }
        return null;
   }

    @Transactional
    public List issuedids() {
        List l=null;
        String hql="SELECT DISTINCT s.requisition_id FROM requisition r LEFT OUTER JOIN storeissue s on (r.id=s.requisition_id) WHERE s.requisition_id IS NOT NULL and issue_date like '"+ BaseUtils.getCurrentTimestamp()+"%'";
       try {
                 
                 l=getSession().createSQLQuery(hql).list();
                 return l;
       }catch (Exception e){
                e.printStackTrace();
       }finally{
       }
       return null;
    }

    @Transactional
     public List<StoreIssue> searchByName(Long  n){
        try {
                          

                  }catch (Exception e){

                  }
                return (List<StoreIssue>)getSession().createQuery("FROM StoreIssue WHERE id='"+n+"'").list();
           }


    @Transactional
    public Double getDailyStockVal(Double itemcode) {
        {
                double closing_quantity=0;
         try {

                         
              Query strQuerydb = getSession().createSQLQuery("Select closing_quantity,issued_quantity from daily_stock where item_id='"+itemcode+"'");
                  List<?> lst1 = strQuerydb.list();
            Iterator idb=lst1.iterator();


            while (idb.hasNext()){
                Object[] rowData=(Object[])idb.next();

                     closing_quantity=(Double)rowData[0];

            }


                 }catch (Exception e){
                     e.printStackTrace();

                 }finally{
                   //  s.close();
                 }


                  //return (Long)getSession().createQuery("From DailyStockRecord WHERE  item.id=itemcode AND date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").uniqueResult();
                  return closing_quantity;
            }
    }

                  @Transactional
    public List<Long> allStoreIssue() {
                  String hql="Select id from StoreIssue";
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
    public StoreIssue latestStoreIssue(){
         String hql="from StoreIssue where id=(select max(id) from StoreIssue)";
        try {
         
         }catch (Exception e){
             e.printStackTrace();

         }finally{
           //  s.close();
         }
          return (StoreIssue)getSession().createQuery(hql).uniqueResult();
    }

    @Transactional
	public List<DailyStockRecord> getDailyStockDate() {
        {
         try {

                         

                 }catch (Exception e){
                     e.printStackTrace();

                 }finally{
                   //  s.close();
                 }

                                                                                                                                //"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'
                  //return (Long)getSession().createQuery("From DailyStockRecord WHERE  item.id=itemcode AND date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").uniqueResult();
                  return (List<DailyStockRecord>)getSession().createQuery("from DailyStockRecord WHERE date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").list();
                //  return getSession().createQuery("From DailyStockRecord WHERE date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").list();
            }
    }

    @Transactional
 public List<DailyStockRecord> getDailyStockDate(String s) {
        {
         try {

                         

                 }catch (Exception e){
                     e.printStackTrace();

                 }finally{
                   //  s.close();
                 }

                                                                                                                                //"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'
                  //return (Long)getSession().createQuery("From DailyStockRecord WHERE  item.id=itemcode AND date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").uniqueResult();
                  return (List<DailyStockRecord>)getSession().createQuery("from DailyStockRecord WHERE date LIKE '"+s+"%'").list();
                //  return getSession().createQuery("From DailyStockRecord WHERE date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").list();
            }
    }


    @Transactional
 public List<DailyStockRecord> getDailyStockMonth(int month, int year) {
        {
         try {

                         

                 }catch (Exception e){
                     e.printStackTrace();

                 }finally{
                   //  s.close();
                 }

                                                                                                                                //"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'
                  //return (Long)getSession().createQuery("From DailyStockRecord WHERE  item.id=itemcode AND date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").uniqueResult();
                if(month<=9)
                  return (List<DailyStockRecord>)getSession().createQuery("from DailyStockRecord WHERE date LIKE '"+year+"-"+0+month+"%'").list();
                else
                    return (List<DailyStockRecord>)getSession().createQuery("from DailyStockRecord WHERE date LIKE '"+year+"-"+month+"%'").list();                    
                //  return getSession().createQuery("From DailyStockRecord WHERE date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").list();
            }
    }
                  @Transactional
public List<DailyStockRecord> getDailyStockYear(int year) {
        {
         try {

                         

                 }catch (Exception e){
                     e.printStackTrace();

                 }finally{
                   //  s.close();
                 }
            return (List<DailyStockRecord>)getSession().createQuery("from DailyStockRecord WHERE date LIKE '"+year+"-%'").list();

            }
    }

    @Transactional
public List getDailyStockByItemCode(String itemcodetxt)
{
    List results;
    String sql="SELECT d.id,d.create_date,i.name,i.item_code,d.closing_quantity,d.issued_quantity,d.open_quantity,d.received_quantity,d.remark FROM daily_stock d LEFT OUTER JOIN item i on d.item_id=i.id WHERE i.item_code='"+itemcodetxt+"'";
//    String hql="FROM DailyStockRecord d LEFT OUTER JOIN Item i on d.item.id=i.id WHERE i.itemCode='"+itemcodetxt+"'";
        //String hql="FROM DailyStockRecord as d LEFT OUTER JOIN Item as i  WHERE itemCode='"+itemcodetxt+"'";\
            {
         try {
                
             }catch (Exception e){
                  e.printStackTrace();
             }finally{
               //  s.close();
             }
                Query query = getSession().createSQLQuery(sql);
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                results = query.list();
                return results;//getSession().createSQLQuery(sql).list();
            }
    }

    @Transactional
    public List<DailyStockRecord> getDailyStockByFromTo(String fromdate, String todate) {
        {
         try {

                         

                 }catch (Exception e){
                     e.printStackTrace();

                 }finally{
                   //  s.close();
                 }

                                                                                                                                //"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'
                  //return (Long)getSession().createQuery("From DailyStockRecord WHERE  item.id=itemcode AND date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").uniqueResult();
                  return (List<DailyStockRecord>)getSession().createQuery("from DailyStockRecord WHERE date BETWEEN '"+fromdate+"%' AND '"+todate+"%'").list();
                //  return getSession().createQuery("From DailyStockRecord WHERE date LIKE '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"%'").list();
            }
    }
   
}
