import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfoimgPage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/imgPage.gsp" }
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
expressionOut.print(resource(dir: 'css',file: 'viewer.min.css'))
printHtmlPart(6)
invokeTag('javascript','g',10,['src':("viewer.min.js")],-1)
printHtmlPart(7)
invokeTag('createLink','g',83,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(8)
invokeTag('createLink','g',121,['controller':("shareInfo"),'action':("shares")],-1)
printHtmlPart(9)
invokeTag('createLink','g',211,['controller':("groupInfo"),'action':("myGroup")],-1)
printHtmlPart(10)
invokeTag('createLink','g',242,['controller':("shareInfo"),'action':("shareGroup")],-1)
printHtmlPart(11)
invokeTag('createLink','g',266,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(12)
invokeTag('createLink','g',292,['controller':("resourceInfo"),'action':("documentRelation")],-1)
printHtmlPart(13)
invokeTag('createLink','g',316,['controller':("resourceInfo"),'action':("download")],-1)
printHtmlPart(14)
invokeTag('createLink','g',323,['controller':("resourceInfo"),'action':("singleFileDownload")],-1)
printHtmlPart(15)
invokeTag('createLink','g',328,['controller':("resourceInfo"),'action':("moveCommon")],-1)
printHtmlPart(16)
})
invokeTag('captureHead','sitemesh',350,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(19)
})
invokeTag('link','g',362,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(20)
createClosureForHtmlPart(21, 2)
invokeTag('link','g',367,['action':("nextIndex")],2)
printHtmlPart(22)
createClosureForHtmlPart(23, 2)
invokeTag('link','g',368,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(24)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(25)
expressionOut.print(session.userName)
printHtmlPart(26)
createClosureForHtmlPart(27, 2)
invokeTag('link','g',379,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(28)
createClosureForHtmlPart(29, 2)
invokeTag('link','g',381,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(30)
createClosureForHtmlPart(31, 2)
invokeTag('link','g',395,['action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(33, 2)
invokeTag('link','g',396,['action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(34, 2)
invokeTag('link','g',397,['action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(35, 2)
invokeTag('link','g',398,['action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(36, 2)
invokeTag('link','g',399,['action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(37, 2)
invokeTag('link','g',400,['action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(38, 2)
invokeTag('link','g',401,['action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(39, 2)
invokeTag('link','g',402,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(32)
createClosureForHtmlPart(40, 2)
invokeTag('link','g',403,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
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
invokeTag('form','g',422,['controller':("resourceInfo"),'action':("categorySearch"),'method':("get"),'class':("navbar-form navbar-right hidden-xs sea"),'role':("search")],2)
printHtmlPart(46)
loop:{
int i = 0
for( resourceInfoInstance in (ResourceInfoList) ) {
printHtmlPart(47)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(48)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(49)
expressionOut.print(resource(dir:'',file: resourceInfoInstance.path))
printHtmlPart(50)
expressionOut.print(resource(dir: '',file: resourceInfoInstance.preImgPath))
printHtmlPart(51)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(52)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(53)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(54)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(55)
i++
}
}
printHtmlPart(56)
invokeTag('createLink','g',612,['controller':("resourceInfo"),'action':("showLink")],-1)
printHtmlPart(57)
invokeTag('createLink','g',692,['action':("showMusic")],-1)
printHtmlPart(58)
invokeTag('createLink','g',703,['controller':("resourceInfo"),'action':("showImg")],-1)
printHtmlPart(59)
invokeTag('createLink','g',749,['controller':("resourceInfo"),'action':("pagination")],-1)
printHtmlPart(60)
})
invokeTag('captureBody','sitemesh',787,[:],1)
printHtmlPart(61)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471922294560L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
