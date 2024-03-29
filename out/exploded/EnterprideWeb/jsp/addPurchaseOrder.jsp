<%--
  Created by IntelliJ IDEA.
  User: Milind
  Date: Feb 29, 2012
  Time: 5:26:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/includes/_taglibInclude.jsp" %>
<link rel="stylesheet" href="/css/general.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/stylesheet.css"/>
<s:useActionBean beanclass="com.erp.action.PurchaseOrderActionBean" var="listoforder" event="addPOLink"></s:useActionBean>
<%
    request.setAttribute("vendorlst",listoforder.getVendorlst());
    request.setAttribute("itemidlst",listoforder.getItemidlst());

%>

         <c:set var = "TR2" value="addreceipt"/>
          <c:if test="${listoforder.test eq TR2}">
<script type="text/javascript">
   function OpenPopup(){
       var w = 800;
       var h = 480;
       var winl = (screen.width-w)/2;
       var wint = (screen.height-h)/2;
       if (winl < 0) winl = 0;
       if (wint < 0) wint = 0;
       var page = "/PurchaseOrder.action?redirectpopup";
       windowprops = "height="+h+",width="+w+",top="+ wint +",left="+ winl +",location=no,"
       + "scrollbars=yes,menubars=no,toolbars=no,resizable=no,status=yes";
       window.open(page, "Popup", windowprops);
       return;
}
       window.onLoad =OpenPopup();
  </script>
    </c:if >
 <c:set var = "TR2" value="updatereceipt"/>
          <c:if test="${listoforder.test eq TR2}">
