package code.model
import net.liftweb.mapper._

import net.liftweb.http.SHtml
import net.liftweb.common._
import java.sql.Date
import net.liftweb.util.{FieldError, FieldIdentifier}
import scala.Predef._
import net.liftweb.common.Full

import code.lib.util.Utils
class Jobs extends LongKeyedMapper[Jobs] with IdPK with CreatedTrait with UpdatedTrait{
  def getSingleton = Jobs


  object title extends MappedString(this, 140)  {
    override def validations = valMaxLen(140, "Title must be no longer than 140 characters!") _ :: valMinLen(2, "Title must be at least 2 characters!") _:: super.validations
    override def displayName = "工作名称"
  }
  object CreatedUser extends MappedLongForeignKey(this, User){
    override def displayName = "创建者"
  }
  object position extends MappedEnum(this,Utils.Positions) {
    override def displayName= "职位名称"
  }
  object companyName extends MappedString(this, 140)  {
    override def validations = valMaxLen(140, "Title must be no longer than 140 characters!") _ :: valMinLen(2, "Title must be at least 2 characters!") _:: super.validations
    override def displayName = "公司名称"
  }
  object duration extends MappedString(this, 140){
    override def displayName = "工作时长"
    override def defaultValue = "无时长限制"
  }
  object location extends MappedEnum(this,Utils.getStateList()){
    override def displayName = "工作地点"
  }
  object city extends  MappedString(this, 140){
    override def displayName = "工作城市"
  }
  object req extends MappedTextarea(this, 2048){
    override def displayName = "工作描述"
  }
  object chas extends MappedTextarea(this, 2048){
    override def displayName = "技能需求"
  }
  object respons extends MappedTextarea(this, 2048){
    override def displayName = "工作责任"
  }

}

object Jobs extends Jobs with LongKeyedMetaMapper[Jobs] with CRUDify[Long, Jobs]{
  def findJobsinTop = Jobs.findAll(OrderBy(Jobs.createdAt, Ascending), MaxRows(10))
  def findJobsById(id:Long) = Jobs.findAll(By(Jobs.id,id))
  def findJobsByCurUser = Jobs.findAll(By(Jobs.CreatedUser,User.currentUser.open_!))

}