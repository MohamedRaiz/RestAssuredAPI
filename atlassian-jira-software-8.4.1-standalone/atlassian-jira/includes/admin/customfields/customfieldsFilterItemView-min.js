define("jira/customfields/customfieldsFilterItemView",["jira/marionette-4.1","atlassian/libs/underscore-1.8.3"],function(e,t){"use strict";return e.View.extend({ui:{filter:'input[type="checkbox"]'},triggers:{"change @ui.filter":"item:clicked"},tagName:"li",template:JIRA.Templates.Admin.Customfields.filterDropdownItem,templateContext:function(){return{checked:t.contains(this.options.filterState,this.model.id.toString())}}})});