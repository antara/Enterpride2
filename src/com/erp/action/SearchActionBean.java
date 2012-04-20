package com.erp.action;


import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.ajax.JavaScriptResolution;
import com.erp.pojo.*;
import com.erp.constants.PermissionConstants;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Milind
 * Date: Mar 2, 2012
 * Time: 10:35:45 AM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class SearchActionBean extends BaseActionBean{

   private  List<Item> itemlst;
    private List<Vendor> vendorlst;
    private String name;
    private Long no;
    private String search;
    public  List<String> vendorhlst;
    private String q;
    private List<String> itemnamelst;
     private List<Grn> grnlst;
    public  List<Long> temp;
    public  String temp1;
    private String searchSubmenu;
    private String searchMenu;
    public  List<String> grnnolst;
    private List<StoreIssue> storeissuelst ;
    public  List<String> storeissuenolst  ;
      private List<PurchaseOrder> purchaseOrderlst ;
    public  List<String> PurchaseOrderNolst  ;
    public  List<Long> tempSI;
       private PurchaseOrder purchaseOrder;
   private  String hdnvalue;
     public StoreIssue storeissue;

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getSearchMenu() {
        return searchMenu;
    }

    public void setSearchMenu(String searchMenu) {
        this.searchMenu = searchMenu;
    }

    public String getSearchSubmenu() {
        return searchSubmenu;
    }

    public void setSearchSubmenu(String searchSubmenu) {
        this.searchSubmenu = searchSubmenu;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public List<String> getItemnamelst() {
        return itemnamelst;
    }

    public void setItemnamelst(List<String> itemnamelst) {
        this.itemnamelst = itemnamelst;
    }

    public List<String> getVendorhlst() {
        return vendorhlst;
    }

    public void setVendorhlst(List<String> vendorhlst) {
        this.vendorhlst = vendorhlst;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Item> getItemlst() {
        return itemlst;
    }

    public void setItemlst(List<Item> itemlst) {
        this.itemlst = itemlst;
    }

    public List<Vendor> getVendorlst() {
        return vendorlst;
    }

    public void setVendorlst(List<Vendor> vendorlst) {
        this.vendorlst = vendorlst;
    }

    public List<Grn> getGrnlst() {
        return grnlst;
    }

    public void setGrnlst(List<Grn> grnlst) {
        this.grnlst = grnlst;
    }

    public List<Long> getTemp() {
        return temp;
    }

    public void setTemp(List<Long> temp) {
        this.temp = temp;
    }

    public List<String> getGrnnolst() {
        return grnnolst;
    }

    public void setGrnnolst(List<String> grnnolst) {
        this.grnnolst = grnnolst;
    }

    public List<String> getStoreissuenolst() {
        return storeissuenolst;
    }

    public void setStoreissuenolst(List<String> storeissuenolst) {
        this.storeissuenolst = storeissuenolst;
    }

    public List<Long> getTempSI() {
        return tempSI;
    }

    public void setTempSI(List<Long> tempSI) {
        this.tempSI = tempSI;
    }

    public List<StoreIssue> getStoreissuelst() {
        return storeissuelst;
    }

    public void setStoreissuelst(List<StoreIssue> storeissuelst) {
        this.storeissuelst = storeissuelst;
    }

    public List<PurchaseOrder> getPurchaseOrderlst() {
        return purchaseOrderlst;
    }

    public void setPurchaseOrderlst(List<PurchaseOrder> purchaseOrderlst) {
        this.purchaseOrderlst = purchaseOrderlst;
    }

    public List<String> getPurchaseOrderNolst() {
        return PurchaseOrderNolst;
    }

    public void setPurchaseOrderNolst(List<String> purchaseOrderNolst) {
        PurchaseOrderNolst = purchaseOrderNolst;
    }

    public String getHdnvalue() {
        return hdnvalue;
    }

    public void setHdnvalue(String hdnvalue) {
        this.hdnvalue = hdnvalue;
    }

    public StoreIssue getStoreissue() {
        return storeissue;
    }

    public void setStoreissue(StoreIssue storeissue) {
        this.storeissue = storeissue;
    }
    @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
    @DefaultHandler
    public Resolution searchLink()
    {

        return new ForwardResolution("jsp/search.jsp");
    }
     @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
    public Resolution search()
    {
        if(search.equalsIgnoreCase("byItemName"))
            itemlst=itemdao.searchByName(getName());
        if(name.equalsIgnoreCase("milind"))
        {
            System.out.println("searchSubmenu :"+getSearchSubmenu());
            System.out.println("searchmenu :"+getSearchMenu());
            System.out.println("temp1 :"+getTemp1());
            System.out.println("temp :"+getTemp());
               System.out.println("name"+getName());
            vendorlst=vendordao.searchByName(getName());
            searchSubmenu="vendorName";
            searchMenu="byVendor";
            temp1=searchSubmenu;

            
        }
        if(search.equalsIgnoreCase("byGrn"))
        {
            no=Long.parseLong(getName());
            grnlst=grndao.searchByName(no);

        }
         if(search.equalsIgnoreCase("byStoreIssueNo"))
        {
            no=Long.parseLong(getName());
            storeissuelst=storeissuedao.searchByName(no);
        }
         if(search.equalsIgnoreCase("byPurchaseOrder"))
        {
           purchaseOrderlst=purchaseorderdao.searchByName(getName());

        }

        System.out.println("searchterm "+name);
        return new ForwardResolution("jsp/search.jsp").addParameter("name",name);
    }
    @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
    public Resolution lst()
    {
        //    vendorhlst=vendordao.allVendor();
        itemlst=getItemlst();
          //   itemnamelst=itemdao.allItem();
        vendorlst=getVendorlst();
        grnlst=getGrnlst();
      //  temp=grndao.allGrnno();
        storeissuelst=getStoreissuelst();
      //  tempSI=storeissuedao.allStoreIssue();
        purchaseOrderlst=getPurchaseOrderlst();
            // PurchaseOrderNolst=purchaseorderdao.getAllPurchaseOrderNo();
        return new ForwardResolution("jsp/search.jsp");
    }

    public Resolution autovendor()
    {

             vendorhlst=vendordao.allVendor();

         return new JavaScriptResolution(vendorhlst);
    }
     @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
     public Resolution def()
    {
        // StringBuilder result = new StringBuilder();
        if(search.equalsIgnoreCase("byVendorName"))
        {

                vendorhlst=vendordao.allVendor();
                return new JavaScriptResolution(vendorhlst);
        }
        else if(search.equalsIgnoreCase("byItemName"))
        {
              itemnamelst=itemdao.allItem();
            return new JavaScriptResolution(itemnamelst);
        }
         else
            return new JavaScriptResolution(null);

    }
     @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
     public Resolution autoitem()
    {

             itemnamelst=itemdao.allItem();

        return new JavaScriptResolution(itemnamelst);
    }

    public Resolution autogrn()
    {
            // StringBuilder result = new StringBuilder();
                 temp=grndao.allGrnno();
              grnnolst=new ArrayList<String>();
        for(Iterator<Long> i=temp.iterator();i.hasNext();){
            grnnolst.add(i.next().toString());
        }

            return new JavaScriptResolution(grnnolst);
        }
     @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
    public Resolution   autoStoreIssue ()
           {

                    tempSI=storeissuedao.allStoreIssue();
               storeissuenolst=new ArrayList<String>();
        for(Iterator<Long> i=tempSI.iterator();i.hasNext();){
            storeissuenolst.add(i.next().toString());
        }

               return new JavaScriptResolution(storeissuenolst);
           }
      @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
         public Resolution autoPurchaseOrder()
         {

                    PurchaseOrderNolst=purchaseorderdao.getAllPurchaseOrderNo();

               return new JavaScriptResolution(PurchaseOrderNolst);
         }
     @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
     public Resolution print()
    {

        System.out.println("hkuyu");
        //    vendorhlst=vendordao.allVendor();
        itemlst=getItemlst();
          //   itemnamelst=itemdao.allItem();
        vendorlst=getVendorlst();
        grnlst=getGrnlst();
      //  temp=grndao.allGrnno();
         if(hdnvalue.equals("test"))
              {
                    hdnvalue="receipt";
                 search="byStoreIssueNo";
                     no=Long.parseLong(getName());
            storeissuelst=storeissuedao.searchByName(no);
              }
          if(hdnvalue.equals("testpurchase"))
              {
                 System.out.println("hkuyuiftest");
                    hdnvalue="receiptpurchase";
                 search="byPurchaseOrder";
           purchaseOrderlst=purchaseorderdao.searchByName(getName());
              }

        
      //  storeissuelst=getStoreissuelst();

      //  tempSI=storeissuedao.allStoreIssue();
    //    purchaseOrderlst=getPurchaseOrderlst();

            // PurchaseOrderNolst=purchaseorderdao.getAllPurchaseOrderNo();
        return new ForwardResolution("jsp/search.jsp");
    }
     @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
     public Resolution redirectpopup()
   {
        storeissue=storeissuedao.findById(getId());
         storeissuelst=getStoreissuelst();
       return new ForwardResolution("jsp/receipt/storeIssueSlip.jsp");
   }
     @RolesAllowed({PermissionConstants.ADD_SEARCH,PermissionConstants.UPDATE_SEARCH,PermissionConstants.DELETE_SEARCH})
     public Resolution redirectpurchase()
   {       
           purchaseOrder=purchaseorderdao.findById(getId());
           return new ForwardResolution("jsp/printPurchaseOrder.jsp");
   }

}
