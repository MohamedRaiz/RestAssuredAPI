<%@ taglib uri="webwork" prefix="ww" %>
<%@ taglib uri="webwork" prefix="ui" %>
<%@ taglib uri="webwork" prefix="aui" %>
<%@ taglib uri="sitemesh-page" prefix="page" %>
<html>
<head>
    <title><ww:text name="'portal.configure'"/></title>
    <content tag="section">home_link</content>
</head>
<body>
    <ui:soy moduleKey="'jira.webresources:soy-templates'" template="'JIRA.Templates.Headers.pageHeader'">
        <ui:param name="'mainContent'">
            <h1><ww:text name="'configureportalpages.title'"/></h1>
        </ui:param>
        <ui:param name="'actionsContent'">
            <div class="aui-buttons">
                <a class="aui-button aui-button-light" id="create_page" href="<ww:url page="AddPortalPage!default.jspa" />"><ww:text name="'addportalpage.clone.blank'"/></a>
                <a class="aui-button aui-button-light" id="restore_defaults" href="<ww:url page="RestoreDefaultDashboard!default.jspa"><ww:param name="'destination'" value="'manageportal'"/></ww:url>"><ww:text name="'restoredefaultdashboard.restoredefaults'"/></a>
            </div>
        </ui:param>
    </ui:soy>

    <ui:soy moduleKey="'com.atlassian.auiplugin:soy'" template="'aui.page.pagePanel'">
        <ui:param name="'content'">
            <ui:soy moduleKey="'com.atlassian.auiplugin:soy'" template="'aui.page.pagePanelNav'">
                <ui:param name="'content'">
                    <jsp:include page="configureportalpages-tabs.jsp" />
                </ui:param>
            </ui:soy>
            <ui:soy moduleKey="'com.atlassian.auiplugin:soy'" template="'aui.page.pagePanelContent'">
                <ui:param name="'content'">
                    <jsp:include page="configureportalpages-content.jsp" />
                </ui:param>
            </ui:soy>
        </ui:param>
    </ui:soy>
</body>
</html>
