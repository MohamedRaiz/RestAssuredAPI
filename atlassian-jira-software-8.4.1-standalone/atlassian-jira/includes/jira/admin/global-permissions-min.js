require(["jira/util/formatter","wrm/context-path","jquery"],function(e,a,t){"use strict";function n(a,n,r,i){var o=t("#globalPermType_select option:selected"),l=o.val();if(t("#groupName_select option:selected").val()===a&&l===n){var s=r.filter(function(e){return t.inArray(a,e.defaultGroups)>-1});if(s.length>0){var d=o.text().trim();t("#default-group-warning-message").text(e.I18n.getText("admin.errors.permissions.grant.admin.to.default.group",a,s[0].name,d)),i.removeClass("hidden")}}}function r(e,a,t){"USER_PICKER"===a&&""===e&&t.removeClass("hidden")}function i(){var e=t("#default-groups-warning");e.addClass("hidden");var i=t("#globalPermType_select option:selected").val(),l=t("#groupName_select option:selected").val(),s=t("#sharing-with-anyone-warning");if(s.addClass("hidden"),r(l,i,s),l&&("SYSTEM_ADMIN"===i||"ADMINISTER"===i)){var d=t("#addpermission_submit");d.attr("aria-disabled","true"),void 0===o&&(o=t.ajax({url:a()+"/rest/api/2/applicationrole",contentType:"application/json",type:"GET"}).promise()),o.done(function(a){n(l,i,a,e)}),o.always(function(){d.attr("aria-disabled","false")})}}var o;t(document).ready(function(){t("#groupName_select").on("change",i),t("#globalPermType_select").on("change",i)})});