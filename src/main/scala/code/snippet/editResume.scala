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
class editResume extends LiftScreen{
  override protected def hasUploadField = true
  val pre = resume.find(By(resume.resumename, S.param("resumeName").open_!)).open_!
  val resumeName = field("简历名称:  ", pre.resumename.is, trim)
  val fullName =  field("Full Name:  ", pre.fullname.is, trim)
  val gender = select("Gender",pre.gender.is.toString,List("Male","Female"))
  val birthday = field("Birthday:  ", pre.birthday.is, trim)
  val Edu = field("Education:  ", pre.Edu.is, trim)
  val grad = field("Year Graduate:  ", pre.yeargraduate.is, trim)
  val major = field("Major:  ", pre.Major.is, trim)
  val itExp = field("Total IT Experience:  ", pre.totalitexperience.is, trim)
  val USitExp = field("US IT Experience:  ", pre.USitexperience.is, trim)
  val curworking = select("Currently Working: ", pre.currentworking.is, List("Yes", "No"))
  val curPosition = field("Current Position:  ", pre.currentposition.is, trim)
  val curSalary = field("Current Salary:  ", pre.currentsalary.is, trim)
  val phoneNum = field("Phone Number: ",pre.phonenumber.is,trim)
  val email = field("Email: ",pre.email.is,trim)
  val curLocation = select("Current Location: ",pre.currentlocation.is.toString,Utils.getLocationDisplay)
  val city = field("City: ",pre.city.is,trim)
  val Relocationpre = select("Relocation Preference: ", pre.preferlocation.is.toString,Utils.getLocationDisplay)
  val Anywhere = select("Anywhere in the US: ", pre.anywhereinus.is, List("Yes", "No"))
  val parttime = select("Consider Parttime: ", pre.parttime.is, List("Yes", "No"))
  val yourresume = new Field {
      type ValueType = String
      override def name = "Resume"
      override implicit def manifest = buildIt[String]
      override def default = ""
      override def toForm: Box[NodeSeq] = SHtml.link("uploads/"+pre.resumeLink.is,()=>{}, <span>Resume</span>)
    }
    //SHtml.link("resume",()=>JsCmds.RedirectTo("/uploads"+pre.resumeLink.is), <span>Resume</span>)
  val resumeupload = makeField[Array[Byte], Nothing]("Upload Resume:  ", new Array[Byte](0),
    field => SHtml.fileUpload(fph => storeFile(fph)),
    NothingOtherValueInitializer)
  var filename = ""
  def storeFile (file : FileParamHolder): Box[File] =
  {
    getBaseApplicationPath match
    {
      case Full(appBasePath) =>
      {
        val d:Date = new Date()
        filename = d.getTime+file.fileName
        var uploadDir = new File(appBasePath + "uploads")
        val uploadingFile = new File(uploadDir, filename)

        var output = new FileOutputStream(uploadingFile)
        try
        {
          output.write(file.file)
        }
        catch
          {
            case e => println(e)
          }
        finally
        {
          output.close
          output = null
        }

        Full(uploadingFile)
      }
      case _ => Empty
    }
  }
  def getBaseApplicationPath: Box[String] =
  {
    LiftRules.context match
    {
      case context: HTTPServletContext =>
      {
        var baseApp: String = context.ctx.getRealPath("/")

        if(!baseApp.endsWith(File.separator))
          baseApp = baseApp + File.separator

        Full(baseApp)
      }
      case _ => Empty
    }
  }



  protected def finish() {
    def getAnywhere:Boolean = {
      if(Anywhere.is == "Yes")
        return true
      else
        return false
    }
    def getPartTime:Boolean = {
      if(parttime.is == "Yes")
        return true
      else
        return false
    }

    def EntoStr(cur:String): Utils.States.Value ={
      for(i <- 1 to 51){
        if(cur  == Utils.getStateList()(i).toString)
          return  Utils.States(i)
      }
      return null;
    }
    def getGender(cur:String): Utils.Gender.Value ={
      for(i <- 1 to 51){
        if(cur  == Utils.getGenderList()(i).toString)
          return  Utils.Gender(i)
      }
      return null;
    }
    def getCurrentWorking:Boolean ={
      if (curworking.is == "Yes")
        return true
      else
        return false
    }
    val save = pre.resumename(resumeName.is).anywhereinus(getAnywhere).birthday(birthday.is).city(city.is).currentlocation(EntoStr(curLocation.is)).currentposition(curPosition.is).currentsalary(curSalary.is).Edu(Edu.is)
      .email(email.is).fullname(fullName.is).currentworking(getCurrentWorking).gender(getGender(gender.is)).Major(major.is).phonenumber(phoneNum.is).preferlocation(EntoStr(Relocationpre.is)).parttime(getPartTime).USitexperience(USitExp.is).totalitexperience(itExp.is)
      .yeargraduate(grad.is).CreatedUser(User.currentUser).resumeLink(filename).save

    S.notice("您的简历已经制作成功.")
  }
}
