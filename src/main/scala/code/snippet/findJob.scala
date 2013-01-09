package code.snippet


import scala.xml.{NodeSeq, Text}
import net.liftweb.http._
import js.{JsCmds, JsCmd}
import net.liftweb.common._
import net.liftweb.util.Helpers._
import code.lib._
import net.liftweb._
import code.lib.util.Utils
import code.model.Jobs.position
import code.lib.util.Sessionvarscollection._
/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 9/25/12
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
class findJob {
  var location = "";
  var tmp = Utils.getStateList()
  var position = "";

  def process()={

  }
  def render= "#searchArea" #> SHtml.select(Utils.StatesSeq,Empty,(s:String)=>location=s)&
              "#searchposition" #> SHtml.select(Utils.PositonsSeq,Empty,(s:String)=>position=s)&
               "#hidden" #> SHtml.hidden(() => {
                 searchJobsByLocation.set(location)
                 searchJobsByPosition.set(position)

                 S.redirectTo("/jobTable.html")
               })
}


