package code.snippet

import code.view.{DataTable, DataTableObjectSource, DataTableParams}
import code.model.{Jobs, resume}
import code.lib.util.Utils
import net.liftweb.http._
import js.JsCmds
import code.lib.util._
import net.liftweb.common._
/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 9/23/12
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
class myjobsTable {
  def table = {
    val cols = "工作名称" :: "创建时间" :: "修改时间" :: "修改" :: Nil
    lazy val fun = (params: DataTableParams) => {
      val p:List[Jobs] = Jobs.findJobsByCurUser
      def rowFun(ps:Jobs) = List((ps.title.is, ps.createdAt.is.toString,ps.updatedAt.is.toString,""))
      var rows:List[(String, String, String, String)] = List.empty
      p.map( a => rows = rowFun(a):::rows)

      val count = p.size

      new DataTableObjectSource(count, count, rows.map(r =>
        List(("0", r._1),
          ("1", r._2),("2", r._3),("3", (<button type="button" datatoggle="button" onClick={process(r._1)}>修改</button>).toString()),
          ("DT_RowId", "rowid_" + r._1))))


    }

    def process(s:String) = {
      JsCmds.RedirectTo("/editjobs.html?jobName="+s)
    }

    DataTable(
      cols, // columns
      fun, // our data provider
      "my-table", // html table id
      List(("bFilter", "false")), // datatables configuration
      ("class", "table table-striped table-bordered")) // set css class for table
  }

}
