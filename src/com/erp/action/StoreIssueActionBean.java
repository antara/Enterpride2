package com.erp.action;

import com.erp.pojo.*;
import com.erp.constants.PermissionConstants;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.ajax.JavaScriptResolution;

import javax.annotation.security.RolesAllowed;

/**
 * Created by IntelliJ IDEA.
 * User: Milind
 * Date: Feb 28, 2012
 * Time: 12:25:13 PM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class StoreIssueActionBean extends BaseActionBean{
    List<StoreIssue> storeissuelst;
    private StoreIssue storeissue;
     List<Requisition> requisitionlst;
    private Requisition requisition;
      private Item item;
      private List<Item> itemidlst;
   private List<StoreIssueDetail> storeissuedetailarray;
    private List requisitionIds;
    private List issuedids;
     private  String popup=null;
     private Double itemcode;
     private Double dailystockval;
     private DailyStockRecord dailystock;
     private Long forid;

    public Long getForid() {
        return forid;
    }

    public void setForid(Long forid) {
        this.forid = forid;
    }

    public DailyStockRecord getDailystock() {
        return dailystock;
    }

    public void setDailystock(DailyStockRecord dailystock) {
        this.dailystock = dailystock;
    }

    public Double getItemcode() {
        return itemcode;
    }

    public void setItemcode(Double itemcode) {
        this.itemcode = itemcode;
    }

    public Double getDailystockval() {
        return dailystockval;
    }

    public void setDailystockval(Double dailystockval) {
        this.dailystockval = dailystockval;
    }

    public List getIssuedids() {
        return issuedids;
    }

    public void setIssuedids(List issuedids) {
        this.issuedids = issuedids;
    }

    public List<Long> getRequisitionIds() {
        return requisitionIds;
    }

    public void setRequisitionIds(List<Long> requisitionIds) {
        this.requisitionIds = requisitionIds;
    }

    public List<StoreIssueDetail> getStoreissuedetailarray() {
        return storeissuedetailarray;
    }

    public void setStoreissuedetailarray(List<StoreIssueDetail> storeissuedetailarray) {
        this.storeissuedetailarray = storeissuedetailarray;
    }

    public List<StoreIssue> getStoreissuelst() {
        return storeissuelst;
    }

    public void setStoreissuelst(List<StoreIssue> storeissuelst) {
        this.storeissuelst = storeissuelst;
    }

    public StoreIssue getStoreissue() {
        if(id!=0)
        {
            return storeissuedao.findById(getId());
        }
        return storeissue;
    }
     public void setStoreissue(StoreIssue storeissue) {
        this.storeissue = storeissue;
    }
    public List<Requisition> getRequisitionlst() {
        return requisitionlst;
    }

    public void setRequisitionlst(List<Requisition> requisitionlst) {
        this.requisitionlst = requisitionlst;
    }

    public Requisition getRequisition() {
        return requisition;
    }

    public void setRequisition(Requisition requisition) {
        this.requisition = requisition;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItemidlst() {
        return itemidlst;
    }

    public void setItemidlst(List<Item> itemidlst) {
        this.itemidlst = itemidlst;
    }

    public String getPopup() {
        return popup;
    }//redirect to store issue page

    public void setPopup(String popup) {
        this.popup = popup;
    }

    @RolesAllowed({PermissionConstants.ADD_STORE_ISSUE})
    public Resolution addStoreIssueLink()
    {
        requisitionlst=storeissuedao.getRequisition();
        requisitionIds=storeissuedao.requisitionIds();
        issuedids=storeissuedao.issuedids();
        System.out.println("requisition"+requisition);
        itemidlst= itemdao.getItem();
        return new ForwardResolution("jsp/addStoreIssue.jsp");
    }
     public Resolution redirectLink()
    {
        requisitionlst=storeissuedao.getRequisition();
        requisitionIds=storeissuedao.requisitionIds();
        issuedids=storeissuedao.issuedids();
        requisition=requisitiondao.latestrequisition();
        
        System.out.println("requisition"+requisition);
        itemidlst= itemdao.getItem();
        System.out.println("item"+itemidlst);
       
        System.out.println("in redirectLink dailystock"+dailystock);
        return new ForwardResolution("jsp/addStoreIssue.jsp");
    }

     @RolesAllowed({PermissionConstants.ADD_STORE_ISSUE})
    //redirect to add store issue page by requisition id
    public Resolution addStoreIssue()
    {
        requisition=requisitiondao.findById(id);
        requisitionIds=storeissuedao.requisitionIds();
        itemidlst= itemdao.getItem();
        return new ForwardResolution("jsp/addStoreIssue.jsp");
    }
     public Resolution getDailyStockDetails()
    {
        System.out.println("itemcode"+getItemcode());
        dailystockval=storeissuedao.getDailyStockVal(getItemcode());
        System.out.println("dailystockval = " + dailystockval);
        return new JavaScriptResolution(dailystockval);
    }
    //get list of item
    public Resolution getItemDetails()
    {
        item= itemdao.findById(id);
        return new JavaScriptResolution(item);
    }
    @RolesAllowed({PermissionConstants.ADD_STORE_ISSUE})
    //add issue
    public Resolution addissue()
    {   popup="popupvalue";
         System.out.println("getStoreissue() = " + getStoreissue());
         System.out.println("storeissuedetailarray = " + storeissuedetailarray);
        storeissuedao.SaveStoreIssue(getStoreissue(),storeissuedetailarray);
        return new RedirectResolution("jsp/addStoreIssue.jsp").addParameter("popup",popup);
    }
    @RolesAllowed({PermissionConstants.UPDATE_STORE_ISSUE})
    //redirect to update page
    public Resolution updateStoreIssueLink()
    {
        issuedids=storeissuedao.issuedids();
        return new ForwardResolution("jsp/updateStoreIssue.jsp");
    }
    //get list of store by id
    public Resolution updateStoreIssue()
    {
        itemidlst= itemdao.getItem();
        issuedids=storeissuedao.issuedids();
        requisition=requisitiondao.findById(getId());
        storeissue=storeissuedao.findById(getId());
        return new ForwardResolution("jsp/updateStoreIssue.jsp");
    }
     @RolesAllowed({PermissionConstants.UPDATE_STORE_ISSUE})
    //update store page
    public Resolution update()
    {
        storeissuedao.update(getStoreissue(),storeissuedetailarray);
        issuedids=storeissuedao.issuedids();
        return new RedirectResolution("jsp/updateStoreIssue.jsp");
    }
    public Resolution cancel()
       {
         
           return new RedirectResolution("jsp/addStoreIssue.jsp");
       }

     public Resolution redirectpopup()
   {
         storeissue=storeissuedao.latestStoreIssue();
       return new ForwardResolution("jsp/receipt/storeIssueSlip.jsp");
   }

}
