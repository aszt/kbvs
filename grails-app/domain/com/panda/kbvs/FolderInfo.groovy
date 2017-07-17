package com.panda.kbvs
/**
 * 文件夹
 */
class FolderInfo {

    String id
    String name //文件夹名
    String beforeOwner  //原始所有者，做删除回收站时使用
    String ownerId  //所有者Id（该文件夹的上一级文件夹）
    Date dateCreated    //文件夹创建时间

    static constraints = {
        beforeOwner(nullable: true)
    }
    static mapping = {
        id generator: 'uuid.hex'
    }
    String toString(){
        return name
    }
}
