package com.erp.action;

import com.erp.pojo.*;
import com.erp.constants.PermissionConstants;
import com.erp.utils.Converter;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.SyncFailedException;
import java.io.FileInputStream;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.ajax.JavaScriptResolution;

import javax.annotation.security.RolesAllowed;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: Feb 29, 2012
 * Time: 5:34:00 PM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class PurchaseOrderActionBean extends BaseActionBean{
    private List<Vendor> vendorlst;
     private List<Item> itemidlst;
     private List<PurchaseOrder> purchaseorderlst;
    private Item item;
    private String content;
    private String purchaseOrderNumber;
    private String vendorName;
    private Terms term;

    public Terms getTerm() {
        return term;
    }

    public void setTerm(Terms term) {
        this.term = term;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    private List<PurchaseOrderDetail> purchasedetailarray = new ArrayList<PurchaseOrderDetail>();
       private PurchaseOrder purchaseOrder;

    private  String test;

    public PurchaseOrder getPurchaseOrder() {
        if(id != 0) {
               return purchaseorderdao.findById(id);
          }
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    public List<PurchaseOrder> getPurchaseorderlst() {
        return purchaseorderlst;
    }

    public void setPurchaseorderlst(List<PurchaseOrder> purchaseorderlst) {
        this.purchaseorderlst = purchaseorderlst;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<PurchaseOrderDetail> getPurchasedetailarray() {
        return purchasedetailarray;
    }

    public void setPurchasedetailarray(List<PurchaseOrderDetail> purchasedetailarray) {
        this.purchasedetailarray = purchasedetailarray;
    }

    public PurchaseOrderDetail getGrndetail() {
        return grndetail;
    }

    public void setGrndetail(PurchaseOrderDetail grndetail) {
        this.grndetail = grndetail;
    }

    private PurchaseOrderDetail grndetail;

    public List<Vendor> getVendorlst() {
        return vendorlst;
    }

    public void setVendorlst(List<Vendor> vendorlst) {
        this.vendorlst = vendorlst;
    }

    public List<Item> getItemidlst() {
        return itemidlst;
    }

    public void setItemidlst(List<Item> itemidlst) {
        this.itemidlst = itemidlst;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @RolesAllowed({PermissionConstants.ADD_PURCHASEORDER})
    @DefaultHandler
     public Resolution addPOLink(){
      vendorlst=vendordao.getVendor();
        itemidlst= itemdao.getItem();


        return new ForwardResolution("jsp/addPurchaseOrder.jsp");
   }
    public Resolution getItemDetails(){

           item= itemdao.findById(id);
            return new JavaScriptResolution(item);
        }
     @RolesAllowed({PermissionConstants.ADD_PURCHASEORDER})
    public Resolution addPurchaseOrder(){
            purchaseorderdao.SavePurchaseOrder(getPurchaseOrder(),purchasedetailarray);

            return new RedirectResolution("jsp/previewPurchaseOrder.jsp");
       }

      @RolesAllowed({PermissionConstants.UPDATE_PURCHASEORDER})
    public Resolution updatePurchaseOrder(){
       purchaseorderdao.update(getPurchaseOrder(),purchasedetailarray);
            return new RedirectResolution("jsp/previewPurchaseOrder.jsp");
    }

    @RolesAllowed({PermissionConstants.UPDATE_PURCHASEORDER})
    public Resolution updatePOLink(){
        System.out.println("in update po link ");
            vendorlst=vendordao.getVendor();
            itemidlst= itemdao.getItem();
            purchaseOrder=purchaseorderdao.latestPurchaseOrder();
            return new ForwardResolution("jsp/updatePurchaseOrder.jsp");
      }

    @RolesAllowed({PermissionConstants.DELETE_PURCHASEORDER})
    public Resolution deletePOLink(){
            purchaseorderlst=purchaseorderdao.getPurchaseOrder();
            return new ForwardResolution("jsp/deletePurchaseOrder.jsp");
   }
    @RolesAllowed({PermissionConstants.ADD_PURCHASEORDER}) 
    public Resolution generatePurchaseOrder()
    {
        vendorlst=vendordao.getVendor();
                  itemidlst= itemdao.getItem();
       if(test.equals("gettestvalue"))
              {
                    test="updatereceipt";
              } else{
                       test="addreceipt";
              }
                    purchaseorderdao.generatePurchaseOrder(getPurchaseOrder(),purchasedetailarray);

           return new RedirectResolution("jsp/addPurchaseOrder.jsp").addParameter("test",test).addParameter("id",id);
    }
     @RolesAllowed({PermissionConstants.DELETE_PURCHASEORDER})
    public Resolution delete(){

        purchaseorderdao.deletePO(getPurchaseOrder());
        purchaseorderlst=purchaseorderdao.getPurchaseOrder();
        return new ForwardResolution("jsp/deletePurchaseOrder.jsp");
    }
   public Resolution cancel()
   {

       return new RedirectResolution("jsp/addPurchaseOrder.jsp");
   }


     public Resolution generatePurchaseOrderLink()
    {
            purchaseorderlst= purchaseorderdao.getPurchaseOrder();
            return new ForwardResolution("jsp/generatePurchaseOrder.jsp");
    }

    public Resolution getToGenaratePurchaseOrder()
    {
        purchaseorderlst= purchaseorderdao.getPurchaseOrder();
          purchaseOrder=purchaseorderdao.findById(getId());
        return new ForwardResolution("jsp/generatePurchaseOrder.jsp");
    }

    public Resolution generateUpdatePOLink()
    {
             vendorlst=vendordao.getVendor();
            itemidlst= itemdao.getItem();

          purchaseOrder=purchaseorderdao.findById(getId());
        return new ForwardResolution("jsp/updateGeneratePurchaseOrder.jsp");
    }
    @RolesAllowed({PermissionConstants.UPDATE_PURCHASEORDER})
    public Resolution updateGeneratePurchaseOrder()
    {
        purchaseorderdao.update(getPurchaseOrder(),purchasedetailarray);
           purchaseOrder=purchaseorderdao.findById(getId());
            
        return new RedirectResolution("jsp/previewGeneratePurchaseOrder.jsp").addParameter("id",id);
    }

    public Resolution redirectpopup()
   {
       purchaseOrder=purchaseorderdao.latestPurchaseOrder();
       return new ForwardResolution("jsp/printPurchaseOrder.jsp");
   }
   public Resolution redirectpopupUpdate()
   {
          purchaseOrder=purchaseorderdao.findById(getId());
       return new ForwardResolution("jsp/printPurchaseOrder.jsp");
   }
    public Resolution print(){
        String path=null;
        FileInputStream sis=null;
        try{
            path= Converter.convert(content,vendorName+"_"+purchaseOrderNumber);
            sis=new FileInputStream(path);
        }catch(Exception e){
            System.out.println("achtung "+e.getMessage());
        }


        return new StreamingResolution("application/pdf",sis);
    }
    public Resolution terms()
   {          System.out.println("id"+id);
         purchaseOrder=purchaseorderdao.findById(getId());
       System.out.println("gggggggggg"+purchaseOrder);
       return new ForwardResolution("jsp/receipt/termsPurchaseOrder.jsp");
   }
     public Resolution saveTerm()
   {          

       termdao.SaveTerm(term);
        purchaseOrder=purchaseorderdao.findById(getId());
        System.out.println("jyyu"+purchaseOrder);
       term=termdao.findByMaxId();
          System.out.println("jyyu"+term);
       return new ForwardResolution("jsp/receipt/purchaseOrderSlip.jsp");
   }

}
