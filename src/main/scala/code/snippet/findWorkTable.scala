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
class findWorkTable {
  def table = {
    val cols = "工作代码"::"工作名称" ::"公司名称":: "工作地点"  :: "查看详情" :: Nil
    lazy val fun = (params: DataTableParams) => {
      var tmp = searchJobsByPositionAndLocation.is
      println(tmp)
      def getPos(d:String)= Utils.Positions.withName(Utils.PositonsSeq.filter(s => s._1 == d).head._2)
      def getLoc(d:String)= Utils.States.withName(Utils.StatesSeq.filter(s => s._1 == d).head._2)
      var p:List[Jobs] = List.empty
      tmp.map(t => p=p++Jobs.findAll(By(Jobs.position, getPos(t._1)),By(Jobs.location, getLoc(t._2))))
      def rowFun(ps:Jobs) = List((ps.id.is.toString, ps.title.is, ps.companyName.is, ps.location.is.toString+" "+ps.city.is, "工作详情"))
      var rows:List[(String, String, String, String, String)] = List.empty
      p.map( a => rows = rowFun(a):::rows)

      val count = p.size
  //set back    searchJobsByPositionAndLocation.set(List.empty)
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
