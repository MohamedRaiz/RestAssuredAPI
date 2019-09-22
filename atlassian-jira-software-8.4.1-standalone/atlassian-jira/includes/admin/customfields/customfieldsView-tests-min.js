AJS.test.require(["jira.webresources:viewcustomfields"],function(){"use strict";var e=require("underscore"),s=require("jquery"),t=require("jira/customfields/customfieldsView");module("CustomfieldsPageView",{setup:function(){this.sandbox=sinon.sandbox.create({useFakeServer:!0}),this.customFields=[{name:"CF 1",type:"Number Field",contextSchemesCount:1,screensCount:1},{name:"CF 2",type:"Magic Field",searcherKey:"nosearcher",contextSchemesCount:10,screensCount:0}],s("#qunit-fixture").html('<section id="customfields-container"> </section>'),this.mockCustomfieldsResponse(),this.customfieldsPageView=new t({el:"#customfields-container"})},teardown:function(){this.sandbox.restore()},mockCustomfieldsResponse:function(){this.sandbox.server.respondWith([200,{"Content-Type":"application/json"},JSON.stringify({maxResults:25,startAt:0,total:this.customFields.length,isLast:!0,values:this.customFields.map(function(s,t){return e.extend({id:"customfield_"+(1e4+t),self:"http://localhost:8090/jira/rest/api/2/customFields/customfield_"+(1e4+t),numericId:1e4+t,isLocked:!1,isManaged:!1,searcherKey:"asdsda"},s)})})])}}),test("Custom fields list is rendered",function(){this.sandbox.server.respond();var t=s("#qunit-fixture #custom-fields-table tbody"),i=t.find("tr>td:first-child>strong").map(function(e,s){return s.innerHTML}).get(),n=t.find("tr>td:first-child+td>span").map(function(e,s){return s.innerHTML}).get();deepEqual(i,e.pluck(this.customFields,"name"),"Names are rendered"),deepEqual(n,e.pluck(this.customFields,"type"),"Types are rendered")}),test("Displays progress indicator on load",function(){var e=s("#qunit-fixture #customfields-container");ok(e.hasClass("active"),"has progress indicator overlay"),ok(e.has("aui-spinner").length,"has aui spinner"),this.sandbox.server.respond(),notOk(e.hasClass("active"),"has no progress indicator overlay after data is loaded"),notOk(e.has("aui-spinner").length,"hides aui spinner after data is loaded")}),asyncTest("Displays progress indicator on search",function(){var e=this;this.sandbox.server.respond(),this.sandbox.server.respondWith([200,{"Content-Type":"application/json"},JSON.stringify([])]),this.sandbox.server.respond();var t=s("#qunit-fixture #customfields-container");t.find("#custom-fields-filter-text").val("blah").trigger("input"),setTimeout(function(){ok(t.hasClass("active"),"has progress indicator overlay"),ok(t.has("aui-spinner").length,"has aui spinner"),e.sandbox.server.respond(),notOk(t.hasClass("active"),"has no progress indicator overlay after data is loaded"),notOk(t.has("aui-spinner").length,"hides aui spinner after data is loaded"),start()},1e3)}),asyncTest("Displays empty search results view on empty search",function(){var e=this;this.sandbox.server.respond(),this.sandbox.server.respondWith([200,{"Content-Type":"application/json"},JSON.stringify([])]),this.sandbox.server.respond();var t=s("#qunit-fixture #customfields-container");t.find("#custom-fields-filter-text").val("blah").trigger("input"),setTimeout(function(){e.sandbox.server.respondWith([200,{"Content-Type":"application/json"},JSON.stringify({maxResults:25,startAt:0,total:0,isLast:!0,values:[]})]),e.sandbox.server.respond();var i=s("#qunit-fixture #custom-fields-table tbody tr");ok(i.length,"only one row for empty result message is rendered"),ok(i.find(".jira-adbox.no-project-results").length,"empty view is displayed"),notOk(t.hasClass("active"),"has no progress indicator overlay on empty screen"),notOk(t.has("aui-spinner").length,"hides aui spinner after displaying empty screen"),start()},1e3)}),asyncTest("Displays error flag on search failure",function(){var e=this;this.sandbox.server.respond();var t=s("#qunit-fixture #customfields-container");t.find("#custom-fields-filter-text").val("blah").trigger("input"),setTimeout(function(){e.sandbox.server.respondWith([404,{},""]),e.sandbox.server.respond();var i=s("#aui-flag-container .aui-message");ok(i.length,"error message is displayed"),equal(i.text(),"rest.error.internal","error message is correct"),notOk(t.hasClass("active"),"has no progress indicator overlay after search failure"),notOk(t.has("aui-spinner").length,"hides aui spinner after search failure"),start()},1e3)}),test("Projects link is correctly displayed",function(){this.customFields=[{name:"CF 3",type:"Number Field",isAllProjects:!0,projectsCount:0,screensCount:1},{name:"CF 4",type:"Number Field",isAllProjects:!1,projectsCount:0,screensCount:1},{name:"CF 5",type:"Test Field",searcherKey:"nosearcher",isAllProjects:!1,projectsCount:10,screensCount:3}],this.mockCustomfieldsResponse(),this.sandbox.server.respond();var e=s("#qunit-fixture #customfields-container #custom-fields-table tbody");equal(e.find("tr:first td:eq(2)").text(),"admin.issuefields.customfields.global.all.projects","displays global projects read onlytext"),equal(e.find("tr:eq(1) td:eq(2)").text(),"admin.issuefields.customfields.projects.conditional","displays 0 projects read only text"),notOk(e.find("tr:eq(1) td:eq(2)").has("a").length,"0 projects text is read only"),equal(e.find("tr:eq(2) td:eq(2)").text(),"admin.issuefields.customfields.projects.conditional","displays n projects text"),ok(e.find("tr:eq(2) td:eq(2)").has("a").length,"n projects text is clickable link")}),asyncTest("Focus on search input field should not cause search execution (IE11)",function(){var e=this;this.sandbox.server.respond();var t=s("#qunit-fixture #customfields-container"),i=t.find("#custom-fields-filter-text");this.customfieldsPageView.collection.getFirstPage=this.sandbox.spy(),i.focus().trigger("focus"),setTimeout(function(){notOk(t.hasClass("active"),"doesn't have progress indicator overlay"),notOk(t.has("aui-spinner").length,"doesn't have aui spinner"),sinon.assert.notCalled(e.customfieldsPageView.collection.getFirstPage),start()},1e3)})});