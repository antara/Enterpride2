<%--
  Created by IntelliJ IDEA.
  User: Milind
  Date: Mar 2, 2012
  Time: 10:21:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/_taglibInclude.jsp" %>
<link rel="stylesheet" href="/css/general.css" type="text/css" media="screen"/>
<link rel="stylesheet" type="text/css" href="../css/stylesheet.css"/>
<s:useActionBean beanclass="com.erp.action.SearchActionBean" var="after"  event="search"></s:useActionBean>
<%request.setAttribute("tt",after.getSearchSubmenu());%>
  <c:set var = "TR2" value="receipt"/>
          <c:if test="${actionBean.hdnvalue eq TR2}">
<script type="text/javascript">
   function OpenPopup(){
       var w = 760;
       var h = 450;
       var winl = (screen.width-w)/2;
       var wint = (screen.height-h)/2;
       if (winl < 0) winl = 0;
       if (wint < 0) wint = 0;
       var page = "/Search.action?redirectpopup=&id="+${actionBean.id}+"&name=+${actionBean.name}+";
       windowprops = "height="+h+",width="+w+",top="+ wint +",left="+ winl +",location=no,"
       + "scrollbars=yes,menubars=no,toolbars=no,resizable=no,status=yes";
       window.open(page, "Popup", windowprops);
       return;
}
       window.onLoad =OpenPopup();
  </script>
    </c:if >
 <c:set var = "TR3" value="receiptpurchase"/>
          <c:if test="${actionBean.hdnvalue eq TR3}">
<script type="text/javascript">
   function OpenPopup(){

       var w = 750;
       var h = 450;
       var winl = (screen.width-w)/2;
       var wint = (screen.height-h)/2;
       if (winl < 0) winl = 0;
       if (wint < 0) wint = 0;
       var page = "/Search.action?redirectpurchase=&id="+${actionBean.id}+"";
       windowprops = "height="+h+",width="+w+",top="+ wint +",left="+ winl +",location=no,"
       + "scrollbars=yes,menubars=no,toolbars=no,resizable=no,status=yes";
       window.open(page, "Popup", windowprops);
       return;
}
       window.onLoad =OpenPopup();
  </script>
    </c:if >
