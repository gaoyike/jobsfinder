package code.lib.util
import net.liftweb._
import common.Full
import http.SessionVar
import util._
import net.liftweb.http.provider.servlet.HTTPServletContext
import code.model.resume
import java.io._
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.model._
import code.lib.util._
import net.liftweb.mapper._
/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 9/23/12
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
object Sessionvarscollection {
  object searchJobsByPosition extends SessionVar[String]("None")
  object searchJobsByLocation extends SessionVar[String]("None")
  object searchJobsByPositionAndLocation extends SessionVar[Traversable[(String, String)]] (Traversable())

}
