package com.erp.action;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.ajax.JavaScriptResolution;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.Validate;

import java.util.List;

import com.erp.dao.ItemDao;
import com.erp.dao.SectionDao;
import com.erp.dao.UomDao;
import com.erp.pojo.Item;
import com.erp.pojo.Uom;
import com.erp.pojo.Section;
import com.erp.constants.PermissionConstants;
import com.google.inject.Inject;

import javax.annotation.security.RolesAllowed;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 13, 2012
 * Time: 9:59:26 AM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class ItemActionBean extends BaseActionBean{
    private List<Uom> uomlst;
    private List<Section> sectionlst;
    private List<Item> itemlst;
    private Item item;
    private boolean flag;
    private String addUomName;
    private String addSectionName;
    private String addItemName;


    public String getAddItemName() {
        return addItemName;
    }

    public void setAddItemName(String addItemName) {
        this.addItemName = addItemName;
    }

    public String getAddSectionName() {
        return addSectionName;
    }

    public void setAddSectionName(String addSectionName) {
        this.addSectionName = addSectionName;
    }

    public String getAddUomName() {
        return addUomName;
    }

    public void setAddUomName(String addUomName) {
        this.addUomName = addUomName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Item> getItemlst() {
        return itemlst;
    }

    public void setItemlst(List<Item> itemlst) {
        this.itemlst = itemlst;
    }


    public Item getItem() {
         if(id != 0) {

               return itemdao.findById(id);
          }
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Uom> getUomlst() {
        return uomlst;
    }

    public void setUomlst(List<Uom> uomlst) {
        this.uomlst = uomlst;
    }

    public List<Section> getSectionlst() {
        return sectionlst;
    }

    public void setSectionlst(List<Section> sectionlst) {
        this.sectionlst = sectionlst;
    }

    @RolesAllowed({PermissionConstants.ADD_ITEM})
    @DefaultHandler
    //Redirect to add item page
   public Resolution pagedirect(){
           uomlst=uomdao.getUom();
         sectionlst=sectiondao.getSection();
                 itemlst=itemdao.getItem();

       return new ForwardResolution("jsp/addItem.jsp");
   }
    public Resolution checkUomAlreadyPresent()
    {
        flag=itemdao.checkUomPresent(addUomName);
        return new JavaScriptResolution(flag);
    }
    public Resolution checkSectionAlreadyPresent()
    {
       flag=itemdao.checkSectionPresent(addSectionName);
        return new JavaScriptResolution(flag);
    }
public Resolution checkItemAlreadyPresent()
    {
       flag=itemdao.checkItemPresent(addItemName);
        return new JavaScriptResolution(flag);
    }

     @RolesAllowed({PermissionConstants.DELETE_ITEM})
  //Delete a item
     public Resolution delete(){

         item=itemdao.findById(id);
         

        itemdao.delete(item);
                 itemlst=itemdao.getItem();
        return new ForwardResolution("jsp/deleteItem.jsp");
    }


   @RolesAllowed({PermissionConstants.ADD_ITEM})
   //Add a item
     public Resolution additem(){
         itemdao.SaveItem(item);

       return new RedirectResolution(getContext().getSourcePage());
   }

    @RolesAllowed({PermissionConstants.DELETE_ITEM})
  //Redirect to delete item page
     public Resolution deleteitemlink(){

            itemlst=itemdao.getItem();

      return new ForwardResolution("jsp/deleteItem.jsp");
   }

    @RolesAllowed({PermissionConstants.UPDATE_ITEM})
  //Redirect to update item page
     public Resolution updateitemlink(){
            itemlst=itemdao.getItem();

      return new ForwardResolution("jsp/updateItem.jsp");
   }
     @RolesAllowed({PermissionConstants.UPDATE_ITEM})
   //get list of item
   public Resolution getitem(){
       item= itemdao.findById(id);
        uomlst=uomdao.getUom();
        sectionlst=sectiondao.getSection();
       itemlst=itemdao.getItem();
        return new ForwardResolution("jsp/updateItem.jsp");
    }
      @RolesAllowed({PermissionConstants.UPDATE_ITEM})
    //Update a item
     public Resolution updateitem(){
       
       itemdao.update(item);
                itemlst=itemdao.getItem();
        return new RedirectResolution("jsp/updateItem.jsp");
    }

}
