import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfoactivePage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/activePage.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
expressionOut.print(resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css'))
printHtmlPart(5)
invokeTag('createLink','g',78,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(6)
invokeTag('createLink','g',105,['controller':("resourceInfo"),'action':("remove")],-1)
printHtmlPart(7)
invokeTag('createLink','g',143,['controller':("shareInfo"),'action':("shares")],-1)
printHtmlPart(8)
invokeTag('createLink','g',233,['controller':("groupInfo"),'action':("myGroup")],-1)
printHtmlPart(9)
invokeTag('createLink','g',264,['controller':("shareInfo"),'action':("shareGroup")],-1)
printHtmlPart(10)
invokeTag('createLink','g',288,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(11)
invokeTag('createLink','g',314,['controller':("resourceInfo"),'action':("documentRelation")],-1)
printHtmlPart(12)
invokeTag('createLink','g',338,['controller':("resourceInfo"),'action':("download")],-1)
printHtmlPart(13)
invokeTag('createLink','g',348,['controller':("folderInfo"),'action':("reName")],-1)
printHtmlPart(14)
invokeTag('createLink','g',366,['controller':("resourceInfo"),'action':("singleFileDownload")],-1)
printHtmlPart(15)
invokeTag('createLink','g',371,['controller':("resourceInfo"),'action':("moveCommon")],-1)
printHtmlPart(16)
})
invokeTag('captureHead','sitemesh',472,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(19)
})
invokeTag('link','g',487,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(20)
createClosureForHtmlPart(21, 2)
invokeTag('link','g',492,['action':("nextIndex")],2)
printHtmlPart(22)
createClosureForHtmlPart(23, 2)
invokeTag('link','g',493,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(24)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(25)
expressionOut.print(session.userName)
printHtmlPart(26)
createClosureForHtmlPart(27, 2)
invokeTag('link','g',504,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(28)
createClosureForHtmlPart(29, 2)
invokeTag('link','g',506,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(30)
createClosureForHtmlPart(31, 2)
invokeTag('link','g',520,['action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(33, 2)
invokeTag('link','g',521,['action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(34, 2)
invokeTag('link','g',522,['action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(35, 2)
invokeTag('link','g',523,['action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(36, 2)
invokeTag('link','g',524,['action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(37, 2)
invokeTag('link','g',525,['action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(38, 2)
invokeTag('link','g',526,['action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(39, 2)
invokeTag('link','g',527,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(40, 2)
invokeTag('link','g',528,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(41)
createTagBody(2, {->
printHtmlPart(42)
expressionOut.print(search)
printHtmlPart(43)
expressionOut.print(search)
printHtmlPart(44)
expressionOut.print(fillType)
printHtmlPart(45)
})
invokeTag('form','g',546,['controller':("resourceInfo"),'action':("categorySearch"),'method':("get"),'class':("navbar-form navbar-right hidden-xs sea"),'role':("search")],2)
printHtmlPart(46)
loop:{
int i = 0
for( resourceInfoInstance in (ResourceInfoList) ) {
printHtmlPart(47)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(48)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(49)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(50)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(51)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg'||resourceInfoInstance?.extName=='gif'||resourceInfoInstance?.extName=='bmp'||resourceInfoInstance?.extName=='ico')) {
printHtmlPart(52)
}
else if(true && (resourceInfoInstance?.extName=='doc'||resourceInfoInstance?.extName=='docx')) {
printHtmlPart(53)
}
else if(true && (resourceInfoInstance?.extName=='xls'||resourceInfoInstance?.extName=='xlsx')) {
printHtmlPart(54)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma'||resourceInfoInstance?.extName=='flac'||resourceInfoInstance?.extName=='ape')) {
printHtmlPart(55)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf')) {
printHtmlPart(56)
}
else if(true && (resourceInfoInstance?.extName=='pptx'||resourceInfoInstance?.extName=='ppt')) {
printHtmlPart(57)
}
else if(true && (resourceInfoInstance?.extName=='rar'||resourceInfoInstance?.extName=='zip'||resourceInfoInstance?.extName=='7z')) {
printHtmlPart(58)
}
else if(true && (resourceInfoInstance?.extName=='pdf')) {
printHtmlPart(59)
}
else if(true && (resourceInfoInstance?.extName=='txt')) {
printHtmlPart(60)
}
else if(true && (resourceInfoInstance?.extName=='torrent')) {
printHtmlPart(61)
}
else {
printHtmlPart(62)
}
printHtmlPart(63)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg')) {
printHtmlPart(64)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(65)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(66)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(67)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf')) {
printHtmlPart(68)
createTagBody(4, {->
printHtmlPart(69)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(66)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(68)
})
invokeTag('link','g',682,['controller':("resourceInfo"),'action':("showVideo"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(70)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma')) {
printHtmlPart(71)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(65)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(66)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(67)
}
else if(true && (resourceInfoInstance?.type=='other')) {
printHtmlPart(72)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(66)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(67)
}
else {
printHtmlPart(68)
createTagBody(4, {->
printHtmlPart(69)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(66)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(68)
})
invokeTag('link','g',690,['action':("show"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(70)
}
printHtmlPart(73)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(74)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(75)
expressionOut.print(resourceInfoInstance.name)
printHtmlPart(75)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(76)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(77)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(78)
expressionOut.print(fieldValue(bean: resourceInfoInstance, field: "fileSize"))
printHtmlPart(79)
expressionOut.print(resourceInfoInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(80)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(81)
expressionOut.print(raw(resourceInfoInstance.remark))
printHtmlPart(82)
i++
}
}
printHtmlPart(83)
invokeTag('createLink','g',836,['controller':("resourceInfo"),'action':("showLink")],-1)
printHtmlPart(84)
invokeTag('createLink','g',913,['action':("showMusic")],-1)
printHtmlPart(85)
invokeTag('createLink','g',929,['controller':("resourceInfo"),'action':("showImg")],-1)
printHtmlPart(86)
invokeTag('createLink','g',971,['controller':("resourceInfo"),'action':("pagination")],-1)
printHtmlPart(87)
})
invokeTag('captureBody','sitemesh',1039,[:],1)
printHtmlPart(88)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471908638397L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
