import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfoindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/index.gsp" }
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
invokeTag('createLink','g',14,['controller':("resourceInfo"),'action':("upload")],-1)
printHtmlPart(6)
expressionOut.print(FolderInfoId)
printHtmlPart(7)
invokeTag('createLink','g',28,['controller':("resourceInfo"),'action':("upload")],-1)
printHtmlPart(8)
invokeTag('createLink','g',44,['controller':("resourceInfo"),'action':("findResourcesByFolder"),'id':(FolderInfoId)],-1)
printHtmlPart(9)
invokeTag('createLink','g',68,['controller':("folderInfo"),'action':("newFolders")],-1)
printHtmlPart(10)
invokeTag('createLink','g',192,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(11)
invokeTag('createLink','g',230,['controller':("shareInfo"),'action':("shares")],-1)
printHtmlPart(12)
invokeTag('createLink','g',320,['controller':("groupInfo"),'action':("myGroup")],-1)
printHtmlPart(13)
invokeTag('createLink','g',351,['controller':("shareInfo"),'action':("shareGroup")],-1)
printHtmlPart(14)
invokeTag('createLink','g',370,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(15)
invokeTag('createLink','g',392,['controller':("resourceInfo"),'action':("documentRelation")],-1)
printHtmlPart(16)
invokeTag('createLink','g',416,['controller':("resourceInfo"),'action':("download")],-1)
printHtmlPart(17)
invokeTag('createLink','g',427,['controller':("resourceInfo"),'action':("open")],-1)
printHtmlPart(18)
invokeTag('createLink','g',441,['controller':("folderInfo"),'action':("reName")],-1)
printHtmlPart(19)
invokeTag('createLink','g',458,['controller':("resourceInfo"),'action':("singleFileDownload")],-1)
printHtmlPart(20)
invokeTag('createLink','g',463,['controller':("resourceInfo"),'action':("moveCommon")],-1)
printHtmlPart(21)
})
invokeTag('captureHead','sitemesh',560,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(22)
createTagBody(2, {->
printHtmlPart(23)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(24)
})
invokeTag('link','g',577,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(25)
createClosureForHtmlPart(26, 2)
invokeTag('link','g',582,['action':("nextIndex")],2)
printHtmlPart(27)
createClosureForHtmlPart(28, 2)
invokeTag('link','g',583,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(29)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(30)
expressionOut.print(session.userName)
printHtmlPart(31)
createClosureForHtmlPart(32, 2)
invokeTag('link','g',594,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(33)
createClosureForHtmlPart(34, 2)
invokeTag('link','g',596,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(35)
createClosureForHtmlPart(36, 2)
invokeTag('link','g',610,['action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(37)
createClosureForHtmlPart(38, 2)
invokeTag('link','g',611,['action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(37)
createClosureForHtmlPart(39, 2)
invokeTag('link','g',612,['action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(37)
createClosureForHtmlPart(40, 2)
invokeTag('link','g',613,['action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(37)
createClosureForHtmlPart(41, 2)
invokeTag('link','g',614,['action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(37)
createClosureForHtmlPart(42, 2)
invokeTag('link','g',615,['action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(37)
createClosureForHtmlPart(43, 2)
invokeTag('link','g',616,['action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(37)
createClosureForHtmlPart(44, 2)
invokeTag('link','g',617,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(37)
createClosureForHtmlPart(45, 2)
invokeTag('link','g',618,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(46)
createTagBody(2, {->
printHtmlPart(47)
expressionOut.print(search)
printHtmlPart(48)
expressionOut.print(search)
printHtmlPart(49)
expressionOut.print(nextLoadingNumber)
printHtmlPart(50)
expressionOut.print(FolderInfoId)
printHtmlPart(51)
expressionOut.print(nowPage)
printHtmlPart(52)
})
invokeTag('form','g',664,['controller':("resourceInfo"),'action':("find"),'method':("get"),'class':("navbar-form navbar-right hidden-xs sea"),'role':("search")],2)
printHtmlPart(53)
createClosureForHtmlPart(54, 2)
invokeTag('link','g',667,['action':("nextIndex")],2)
printHtmlPart(55)
loop:{
int i = 0
for( folderList in (FolderList) ) {
printHtmlPart(56)
createTagBody(3, {->
printHtmlPart(57)
expressionOut.print(folderList.name)
printHtmlPart(58)
})
invokeTag('link','g',676,['action':("findResourcesByFolder"),'id':(folderList.id)],3)
printHtmlPart(59)
i++
}
}
printHtmlPart(60)
createTagBody(2, {->
printHtmlPart(61)
expressionOut.print(FolderInfoId)
printHtmlPart(62)
})
invokeTag('form','g',743,['style':("float: left;")],2)
printHtmlPart(63)
createClosureForHtmlPart(64, 2)
invokeTag('link','g',766,['action':("findResourcesByFolder"),'class':("thisa")],2)
printHtmlPart(65)
loop:{
int i = 0
for( folderInfoListInstance in (FolderInfoList) ) {
printHtmlPart(66)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(67)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(68)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(69)
createTagBody(3, {->
printHtmlPart(70)
expressionOut.print(fieldValue(bean: folderInfoListInstance, field: "name"))
printHtmlPart(71)
})
invokeTag('link','g',831,['action':("findResourcesByFolder"),'id':(folderInfoListInstance.id)],3)
printHtmlPart(72)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(73)
expressionOut.print(folderInfoListInstance.name)
printHtmlPart(74)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(75)
expressionOut.print(fieldValue(bean: folderInfoListInstance, field: ""))
printHtmlPart(76)
expressionOut.print(folderInfoListInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(77)
i++
}
}
printHtmlPart(78)
loop:{
int i = 0
for( resourceInfoInstance in (ResourceInfoList) ) {
printHtmlPart(79)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(80)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(81)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(82)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(83)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg'||resourceInfoInstance?.extName=='gif'||resourceInfoInstance?.extName=='bmp'||resourceInfoInstance?.extName=='ico')) {
printHtmlPart(84)
}
else if(true && (resourceInfoInstance?.extName=='doc'||resourceInfoInstance?.extName=='docx')) {
printHtmlPart(85)
}
else if(true && (resourceInfoInstance?.extName=='xls'||resourceInfoInstance?.extName=='xlsx')) {
printHtmlPart(86)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma'||resourceInfoInstance?.extName=='flac'||resourceInfoInstance?.extName=='ape')) {
printHtmlPart(87)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf')) {
printHtmlPart(88)
}
else if(true && (resourceInfoInstance?.extName=='pptx'||resourceInfoInstance?.extName=='ppt')) {
printHtmlPart(89)
}
else if(true && (resourceInfoInstance?.extName=='rar'||resourceInfoInstance?.extName=='zip'||resourceInfoInstance?.extName=='7z')) {
printHtmlPart(90)
}
else if(true && (resourceInfoInstance?.extName=='pdf')) {
printHtmlPart(91)
}
else if(true && (resourceInfoInstance?.extName=='txt')) {
printHtmlPart(92)
}
else if(true && (resourceInfoInstance?.extName=='torrent')) {
printHtmlPart(93)
}
else {
printHtmlPart(94)
}
printHtmlPart(95)
if(true && (resourceInfoInstance?.extName=='jpg'||resourceInfoInstance?.extName=='png'||resourceInfoInstance?.extName=='jpeg')) {
printHtmlPart(96)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(97)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(98)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(99)
}
else if(true && (resourceInfoInstance?.extName=='avi'||resourceInfoInstance?.extName=='3gp'||resourceInfoInstance?.extName=='mp4'||resourceInfoInstance?.extName=='rmvb'||resourceInfoInstance?.extName=='mkv'||resourceInfoInstance?.extName=='flv'||resourceInfoInstance?.extName=='swf')) {
printHtmlPart(100)
createTagBody(4, {->
printHtmlPart(101)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(98)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(100)
})
invokeTag('link','g',907,['controller':("resourceInfo"),'action':("showVideo"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(71)
}
else if(true && (resourceInfoInstance?.extName=='mp3'||resourceInfoInstance?.extName=='wav'||resourceInfoInstance?.extName=='wma'||resourceInfoInstance?.extName=='flac'||resourceInfoInstance?.extName=='ape')) {
printHtmlPart(102)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(97)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(98)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(99)
}
else if(true && (resourceInfoInstance?.type=='other')) {
printHtmlPart(103)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(98)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(99)
}
else {
printHtmlPart(100)
createTagBody(4, {->
printHtmlPart(101)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(98)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(100)
})
invokeTag('link','g',915,['action':("show"),'id':(resourceInfoInstance.id)],4)
printHtmlPart(71)
}
printHtmlPart(72)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(73)
expressionOut.print(resourceInfoInstance.name)
printHtmlPart(73)
expressionOut.print(resourceInfoInstance.extName)
printHtmlPart(74)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(104)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(105)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(106)
expressionOut.print(fieldValue(bean: resourceInfoInstance, field: "fileSize"))
printHtmlPart(107)
expressionOut.print(resourceInfoInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(108)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(109)
expressionOut.print(raw(resourceInfoInstance.remark))
printHtmlPart(110)
i++
}
}
printHtmlPart(111)
invokeTag('createLink','g',1072,['controller':("resourceInfo"),'action':("showLink")],-1)
printHtmlPart(112)
invokeTag('createLink','g',1140,['action':("showMusic")],-1)
printHtmlPart(113)
invokeTag('createLink','g',1145,['controller':("resourceInfo"),'action':("showImg")],-1)
printHtmlPart(114)
invokeTag('createLink','g',1198,['controller':("resourceInfo"),'action':("rollingLoad")],-1)
printHtmlPart(115)
})
invokeTag('captureBody','sitemesh',1306,[:],1)
printHtmlPart(116)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471911660187L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
