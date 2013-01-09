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
class writingresume extends LiftScreen{
  override protected def hasUploadField = true

  val resumeName = field("Resume Name:  ", "", trim)
  val fullName =  field("Full Name:  ", "", trim)
  val gender = select("Gender","Male",List("Male","Female"))
  val birthday = field("Birthday:  ", "", trim)
  val Edu = field("Education:  ", "", trim)
  val grad = field("Year Graduate:  ", "", trim)
  val major = field("Major:  ", "", trim)
  val itExp = field("Total IT Experience:  ", "", trim)
  val USitExp = field("US IT Experience:  ", "", trim)
  val curworking = select("Currently Working: ", "No", List("Yes", "No"))
  val curPosition = field("Current Position:  ", "", trim)
  val curSalary = field("Current Salary:  ", "", trim)
  val phoneNum = field("Phone Number: ","",trim)
  val email = field("Email: ","",trim)
  val curLocation = select("Current Location: ", "Alabama",Utils.getLocationDisplay)
  val city = field("City: ","",trim)
  val Relocationpre = select("Relocation Preference: ", "Alabama",Utils.getLocationDisplay)
  val Anywhere = select("Anywhere in the US: ", "No", List("Yes", "No"))
  val parttime = select("Consider Parttime: ", "No", List("Yes", "No"))
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
    val save = resume.create.resumename(resumeName.is).anywhereinus(getAnywhere).birthday(birthday.is).city(city.is).currentlocation(EntoStr(curLocation.is)).currentposition(curPosition.is).currentsalary(curSalary.is).Edu(Edu.is)
    .email(email.is).fullname(fullName.is).currentworking(getCurrentWorking).gender(getGender(gender.is)).Major(major.is).phonenumber(phoneNum.is).preferlocation(EntoStr(Relocationpre.is)).parttime(getPartTime).USitexperience(USitExp.is).totalitexperience(itExp.is)
    .yeargraduate(grad.is).CreatedUser(User.currentUser).resumeLink(filename).save

    S.notice("您的简历已经制作成功.")
  }
}
