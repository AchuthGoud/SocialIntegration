<%@include file="/apps/SocialIntegration/global.jsp"%>
<%@page import="com.inkriti.socialintegration.models.TwitterSearch,org.apache.sling.commons.json.JSONArray,org.apache.sling.commons.json.JSONObject" %>
<html>
<head>
<title>AEM Twitter Feed Page</title>
</head>
<body>
    <%= properties.get("twitter")%>
<% TwitterSearch twitterSearch= new TwitterSearch();
JSONArray jsonArray=twitterSearch.getConnection(properties.get("twitter").toString());
for(int i=0;i<jsonArray.length();i++){
   %><%= ((JSONObject)jsonArray.get(i)).get("text").toString()%><hr/><%
}
%>
   </body>