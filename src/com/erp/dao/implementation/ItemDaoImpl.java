package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.ItemDao;
import com.erp.pojo.Item;
import com.wideplay.warp.persist.Transactional;
import java.util.List;


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
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
    public boolean checkItemPresent(String addItemName) {

        String u;
        boolean f=false;
        try{

            u= (String) getSession().createQuery("select name from Item where deleted='1' and name='"+addItemName+"'").uniqueResult();
            System.out.println("check item present "+u);
            if(u!=null)
                f=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
    public List<String> getItemCodelst(){
        return (List<String>)getSession().createQuery("select itemCode from Item where deleted='1'").list();
    }
     public List<String> getItemNamelst(){
        return (List<String>)getSession().createQuery("select name from Item where deleted='1'").list();
    }
    public List<String> allItem() {
        return getSession().createQuery("Select name from Item").list();
    }

    @Transactional
    public List<Item> searchByName(String name) {
        return (List<Item>)getSession().createQuery("FROM Item WHERE name LIKE '"+name+"%'").list();
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
        }
    }
    //find by id
    public Item findById(Long id){
        return (Item)getSession().createQuery("from Item where id='"+id+"'").uniqueResult();
    }

    public List getItemId(){
        return getSession().createQuery("select id from Item where deleted='1'").list();
    }

    public List getItemCode(){
        return getSession().createQuery("select itemCode from Item where deleted='1'").list();
    }

    public List getItem(){
        return getSession().createQuery("from Item where deleted='1'").list();
    }
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
        }
    }
    public List<String> getItemUomlst() {
        return getSession().createQuery("Select DISTINCT i.uom.name from Item i where deleted='1' ").list();
    }
    public List<String> getItemSectionlst() {
        return getSession().createQuery("Select DISTINCT i.section.name from Item i where deleted='1' ").list();
    }
    public List<Item> searchByItemCode(String name) {
        return (List<Item>)getSession().createQuery("FROM Item WHERE itemCode LIKE '"+name+"%'").list();
    }
    public List<Item> searchByItemUom(String name) {
        return (List<Item>)getSession().createQuery("FROM Item i WHERE i.uom.name LIKE '"+name+"%'").list();
    }
    public List<Item> searchByItemSection(String name) {
        return (List<Item>)getSession().createQuery("FROM Item i WHERE i.section.name ='"+name+"'").list();
    }

}