<script type="text/javascript">

    $(document).ready(function() {


        $('#menu').change(function() {
           if($('#menu').val()=='byPurchaseOrder')
            {

                var po=document.getElementById("submenu");
               /* var options=po.getElementsByTagName("option");
   var i;
   // Loop through the array of options and remove each
   // each one from the parent's childNode list.
   for (i=0; i<options.length; i++)
   {
      po.removeChild(options[i]);
   }// end for i*/
         var poshow=new Array("none","poNumber","poVendorName","poVendorId","poDate","poBetweenDate");
         var povalue=new Array("<--Select PO -->","PO Number","Vendor Name","Vendor Id","PO Date","PO Between Date");





      for(var i=0;i<poshow.length;i++){
      <c:set var="temp1" value="poshow[i]"/>
      <c:choose>
          <c:when test="${actionBean.searchMenu eq temp1}">
            <%--<option value="<%= povalue[i]%>" selected="selected"><%=poshow[i]%></option>--%>
            po.options[i]=new Option(povalue[i],poshow[i],true);
          </c:when>
          <c:otherwise>
              
                po.options[i]=new Option(povalue[i],poshow[i]);


          </c:otherwise>

      </c:choose>
      }

     



            }
         else if($('#menu').val()=='byGrn')
            {
var grn=document.getElementById("submenu");

grn.options[0]=new Option("---Select Grn Options---",0);
grn.options[1]=new Option("By PO Number","grnNumber");
grn.options[2]=new Option("By Vendor Name","grnVendorName");
grn.options[3]=new Option("By Vendor Id","grnVendorId");
grn.options[4]=new Option("By Grn No","grnNO");
grn.options[5]=new Option("Order Date","grnOrderDate");
grn.options[6]=new Option("Between Date","grnBetweenDate");
            }
         else if($('#menu').val()=='byItem')
            {
var item=document.getElementById("submenu");
   var options=item.getElementsByTagName("option");
   var i;
   // Loop through the array of options and remove each
   // each one from the parent's childNode list.
   for (i=0; i<options.length; i++)
   {
      item.removeChild(options[i]);
   }// end for i
item.options[0]=new Option("---Select Item Options---",0);
item.options[1]=new Option("By Item Name","itemiName");
item.options[2]=new Option("By Item Code","itemiCode");
item.options[3]=new Option("By UOM","item");
item.options[4]=new Option("By Section ","poDate");
}
         else if($('#menu').val()=='byVendor')
          {

			 var v=document.getElementById("submenu");

   var options=v.getElementsByTagName("option");
   var i;

   for (i=0; i<options.length; i++)
   {
      v.removeChild(options[i]);
   }
              var t;
                var vshow=new Array("none","vendorName","vendorId","vendorProduct");
             
                         var vvalue=new Array("--Select Vendor Option --","Vendor Name","Vendor Id","Vendor Product");
                      for(var i=0;i<vshow.length;i++){
                          t=vshow[i];
                         alert(t);
                      if(tt==t)
                            v.options[i]=new Option(vvalue[i],vshow[i]);
                         else
                            v.options[i]=new Option(vvalue[i],vshow[i]);
                       
                      }

            }
         else if($('#menu').val()=='byStoreIssue')
            {
			 var si=document.getElementById("submenu");

    // Set select to the element "submenu"
   // Set options to an array of all "option" tags that
   // are children of the select element.
   var options=si.getElementsByTagName("option");
   var i;
   // Loop through the array of options and remove each
   // each one from the parent's childNode list.
   for (i=0; i<options.length; i++)
   {
      si.removeChild(options[i]);
   }// end for i
//            $('#submenu').attr();

si.options[0]=new Option("---Select StoreIssue Options---",0);
si.options[1]=new Option("By StoreIssue Number","siNumber");
si.options[2]=new Option("By Requisition Id","siVendorName");
            }

        });
          $('#submenu').change(function() {
                 alert($('#submenu').val());
              $('#myDiv1').show();

          if($('#submenu').val() == 'iteminame'){
                      $.get("/Search.action?autoitem",function(result) {
	        var availableTags=eval(result);
                     $("input#autocomplete").autocomplete({
                         source: availableTags
                     });
		});
          }
           else if($('#submenu').val() == 'grnNumber'){
               $.get("/Search.action?autogrn",function(result) {
        var availableTags=eval(result);
        $("input#autocomplete").autocomplete({
            source: availableTags
        });
               });
          }
         else if($('#submenu').val() == 'vendorName'){

               $.get("/Search.action?autovendor",function(result) {
        var availableTags=eval(result);
        $("input#autocomplete").autocomplete({
            source: availableTags
        });
               });
          }
           else if($('#submenu').val() == 'siNumber'){

               $.get("/Search.action?autoStoreIssue ",function(result) {
        var availableTags=eval(result);

        $("input#autocomplete").autocomplete({
            source: availableTags
        });
               });
          }
           else if($('#submenu').val()== 'poNumber'){
               $.get("/Search.action?autoPurchaseOrder ",function(result) {
        var availableTags=eval(result);
        $("input#autocomplete").autocomplete({
            source: availableTags
        });
               });
          }
});
          var numeric = /^[0-9]+$/;
         $("#seperategetbtn").click(function(){
         if ($("#autocomplete").val().trim()==""){
            alert("please enter search details.");
            return false;
         }
             else if(document.getElementById('radio1').checked && $("#autocomplete").val().trim().length<6)
         {
             alert("select PO Number From list Only..");
             return false
         }
             if(document.getElementById('radio2').checked){
             if(!numeric.test($('#autocomplete').val()))        {
             alert("please enter numeric value to search grn detail");
             $("#autocomplete").val("");
             $("#autocomplete").focus();
                return false;
         }
         else if($("#autocomplete").val().trim().length<6 || $("#grn").val().trim().length<6)
         {
             alert("Select Grn no From list Only..")
             return false
         }
            }
            if(document.getElementById('radio5').checked){

            if(!numeric.test($('#autocomplete').val()))        {
            alert("please enter numeric value to search store detail");
              $("#autocomplete").val("");
              $("#autocomplete").focus();
                return false;
         }
              
            }

   });
    
         $("#storebutton").click(function(){
        if(!numeric.test($('#storeIssue').val()))        {
            alert("please enter numeric value to search store detail");
              $("#storeIssue").val("");
              $("#storeIssue").focus();
                return false;
         }
         });
         $("#getgrnbtton").click(function(){
        if(!numeric.test($('#grn').val()))        {
            alert("please enter numeric value to search grn");
              $("#grn").val("");
              $("#grn").focus();
                return false;
         }
         });

 });
 </script>
<s:useActionBean beanclass="com.erp.action.SearchActionBean" event="lst" var="searchlst"></s:useActionBean>
<%
       request.setAttribute("itemlst",searchlst.getItemlst());
       request.setAttribute("vendorlst",searchlst.getVendorlst());
       request.setAttribute("grnlst",searchlst.getGrnlst());
       request.setAttribute("storeissuelst",searchlst.getStoreissuelst());
       request.setAttribute("purchaseOrderlst",searchlst.getPurchaseOrderlst());
   
%>
<s:layout-render name="/layout/_base.jsp">
    <s:layout-component name="body">
    <s:form beanclass="com.erp.action.SearchActionBean">
 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" >
 <tr valign="top"><td >&nbsp;
 </td></tr>
 <tr><td align="left" class="pageheading" valign="top">
