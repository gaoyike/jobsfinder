package code.snippet

/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 9/24/12
 * Time: 11:30 PM
 * To change this template use File | Settings | File Templates.
 */
import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import code.lib._
import Helpers._
import _root_.scala.xml.{NodeSeq,Text}
import net.liftweb.http.{Templates, SHtml, S, DispatchSnippet}
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.http.js.jquery.JqJsCmds.ModalDialog
import net.liftweb.http.js.JE.{Str, JsVar, JsVal, JsObj}
import net.liftweb.http.js.{JsCmds, JsExp}
import code.model._
import  net.liftweb.mapper._
object jobDetail {



  def render = {
    var id = S.param("jobid").open_!
    val theJob = Jobs.findJobsById(id.toLong).head
    def checkingUserType:CssSel = {
      if(User.currentUser.open_!.userType.is.id == 2){
       "#apply [onClick]" #> SHtml.ajaxButton("返回主页",()=>JsCmds.RedirectTo("/"))
      }
      else
      {
        "#apply [onClick]" #> SHtml.ajaxInvoke(()=>JsCmds.RedirectTo("/jobapply.html?jobid="+theJob.id))
      }
    }

    "#title" #>  <p><strong>{theJob.title}</strong></p> &
      "#location" #> <p>{ theJob.location}</p> &
      "#po" #> <p>{ theJob.position}</p> &
      "#duration" #> <p>{theJob.duration}</p> &
      "#req" #> <p>{theJob.req}</p> &
      "#cha" #> <p>{theJob.chas}</p> &
      "#resp" #> <p>{theJob.respons}</p> & checkingUserType

  }

}
