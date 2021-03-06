<%@ taglib uri="webwork" prefix="ww" %>
<ul id="filter_type_table" class="vertical tabs">
    <ww:if test="/userLoggedIn == true">
        <li id="md_fav_li" class="<ww:if test="/view == 'favourites'">active </ww:if>first"><a id="favourite-dash-tab" title="<ww:text name="'configureportalpages.favourite.desc'"/>" href="<%= request.getContextPath() %>/secure/ConfigurePortalPages.jspa?view=favourites"><strong><ww:text name="'common.favourites.favourites'"/></strong></a></li>
        <li id="md_my_li" <ww:if test="/view == 'my'">class="active"</ww:if>><a id="my-dash-tab" title="<ww:text name="'configureportalpages.my.desc'"/>" href="<%= request.getContextPath() %>/secure/ConfigurePortalPages.jspa?view=my"><strong><ww:text name="'common.concepts.my'"/></strong></a></li>
    </ww:if>
    <li id="md_popular_li" <ww:if test="/view == 'popular'">class="active"</ww:if>><a id="popular-dash-tab" title="<ww:text name="'configureportalpages.popular.desc'"/>" href="<%= request.getContextPath() %>/secure/ConfigurePortalPages.jspa?view=popular"><strong><ww:text name="'common.concepts.popular'"/></strong></a></li>
    <li id="md_search_li" <ww:if test="/view == 'search'">class="active"</ww:if>><a id="search-dash-tab" title="<ww:text name="'configureportalpages.search.desc'"/>" href="<%= request.getContextPath() %>/secure/ConfigurePortalPages.jspa?view=search"><strong><ww:text name="'common.concepts.search'"/></strong></a></li>
</ul>
