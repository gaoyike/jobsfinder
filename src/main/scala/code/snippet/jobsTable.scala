package code.snippet

import code.view.{DataTable, DataTableObjectSource, DataTableParams}
import code.model.Jobs
import code.lib.util.Utils
import net.liftweb.http._
import js.JsCmds
import code.lib.util._
import net.liftweb.common._
import net.liftweb.mapper._
import code.lib.util.Utils
import code.lib.util.Sessionvarscollection._
class jobsTable {
  def table = {
    val cols = "工作代码"::"工作名称" ::"公司名称":: "工作地点"  :: "查看详情" :: Nil
    lazy val fun = (params: DataTableParams) => {
      val positon = Utils.PositonsSeq.filter(s => s._1 == searchJobsByPosition.is).head._2
      val location = Utils.StatesSeq.filter(s => s._1 == searchJobsByLocation.is).head._2
      searchJobsByLocation.set("None")
      searchJobsByPosition.set("None")
      println("****Working?****"+positon+location)
      val p:List[Jobs] = Jobs.findAll(By(Jobs.position, Utils.Positions.withName(positon)),By(Jobs.location, Utils.States.withName(location)), OrderBy(Jobs.createdAt, Ascending))
      def rowFun(ps:Jobs) = List((ps.id.is.toString, ps.title.is, ps.companyName.is, ps.location.is.toString+" "+ps.city.is, "工作详情"))
      var rows:List[(String, String, String, String, String)] = List.empty
      p.map( a => rows = rowFun(a):::rows)

      val count = p.size

      new DataTableObjectSource(count, count, rows.map(r =>
        List(("0", r._1),
          ("1", r._2),("2", r._3),("3", r._4),("4", (<button type="button" datatoggle="button" onClick={process(r._1)}>查看详情</button>).toString()),
          ("DT_RowId", "rowid_" + r._1))))


    }

    def process(id:String) = {
      JsCmds.RedirectTo("/jobDetail.html?jobid="+id)
    }

    DataTable(
      cols, // columns
      fun, // our data provider
      "my-table", // html table id
      List(("bFilter", "false")), // datatables configuration
      ("class", "table table-striped table-bordered")) // set css class for table
  }

}
