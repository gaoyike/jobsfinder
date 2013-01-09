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
import code.lib.util.Crossable.trav2Crossable
/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 10/2/12
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
class findingwork {
  var pos:List[String] = null
  var loc:List[String] = null
  def render = "#byPos" #> SHtml.multiSelect(Utils.PositonsSeq, Seq.empty[String],  s => pos=s )&
               "#byloc" #> SHtml.multiSelect(Utils.StatesSeq, Seq.empty[String],  s => loc=s )&
               "#hidden" #> SHtml.hidden(() => {
                var tmp =  (pos × loc).seq
                 searchJobsByPositionAndLocation.set(tmp)
                 S.redirectTo("/findWorkTable.html")
               })

}
