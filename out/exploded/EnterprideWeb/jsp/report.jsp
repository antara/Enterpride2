<%--
  Created by IntelliJ IDEA.
  User: Milind
  Date: Mar 28, 2012
  Time: 11:55:47 AM
  To change this template use File | Settings | File Templates.
--%>

 <%@ include file="/includes/_taglibInclude.jsp" %>
<link rel="stylesheet" href="/css/general.css" type="text/css" media="screen"/>
<link rel="stylesheet" type="text/css" href="../css/stylesheet.css"/>
<script type="text/javascript">
    var availableTags;
     $(function() {
      		  $.get("/Report.action?getItemCodeLst",function(result) {
     
	       availableTags =eval(result);
                           $("input#itemcode").autocomplete({
                         source: availableTags
                     });
		});
	});
 $(document).ready(function() {
     $("#getselectbydatebtn").click(function(){
            if ($("#textboxh").val().trim() ==""){
                              alert("please Enter the Details.");
                              $("#textboxh").focus() ;
                              return false;
                          }

     });
$("#getdailyledgerbtn").click(function(){
             
            if ($("#itemcode").val().trim() ==""){
                              alert("please enter the item code through drop down list.");
                              $("#itemcode").focus() ;
                              $("#itemcode").val("") ;
                              return false;
                          }
    else
             if(!phoneval.test($('#itemcode').val()))
                 {
                     alert("please enter the item code through drop down list.");
                              $("#itemcode").focus();
                              $("#itemcode").val("");
                              return false;
                    return false;
                 }

     });
$("#getbymonthbtn").click(function(){
            if ($("#month").attr("value") =="0"){
                              alert("please select the month.");
                              $("#month").focus() ;
                              return false;
                          }
            else if($("#year").attr("value") =="0"){
                              alert("please select the year.");
                              $("#year").focus() ;
                              return false;
                          }

     });
$("#getfromtobtn").click(function(){
            if ($("#fromd").val().trim() ==""){
                              alert("please enter from date.");
                              $("#fromd").focus() ;
                              return false;
                          }
            else if($("#tod").val().trim() ==""){
                              alert("please enter TO date.");
                              $("#tod").focus() ;
                              return false;
                          }

     });
  $("#getyearbtn").click(function(){
            if ($("#yearonly").attr("value") =="0"){
                              alert("please select the year.");
                              $("#yearonly").focus() ;
                              return false;
                          }
           

     });
     $('#radioitem').click(function() {
              $('.foritem').show();
           $('.formonth').hide();
          $('.forfromto').hide();
         });
     $('#radiosection').click(function() {

         });
     $('#radio3').click(function() {
              $('.foritemdate').show();

           $('.formonth').hide();
            $('.foryear').hide();
            $('.fordailyledger').hide();
              $('.forfromto').hide();
            $('.forfromto').hide();

         });
     $('#radio4').click(function() {

              $('.foritemselectdate').hide();

              $('.foritemdate').hide();
              $('.foryear').hide();
              $('#itemselectdatetable').hide();
              $('#textboxh').hide();


              $('.foritemselectdate').hide();
              $('#texboxh').hide();
              $('.formonth').show();
              $('.fordailyledger').hide();
           $('.foryear').hide();
          $('.forfromto').hide();
         });
     $('#radio5').click(function() {
             $('.forfromto').hide();
              $('.foritemselectdate').hide();
              $('.foritemdate').hide();
              $('#itemselectdatetable').hide();
              $('.foritemselectdate').hide();
              $('.texboxh').hide();
              $('.formonth').hide();
              $('.foryear').show();
              $('.fordailyledger').hide();
      });
 $('#radio7').click(function() {
              $('.forfromto').hide();
              $('.foritemselectdate').hide();
              $('.foritemdate').hide();
              $('#itemselectdatetable').hide();
              $('.foritemselectdate').hide();
              $('.texboxh').hide();
              $('.formonth').hide();
              $('.foryear').hide();
              $('.fordailyledger').show();


         });
$('#radio6').click(function() {
              $('.forfromto').show();
              $('#fromd').val("");
              $('#tod').val("");
              $('.foritemselectdate').hide();
              $('.foritemdate').hide();
              $('#itemselectdatetable').hide();
              $('.foritemselectdate').hide();
              $('.texboxh').hide();
              $('.formonth').hide();
              $('.foryear').hide();
              $('.fordailyledger').hide();
         });
     $('#radio8').click(function() {
            $('.forfromto').hide();
            $('#d').show();
            $('#itemselectdatetable').hide();
            $('.foritemselectdate').hide();
            $('.texboxh').hide();
            $('.texboxh').val("");
            $('.formonth').hide();
            $('.fordailyledger').hide();
            $('.foryear').hide();
        });
      $('#radio9').click(function() {
            $('#d').hide();
            $('#textboxh').val("");
            $('.foritemselectdate').show();

            $('.itemdailytable').hide();
            $('.formonth').hide();
            $('.fordailyledger').hide();
            $('.foryear').hide();
            $('.forfromto').hide();
        });
 });