<strong>Search</strong>
 </td></tr>
 <tr valign="top"><td align="center">&nbsp;
 </td></tr>
 </table>
 <table bordercolor="#FF6600" width="77%" border="1">
 <tr>
 <td width="70%" height="124">
 <table width="100%" cellspacing="1">
   <tr>
     <td width="13%" align="left" valign="top">Search What ?</td>
     <td width="87%" align="left" valign="top">
         <%
         String[] menu={"byPurchaseOrder","byGrn","byVendor"};
             String[] name={"Purchase Order","GRN","Vendor"};
         %>
      <s:select name="searchMenu" id="menu" class="dropdown">
            <option value="0">----Select Menu----</option>
          <%for(int i=0;i<menu.length;i++){%>
          <c:set var="temp" value="<%=menu[i] %>"/>
          <c:choose>
              <c:when test="${actionBean.searchMenu eq temp}">
                <option value="<%= menu[i]%>" selected="selected"><%=name[i]%></option>        
              </c:when>
              <c:otherwise>
                  <option value="<%= menu[i]%>"><%=name[i]%></option>
              </c:otherwise>

          </c:choose>
          <%}%>
            <%--<option value="byGrn">GRN</option><option value="byItem">Item</option><option value="byVendor">Vendor</option><option value="byStoreIssue">StoreIssue</option>--%>
         </s:select>
     </td>
   </tr>
    <tr>
     <td>Search By</td>
     <td align="left" valign="top"> 
      <s:select id="submenu" name="searchSubmenu" class="dropdown">

            <option  value="0">---Select Submenu---</option>
            
         </s:select>
 </td>
   
   </tr>
    <tr>
     <td>&nbsp;</td>
     <td align="left" valign="top">
         
      <div id="myDiv1"  align="left" style="display:none;" class="labels">
                <%--Please Enter Search Details first <span style="color:#FF0000"> *</span>--%>
                &nbsp;&nbsp;<s:text style="border:1px solid #FFCC66;height: 23px; width: 173px;" name="name" id="autocomplete" value=""/>
               <s:submit name="search" id="seperategetbtn" value="Get" ></s:submit>

          </div>
        </td>

   </tr>

