package code.snippet
import net.liftweb.http._
import net.liftweb._
import common.Full
import util._
import net.liftweb.http.provider.servlet.HTTPServletContext
import code.model.resume
import java.io._
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.model._
import code.lib.util._
class postjobs extends LiftScreen{
  override protected def hasUploadField = true
  val title = field("Job Title: ", "", trim)
  val position =  select("Position: ", "Java Developer", Utils.getPositionDisplay)
  val companyName = field("Company Name: ", "", trim)
  val duration = field("Duration:  ", "无限期", trim)
  val location = select("Location:  ", "Alabama", Utils.getLocationDisplay)
  val city = field("City:  ", "", trim)
  val req = textarea("Requirements: ", "")
  val chas = textarea("Desired Characteristics: ", "")
  val respons = textarea("Responsibilities:  ", "")
  def EntoStr(cur:String): Utils.States.Value ={
    for(i <- 1 to 51){
      if(cur  == Utils.getStateList()(i).toString)
        return  Utils.States(i)
    }
    return null;
  }
  def PotoStre(cur:String): Utils.Positions.Value={
    for(i <- 1 to 33){
      if(cur  == Utils.getPositionList()(i).toString)
        return  Utils.Positions(i)
    }
    return null;
  }
  protected def finish() {
    val save = Jobs.create.CreatedUser(User.currentUser).chas(chas.is).city(city.is).companyName(companyName.is).title(title.is).position(PotoStre(position.is.toString)).duration(duration.is).req(req.is).respons(respons.is).location(EntoStr(location.is.toString)).save
    S.notice("工作已经发布")
  }
}