<script type="text/javascript">
   function OpenPopup(){
       var w = 800;
       var h = 480;
       var winl = (screen.width-w)/2;
       var wint = (screen.height-h)/2;
       if (winl < 0) winl = 0;
       if (wint < 0) wint = 0;
       var page = "/PurchaseOrder.action?redirectpopupUpdate=&id="+${listoforder.id}+"";
       windowprops = "height="+h+",width="+w+",top="+ wint +",left="+ winl +",location=no,"
       + "scrollbars=yes,menubars=no,toolbars=no,resizable=no,status=yes";
       window.open(page, "Popup", windowprops);
       return;
}
       window.onLoad =OpenPopup();
  </script>
    </c:if >
       <script type="text/javascript">
           function GetItemDetail(button){
               var count=$('#family #tabletr').length;
                var rowid=button.name.substring(button.name.indexOf("[")+1,button.name.indexOf("]"));
                var flag=true;
               var check=$('#itemcode'+rowid+'').val();
                for(var i=1;i<=count;i++)
                {
                    if(rowid==i){
                        continue;
                    }
                    var temp=$('#itemcode'+i+'').val();

                    if(check==temp)
                    {
                        flag=false;
                        --rowid;
                        alert("This Item has been already added. Please select another item.");
                        $('#family #tabletr:eq('+rowid+') select:eq(0)').attr("value","0");
                        $('#family #tabletr:eq('+rowid+') input').removeAttr("value");
                        return false;
                    }//end of if
                }//end of for
                    if(flag==true)
                    {
                        $.post('PurchaseOrder.action?getItemDetails', {id:button.value}, function (data) {
                            var result=eval(data);
                            $('#item'+rowid+'').attr("value",result.itemCode);
                            $('#uom'+rowid+'').attr("value",result.uom.name);
                            });//end of post funtion
                    }//end of flag==true if
           } //end of getItem Funntion
            /*the function calculateBalance is use to validate rate textfiled for numeric as well as blank
           this function is called onChange of rate textfield  */
           function calculateBalance(p,i){

               var rowid=p.name.substring(p.name.indexOf("[")+1,p.name.indexOf("]"));

               if(p.value!=0){
                    var chk = /^[0-9]+$/.test(p.value);
                    if (!chk) {
                    alert('please Enter Numeric value for rate');
                        --rowid;
                    $('#family #tabletr:eq('+rowid+') input:eq(4)').val("");
                    $('#family #tabletr:eq('+rowid+') input:eq(5)').val("");
                    $('#family #tabletr:eq('+rowid+') input:eq(4)').focus();
                    }//end of chk loop
                    else if($('#ordqty'+rowid+'').val().trim()!=""){
                            var ordqty=$('#ordqty'+rowid+'').val();
                            var valueset=parseFloat(p.value)*parseFloat(ordqty);
                            $('#amount'+rowid+'').attr("value",valueset);
                            }//end of else if loop
                            else{
                        --rowid;
                                alert("Please enter order quantity");
                                $('#family #tabletr:eq('+rowid+') input:eq(3)').focus();
                                }//ennd of else
                }//end of p.value!=0 if
              else{
                   --rowid;
                    alert("Please enter valid rate");
                    $('#family #tabletr:eq('+rowid+') input:eq(4)').val("");
                    $('#family #tabletr:eq('+rowid+') input:eq(5)').val("");
                    $('#family #tabletr:eq('+rowid+') input:eq(4)').focus();
                }

            }//end of function
           /*the function calculateBalancechangeorder is use to validate quantity textfiled for numeric as well as blank
           this function is called onChange of quantity  textfield  */
            function calculateBalancechaneorder(p,i){
                var rowid=p.name.substring(p.name.indexOf("[")+1,p.name.indexOf("]"));

                if(p.value!=0){
                        var chk = /^[0-9]+$/.test(p.value);
                        if (!chk) {
                            --rowid;
                            alert('please Enter Numeric value for Order Quantity');
                            $('#family #tabletr:eq('+rowid+') input:eq(3)').val("");
                            $('#family #tabletr:eq('+rowid+') input:eq(5)').val("");
                            $('#family #tabletr:eq('+rowid+') input:eq(3)').focus();
                        }
                        else if($('#rate'+rowid+'').val().trim()!=""){
                                var rate=$('#rate'+rowid+'').val();
                                var valueset=parseFloat(p.value)*parseFloat(rate);
                                $('#amount'+rowid+'').attr("value",valueset);
                                }
                }
                else{
                    --rowid;
                        alert("Please enter valid Quantity");
                        $('#family #tabletr:eq('+i+') input:eq(3)').val("");
                     $('#family #tabletr:eq('+i+') input:eq(5)').val("");
                        $('#family #tabletr:eq('+i+') input:eq(3)').focus();
                    }
            }//end of function

           function deletethis(p,a){
               var tr=$('#family #tabletr').length;
                              if(tr==1){
                                      alert("You can not deleted the last row.");
                              }
                              else{
                                          var count=$('#family #tabletr').length;
                                   var rowid=p.name.substring(p.name.indexOf("[")+1,p.name.indexOf("]"))-1;
                              $('#family #tabletr:eq('+rowid+') input').removeAttr("value");
                                  $('#family #tabletr:eq('+rowid+') select:eq(0)').attr("value","0");
                                  }
           }
             $(document).ready(function(){
       
                $('#add').click(function(){
                    var count=$('#family #tabletr').length+1;
                    $('#family #tabletr:last').clone(true).insertAfter('#family #tabletr:last');
                     $('#family #tabletr:last select:eq(0)').attr("value","0");
                    $('#family #tabletr:last input').removeAttr("value");
                    $('#family #tabletr:last select:eq(0)').attr("name","purchasedetailarray["+count+"].item.id");
                    $('#family #tabletr:last select:eq(0)').attr("id","itemcode"+count);
                    $('#family #tabletr:last input:eq(0)').attr("id","item"+count);
                    $('#family #tabletr:last input:eq(1)').attr("id","uom"+count);
                    $('#family #tabletr:last input:eq(2)').attr("name","purchasedetailarray["+count+"].description");
                    $('#family #tabletr:last input:eq(3)').attr("name","purchasedetailarray["+count+"].orderedQty");
                    $('#family #tabletr:last input:eq(3)').attr("id","ordqty"+count);
                    $('#family #tabletr:last input:eq(4)').attr("name","purchasedetailarray["+count+"].rate");
                    $('#family #tabletr:last input:eq(4)').attr("id","rate"+count);
                    $('#family #tabletr:last input:eq(5)').attr("name","purchasedetailarray["+count+"].amount");
                    $('#family #tabletr:last input:eq(5)').attr("id","amount"+count);
                    $('#family #tabletr:last input:eq(6)').attr("name","delete["+count+"]");
                    $('#family #tabletr:last input:eq(6)').attr("id","delete"+count);
                });
                $("#addpopreview").click(function(){
                    var numeric = /^[0-9]+$/;
                    var count=$('#family #tabletr').length;
                    if($('#vendorName').attr("value")=="0"){
                       alert("please select Vendor Name");
                          $('#vendorName').focus();
                         return false;
                       }
                    for(var i=0;i<count;i++){
                          if(i==0){
                                if($('#family #tabletr:eq('+i+') select:eq(0)').attr("value")=="0"){
                                    alert("please select Item Code")
                                    return false;
                                    }
                                 else if($('#family #tabletr:eq('+i+') input:eq(3)').attr("value").trim()=="" || $('#family #tabletr:eq('+i+') input:eq(3)').attr("value").trim()=="0"){
                                        $('#family #tabletr:eq('+i+') input:eq(3)').focus();
                                        alert("please enter ordered valid quantity");
                                        return false;
                                        }
                                 else if($('#family #tabletr:eq('+i+') input:eq(4)').attr("value").trim()=="" || $('#family #tabletr:eq('+i+') input:eq(4)').attr("value").trim()=="0"){
                                        $('#family #tabletr:eq('+i+') input:eq(4)').focus();
                                        alert("please enter valid rate");
                                        return false;
                                        }
                            }
                    }   //end of for
                    return true;
                    });
                });
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:layout-render name="/layout/_base.jsp">
        <s:layout-component name="body">
            <s:form beanclass="com.erp.action.PurchaseOrderActionBean">
              <%--  <c:if test="${actionBean.test!=null}">
                    <script type="text/javascript">
                        function OpenPopup(){
	                        var w = 800;
	                        var h = 600;
                            var winl = (screen.width-w)/2;
                            var wint = (screen.height-h)/2;
                            if (winl < 0) winl = 0;
                            if (wint < 0) wint = 0;
                            var page = "/PurchaseOrder.action?redirectpopup";
                            windowprops = "height="+h+",width="+w+",top="+ wint +",left="+ winl +",location=no,"
                            + "scrollbars=yes,menubars=no,toolbars=no,resizable=no,status=yes";
                            window.open(page, "Popup", windowprops);
                       }//end of popup function
                            window.onLoad =OpenPopup();
                    </script>
                </c:if >--%>
 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" >
 <tr valign="top">
     <td >&nbsp;
     </td>
 </tr>
 <tr>
     <td align="left" class="pageheading" valign="top">
        Purchase Order > Add Order
    </td>
 </tr>
 <tr valign="top"><td align="center">&nbsp;
    </td>
 </tr>
 </table>
