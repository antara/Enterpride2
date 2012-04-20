package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.ItemDao;
import com.erp.utils.HibernateUtils;
import com.erp.pojo.Item;
import com.wideplay.warp.persist.Transactional;

import org.hibernate.Transaction;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 14, 2012
 * Time: 12:27:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemDaoImpl extends BaseDao implements ItemDao {

    public boolean checkUomPresent(String addUomName) {

           String u;
       
           boolean f=false;
           try{
                  u= (String) getSession().createQuery("select name from Uom where deleted='1' and name='"+addUomName+"'").uniqueResult();

              if(u!=null)
                  f=true;


              }
           finally{

                  }

           return f;  //To change body of implemented methods use File | Settings | File Templates.
       }
public boolean checkItemPresent(String addItemName) {

           String u;

           boolean f=false;
           try{
                  u= (String) getSession().createQuery("select name from Item where deleted='1' and name='"+addItemName+"'").uniqueResult();

              if(u!=null)
                  f=true;


              }
           finally{

                  }

           return f;  //To change body of implemented methods use File | Settings | File Templates.
       }
  public boolean checkSectionPresent(String addSectionName) {

           String u;

           boolean f=false;
           try{
                  u= (String) getSession().createQuery("select name from Section where deleted='1' and name='"+addSectionName+"'").uniqueResult();

              if(u!=null)
                  f=true;


              }
           finally{

                  }

           return f;  //To change body of implemented methods use File | Settings | File Templates.
       }
    @Transactional
    public List<String> getItemCodelst(){
        String hql="select itemCode from Item where deleted='1'";
// List list=null;
        try {




        }catch (Exception e){
            e.printStackTrace();

        }finally{
//  s.close();
        }
        return (List<String>)getSession().createQuery(hql).list();
    }

    @Transactional
    public List<String> allItem() {
        String hql="Select name from Item";
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
    public List<Item> searchByName(String name) {

        try {


        }catch (Exception e){

        }
        return (List<Item>)getSession().createQuery("FROM Item WHERE name LIKE '"+name+"%'").list();
// return (List<Item>)getSession().createQuery("SELECT * FROM item WHERE name LIKE '"+name+"' ").list();

    }

    @Transactional
    public boolean SaveItem(Item item)

    {
        try {

            if(item!=null){
                String subname=((String) getSession().createQuery("SELECT name FROM Section WHERE id='"+item.getSection().getId()+"'").uniqueResult()).substring(0,3);//getSection().getName();
                String sname= ((String) getSession().createQuery("SELECT max(itemCode) FROM Item WHERE itemCode like '"+subname+"%'").uniqueResult());
                if(sname==null)
                    item.setItemCode(subname+1000);
                else{


                    long l=Long.parseLong(sname.substring(3));
                    System.out.println(l);
                    l=l+1;
                    item.setItemCode(subname+l);
                }


                getSession().save(item);

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
    public void delete(Item item)  {
        try{

            item.setDeleted(0);
            getSession().update(item);



        }catch (Exception e){
            e.printStackTrace();

        }finally{
//  getSession().close();
        }
    }
    //find by id
    @Transactional
    public Item findById(Long id){
        try {

            return (Item)getSession().createQuery("from Item where id='"+id+"'").uniqueResult();
        }catch (Exception e){

        }
        return null;
    }
    @Transactional
    public List getItemId(){
        String hql="select id from Item where deleted='1'";
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
    public List getItemCode(){
        String hql="select itemCode from Item where deleted='1'";
// List list=null;
        try {




        }catch (Exception e){
            e.printStackTrace();

        }finally{
//  s.close();
        }
        return getSession().createQuery(hql).list();
    }
    //get all list
    @Transactional
    public List getItem(){

        String hql="from Item where deleted='1'";
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
    public void update(Item item)  {
        try{
           if(item!=null){


               String subname=((String) getSession().createQuery("SELECT name FROM Section WHERE id='"+item.getSection().getId()+"'").uniqueResult()).substring(0,3);//getSection().getName();

                String sname= ((String) getSession().createQuery("SELECT max(itemCode) FROM Item WHERE itemCode like '"+subname+"%'").uniqueResult());
               if(subname.contains(item.getItemCode()))
               {
                  item.setItemCode(item.getItemCode());
               }
                else{
               if(sname==null)
                    item.setItemCode(subname+1000);
                else{


                    long l=Long.parseLong(sname.substring(3));
                    System.out.println(l);
                    l=l+1;
                    item.setItemCode(subname+l);

               }
               }

                   
                

            getSession().update(item);
           }


        }catch (Exception e){
            e.printStackTrace();

        }finally{
//  getSession().close();
        }
    }
   
}
