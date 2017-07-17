import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfootherPage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/otherPage.gsp" }
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
invokeTag('createLink','g',116,['controller':("shareInfo"),'action':("shares")],-1)
printHtmlPart(7)
invokeTag('createLink','g',206,['controller':("groupInfo"),'action':("myGroup")],-1)
printHtmlPart(8)
invokeTag('createLink','g',237,['controller':("shareInfo"),'action':("shareGroup")],-1)
printHtmlPart(9)
invokeTag('createLink','g',256,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(10)
invokeTag('createLink','g',280,['controller':("resourceInfo"),'action':("documentRelation")],-1)
printHtmlPart(11)
invokeTag('createLink','g',304,['controller':("resourceInfo"),'action':("download")],-1)
printHtmlPart(12)
invokeTag('createLink','g',314,['controller':("folderInfo"),'action':("reName")],-1)
printHtmlPart(13)
invokeTag('createLink','g',329,['controller':("resourceInfo"),'action':("singleFileDownload")],-1)
printHtmlPart(14)
invokeTag('createLink','g',333,['controller':("resourceInfo"),'action':("moveCommon")],-1)
printHtmlPart(15)
})
invokeTag('captureHead','sitemesh',433,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(18)
})
invokeTag('link','g',448,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(19)
createClosureForHtmlPart(20, 2)
invokeTag('link','g',453,['action':("nextIndex")],2)
printHtmlPart(21)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',454,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(23)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(24)
expressionOut.print(session.userName)
printHtmlPart(25)
createClosureForHtmlPart(26, 2)
invokeTag('link','g',465,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(27)
createClosureForHtmlPart(28, 2)
invokeTag('link','g',467,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(29)
createClosureForHtmlPart(30, 2)
invokeTag('link','g',481,['action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(31)
createClosureForHtmlPart(32, 2)
invokeTag('link','g',482,['action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(31)
createClosureForHtmlPart(33, 2)
invokeTag('link','g',483,['action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(31)
createClosureForHtmlPart(34, 2)
invokeTag('link','g',484,['action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(31)
createClosureForHtmlPart(35, 2)
invokeTag('link','g',485,['action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(31)
createClosureForHtmlPart(36, 2)
invokeTag('link','g',486,['action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(31)
createClosureForHtmlPart(37, 2)
invokeTag('link','g',487,['action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(31)
createClosureForHtmlPart(38, 2)
invokeTag('link','g',488,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(31)
createClosureForHtmlPart(39, 2)
invokeTag('link','g',489,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(40)
createTagBody(2, {->
printHtmlPart(41)
expressionOut.print(search)
printHtmlPart(42)
expressionOut.print(search)
printHtmlPart(43)
expressionOut.print(fillType)
printHtmlPart(44)
})
invokeTag('form','g',508,['controller':("resourceInfo"),'action':("categorySearch"),'method':("get"),'class':("navbar-form navbar-right hidden-xs sea"),'role':("search")],2)
printHtmlPart(45)
loop:{
int i = 0
for( resourceInfoInstance in (ResourceInfoList) ) {
printHtmlPart(46)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(47)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(48)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(49)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(50)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg'||resourceInfoInstance?.extName=='gif'||resourceInfoInstance?.extName=='bmp'||resourceInfoInstance?.extName=='ico')) {
printHtmlPart(51)
}
else if(true && (resourceInfoInstance?.extName=='doc'||resourceInfoInstance?.extName=='docx')) {
printHtmlPart(52)
}
else if(true && (resourceInfoInstance?.extName=='xls'||resourceInfoInstance?.extName=='xlsx')) {
printHtmlPart(53)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma'||resourceInfoInstance?.extName=='flac'||resourceInfoInstance?.extName=='ape')) {
printHtmlPart(54)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf')) {
printHtmlPart(55)
}
else if(true && (resourceInfoInstance?.extName=='pptx'||resourceInfoInstance?.extName=='ppt')) {
printHtmlPart(56)
}
else if(true && (resourceInfoInstance?.extName=='rar'||resourceInfoInstance?.extName=='zip'||resourceInfoInstance?.extName=='7z')) {
printHtmlPart(57)
}
else if(true && (resourceInfoInstance?.extName=='pdf')) {
printHtmlPart(58)
}
else if(true && (resourceInfoInstance?.extName=='txt')) {
printHtmlPart(59)
}
else if(true && (resourceInfoInstance?.extName=='torrent')) {
printHtmlPart(60)
}
else {
printHtmlPart(61)
}
printHtmlPart(62)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg')) {
printHtmlPart(63)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(64)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(65)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(66)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf')) {
printHtmlPart(67)
createTagBody(4, {->
printHtmlPart(68)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(65)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(67)
})
invokeTag('link','g',642,['controller':("resourceInfo"),'action':("showVideo"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(69)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma')) {
printHtmlPart(70)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(64)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(65)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(66)
}
else if(true && (resourceInfoInstance?.type=='other')) {
printHtmlPart(71)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(65)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(66)
}
else {
printHtmlPart(67)
createTagBody(4, {->
printHtmlPart(68)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(65)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(67)
})
invokeTag('link','g',650,['action':("show"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(69)
}
printHtmlPart(72)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(73)
expressionOut.print(resourceInfoInstance.name)
printHtmlPart(73)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(74)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(75)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(76)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(77)
expressionOut.print(fieldValue(bean: resourceInfoInstance, field: "fileSize"))
printHtmlPart(78)
expressionOut.print(resourceInfoInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(79)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(80)
expressionOut.print(raw(resourceInfoInstance.remark))
printHtmlPart(81)
i++
}
}
printHtmlPart(82)
invokeTag('createLink','g',795,['controller':("resourceInfo"),'action':("showLink")],-1)
printHtmlPart(83)
invokeTag('createLink','g',872,['action':("showMusic")],-1)
printHtmlPart(84)
invokeTag('createLink','g',887,['controller':("resourceInfo"),'action':("showImg")],-1)
printHtmlPart(85)
invokeTag('createLink','g',932,['controller':("resourceInfo"),'action':("pagination")],-1)
printHtmlPart(86)
})
invokeTag('captureBody','sitemesh',1000,[:],1)
printHtmlPart(87)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471909004089L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
