package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.sitemap.Loc.LocGroup
import net.liftweb.sitemap.Loc.LocParam
import net.liftweb.sitemap.Loc.Template
import lib.util.Utils.userTypes


object User extends User with MetaMegaProtoUser[User]{
  override def dbTableName = "users" // define the DB table name
  override def screenWrap = Full(<lift:surround with="default" at="content">
      <lift:bind /></lift:surround>)
  override def fieldOrder = List(firstName,
    lastName,userType,
    email,
    password)

  // comment this line out to require email validations
  override def skipEmailValidation = true

  override def loginMenuLocParams = LocGroup("user") :: super.loginMenuLocParams
  override def createUserMenuLocParams = LocGroup("user") :: super.createUserMenuLocParams
  override def resetPasswordMenuLocParams = LocGroup("user")::super.resetPasswordMenuLocParams
  override def signupFields: List[FieldPointerType] = List(firstName,
    lastName,userType,companyName,
    email,
    password)
}


class User extends MegaProtoUser[User]{
  override def firstNameDisplayName = "名字"
  override def lastNameDisplayName = "姓"
  override def emailDisplayName = "邮箱"
  override def passwordDisplayName = "密码"

  def getSingleton = User // what's the "meta" server


  object userType extends MappedEnum(this, userTypes)
  {
    override def displayName= "用户类型"
  }
  object companyName extends MappedString(this,140)
  {
    override def displayName= "企业名称(企业用户)"
  }
}

