package code.model

import net.liftweb.mapper._
import code.lib.util.Utils

class resume extends LongKeyedMapper[resume] with IdPK with CreatedTrait with UpdatedTrait {
  def getSingleton = resume ;
  object resumename extends MappedString(this, 140){
    override def displayName = "简历名称"

  }
  object CreatedUser extends MappedLongForeignKey(this, User){
    override def displayName = "创建者"
  }

  object fullname extends MappedString(this, 140){
    override def displayName = "全名"
  }
  object gender extends MappedEnum(this, Utils.Gender){
    override def displayName = "性别"
  }
  object birthday extends MappedString(this, 140){
    override def displayName = "生日日期"
  }
  object Edu extends MappedString(this, 140){
    override def displayName = "最高学历"
  }
  object yeargraduate extends MappedString(this, 140){
    override def displayName = "毕业时间"
  }
  object Major extends MappedString(this, 140){
    override def displayName = "专业"
  }
  object totalitexperience extends MappedString(this, 140){
    override def displayName = "总体IT经验"
  }
  object USitexperience extends MappedString(this, 140){
    override def displayName = "在美IT经验"
  }
  object currentworking extends MappedBoolean(this){
    override def displayName = "是否在职"
  }
  object currentposition extends MappedString(this, 140){
    override def displayName = "现在职位"
  }
  object currentsalary extends MappedString(this, 140){
    override def displayName = "现在年薪"
  }
  object phonenumber extends MappedString(this, 140){
    override def displayName = "电话号码"
  }
  object email extends MappedEmail(this, 140){
    override def displayName = "邮箱"
  }
  object currentlocation extends MappedEnum(this,Utils.States){
    override def displayName = "现在工作地点"
  }
  object city extends  MappedString(this, 140){
    override def displayName = "现在工作城市"
  }
  object preferlocation extends MappedEnum(this,Utils.States){
      override def displayName = "希望工作城市"
  }
  object anywhereinus extends MappedBoolean(this){
      override def displayName = "在美任何地点均可"
    }

  object parttime extends MappedBoolean(this){
    override def displayName = "是否兼职"
  }


  //resume by uploaded
  object resumeLink extends MappedString(this, 140){
    override def displayName = "简历链接"
  }
}


object resume extends resume with LongKeyedMetaMapper[resume] with CRUDify[Long, resume] {
  def findResumeByCurUser = resume.findAll(By(resume.CreatedUser,User.currentUser.open_!))
}