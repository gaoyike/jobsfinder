package code.comet

/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 9/24/12
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
import net.liftweb._
import http._
import js.JsCmds
import SHtml._
import net.liftweb.common.{Box, Full}
import net.liftweb.util._
import net.liftweb.actor._
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds.{SetHtml}
import net.liftweb.http.js.JE.Str
import code.model.{resume, Jobs}
import xml.NodeSeq

class hotjobs  extends CometActor {
  private var jobs:List[Jobs] = Nil

  def render = "#hot" #> <table class="table table-striped">{jobs.flatMap(jobsExtrator _)}</table>

  def jobsExtrator(j:Jobs):NodeSeq=
    <tr>
      <td>{modifyDate(j.createdAt.toString())}</td>
      <td>{j.title}</td>
      <td>{j.companyName}</td>
      <td>{j.location}</td>
      <td style="text-align: right;"><button type="button" datatoggle="button" onClick={JsCmds.RedirectTo("/jobDetail.html?jobid="+j.id)}>立即申请</button></td>
    </tr>

  def modifyDate(s:String):String={
    val b = s.split(" ")
    return  b(1)+"-"+b(2)+"-"+b(3)
  }


  ActorPing.schedule(this, Message, 10000L)

  override def lowPriority = {
    case Message => {
      jobs = Jobs.findJobsinTop
      ActorPing.schedule(this, Message, 10000L)
      reRender()
    }
  }
}
case object Message
