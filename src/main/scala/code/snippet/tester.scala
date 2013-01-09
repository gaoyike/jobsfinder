package code.snippet

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

class tester {
  case class provider (name:String,localtion:String)
  var providers:List[provider] = List(new provider("ATT","Ames"), new provider("VERIZON","BOONE"))
  def render=".provider-name-select" #> SHtml.multiSelectObj(providers.map(p => (p, p.name)), providers.headOption.toList,
  {(selected: List[provider]) =>
    selected.foreach(println);
  })

}