</script>
  
<s:layout-render name="/layout/_base.jsp">
    <s:layout-component name="body">
          <s:form beanclass="com.erp.action.ReportActionBean">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" >
 <tr valign="top"><td >&nbsp;
 </td></tr>
 <tr><td align="left" class="pageheading" valign="top">
<strong>Report</strong>
 </td></tr>
 <tr valign="top"><td align="center">&nbsp;
 </td></tr>
 </table>
        <table bordercolor="#FF6600" width="77%" border="1">
 <tr>
 <td width="100%" height="119">
      <table width="100%" height="91" cellspacing="1">
          <tr>
                   <td width="8%" height="21"  align="left" valign="top">Search By</td>
                   <td width="1%"  align="right" valign="top"><s:radio  value="byItem" id="radioitem"  name="stock"></s:radio></td>
                   <td width="8%"  align="left" valign="top">By Item</td>
                   <td width="1%">&nbsp;</td>
                   <td width="3%"  align="right" valign="top"><s:radio  value="bySection" id="radiosection"  name="stock"></s:radio></td>
                   <td width="9%"  align="left" valign="top">By Section</td>
                     <td colspan="9"></td>
                 </tr>

                 <tr class="foritem" style="display:none;" >
                   <td  >&nbsp;</td>
                   <td align="right" valign="top"><s:radio  value="byDaily" id="radio3" class="search"  name="search"></s:radio></td>
                   <td align="left" valign="top">Daily</td>
                   <td>&nbsp;</td>
                   <td align="right" valign="top"><s:radio  value="byMonthly" id="radio4"  name="search"></s:radio></td>
                   <td align="left" valign="top">Monthly</td>
                   <td width="1%" >&nbsp;</td>
                   <td width="1%"  align="right" valign="top"><s:radio  value="byYearly" id="radio5"  name="search"></s:radio></td>
                   <td width="6%"  align="left" valign="top">Yearly</td>
                   <td width="1%" >&nbsp;</td>
                   <td width="1%"  align="right" valign="top"><s:radio  value="byFromTo" id="radio6"  name="search"></s:radio></td>
                   <td width="8%"  align="left" valign="top">From-To</td>
                   <td width="1%" >&nbsp;</td>
                   <td width="2%"  align="right" valign="top"><s:radio  value="byDailyLedger" id="radio7"  name="search"></s:radio></td>
                   <td width="49%"  align="left" valign="top">Daily Ledger</td>
                 </tr>
                  <tr class="foritemdate" style="display:none; border-bottom:1 ">
                   <td width="8%"  align="left" valign="top"></td>
                   <td width="1%"  align="right" valign="top">
                    <s:link beanclass="com.erp.action.ReportActionBean" event="byToday">
                       <s:param name="daily" value="byToday"></s:param>
                       <s:radio  value="byToday" id="radio8"  name="daily"></s:radio>
                       </s:link>
                   </td>
                   <td width="8%"  align="left" valign="top">By Today</td>
                   <td width="1%">&nbsp;</td>
                   <td width="3%"  align="right" valign="top"><s:radio  value="bySelectDate" id="radio9"  name="daily"></s:radio></td>
                   <td width="10%"  align="left" valign="top">By Date</td>
                 </tr>
          <tr class="foritemselectdate" style="display:none;" >
                   <td width="8%" height="21" align="left" valign="top"></td>
                   <td width="1%" colspan=""  align="right" valign="top"></td>
                   <td  align="right" colspan="2" valign="top"><strong>Select Date</strong> </td>
                   <td  align="left" colspan="7" valign="top"><s:text name="sdate" id="textboxh" readonly="readonly" onFocus="showCalendarControl(this);" class="textbox"></s:text> &nbsp;&nbsp;<s:submit name="bySelectDate" id="getselectbydatebtn" value="Get"></s:submit></td>
               <td  align="left" colspan="3" valign="top"></td>
                 </tr>
         <tr class="formonth" style="display:none;" >
         <td width="8%" height="21" align="left" valign="top"></td>
         <td width="1%" colspan=""  align="right" valign="top"></td>
         <td  align="left" colspan="3" valign="top">Select Month & Year </td>
         <td  align="left" colspan="10" valign="top">

         <s:select name="month" id="month" class="dropdown">
            <option  value="0">----Select Month----</option>
            <option value="01">January</option><option value="02">February</option><option value="03">March</option><option value="04">April</option><option value="05">May</option><option value="06">June</option><option value="07">July</option><option value="08">August</option><option value="09">September</option><option value="10">October</option><option value="11">November</option><option value="12">December</option>
         </s:select>
         <s:select id="year" name="year" class="dropdown">
            <%int year = 2011;%>
            <option  value="0">---Select Year---</option>
            <c:forEach var="i" begin="<%=year %>" end="<%=year + 20 %>">
                 <option value="<c:out value="${i+1}"/>"><c:out value="${i+1}"/></option>
            </c:forEach>
         </s:select>&nbsp;&nbsp;

         <s:submit name="byMonth" id="getbymonthbtn" value="Get"></s:submit>
             </td>
     
       </tr>
          <tr class="foryear" style="display:none;" >
         <td width="8%" height="21" align="left" valign="top"></td>
         <td width="1%" colspan=""  align="right" valign="top"></td>
         <td  align="left" colspan="3" valign="top">Select Year </td>

         <td  align="left" colspan="10" valign="top">


