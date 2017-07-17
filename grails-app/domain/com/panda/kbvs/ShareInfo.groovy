package com.panda.kbvs
/**
 * 分享
 */
class ShareInfo {

    String id
    String name
    String content    //记录该分享中包含的文件夹Id,包含的文件id
    Date dateCreated
    String userId   //记录分享者
    String ownerId  //记录分享到哪个组群，如果不分享到文件夹就设为“public”

    static constraints = {

    }
    static mapping = {
        id generator: 'uuid.hex'
    }
}
