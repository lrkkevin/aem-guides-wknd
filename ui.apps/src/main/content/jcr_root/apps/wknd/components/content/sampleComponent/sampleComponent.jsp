<%@include file="/libs/foundation/global.jsp"%>
<%
com.adobe.aem.guides.wknd.core.services.ReadJsonService service = sling.getService(com.adobe.aem.guides.wknd.core.services.ReadJsonService.class);

String result = service.getData();

%>
<h1>RESPONSE</h1>
<h3>RESPONSE: <%=result%></h3>