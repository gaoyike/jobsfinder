package code.snippet
import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import code.lib._
import Helpers._
import code.lib.util._
import _root_.scala.xml.{NodeSeq,Text}
import net.liftweb.http.{Templates, SHtml, S, DispatchSnippet}
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.http.js.jquery.JqJsCmds.ModalDialog
import net.liftweb.http.js.JE.{Str, JsVar, JsVal, JsObj}
import net.liftweb.http.js.{JsCmds, JsExp}
import code.model._
import net.liftweb.sitemap.Loc.If

/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 9/16/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
class userInfo {
  def firstName =User.currentUser.map(_.firstName) openOr ""
  def lastName = User.currentUser.map(_.lastName) openOr ""
  def typeofUser = User.currentUser.map(_.userType) openOr ""
  def companyName = User.currentUser.map(_.companyName) openOr ""
  def companyUserlogged() : NodeSeq=
    <div style="text-align:center" >
      <h3>欢迎你 {firstName} {lastName} </h3>
      <p>用户类型: {typeofUser}</p>
      <p>公司名称: {companyName}</p>
      <a href="/postjobs">发布工作</a>
      <a href="/myjobs">修改工作</a>
    </div>

  def logged() : NodeSeq =
    <div style="text-align:center" >
      <h3>欢迎你 {firstName} {lastName}</h3>
      <p>用户类型: {typeofUser}</p>
      <a href="/wrtingresume">提交简历</a>
      <a href="/myresumes">修改简历</a>
    </div>

  def unlogged(): NodeSeq = <div style="text-align:center" >
    <h3>请登录您的账号</h3>
    <a href="/user_mgt/login" class="btn btn-primary">登录</a>
  </div>

  def render(in: NodeSeq): NodeSeq ={if(User.loggedIn_?){
       if(User.currentUser.open_!.userType.is.id == 2)
     companyUserlogged
   else
     logged
 }
  else
   unlogged
 }





}