<script type="text/javascript">

        $(document).ready(function() {
         
           
                              $('#menu').change();
               
            

            
        });
  </script>
   <%--
    <tr>
     <td width="9%" align="left" valign="top">Search By</td>
     <td width="1%" align="right" valign="top"><s:radio  value="byPurchaseOrder" id="radio1"  name="search"></s:radio></td>
     <td width="90%" align="left" valign="top">By Purchase Order No</td>
   </tr>
  
   <tr>
     <td>&nbsp;</td>
     <td align="right" valign="top"><s:radio  value="byItemName" id="radio3" class="search"  name="search"></s:radio></td>
     <td align="left" valign="top">By Item Name</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td align="right" valign="top"><s:radio  value="byVendorName" id="radiovendor"  name="search"></s:radio></td>
     <td align="left" valign="top">By Vendor Name</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td align="right" valign="top"><s:radio  value="byStoreIssueNo" id="radio5"  name="search"></s:radio></td>
     <td align="left" valign="top">By Store Issue No</td>
   </tr>--%>
   
 </table>
         </td>
 </tr>
 </table>
        <c:if test="${actionBean.grnlst!=null}">
        <table id="grntable" width="100%"><tr><td>
                    <d:table name="grnlst" id="grn1" pagesize="10" class="disp" requestURI="/Search.action?searchGrn">
                 <d:column property="id" title="Grn no"/>
                 <d:column property="createDate" title="Date"   format="{0,date,yyyy-MM-dd}" sortable="false"/>
                  <d:column property="billValue" title="Bill Value" />
                        <d:column property="purchaseOrder.purchaseOrderNo" title="Purchase Order No" />
                    <d:column title="Item name">
                <table>
                <c:forEach items="${grn1.grndetailarray}" var="grnloop" varStatus="loop" >
              <tr>  <td>${grnloop.item.name}</td></tr>
                </c:forEach>
                     </table>     </d:column>
                        <d:column title="Ordered Qty">
                      <table>
                    <c:forEach items="${grn1.purchaseOrder.purchasedetailarray}" var="grnloop1" varStatus="loop" >
                <tr>  <td>${grnloop1.orderedQty} </td> </tr>
                  </c:forEach>  </table>
                          </d:column>
                       <d:column title="accepted Qty">
                      <table>
                    <c:forEach items="${grn1.grndetailarray}" var="grnloop2" varStatus="loop" >
                <tr>  <td>${grnloop2.acceptedQty} </td> </tr>
                  </c:forEach>  </table>
                          </d:column>
             </d:table></td></tr></table>
           </c:if>
          <c:if test="${actionBean.purchaseOrderlst!=null}">
         <table id="purchaseOrdertable" width="100%"><tr><td>  <!--print item in table format-->
        <d:table name="purchaseOrderlst" id="purchase" pagesize="10" class="disp" requestURI="/Search.action?autoStoreIssue">
                <d:column property="purchaseOrderNo" title="Purchase Order No"/>
             <d:column property="createDate" title="Purchase Order Date"  format="{0,date,yyyy-MM-dd}" sortable="false"/>
            <d:column property="status" title="status"/>
                <d:column title="Item name">
                <table>
                <c:forEach items="${purchase.purchasedetailarray}" var="purchaseloop" varStatus="loop" >
              <tr>  <td>${purchaseloop.item.name}</td></tr>
                </c:forEach>
                     </table>     </d:column>
                        <d:column title="Ordered Qty">
                      <table>
                    <c:forEach items="${purchase.purchasedetailarray}" var="purchaseloop1" varStatus="loop" >
                <tr>  <td>${purchaseloop1.orderedQty} </td> </tr>
                  </c:forEach>  </table>
                          </d:column>
                   <d:column title="Amount">
                      <table>
                    <c:forEach items="${purchase.purchasedetailarray}" var="purchaseloop2" varStatus="loop" >
                <tr>  <td>${purchaseloop2.rate * purchaseloop2.orderedQty}</td></tr>
                  </c:forEach></table> </d:column>
                 
                   <d:column title="View" class="delete" >
                       <s:link beanclass="com.erp.action.SearchActionBean" event="print" >
                                    <s:param name="id" value="${purchase.id}"></s:param>
                             <s:param name="hdnvalue" value="testpurchase"></s:param>
                           <s:param name="name" value="${purchase.purchaseOrderNo}"></s:param>


                       <img src="/images/view.png" />
                       </s:link>
                   </d:column>
             </d:table></td></tr>
      </table>
        </c:if>
        <c:if test="${actionBean.itemlst!=null}">
         <table id="itemtable" width="100%"><tr><td>  <!--print item in table format-->
        <d:table name="itemlst" id="item1" pagesize="6" class="disp" requestURI="/Search.action?">
            <d:column property="itemCode" title="Item Code"/>
	        <d:column property="name" title="Item Name"  />
 	        <d:column property="uom.name" title="UOM" />
	        <d:column property="section.name" title="Section" />
        </d:table></td></tr>
      </table>
        </c:if>
           <c:if test="${actionBean.vendorlst!=null}">
        <table id="vendortable" width="100%"><tr><td>
                    <d:table name="vendorlst" id="vendor1" pagesize="10" class="disp" requestURI="/Search.action?">
                 <d:column property="name" title="Vendor Name"/>
                 <d:column property="address" title="Vendor Address"  />
                  <d:column property="emailId" title="Email Id" />
                        <d:column property="phoneNo" title="Phone Number" />
                <d:column property="productSupplied" title="Product Supplied" />
             </d:table></td></tr></table>
           </c:if>
        <c:if test="${actionBean.storeissuelst!=null}">
        <table id="SItable" width="100%"><tr><td>
                <d:table name="storeissuelst" id="storeissue" pagesize="10" class="disp" requestURI="/Search.action?autoStoreIssue">
                <d:column property="id" title="Store Issue No"/>
                <d:column title="Item name">
                <table>
                <c:forEach items="${storeissue.storeissuedetailarray}" var="loopstore" varStatus="loop" >
              <tr>  <td>${loopstore.item.name}</td></tr>
                </c:forEach>
                     </table>     </d:column>
                        <d:column title="Required Qty">
                      <table>
                   <c:forEach items="${storeissue.requisition.requisitiondetailarray}" var="requisitionloop" varStatus="loop" >
                <tr>  <td>${requisitionloop.requiredQty} </td> </tr>
                  </c:forEach>  </table>
                          </d:column>
                   <d:column title="Issue Qty">
                      <table>
                   <c:forEach items="${storeissue.storeissuedetailarray}" var="loopstore1" varStatus="loop" >
                <tr>  <td>${loopstore1.issueQty}</td></tr>
                  </c:forEach></table> </d:column>

                 <d:column property="issueDate" title="Issue Date"    format="{0,date,yyyy-MM-dd}" sortable="false"/>
                   <d:column title="View" class="delete" style="valign:middle">
                       <s:link beanclass="com.erp.action.SearchActionBean" event="print" >
                           <s:param name="id" value="${storeissue.id}" ></s:param>
                             <s:param name="hdnvalue" value="test"></s:param>
                           <s:param name="name" value="${storeissue.id}"></s:param>
                       <img src="/images/view.png" />
                       </s:link>
                   </d:column>
             </d:table></td></tr></table>
           </c:if>
   </s:form>
  </s:layout-component>
</s:layout-render>
