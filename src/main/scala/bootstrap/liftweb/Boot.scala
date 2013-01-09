package bootstrap.liftweb


import net.liftweb.util.Mailer._
import javax.mail.{Authenticator,PasswordAuthentication}
import net.liftweb.http.LiftRules
import net.liftweb._
import common.Full
import common.Full
import http.Html5Properties
import http.Html5Properties
import http.ParsePath
import http.RewriteRequest
import mongodb.MongoDB
import sitemap.Loc.LocGroup
import util._
import Helpers._
import common._
import http._
import js.jquery.JqJsCmds.FadeIn
import sitemap._
import Loc._
import mapper._
import net.liftweb.squerylrecord.RecordTypeMode._
import net.liftmodules.FoBo._
import net.liftmodules.{JQueryModule, FoBo}
import code.model._
import code.snippet._
import com.mongodb.Mongo

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def displaySometimes_? : Boolean =
    (millis / 1000L / 60L) % 2 == 0
  def boot {

    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor =
        new StandardDBVendor("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/lift?useUnicode=true&characterEncoding=UTF-8", Full("root"), Full(""))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }
    Schemifier.schemify(true, Schemifier.infoF _, User, Jobs, resume, tech)
    S.addAround(DB.buildLoanWrapper)

    //mongodbConfig.init()

    // init auth-mongo
    //  MongoAuth.siteName.default.set("lift-mongo-app")
    //  MongoAuth.systemEmail.default.set("info@") // TODO: Set me
    // MongoAuth.systemUsername.default.set("lift-mongo-app Staff")


    // where to search snippet
    LiftRules.addToPackages("code")
    //We skip the FoBo built in JQuery in favor for the FoBo included lift-jquery-module
    //FoBo.InitParam.JQuery=FoBo.JQuery171  
    FoBo.InitParam.ToolKit = FoBo.Bootstrap210
    FoBo.InitParam.ToolKit = FoBo.JQueryMobile110
    FoBo.InitParam.ToolKit = FoBo.PrettifyJun2011
    FoBo.init()

    //mailer
    Mailer.authenticator = for {
      user <- Props.get("mail.user")
      pass <- Props.get("mail.password")
    } yield new Authenticator {
        override def getPasswordAuthentication =
          new PasswordAuthentication(user,pass)
      }
    //download things
    LiftRules.passNotFoundToChain = true

    LiftRules.liftRequest.append {
      case Req("uploads" :: _, _, _) => false
    }



    // notice fadeout
    LiftRules.noticesAutoFadeOut.default.set((noticeType: NoticeType.Value) => Full((1 seconds, 2 seconds)))

    LiftRules.addToPackages("com.damianhelme.tbutils")
    val MustBeLoggedIn = If(() => User.loggedIn_?, "")
    def userLinkText = User.currentUser.map(_.shortName).openOr("not logged in").toString


    // Build SiteMap
    val entries = List(
      Menu("Home") / "index" >> LocGroup("main"),
      Menu("找工作") / "findingwork" >> LocGroup("main"),
      Menu("写简历") / "wrtingresume" >> LocGroup("main"),
      Menu("高级人才招聘") / "gaoji" >> LocGroup("main"),
      Menu("新毕业生求职") / "xinbiye" >> LocGroup("main"),
      Menu("IT职业培训指导") / "itpeixun" >> LocGroup("main"),
      Menu("IT行业动态") / "dongtai" >> LocGroup("main"),
      Menu.i("myresumes") / "myresumes",
      Menu.i("findWorkTable") / "findWorkTable",
      Menu.i("editResume") / "editResume",
      Menu.i("jobDetail") / "jobDetail",
      Menu.i("postjobs")/ "postjobs",
      Menu.i("editjobs")/ "editjobs",
      Menu.i("myjobs")/ "myjobs",
      Menu.i("sendMail") / "sendMail",
      Menu.i("jobapply") / "jobapply",
      Menu.i("jobTable") /"jobTable",
      Menu("IT求职论坛") / "luntan" >> LocGroup("main"),
      Menu("管理员") / "dummyAdmin" >> MustBeLoggedIn >> LocGroup("main") >> PlaceHolder submenus tech.menus ::: resume.menus ::: Jobs.menus,
      User.loginMenuLoc.open_!,
      User.lostPasswordMenuLoc.open_!,
      User.createUserMenuLoc.open_!,
      Menu("user", userLinkText) / "#" >>
        MustBeLoggedIn >> LocGroup("user") >> PlaceHolder submenus(
        User.logoutMenuLoc.open_!,
        User.editUserMenuLoc.open_!,
        User.changePasswordMenuLoc.open_!
        )
    )




    def sitemap = SiteMap(entries: _*)
    SiteMap.enforceUniqueLinks = false
    LiftRules.setSiteMap(sitemap)

    //  LiftRules.unloadHooks.append(() => MongoDB.close)

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    // What is the function to test if a user is logged in?
    LiftRules.loggedInTest = Full(() => User.loggedIn_?)
    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    //Modal window using jquery's UIblock


  }
}
