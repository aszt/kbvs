import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_groupInfoindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/groupInfo/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(4)
expressionOut.print(resource(dir: 'css',file: 'reset.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'css',file: 'mygroup.css'))
printHtmlPart(6)
invokeTag('javascript','g',9,['src':("group.js")],-1)
printHtmlPart(7)
invokeTag('createLink','g',18,['controller':("groupInfo"),'action':("newGroup")],-1)
printHtmlPart(8)
expressionOut.print(state)
printHtmlPart(9)
expressionOut.print(groupName)
printHtmlPart(10)
expressionOut.print(state)
printHtmlPart(11)
invokeTag('createLink','g',73,['controller':("groupInfo"),'action':("shares")],-1)
printHtmlPart(12)
invokeTag('createLink','g',107,['controller':("groupInfo"),'action':("delGroup")],-1)
printHtmlPart(13)
invokeTag('createLink','g',128,['controller':("groupInfo"),'action':("quitGroup")],-1)
printHtmlPart(14)
invokeTag('createLink','g',151,['controller':("groupInfo"),'action':("getMember")],-1)
printHtmlPart(15)
invokeTag('createLink','g',187,['controller':("groupInfo"),'action':("getShare")],-1)
printHtmlPart(16)
expressionOut.print(groupId)
printHtmlPart(17)
invokeTag('createLink','g',223,['controller':("groupInfo"),'action':("addGroup")],-1)
printHtmlPart(18)
})
invokeTag('captureHead','sitemesh',254,[:],1)
printHtmlPart(19)
createTagBody(1, {->
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(22)
})
invokeTag('link','g',265,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(23)
createClosureForHtmlPart(24, 2)
invokeTag('link','g',270,['controller':("resourceInfo"),'action':("nextIndex")],2)
printHtmlPart(25)
createClosureForHtmlPart(26, 2)
invokeTag('link','g',271,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(27)
expressionOut.print(resource(dir: '',file:session.imagePath ))
printHtmlPart(28)
expressionOut.print(session.userName)
printHtmlPart(29)
createClosureForHtmlPart(30, 2)
invokeTag('link','g',282,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(31)
createClosureForHtmlPart(32, 2)
invokeTag('link','g',284,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(33)
expressionOut.print(FolderInfoId)
printHtmlPart(34)
loop:{
int i = 0
for( gruopInfo in (groupInfoList) ) {
printHtmlPart(35)
expressionOut.print(gruopInfo.id)
printHtmlPart(36)
expressionOut.print(gruopInfo.id)
printHtmlPart(37)
expressionOut.print(gruopInfo.id)
printHtmlPart(38)
expressionOut.print(gruopInfo.name)
printHtmlPart(39)
if(true && (gruopInfo.creatorId==session.userId)) {
printHtmlPart(40)
expressionOut.print(gruopInfo.id)
printHtmlPart(41)
}
else {
printHtmlPart(42)
expressionOut.print(gruopInfo.id)
printHtmlPart(43)
}
printHtmlPart(44)
expressionOut.print(gruopInfo.id)
printHtmlPart(45)
i++
}
}
printHtmlPart(46)
expressionOut.print(fillType)
printHtmlPart(47)
loop:{
int i = 0
for( folderInfoListInstance in (ShareInfoLidt) ) {
printHtmlPart(48)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(49)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(50)
createTagBody(3, {->
printHtmlPart(51)
expressionOut.print(fieldValue(bean: folderInfoListInstance, field: "name"))
printHtmlPart(52)
})
invokeTag('link','g',400,['controller':("shareInfo"),'action':("show"),'id':(folderInfoListInstance.id)],3)
printHtmlPart(53)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(54)
expressionOut.print(fieldValue(bean: folderInfoListInstance, field: ""))
printHtmlPart(55)
expressionOut.print(folderInfoListInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(56)
i++
}
}
printHtmlPart(57)
loop:{
int i = 0
for( userInfo in (userInfoList) ) {
printHtmlPart(58)
expressionOut.print(userInfo.imagePath)
printHtmlPart(59)
expressionOut.print(userInfo.loginName)
printHtmlPart(60)
i++
}
}
printHtmlPart(61)
})
invokeTag('captureBody','sitemesh',457,['style':("background:#F6FAFD;")],1)
printHtmlPart(62)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471768432869L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
