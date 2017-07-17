import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfoshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/show.gsp" }
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
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1, maximum-scale=1")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("google"),'content':("notranslate")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',9,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(4)
expressionOut.print(resource(dir: 'css',file: 'bootstrap.css'))
printHtmlPart(5)
invokeTag('javascript','g',12,['src':("layer/layer.js")],-1)
printHtmlPart(4)
expressionOut.print(resource(dir: 'css',file: 'textshow.css'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'css', file: 'viewer.css'))
printHtmlPart(7)
invokeTag('javascript','g',16,['src':("compatibility.js")],-1)
printHtmlPart(8)
expressionOut.print(resource(dir: 'locale', file: 'locale.properties'))
printHtmlPart(9)
invokeTag('javascript','g',18,['src':("l10n.js")],-1)
printHtmlPart(2)
invokeTag('javascript','g',19,['src':("build/pdf.js")],-1)
printHtmlPart(2)
invokeTag('javascript','g',20,['src':("debugger.js")],-1)
printHtmlPart(2)
invokeTag('javascript','g',21,['src':("viewer.js")],-1)
printHtmlPart(10)
})
invokeTag('captureHead','sitemesh',23,[:],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(14)
})
invokeTag('link','g',36,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',41,['controller':("resourceInfo"),'action':("nextIndex")],2)
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',42,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(19)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(20)
expressionOut.print(session.userName)
printHtmlPart(21)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',54,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(23)
createClosureForHtmlPart(24, 2)
invokeTag('link','g',56,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(25)
expressionOut.print(resourceInfo.name)
printHtmlPart(26)
expressionOut.print(resourceInfo.remark)
printHtmlPart(27)
expressionOut.print(resource(dir: 'resourceInfo',file: 'show2'))
printHtmlPart(28)
expressionOut.print(resourceInfo.pdfPath)
printHtmlPart(29)
loop:{
int i = 0
for( resourceInfo in (ResourceList) ) {
printHtmlPart(30)
if(true && (resourceInfo?.extName=='jpg'||resourceInfo?.extName=='png'||resourceInfo?.extName=='jpeg'||resourceInfo?.extName=='gif'||resourceInfo?.extName=='bmp'||resourceInfo?.extName=='ico')) {
printHtmlPart(31)
}
else if(true && (resourceInfo?.extName=='doc'||resourceInfo?.extName=='docx')) {
printHtmlPart(32)
}
else if(true && (resourceInfo?.extName=='xls'||resourceInfo?.extName=='xlsx')) {
printHtmlPart(33)
}
else if(true && (resourceInfo?.extName=='mp3'||resourceInfo?.extName=='wav'||resourceInfo?.extName=='wma'||resourceInfo?.extName=='flac'||resourceInfo?.extName=='ape')) {
printHtmlPart(34)
}
else if(true && (resourceInfo?.extName=='avi'||resourceInfo?.extName=='3gp'||resourceInfo?.extName=='mp4'||resourceInfo?.extName=='rmvb'||resourceInfo?.extName=='mkv'||resourceInfo?.extName=='flv'||resourceInfo?.extName=='swf')) {
printHtmlPart(35)
}
else if(true && (resourceInfo?.extName=='pptx'||resourceInfo?.extName=='ppt')) {
printHtmlPart(36)
}
else if(true && (resourceInfo?.extName=='rar'||resourceInfo?.extName=='zip'||resourceInfo?.extName=='7z')) {
printHtmlPart(37)
}
else if(true && (resourceInfo?.extName=='pdf')) {
printHtmlPart(38)
}
else if(true && (resourceInfo?.extName=='txt')) {
printHtmlPart(39)
}
else if(true && (resourceInfo?.extName=='torrent')) {
printHtmlPart(40)
}
else {
printHtmlPart(41)
}
printHtmlPart(42)
if(true && (resourceInfo?.extName=='jpg'||resourceInfo?.extName=='png'||resourceInfo?.extName=='jpeg')) {
printHtmlPart(43)
expressionOut.print(resourceInfo.id)
printHtmlPart(44)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(46)
}
else if(true && (resourceInfo?.extName=='avi'||resourceInfo?.extName=='3gp'||resourceInfo?.extName=='mp4'||resourceInfo?.extName=='rmvb'||resourceInfo?.extName=='mkv'||resourceInfo?.extName=='flv'||resourceInfo?.extName=='swf')) {
printHtmlPart(47)
createTagBody(4, {->
printHtmlPart(48)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(47)
})
invokeTag('link','g',130,['controller':("resourceInfo"),'action':("showVideo"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(49)
}
else if(true && (resourceInfo?.extName=='mp3'||resourceInfo?.extName=='wav'||resourceInfo?.extName=='wma')) {
printHtmlPart(50)
expressionOut.print(resourceInfo.id)
printHtmlPart(44)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(46)
}
else if(true && (resourceInfo?.type=='other')) {
printHtmlPart(51)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(52)
}
else {
printHtmlPart(47)
createTagBody(4, {->
printHtmlPart(48)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(47)
})
invokeTag('link','g',141,['action':("show"),'id':(resourceInfo.id)],4)
printHtmlPart(49)
}
printHtmlPart(53)
if(true && (resourceInfo?.author=="")) {
printHtmlPart(54)
}
else {
printHtmlPart(48)
expressionOut.print(resourceInfo.author)
printHtmlPart(47)
}
printHtmlPart(55)
i++
}
}
printHtmlPart(56)
loop:{
int i = 0
for( resourceInfo in (resourceInfoList) ) {
printHtmlPart(57)
if(true && (resourceInfo?.extName=='jpg'||resourceInfo?.extName=='png'||resourceInfo?.extName=='jpeg'||resourceInfo?.extName=='gif'||resourceInfo?.extName=='bmp'||resourceInfo?.extName=='ico')) {
printHtmlPart(58)
}
else if(true && (resourceInfo?.extName=='doc'||resourceInfo?.extName=='docx')) {
printHtmlPart(59)
}
else if(true && (resourceInfo?.extName=='xls'||resourceInfo?.extName=='xlsx')) {
printHtmlPart(60)
}
else if(true && (resourceInfo?.extName=='mp3'||resourceInfo?.extName=='wav'||resourceInfo?.extName=='wma'||resourceInfo?.extName=='flac'||resourceInfo?.extName=='ape')) {
printHtmlPart(61)
}
else if(true && (resourceInfo?.extName=='avi'||resourceInfo?.extName=='3gp'||resourceInfo?.extName=='mp4'||resourceInfo?.extName=='rmvb'||resourceInfo?.extName=='mkv'||resourceInfo?.extName=='flv'||resourceInfo?.extName=='swf')) {
printHtmlPart(62)
}
else if(true && (resourceInfo?.extName=='pptx'||resourceInfo?.extName=='ppt')) {
printHtmlPart(63)
}
else if(true && (resourceInfo?.extName=='rar'||resourceInfo?.extName=='zip'||resourceInfo?.extName=='7z')) {
printHtmlPart(64)
}
else if(true && (resourceInfo?.extName=='pdf')) {
printHtmlPart(65)
}
else if(true && (resourceInfo?.extName=='txt')) {
printHtmlPart(66)
}
else if(true && (resourceInfo?.extName=='torrent')) {
printHtmlPart(67)
}
else {
printHtmlPart(68)
}
printHtmlPart(69)
if(true && (resourceInfo?.extName=='jpg'||resourceInfo?.extName=='png'||resourceInfo?.extName=='jpeg')) {
printHtmlPart(70)
expressionOut.print(resourceInfo.id)
printHtmlPart(44)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(71)
}
else if(true && (resourceInfo?.extName=='avi'||resourceInfo?.extName=='3gp'||resourceInfo?.extName=='mp4'||resourceInfo?.extName=='rmvb'||resourceInfo?.extName=='mkv'||resourceInfo?.extName=='flv'||resourceInfo?.extName=='swf')) {
printHtmlPart(72)
createTagBody(4, {->
printHtmlPart(73)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(72)
})
invokeTag('link','g',204,['controller':("resourceInfo"),'action':("showVideo"),'id':(resourceInfo.id)],4)
printHtmlPart(74)
}
else if(true && (resourceInfo?.extName=='mp3'||resourceInfo?.extName=='wav'||resourceInfo?.extName=='wma')) {
printHtmlPart(75)
expressionOut.print(resourceInfo.id)
printHtmlPart(44)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(71)
}
else if(true && (resourceInfo?.type=='other')) {
printHtmlPart(76)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(77)
}
else {
printHtmlPart(72)
createTagBody(4, {->
printHtmlPart(73)
expressionOut.print(raw(resourceInfo.name))
printHtmlPart(45)
expressionOut.print(resourceInfo.extName)
printHtmlPart(72)
})
invokeTag('link','g',215,['action':("show"),'id':(resourceInfo.id),'class':("widthnum")],4)
printHtmlPart(74)
}
printHtmlPart(78)
if(true && (resourceInfo?.author=="")) {
printHtmlPart(79)
}
else {
printHtmlPart(72)
expressionOut.print(resourceInfo.author)
printHtmlPart(74)
}
printHtmlPart(80)
i++
}
}
printHtmlPart(81)
invokeTag('createLink','g',289,['action':("showMusic")],-1)
printHtmlPart(82)
invokeTag('createLink','g',299,['controller':("resourceInfo"),'action':("showImg")],-1)
printHtmlPart(83)
})
invokeTag('captureBody','sitemesh',319,[:],1)
printHtmlPart(84)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471770317035L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
