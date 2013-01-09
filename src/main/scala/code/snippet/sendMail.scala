package code.snippet
import code.view.{DataTable, DataTableObjectSource, DataTableParams}
import code.model.{resume, User, Jobs}
import code.lib.util.Utils
import net.liftweb.http._
import js.JsCmds
import code.lib.util._
import net.liftweb.common._
import net.liftweb.mapper._
import code.lib.util.Utils
import code.lib.util.Sessionvarscollection._
/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 10/3/12
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
class sendMail {
  def render = {
    val para = S.param("resumeName").open_!
    val jobid = S.param("jobid").open_!
    var myresume = resume.find(By(resume.CreatedUser,User.currentUser.open_!), By(resume.resumename, para)).open_!
    var thejob = Jobs.findJobsById(jobid.toLong).head
    val content = "Someone apply "+thejob.title+" here is the resume"
    case class CSVFile(bytes: Array[Byte],
                       filename: String = "file.csv",
                       mime: String = "text/csv; charset=utf8; header=present" )


  }

}
