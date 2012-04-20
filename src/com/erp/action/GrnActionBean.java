package com.erp.action;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.ajax.JavaScriptResolution;
import com.erp.pojo.*;
import com.erp.enums.EnumModule;
import com.erp.constants.PermissionConstants;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.text.DateFormat;


/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 20, 2012
 * Time: 4:42:02 PM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class GrnActionBean extends BaseActionBean{
     private Grn grn;
     private List<Vendor> vendorlst;
     private List<Item> itemidlst;
    private List<Grn> grnlst;
    private Item item;
      private List<PurchaseOrder> purchaseorderlst;
    private List<GrnDetail> grndetailarray;
    private GrnDetail grndetail;
     private PurchaseOrder purchaseOrder;
    private String hdnvalue;
    private Double tot;
              protected long  iddrop;

    public Double getTot() {
        return tot;
    }

    public void setTot(Double tot) {
        this.tot = tot;
    }

    public String getHdnvalue() {
        return hdnvalue;
    }

    public void setHdnvalue(String hdnvalue) {
        this.hdnvalue = hdnvalue;
    }

    public PurchaseOrder getPurchaseOrder() {
      
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    public Grn getGrn() {
          if(id != 0) {

              
               return grndao.findById(id);
          }
        return grn;
    }

    public void setGrn(Grn grn) {
        this.grn = grn;
    }

   public GrnDetail getGrndetail() {

        return grndetail;
    }

    public void setGrndetail(GrnDetail grndetail) {
        this.grndetail = grndetail;
    }

    public List<GrnDetail> getGrndetailarray() {
        return grndetailarray;
    }

    public void setGrndetailarray(List<GrnDetail> grndetailarray) {
        this.grndetailarray = grndetailarray;
    }

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Grn> getGrnlst() {
        return grnlst;
    }

    public void setGrnlst(List<Grn> grnlst) {
        this.grnlst = grnlst;
    }

    public List<PurchaseOrder> getPurchaseorderlst() {

        return purchaseorderlst;
    }

    public void setPurchaseorderlst(List<PurchaseOrder> purchaseorderlst) {
        this.purchaseorderlst = purchaseorderlst;
    }

    public long getIddrop() {
        return iddrop;
    }

    public void setIddrop(long iddrop) {
        this.iddrop = iddrop;
    }


    @RolesAllowed({PermissionConstants.ADD_GRN})
    @DefaultHandler
    //Redirect to add grn page
   public Resolution addGrnLink(){
         purchaseorderlst=purchaseorderdao.getPoByStatus();
        vendorlst=vendordao.getVendor();
        itemidlst= itemdao.getItem();
       return new ForwardResolution("jsp/addGrn.jsp");
   }
     @RolesAllowed({PermissionConstants.ADD_GRN})
    //Add Grn
     public Resolution addGrn(){
         grndao.SaveGrn(getGrn(),grndetailarray);
          return new RedirectResolution("jsp/addGrn.jsp");
   }
   //Get dropdown item list
    public Resolution getItemDetails(){
     
       item= itemdao.findById(id);
        return new JavaScriptResolution(item);
    }

    @RolesAllowed({PermissionConstants.UPDATE_GRN})
   //Redirect to update grn page
    public Resolution updateGrnLink(){
           grnlst=grndao.getTodayGrn();
           return new ForwardResolution("jsp/updateGrn.jsp");
      }
    //Get grn detail by its id
     @RolesAllowed({PermissionConstants.UPDATE_GRN})
     public Resolution getGrnButton()
    {
       grn=grndao.findById(id);
       purchaseorderlst=purchaseorderdao.getPurchaseOrder();
       grnlst=grndao.getTodayGrn();
       vendorlst=vendordao.getVendor();
       itemidlst= itemdao.getItem();
       return new ForwardResolution("jsp/updateGrn.jsp");

    }
    //Update  grn
     @RolesAllowed({PermissionConstants.UPDATE_GRN})
     public Resolution updategrn(){
        grndao.update(getGrn(),grndetailarray);
        grnlst=grndao.getTodayGrn();
        return new RedirectResolution("jsp/updateGrn.jsp");
    }
    @RolesAllowed({PermissionConstants.ADD_GRN})
    //add grn detail by purchase order
    public Resolution getorderlstforgrn(){
         purchaseOrder =purchaseorderdao.findById(iddrop);
         purchaseorderlst=purchaseorderdao.getPoByStatus();
         vendorlst=vendordao.getVendor();
        itemidlst= itemdao.getItem();
        return new ForwardResolution("jsp/addGrn.jsp");
    }

    public Resolution verify(){

        grnlst=grndao.unverifiedGrnList();

        System.out.println("list "+grnlst);

        return new ForwardResolution("jsp/verifyGrn.jsp");
    }


    public Resolution proceedForPayment(){
        System.out.println("id of proceed for payment "+id);
        grn=grndao.findById(id);
         if(hdnvalue.equals("test"))
              {
                    hdnvalue="receipt";

                   
              }
        hdnvalue="receipt";
        //grn.setVerified("Y");
        //grndao.update(grn,grn.getGrndetailarray());
        grnlst=grndao.unverifiedGrnList();
        System.out.println("list "+grnlst);
         return new ForwardResolution("jsp/verifyGrn.jsp");

    }
    public Resolution redirectpopup()
   {
      // Date d=grn.getChallanDate();

      tot=0.0;

       grn=grndao.findById(getId());


       Iterator<GrnDetail> it=grn.getGrndetailarray().iterator();
       while (it.hasNext())
       {
           tot=tot+it.next().getValue();
       }

       System.out.println("total "+tot);

      
       return new ForwardResolution("jsp/receipt/verifyGrnSlip.jsp");
   }

   public Resolution redirectpopupforverified()
   {
      // Date d=grn.getChallanDate();

      tot=0.0;

       grn=grndao.findById(getId());


       Iterator<GrnDetail> it=grn.getGrndetailarray().iterator();
       while (it.hasNext())
       {
           tot=tot+it.next().getValue();
       }

       System.out.println("total "+tot);


       return new ForwardResolution("jsp/receipt/verifiedGrnSlip.jsp");
   }

    public Resolution forAccountant(){
        grnlst=grndao.verifiedGrnList();

         return new ForwardResolution("jsp/grnPaymentAccountant.jsp");
    }

    public Resolution makePayment(){
        System.out.println("id "+id);
        grn=grndao.findById(id);
         hdnvalue="receipt";
       // grn.setPaymentStatus("Success");
        //grndao.update(grn,grn.getGrndetailarray());
        grnlst=grndao.verifiedGrnList();
        System.out.println("list "+grnlst);
         return new ForwardResolution("jsp/grnPaymentAccountant.jsp");

    }
public Resolution success(){
        System.out.println("id "+id);
       grndao.setPaymentStatusSuccess(getGrn().getId());
       // grn.setPaymentStatus("Success");
        //grndao.update(grn,grn.getGrndetailarray());
        grnlst=grndao.verifiedGrnList();
        System.out.println("list "+grnlst);
         return new ForwardResolution("jsp/grnPaymentAccountant.jsp");

    }
public Resolution verification(){
         System.out.println("iiiiiiiiiiddddddddd"+getGrn().getId());
         grndao.setVerifiedToY(getGrn().getId());
        grnlst=grndao.unverifiedGrnList();
     hdnvalue="test12";
        System.out.println("list "+grnlst);
        //  return new JavaScriptResolution(hdnvalue);
         //return new ForwardResolution("jsp/verifyGrn.jsp");
           return new ForwardResolution("jsp/verifyGrn.jsp");
    }

    
}

