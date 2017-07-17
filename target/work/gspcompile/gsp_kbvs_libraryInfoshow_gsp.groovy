import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_libraryInfoshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/libraryInfo/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',13,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',14,[:],2)
printHtmlPart(4)
expressionOut.print(resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'css',file:'mobiscroll.2.13.2.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'css',file:'liMarquee.css'))
printHtmlPart(6)
invokeTag('javascript','g',17,['src':("mobiscroll.2.13.2.js")],-1)
printHtmlPart(2)
invokeTag('javascript','g',18,['src':("jquery.liMarquee.js")],-1)
printHtmlPart(7)
invokeTag('createLink','g',90,['controller':("libraryInfo"),'action':("deleteRes")],-1)
printHtmlPart(8)
invokeTag('createLink','g',126,['controller':("shareInfo"),'action':("shares")],-1)
printHtmlPart(9)
invokeTag('createLink','g',215,['controller':("groupInfo"),'action':("myGroup")],-1)
printHtmlPart(10)
invokeTag('createLink','g',248,['controller':("shareInfo"),'action':("shareGroup")],-1)
printHtmlPart(11)
invokeTag('createLink','g',273,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(12)
invokeTag('createLink','g',298,['controller':("resourceInfo"),'action':("download")],-1)
printHtmlPart(13)
invokeTag('createLink','g',306,['controller':("resourceInfo"),'action':("singleFileDownload")],-1)
printHtmlPart(14)
invokeTag('createLink','g',310,['controller':("resourceInfo"),'action':("moveCommon")],-1)
printHtmlPart(15)
})
invokeTag('captureHead','sitemesh',330,[:],1)
printHtmlPart(16)
createTagBody(1, {->
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(19)
})
invokeTag('link','g',347,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(20)
createClosureForHtmlPart(21, 2)
invokeTag('link','g',352,['controller':("resourceInfo"),'action':("nextIndex")],2)
printHtmlPart(22)
createClosureForHtmlPart(23, 2)
invokeTag('link','g',353,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(24)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(25)
expressionOut.print(session.userName)
printHtmlPart(26)
createClosureForHtmlPart(27, 2)
invokeTag('link','g',364,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(28)
createClosureForHtmlPart(29, 2)
invokeTag('link','g',366,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(30)
createClosureForHtmlPart(31, 2)
invokeTag('link','g',381,['controller':("resourceInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(33, 2)
invokeTag('link','g',382,['controller':("resourceInfo"),'action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(34, 2)
invokeTag('link','g',383,['controller':("resourceInfo"),'action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(35, 2)
invokeTag('link','g',384,['controller':("resourceInfo"),'action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(36, 2)
invokeTag('link','g',385,['controller':("resourceInfo"),'action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(37, 2)
invokeTag('link','g',386,['controller':("resourceInfo"),'action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(38, 2)
invokeTag('link','g',387,['controller':("resourceInfo"),'action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(39, 2)
invokeTag('link','g',388,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(40, 2)
invokeTag('link','g',389,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(41)
expressionOut.print(libraryInfo.resName)
printHtmlPart(42)
expressionOut.print(libraryInfo.resContent1)
printHtmlPart(43)
expressionOut.print(libraryInfo.resContent2)
printHtmlPart(44)
expressionOut.print(libraryInfo.resContent3)
printHtmlPart(45)
expressionOut.print(libraryInfo.resAuthor)
printHtmlPart(46)
if(true && (libraryInfo?.beginTime!=null)) {
printHtmlPart(47)
expressionOut.print(libraryInfo?.beginTime.format('yyyy-MM-dd'))
printHtmlPart(47)
}
printHtmlPart(48)
if(true && (libraryInfo?.endTime!=null)) {
printHtmlPart(49)
expressionOut.print(libraryInfo?.endTime.format('yyyy-MM-dd'))
printHtmlPart(47)
}
printHtmlPart(50)
expressionOut.print(libraryInfo.types.replace(","," "))
printHtmlPart(51)
expressionOut.print(libraryInfo.id)
printHtmlPart(52)
loop:{
int i = 0
for( resourceInfoInstance in (ResourceInfoList) ) {
printHtmlPart(53)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(54)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(55)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(56)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(57)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg'||resourceInfoInstance?.extName=='gif'||resourceInfoInstance?.extName=='bmp'||resourceInfoInstance?.extName=='ico')) {
printHtmlPart(58)
}
else if(true && (resourceInfoInstance?.extName=='doc'||resourceInfoInstance?.extName=='docx')) {
printHtmlPart(59)
}
else if(true && (resourceInfoInstance?.extName=='xls'||resourceInfoInstance?.extName=='xlsx')) {
printHtmlPart(60)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma'||resourceInfoInstance?.extName=='flac'||resourceInfoInstance?.extName=='ape')) {
printHtmlPart(61)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf')) {
printHtmlPart(62)
}
else if(true && (resourceInfoInstance?.extName=='pptx'||resourceInfoInstance?.extName=='ppt')) {
printHtmlPart(63)
}
else if(true && (resourceInfoInstance?.extName=='rar'||resourceInfoInstance?.extName=='zip'||resourceInfoInstance?.extName=='7z')) {
printHtmlPart(64)
}
else if(true && (resourceInfoInstance?.extName=='pdf')) {
printHtmlPart(65)
}
else if(true && (resourceInfoInstance?.extName=='txt')) {
printHtmlPart(66)
}
else if(true && (resourceInfoInstance?.extName=='torrent')) {
printHtmlPart(67)
}
else {
printHtmlPart(68)
}
printHtmlPart(69)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg')) {
printHtmlPart(70)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(71)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(72)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(73)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf')) {
printHtmlPart(74)
createTagBody(4, {->
printHtmlPart(75)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(72)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(74)
})
invokeTag('link','g',509,['controller':("resourceInfo"),'action':("showVideo"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(76)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma')) {
printHtmlPart(77)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(71)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(72)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(73)
}
else if(true && (resourceInfoInstance?.type=='other')) {
printHtmlPart(78)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(72)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(73)
}
else {
printHtmlPart(74)
createTagBody(4, {->
printHtmlPart(75)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(72)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(74)
})
invokeTag('link','g',520,['controller':("resourceInfo"),'action':("show"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(76)
}
printHtmlPart(79)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(80)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(81)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(82)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(83)
expressionOut.print(fieldValue(bean: resourceInfoInstance, field: "fileSize"))
printHtmlPart(84)
expressionOut.print(resourceInfoInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(85)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(86)
expressionOut.print(raw(resourceInfoInstance.remark))
printHtmlPart(87)
i++
}
}
printHtmlPart(88)
expressionOut.print(libraryInfo.name)
printHtmlPart(89)
expressionOut.print(libraryInfo.resName)
printHtmlPart(90)
expressionOut.print(libraryInfo.resContent1)
printHtmlPart(91)
expressionOut.print(libraryInfo.resContent2)
printHtmlPart(92)
expressionOut.print(libraryInfo.resContent3)
printHtmlPart(93)
expressionOut.print(libraryInfo.resAuthor)
printHtmlPart(94)
expressionOut.print(libraryInfo.beginTime)
printHtmlPart(95)
expressionOut.print(libraryInfo.endTime)
printHtmlPart(96)
invokeTag('createLink','g',660,['controller':("libraryInfo"),'action':("validate")],-1)
printHtmlPart(97)
invokeTag('createLink','g',679,['controller':("libraryInfo"),'action':("update")],-1)
printHtmlPart(98)
invokeTag('createLink','g',689,['controller':("libraryInfo"),'action':("show"),'id':(libraryInfo.id)],-1)
printHtmlPart(99)
invokeTag('createLink','g',846,['controller':("resourceInfo"),'action':("showLink")],-1)
printHtmlPart(100)
invokeTag('createLink','g',931,['controller':("resourceInfo"),'action':("showMusic")],-1)
printHtmlPart(101)
invokeTag('createLink','g',942,['controller':("resourceInfo"),'action':("showImg")],-1)
printHtmlPart(102)
})
invokeTag('captureBody','sitemesh',961,[:],1)
printHtmlPart(103)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471969818820L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