<s:select id="yearonly" name="y" class="dropdown">
                            <%int year = 2011;%>
                        <option  value="0">---Select Year---</option>

                <c:forEach var="i" begin="<%=year %>" end="<%=year + 20 %>">
                    <option value="<c:out value="${i+1}"/>"><c:out value="${i+1}"/></option>
                </c:forEach>
            </s:select>
            &nbsp;&nbsp;<s:submit name="byYear" id="getyearbtn" value="Get"></s:submit>
             </td>

       </tr>

<tr class="fordailyledger" style="display:none;" >
         <td width="8%" height="21" align="left" valign="top"></td>
         <td width="1%"  align="right" valign="top"></td>
         <td  align="right" colspan="3" valign="top">Enter Item Code </td>
         <td  align="left" colspan="9" valign="top">
         <s:text name="itemcodetxt" class="textbox" id="itemcode"/>
         &nbsp;&nbsp;<s:submit name="byDailyLedger" id="getdailyledgerbtn" value="Get"></s:submit>
         </td>
</tr>
<tr class="forfromto" style="display:none;" >
         <td width="8%" height="21" align="left" valign="top"></td>
         <td width="1%"  align="right" valign="top"></td>
         <td  align="right" colspan="2" valign="top">From Date </td>
         <td  align="left" colspan="4"  valign="top">
         <s:text name="fromdate" id="fromd" class="textbox" onFocus="showCalendarControl(this);" />
        </td>
          <td  align="right" colspan="2" valign="top">To Date </td>
          <td  align="left"  colspan="5" valign="top">
         <s:text name="todate" class="textbox" id="tod" onFocus="showCalendarControl(this);"/>
         &nbsp;&nbsp;<s:submit name="byFromTo" id="getfromtobtn" value="Get"></s:submit>
         </td>
</tr>

 <c:if test="${actionBean.daily eq 'bySelectDate'}">
