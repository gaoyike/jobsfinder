package code.lib.util

import net.liftweb.common.{Empty, Full, Box}
import net.liftweb.http.LiftRules
import net.liftweb.http.provider.servlet.HTTPServletContext
import java.io.File

/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 9/19/12
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
object Utils {
  object userTypes extends Enumeration {
    val user = Value(1, "个人用户")
    val company = Value(2, "企业用户")
  }

  def getPositionDisplay:List[String] = {
    var b: List[String] = List.empty
    PositonsSeq.foreach(a => b = b ++ List(a._2))
    return b
  }

  def getLocationDisplay:List[String] = {
    var b: List[String]= List.empty
    StatesSeq.foreach(a => b = b ++ List(a._2))
    return b
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

  object Gender extends Enumeration{
    val male = Value(1, "Male");
    val female = Value(2, "Female");
  }
  val StatesSeq = Seq(
     "Alabama"->  "Alabama",
     "Alaska" -> "Alaska",
     "Arizona"-> "Arizona",
     "Arkansas"-> "Arkansas",
     "California"->"California",
     "Colorado"->"Colorado",
     "Connecticut"->"Connecticut",
     "Delaware"-> "Delaware",
     "Florida" ->"Florida",
     "Georgia" -> "Georgia",
     "Hawaii" -> "Hawaii",
     "Idaho" -> "Idaho",
     "Illinois" -> "Illinois" ,
     "Indiana" -> "Indiana"    ,
     "Iowa" ->  "Iowa"           ,
     "Kansas" -> "Kansas"        ,
     "Kenturkey" -> "Kenturkey"   ,
     "Louisiana" -> "Louisiana"    ,
     "Maine" ->"Maine"            ,
     "Maryland" -> "Maryland"        ,
     "Massachusetts"-> "Massachusetts" ,
     "Michigan" -> "Michigan"            ,
     "Minnesota" -> "Minnesota"           ,
     "Mississippi" ->"Mississippi"         ,
     "Missouri" -> "Missouri"               ,
     "Montana" -> "Montana"                  ,
     "Nebraska" -> "Nebraska"                 ,
     "Nevada" -> "Neveda"                      ,
     "New_Hampshire" -> "New Hampshire"         ,
     "New_Jersey" -> "New Jersey"                ,
     "New_Mexico" ->"New Mexico"                 ,
     "New_York" -> "New York"                      ,
     "North_Carolina" -> "North Carolina"           ,
     "North_Dakota" ->"North Dakota"                 ,
     "Ohio" -> "Ohio"                                 ,
     "Oklahoma"-> "Oklahoma"                          ,
     "Oregon" ->"Oregon"                               ,
     "Pennsylvania" -> "Pennsylvania"                    ,
     "Rode_Island" -> "Rode Island"                       ,
     "South_Carolina"-> "South Carolina"                  ,
     "South_Dakota" -> "South Dakota"                       ,
     "Tennessee" -> "Tennessee"                              ,
     "Texas" -> "Texas"                                       ,
     "Utah" -> "Utah"                                          ,
     "Vermont" -> "Vermont"                                     ,
     "Virginia" -> "Virginia"                                    ,
     "Washington" -> "Washington"                                 ,
     "West_Virginia" -> "West Virginia"                            ,
     "Wisconsin" -> "Wisconsin"                                     ,
     "Wyoming" -> "Wyoming"                                         ,
     "Others" -> "Others.."
  )
  val PositonsSeq = Seq(
    "Java_Developer" -> "Java Developer",
    "Sr_Java_Developer" -> "Sr.Java Developer",
    "Java_Architect" -> "Java Architect",
    "Net_Developer" ->".Net Developer",
    "Sr_Net_Developer" ->  "Sr. .Net Developer",
    "Oracle_DBA" -> "Oracle DBA",
    "SQL_Server_DBA"-> "SQL Server DBA",
    "SQL_Developer" ->  "SQL Developer",
    "UI_Developer" ->"UI Designer",
    "Android_Developer" -> "Android Developer" ,
    "iOS_Developer" -> "iOS Developer",
    "System_Architect" -> "System Architect",
    "Project_Manager" ->  "Project Manager",
    "Business_Analyst" -> "Business Analyst",
    "Shairpoint_Developer" -> "Shairpoint Developer",
    "Salesforce_Developer" -> "Salesforce Developer",
    "SAP" -> "SAP",
    "Linux_Administrator" -> "Linux Administrator",
    "Network_Administrator" -> "Network Administrator"    ,
    "SAS_Developer" -> "SAS Developer",
    "SAS_Administrator" -> "SAS Administrator"    ,
    "BI_Developer" -> "BI Developer" ,
    "Game_Developer" -> "Game Developer",
    "Game_Designer" -> "Game Designer",
    "Technical_Writer" -> "Technical Writer" ,
    "PHP_Developer" -> "PHP Developer",
    "Ruby_Developer" ->"Ruby Developer"  ,
    "Buztalk_Developer" ->"Buztalk Developer" ,
    "Python_Developer" ->"Python Developer",
    "ETL_Datawarehouse_Developer" -> "ETL Datawarehouse Developer"  ,
    "QA_Tester" ->"QA Tester"
  )
  object Positions extends Enumeration{
    val Java_Developer = Value(1,"Java Developer")
    val Sr_Java_Developer = Value(2,"Sr.Java Developer")
    val Java_Architect = Value(3,"Java Architect")
    val Net_Developer  = Value(4,".Net Developer")
    val Sr_Net_Developer =Value(5,"Sr. .Net Developer")
    val Oracle_DBA = Value(6,"Oracle DBA")
    val SQL_Server_DBA = Value(7,"SQL Server DBA")
    val SQL_Developer = Value(8,"SQL Developer")
    val UI_Developer = Value(9,"UI Developer")
    val UI_Designer = Value(10,"UI Designer")
    val Android_Developer = Value(11,"Android Developer")
    val iOS_Developer = Value(12,"iOS Developer")
    val System_Architect = Value(13,"System Architect")
    val Project_Manager = Value(14,"Project Manager")
    val Business_Analyst = Value(15, "Business Analyst")
    val Shairpoint_Developer = Value(16,"Shairpoint Developer")
    val Salesforce_Developer = Value(17,"Salesforce Developer")
    val SAP = Value(18,"SAP")
    val Linux_Administrator  = Value(19,"Linux Administrator")
    val Network_Administrator= Value(20,"Network Administrator")
    val SAS_Developer = Value(21,"SAS Developer")
    val SAS_Administrator = Value(22,"SAS Administrator")
    val BI_Developer   = Value(23,"BI Developer")
    val Game_Developer = Value(24,"Game Developer")
    val Game_Designer = Value(25,"Game Designer")
    val Technical_Writer = Value(26,"Technical Writer")
    val Technical_Support = Value(27,"Technical Support")
    val PHP_Developer = Value(28,"PHP Developer")
    val Ruby_Developer = Value(29,"Ruby Developer")
    val Buztalk_Developer = Value(30,"Buztalk Developer")
    val Python_Developer = Value(31,"Python Developer")
    val ETL_Datawarehouse_Developer = Value(32,"ETL/Datawarehouse Developer")
    val QA_Tester = Value(33,"QA Tester")
  }
  object States extends Enumeration {
    val Alabama = Value(1, "Alabama")
    val Alaska = Value(2, "Alaska")
    val Arizona = Value(3,  "Arizona")
    val Arkansas = Value(4, "Arkansas")
    val California = Value(5, "California")
    val Colorado = Value(6, "Colorado")
    val Connecticut = Value(7, "Connecticut")
    val Delaware = Value(8, "Delaware")
    val Florida = Value(9, "Florida")
    val Georgia = Value(10, "Georgia")
    val Hawaii = Value(11, "Hawaii")
    val Idaho = Value(12, "Idaho")
    val Illinois = Value(13, "Illinois")
    val Indiana = Value(14, "Indiana")
    val Iowa = Value(15, "Iowa")
    val Kansas = Value(16, "Kansas")
    val Kenturkey = Value(17, "Kenturkey")
    val Louisiana = Value(18, "Louisiana")
    val Maine = Value(19,  "Maine")
    val Maryland = Value(20, "Maryland")
    val Massachusetts = Value(21, "Massachusetts")
    val Michigan = Value(22, "Michigan")
    val Minnesota = Value(23, "Minnesota")
    val Mississippi = Value(24,"Mississippi")
    val Missouri = Value(25, "Missouri")
    val Montana = Value(26, "Montana")
    val Nebraska = Value(27, "Nebraska")
    val Nevada = Value(28, "Neveda")
    val New_Hampshire = Value(29, "New Hampshire")
    val New_Jersey = Value(30, "New Jersey")
    val New_Mexico = Value(31, "New Mexico")
    val New_York = Value(32, "New York")
    val North_Carolina = Value(33, "North Carolina")
    val North_Dakota = Value(34,"North Dakota")
    val Ohio = Value(35, "Ohio")
    val Oklahoma = Value(36, "Oklahoma")
    val Oregon = Value(37, "Oregon")
    val Pennsylvania = Value(38, "Pennsylvania")
    val Rode_Island = Value(39, "Rode Island")
    val South_Carolina = Value(40, "South Carolina")
    val South_Dakota = Value(41, "South Dakota")
    val Tennessee = Value(42, "Tennessee")
    val Texas = Value(43, "Texas")
    val Utah = Value(44, "Utah")
    val Vermont = Value(45, "Vermont")
    val Virginia = Value(46, "Virginia")
    val Washington = Value(47, "Washington")
    val West_Virginia = Value(48, "West Virginia")
    val Wisconsin = Value(49, "Wisconsin")
    val Wyoming = Value(50, "Wyoming")
    val Others = Value(51, "Others..")
  }

  def getStateList(): Enumeration = States
  def getGenderList(): Enumeration = Gender
  def getPositionList(): Enumeration = Positions
}