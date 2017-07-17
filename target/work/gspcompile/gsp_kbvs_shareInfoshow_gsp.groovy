import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_shareInfoshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/shareInfo/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',12,[:],2)
printHtmlPart(4)
expressionOut.print(resource(dir: 'css',file: 'bootstrap.min.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'css',file: 'link.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'css',file: 'base.css'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'images',file: 'favicon.png'))
printHtmlPart(7)
invokeTag('javascript','g',16,['src':("main.js")],-1)
printHtmlPart(8)
invokeTag('createLink','g',25,['controller':("resourceInfo"),'action':("singleFileDownload")],-1)
printHtmlPart(9)
})
invokeTag('captureHead','sitemesh',25,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(12)
})
invokeTag('link','g',39,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(13)
createClosureForHtmlPart(14, 2)
invokeTag('link','g',44,['controller':("resourceInfo"),'action':("nextIndex")],2)
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',45,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(17)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(18)
expressionOut.print(session.userName)
printHtmlPart(19)
createClosureForHtmlPart(20, 2)
invokeTag('link','g',56,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(21)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',58,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(23)
expressionOut.print(ShareInfo.name)
printHtmlPart(24)
expressionOut.print(ShareInfo.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(25)
createTagBody(2, {->
printHtmlPart(26)
expressionOut.print(ShareInfo.name)
printHtmlPart(27)
loop:{
int i = 0
for( folderList in (FolderList) ) {
printHtmlPart(28)
createTagBody(4, {->
printHtmlPart(29)
expressionOut.print(folderList.name)
printHtmlPart(30)
})
invokeTag('link','g',88,['controller':("shareInfo"),'action':("findResourcesByFolder"),'id':(folderList.id),'params':([userId: UserInfo.id, shareId: ShareInfo.id])],4)
printHtmlPart(31)
i++
}
}
printHtmlPart(32)
})
invokeTag('link','g',90,['controller':("shareInfo"),'action':("show"),'id':(ShareInfo.id)],2)
printHtmlPart(33)
loop:{
int i = 0
for( folderInfoListInstance in (FolderInfoList) ) {
printHtmlPart(34)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(35)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(36)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(37)
createTagBody(3, {->
printHtmlPart(38)
expressionOut.print(fieldValue(bean: folderInfoListInstance, field: "name"))
printHtmlPart(39)
})
invokeTag('link','g',133,['controller':("shareInfo"),'action':("findResourcesByFolder"),'id':(folderInfoListInstance.id),'params':([userId: UserInfo.id, shareId: ShareInfo.id])],3)
printHtmlPart(40)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(41)
expressionOut.print(fieldValue(bean: folderInfoListInstance, field: ""))
printHtmlPart(42)
expressionOut.print(folderInfoListInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(43)
i++
}
}
printHtmlPart(44)
loop:{
int i = 0
for( resourceInfoInstance in (ResourceInfoList) ) {
printHtmlPart(45)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(46)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(47)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(48)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(49)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg')) {
printHtmlPart(50)
}
else if(true && (resourceInfoInstance?.extName=='doc'||resourceInfoInstance?.extName=='docx')) {
printHtmlPart(51)
}
else if(true && (resourceInfoInstance?.extName=='xls'||resourceInfoInstance?.extName=='xlsx')) {
printHtmlPart(52)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma')) {
printHtmlPart(53)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv')) {
printHtmlPart(54)
}
else if(true && (resourceInfoInstance?.extName=='pptx'||resourceInfoInstance?.extName=='ppt')) {
printHtmlPart(55)
}
else if(true && (resourceInfoInstance?.extName=='rar'||resourceInfoInstance?.extName=='zip')) {
printHtmlPart(56)
}
else if(true && (resourceInfoInstance?.extName=='pdf')) {
printHtmlPart(57)
}
else if(true && (resourceInfoInstance?.extName=='txt')) {
printHtmlPart(58)
}
else if(true && (resourceInfoInstance?.extName=='torrent')) {
printHtmlPart(59)
}
else {
printHtmlPart(60)
}
printHtmlPart(61)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg')) {
printHtmlPart(62)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(63)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(64)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(65)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv')) {
printHtmlPart(66)
createTagBody(4, {->
printHtmlPart(67)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(64)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(66)
})
invokeTag('link','g',199,['action':("showVideo"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(68)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma')) {
printHtmlPart(69)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(63)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(64)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(65)
}
else if(true && (resourceInfoInstance?.type=='other')) {
printHtmlPart(70)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(64)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(65)
}
else {
printHtmlPart(66)
createTagBody(4, {->
printHtmlPart(67)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(64)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(66)
})
invokeTag('link','g',210,['controller':("resourceInfo"),'action':("show"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(68)
}
printHtmlPart(71)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(72)
expressionOut.print(fieldValue(bean: resourceInfoInstance, field: "fileSize"))
printHtmlPart(73)
expressionOut.print(resourceInfoInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(74)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(75)
expressionOut.print(raw(resourceInfoInstance.remark))
printHtmlPart(76)
i++
}
}
printHtmlPart(77)
expressionOut.print(resource(dir:'',file:UserInfo.imagePath))
printHtmlPart(78)
expressionOut.print(UserInfo.loginName)
printHtmlPart(79)
invokeTag('createLink','g',302,['action':("showMusic")],-1)
printHtmlPart(80)
invokeTag('createLink','g',310,['controller':("resourceInfo"),'action':("showImg")],-1)
printHtmlPart(81)
})
invokeTag('captureBody','sitemesh',332,[:],1)
printHtmlPart(82)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471768350700L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