<table border="1" width="78%" bgcolor="#FCFCFC" ><tr><td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
       <td width="16%" align="left" valign="top">
         <div align="left" style="margin-left: 2px;" class="labels">
           <div align="right">Name of Supplier<span style="color:#FF0000"> *</span></div>
         </div></td>
	<td width="21%" align="left" valign="top">
        <div align="left">
            <s:select id="vendorName" name="purchaseOrder.vendor.id" class="dropdown">
                        <option  value="0">---Select Vendor---</option>
              <c:forEach items="${vendorlst}" var="vendor" varStatus="loop" >
                      <option value ="${vendor.id}"><c:out value="${vendor.name}"/></option>
    	      </c:forEach>
            </s:select>
         </div>
    </td>
    <td width="15%">&nbsp;<s:hidden name="purchaseOrder.deleted" value="1"/></td>
    <td width="15%">&nbsp;<s:hidden name="purchaseOrder.status" value="open"/></td>
    <td width="48%">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="4"><br><div align="left" style="margin-left:10px;">
				<table width="95%" border="0" cellspacing="0" cellpadding="0" style="border:1px solid #000000;" align="left" id="family">
					<tr>
						<td width="12%" height="28px" style="border-right:1px solid #000000;background:#FFCC66;"><div align="center"><strong><span style="color:#3B3131;font-size:13px;font-weight:bold;" >Item name</span></strong></div></td>
					    <td width="20%"  style="border-right:1px solid #000000; background:#FFCC66;"><div align="center"><strong><span style="color:#3B3131;font-size:13px;font-weight:bold;">Item Code</span></strong></div></td>
					    <td width="9%"  style=" border-right:1px solid #000000;background:#FFCC66;"><div align="center"><strong><span style="color:#3B3131;font-size:13px;font-weight:bold;">UoM</span></strong></div></td>
					    <td width="12%"  style=" border-right:1px solid #000000;background:#FFCC66;"><div align="center"><strong><span style="color:#3B3131;font-size:13px;font-weight:bold;">Description</span></strong></div></td>
					    <td width="13%"  style=" border-right:1px solid #000000;background:#FFCC66;"><div align="center"><strong><span style="color:#3B3131;font-size:13px;font-weight:bold;">Ordered Qty</span></strong></div></td>
                        <td width="12%"  style=" border-right:1px solid #000000;background:#FFCC66;"><div align="center"><strong><span style="color:#3B3131;font-size:13px;font-weight:bold;">Rate</span></strong></div></td>
					    <td width="5%"  style=" border-right:1px solid #000000;background:#FFCC66;"><div align="center"><strong><span style="color:#3B3131;font-size:13px;font-weight:bold;">Amount</span></strong></div></td>
                        <td width="5%"  style=" background:#FFCC66;"><div align="center"><strong><span style="color:#3B3131;font-size:13px;font-weight:bold;"><img src="/images/Cfthrow.gif"></span></strong></div></td>
					  </tr>
                              <c:forEach var="i" begin="1" end="4" step="1" varStatus ="status" >
					      <tr id="tabletr">
					        <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
					         <div align="left" style="margin-left:4px;">
					          <div align="right">
                                   <s:select id="itemcode${i}" name="purchasedetailarray[${i}].item.id"  onchange= "return GetItemDetail(this);">
                        <option  value="0">---Select Item---</option>
              <c:forEach items="${itemidlst}" var="itemidloop" varStatus="loop" >
               <option value ="${itemidloop.id}"><c:out value="${itemidloop.name}"/></option>
		      </c:forEach>
                </s:select>
					          </div></div></td>
					       <td style="border-top:1px solid #000000;border-right:1px solid #000000;"><div align="left" style="margin-left:4px;">
					         <div align="right">
					           <s:text readonly="readonly" name="purchasedetailarray[${i}].item.name" id="item${i}" class="hello" style="text-align:right;margin-right:2px; width:200px; "  />
					         </div></div></td>
					       <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
					         <div align="left" style="margin-left:4px;">
					         <div align="right">
					           <s:text readonly="readonly"  name="purchasedetailarray[${i}].item.uom.name" id="uom${i}" style="text-align:right;margin-right:2px;width:100px; "/>
					         </div></div></td>
                               <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
					         <div align="left" style="margin-left:4px;">
					         <div align="right">
					            <s:text  name="purchasedetailarray[${i}].description" style="text-align:right;margin-right:2px;width:100px; "/>
					         </div></div></td>
                              <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
                             <div align="left" style="margin-left:4px;">
                             <div align="right">
                                 <s:text  name="purchasedetailarray[${i}].orderedQty" id="ordqty${i}" onchange="return calculateBalancechaneorder(this,${i})"  style="text-align:right;margin-right:2px;width:80px; "/>
                             </div></div></td>
                               <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
                             <div align="left" style="margin-left:4px;">
                             <div align="right">
                               <s:text  name="purchasedetailarray[${i}].rate"  id="rate${i}" style="text-align:right;margin-right:2px;width:70px; " onchange="return calculateBalance(this,${i})"/>
                             </div></div></td>
                           <td style="border-top:1px solid #000000;border-right:1px solid #000000;"><div align="left" style="margin-left:4px;">  <div align="right">
                                <s:text readonly="readonly"  name="purchasedetailarray[${i}].amount" id="amount${i}"  style="text-align:right;margin-right:2px;width:70px; "/>
                             </div></div></td>
                              <td style="border-top:1px solid #000000;">
                                  <div align="left" style="margin-left:0px;">
                                <div align="right">
                                    <s:text name="delete[${i}]"   id="delete${i}"  style="background-image:url('../images/Cfthrow.gif');border :none;cursor:auto;"    onclick="return deletethis(this)"/>

                                 </div></div></td>

				                </tr>
                              </c:forEach>
				</table>
                  <tr><td colspan="4">
                        <div style="text-align:right; cursor: pointer;" class="links"><span style="vertical-align: top;text-align: left;font-size:medium; " class="addRow" id="add" >Add row</span></div>
                    </td></tr>
				</div></td>
      </tr>
      <tr>
        <td align="left">&nbsp;</td>
        <td align="left" colspan="3">&nbsp;</td>
        <td align="left">&nbsp;</td>
      </tr>
      <tr>
	<td align="left">&nbsp;</td>
	<td align="left" colspan="3"><div align="left" style="margin-left:20px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<s:submit name="addPurchaseOrder" value="Preview" id="addpopreview"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="reset"  value="Reset" name="reset"  style="width:80px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <s:submit name="cancel" value="Cancel"></s:submit>
	  </div></td>
	<td width="3%" align="left">&nbsp;</td>
	</tr>
    </table></td></tr></table>
    </s:form>
      </s:layout-component>
</s:layout-render>
