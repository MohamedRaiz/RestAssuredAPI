require(["jira/dialog/form-dialog","jira/dropdown/dropdown-factory","jquery"],function(r,e,a){"use strict";function t(){a("a.delete-dashboard, a.change-owner").each(function(){var e=this.id;new r({trigger:"#"+e,autoClose:!0})})}a(document).on("click","#pp_browse tr:first a",function(r){a.ajax({method:"get",dataType:"html",url:a(this).attr("href")+"&decorator=none&contentOnly=true&Search=Search",success:function(r){a("#shared-dashboard-search-results").html(r),e.bindGenericDropdowns(a("#shared-dashboard-search-results")),t()}}),r.preventDefault(),r.stopPropagation()}),a(t)});