<script type="text/javascript">
        $(document).ready(function() {
    $('.foritem').show();
            $('.foritemdate').show();
            $('.foritemselectdate').show();
        });
  </script>
    </c:if >
          <c:if test="${actionBean.daily eq 'byToday'}">
<script type="text/javascript">
        $(document).ready(function() {
    $('.foritem').show();
            $('.foritemdate').show();
            
        });
  </script>
    </c:if >
          <c:if test="${actionBean.search eq 'byMonthly'}">
<script type="text/javascript">
        $(document).ready(function() {
    $('.foritem').show();
            $('.formonth').show();
            $('.foritemselectdate').hide();
            $('.foritemdate').hide();
            var fromact=${actionBean.month};
                        if(fromact<9)
                        {
                        var m="0"+${actionBean.month};
                        $('#month').attr("value",m);    
                        }
                         else
                           $('#month').attr("value",fromact);

            $('#year').attr("value",${actionBean.year});
       });
  </script>
    </c:if >
          <c:if test="${actionBean.search eq 'byYearly'}">
<script type="text/javascript">
        $(document).ready(function() {
    $('.foritem').show();
            $('.foryear').show();
             $('.foritemselectdate').hide();
            $('.foritemdate').hide();
            $('#yearonly').attr("value",${actionBean.y});
       });
  </script>
    </c:if >
<c:if test="${actionBean.search eq 'byDailyLedger'}">
<script type="text/javascript">
        $(document).ready(function() {
            $('.foritem').show();
            $('.fordailyledger').show();
             $('.foritemselectdate').hide();
            $('.foritemdate').hide();
            $('#itemcode').attr("value",${actionBean.itemcodetxt});
       });
  </script>
    </c:if >
<c:if test="${actionBean.search eq 'byFromTo'}">
<script type="text/javascript">
        $(document).ready(function() {
            $('.foritem').show();
            $('.forfromto').show();
             $('.foritemselectdate').hide();
            $('.foritemdate').hide();

       });
  </script>
    </c:if >

    </table></td>
   </tr>
 </table>


               <c:if test="${actionBean.dailystocktoday!=null}">
             <s:useActionBean beanclass="com.erp.action.ReportActionBean" event="byToday" var="lst"></s:useActionBean>
            <%
                request.setAttribute("dailystocktodaylst",lst.getDailystocktoday());
            %>
           <table id="itemdailytable"  width="100%"><tr><td>
          <d:table name="dailystocktodaylst" id="v" pagesize="10" class="disp" requestURI="/Report.action">
                  <d:column  property="date" format="{0,date,yyyy-MM-dd}" sortable="false" title="Create Date"/>
                  <d:column property="item.name" title="Item Name"/>
                  <d:column property="item.itemCode" title="Item code"/>
                  <d:column property="openQuantity" title="open Quantity"/>
                  <d:column property="receivedQuantity" title="receivedQuantity"/>
                  <d:column property="issuedQuantity" title="issued Quantity" />
                  <d:column property="closingQuantity" title="closing Quantity"/>
                  <d:column property="remark" title="remark"/>
           </d:table></td></tr>
           </table>
                   </c:if>
 <c:if test="${actionBean.dailystock!=null}">
<s:useActionBean beanclass="com.erp.action.ReportActionBean" event="byDailyLedger" var="lst"></s:useActionBean>
      <%
          request.setAttribute("dailystocktodaylst",lst.getDailystock());
      %>
            <table id="itemdailytable"  width="100%"><tr><td>
                  <d:table name="dailystocktodaylst" id="v" pagesize="10" class="disp" requestURI="/Report.action">
                  <d:column property="create_date" format="{0,date,yyyy-MM-dd}" sortable="false" title="Create Date"/>
                  <d:column property="name" title="Item Name"/>
                  <d:column property="item_code" title="Item Code"/>
                  <d:column property="open_quantity" title="open Quantity"/>
                  <d:column property="received_quantity" title="received Quantity"/>
                  <d:column property="issued_quantity" title="issued Quantity" />
                  <d:column property="closing_quantity" title="closing Quantity"/>
                  <d:column property="remark" title="remark"/>
                  </d:table></td></tr>
            </table>
                   </c:if>
      </s:form>
    </s:layout-component>
</s:layout-render>