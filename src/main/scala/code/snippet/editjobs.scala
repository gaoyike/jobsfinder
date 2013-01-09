package code.snippet
import net.liftweb.http._
import js.JsCmds
import net.liftweb._
import scala.xml._
import common.Full
import mapper.By
import util._
import net.liftweb.http.provider.servlet.HTTPServletContext
import code.model.resume
import java.io._
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.model._
import code.lib.util._
/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 10/3/12
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
class editjobs extends LiftScreen{
  val pre = Jobs.find(By(Jobs.title, S.param("jobName").open_!)).open_!
  val title = field("Job Title: ", pre.title.is, trim)
  val position =  select("Position: ", pre.position.is.toString(), Utils.getPositionDisplay)
  val companyName = field("Company Name: ", pre.companyName.is, trim)
  val duration = field("Duration:  ", pre.duration.is, trim)
  val location = select("Location:  ", pre.position.is.toString, Utils.getLocationDisplay)
  val city = field("City:  ", pre.city.is,trim)
  val req = textarea("Requirements:  ", pre.req.is)
  val chas = textarea("Desired Characteristics:  ", pre.chas.is)
  val respons = textarea("Responsibilities:  ", pre.respons.is)
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
    val save = pre.CreatedUser(User.currentUser).chas(chas.is).city(city.is).companyName(companyName.is).title(title.is).position(PotoStre(position.is.toString)).duration(duration.is).req(req.is).respons(respons.is).location(EntoStr(location.is.toString)).save
    S.notice("工作已经修改")
  }
}
