package com.panda.kbvs
/**
 * 组群
 */
class GroupInfo {

    String id
    String imgPath
    String name
    String members  //组群中的成员
    String creatorId    //组群的创建者
    String content  //记录组群中的分享id
    static constraints = {
         imgPath(blank: true,nullable: true)
         content(blank: true,nullable: true)
    }
    static mapping = {
        id generator: 'uuid.hex'
    }
}
