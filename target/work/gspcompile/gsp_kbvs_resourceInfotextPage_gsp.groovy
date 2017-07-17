import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfotextPage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/textPage.gsp" }
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
invokeTag('createLink','g',76,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(6)
invokeTag('createLink','g',110,['controller':("shareInfo"),'action':("shares")],-1)
printHtmlPart(7)
invokeTag('createLink','g',197,['controller':("groupInfo"),'action':("myGroup")],-1)
printHtmlPart(8)
invokeTag('createLink','g',229,['controller':("shareInfo"),'action':("shareGroup")],-1)
printHtmlPart(9)
invokeTag('createLink','g',248,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(10)
invokeTag('createLink','g',270,['controller':("resourceInfo"),'action':("documentRelation")],-1)
printHtmlPart(11)
invokeTag('createLink','g',294,['controller':("resourceInfo"),'action':("download")],-1)
printHtmlPart(12)
invokeTag('createLink','g',299,['controller':("resourceInfo"),'action':("singleFileDownload")],-1)
printHtmlPart(13)
invokeTag('createLink','g',304,['controller':("resourceInfo"),'action':("moveCommon")],-1)
printHtmlPart(14)
})
invokeTag('captureHead','sitemesh',323,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(15)
createTagBody(2, {->
printHtmlPart(16)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(17)
})
invokeTag('link','g',335,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(18)
createClosureForHtmlPart(19, 2)
invokeTag('link','g',340,['action':("nextIndex")],2)
printHtmlPart(20)
createClosureForHtmlPart(21, 2)
invokeTag('link','g',341,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(22)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(23)
expressionOut.print(session.userName)
printHtmlPart(24)
createClosureForHtmlPart(25, 2)
invokeTag('link','g',352,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(26)
createClosureForHtmlPart(27, 2)
invokeTag('link','g',354,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(28)
createClosureForHtmlPart(29, 2)
invokeTag('link','g',369,['action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createClosureForHtmlPart(31, 2)
invokeTag('link','g',370,['action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createClosureForHtmlPart(32, 2)
invokeTag('link','g',371,['action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createClosureForHtmlPart(33, 2)
invokeTag('link','g',372,['action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createClosureForHtmlPart(34, 2)
invokeTag('link','g',373,['action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createClosureForHtmlPart(35, 2)
invokeTag('link','g',374,['action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createClosureForHtmlPart(36, 2)
invokeTag('link','g',375,['action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createClosureForHtmlPart(37, 2)
invokeTag('link','g',376,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createClosureForHtmlPart(38, 2)
invokeTag('link','g',377,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(39)
createTagBody(2, {->
printHtmlPart(40)
expressionOut.print(search)
printHtmlPart(41)
expressionOut.print(search)
printHtmlPart(42)
expressionOut.print(fillType)
printHtmlPart(43)
})
invokeTag('form','g',396,['controller':("resourceInfo"),'action':("categorySearch"),'method':("get"),'class':("navbar-form navbar-right hidden-xs sea"),'role':("search")],2)
printHtmlPart(44)
loop:{
int i = 0
for( resourceInfoInstance in (ResourceInfoList) ) {
printHtmlPart(45)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(46)
expressionOut.print(resourceInfoInstance.id)
printHtmlPart(47)
createTagBody(3, {->
printHtmlPart(48)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(49)
expressionOut.print(resource(dir: '',file: resourceInfoInstance.preImgPath))
printHtmlPart(50)
})
invokeTag('link','g',447,['action':("show"),'id':(resourceInfoInstance.id)],3)
printHtmlPart(51)
expressionOut.print(raw(resourceInfoInstance.remark))
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
invokeTag('createLink','g',592,['controller':("resourceInfo"),'action':("showLink")],-1)
printHtmlPart(57)
invokeTag('createLink','g',670,['action':("showMusic")],-1)
printHtmlPart(58)
invokeTag('createLink','g',680,['controller':("resourceInfo"),'action':("showImg")],-1)
printHtmlPart(59)
invokeTag('createLink','g',716,['controller':("resourceInfo"),'action':("pagination")],-1)
printHtmlPart(60)
})
invokeTag('captureBody','sitemesh',747,[:],1)
printHtmlPart(61)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471909057127L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
