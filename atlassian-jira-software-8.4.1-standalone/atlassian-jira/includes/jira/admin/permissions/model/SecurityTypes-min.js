define("jira/project/permissions/securitytypes",[],function(){"use strict";return{PROJECT_ROLE:"projectrole",GROUP:"group",USER_CF:"userCF",GROUP_CF:"groupCF",APPLICATION_ROLE:"applicationRole",REPORTER:"reporter",PROJECT_LEAD:"project-lead",CURRENT_ASSIGNEE:"current-assignee",SINGLE_USER:"user",isCustomFieldType:function(e){return e===this.USER_CF||e===this.GROUP_CF}}});