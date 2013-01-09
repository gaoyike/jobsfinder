package code.model

import net.liftweb.mapper._


class tech extends LongKeyedMapper[tech] with IdPK with CreatedTrait with UpdatedTrait{
  def getSingleton = tech
  object title extends MappedString(this, 140)  {
    override def displayName = "技术名称"
    override def validations = valMaxLen(140, "Title must be no longer than 140 characters!") _ :: valMinLen(2, "Title must be at least 2 characters!") _:: super.validations
  }
  object Job extends MappedLongForeignKey(this, Jobs)

}

object tech extends tech with LongKeyedMetaMapper[tech] with CRUDify[Long, tech]
