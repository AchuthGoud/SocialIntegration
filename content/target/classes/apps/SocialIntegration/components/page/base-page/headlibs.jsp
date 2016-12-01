<%--

--%><%@ page session="false" %><%
%><%@include file="/apps/SocialIntegration/global.jsp" %><%
%><cq:includeClientLib categories="cq.foundation-main"/><%
%><cq:include script="/libs/cq/cloudserviceconfigs/components/servicelibs/servicelibs.jsp"/><%
    currentDesign.writeCssIncludes(pageContext); %><%
      %><cq:includeClientLib css="SocialIntegration.global,SocialIntegration.page,SocialIntegration.components"/>
<c:if test="${WCMEdit}">
    <cq:includeClientLib css="SocialIntegration.author"/>
</c:if>