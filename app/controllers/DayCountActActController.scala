package controllers

import javax.ws.rs.{Path, PathParam}

import com.codahale.metrics.{Timer, Meter}
import com.wordnik.swagger.annotations._
import grizzled.slf4j.Logging
import model.{SingleDoubleValue, SingleLongValue}
import play.api.mvc.Action
import org.quantintel.ql.time.{Date => Dt}

/**
 * @author Paul Bernard
 */
@Api(value ="/daycount",
  description = "Day count related operations")
class DayCountActActController extends BaseApiController with Logging  {

  /**
   *
   * @param path
   * @return
   */
  def getOptions(path: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  // begin Actual/Actual Methods: (ISMA || BOND)

  private def actActIsmaBond(fromdate: Long, todate: Long): Long = {

    import org.quantintel.ql.time.daycounters.ActualActual
    import org.quantintel.ql.time.daycounters.ActualActualConvention._

    val startDate = Dt(fromdate)
    val endDate = Dt(todate)

    ActualActual(ISMA).dayCount(startDate, endDate)

  }


  private def actActIsmaBondYF(fromdate: Long, todate: Long) : Double = {

    import org.quantintel.ql.time.daycounters.ActualActual
    import org.quantintel.ql.time.daycounters.ActualActualConvention._

    val startDate = Dt(fromdate)
    val endDate = Dt(todate)

    ActualActual(BOND).yearFraction(startDate, endDate)

  }





  @ApiOperation(
    nickname = "actActISMA",
    value = "day count using the Act/Act ISMA method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActISMA(
                  @ApiParam(value = "from date", required = true)
                  @PathParam("fromdate")
                  @Path("/daycount/{fromdate}/{todate}/actActISMA") fromdate: Long,
                  @ApiParam(value = "to date", required = true)
                  @PathParam("todate")
                  @Path("/daycount/{fromdate}/{todate}/actActISMA") todate: Long) = Action {

    DayCountActActController.invocationActActISMA.mark

    val context : Timer.Context = DayCountActActController.invocationActActISMATimer.time

    try {

      val result = actActIsmaBond(fromdate, todate)

      val res = new SingleLongValue(result)

      JsonResponse(res)

    } catch {
      case e : Exception => {
        error(e.getMessage)
        throw e
      }
    } finally {
      context.stop()
    }

  }

  @ApiOperation(
    nickname = "actActISMAYF",
    value = "day count fraction using the Act/Act ISMA method",
    notes = "returns the day count fraction given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActISMAYF(
                    @ApiParam(value = "from date", required = true)
                    @PathParam("fromdate")
                    @Path("/daycount/{fromdate}/{todate}/actActISMAYF") fromdate: Long,
                    @ApiParam(value = "to date", required = true)
                    @PathParam("todate")
                    @Path("/daycount/{fromdate}/{todate}/actActISMAYF") todate: Long) = Action {

    DayCountActActController.invocationActActISMAYF.mark

    val context : Timer.Context = DayCountActActController.invocationActActISMAYFTimer.time

    try {

      val result = actActIsmaBondYF(fromdate, todate)

      val res = new SingleDoubleValue(result)

      JsonResponse(res)

    } catch {
      case e : Exception => {
        error(e.getMessage)
        throw e
      }
    } finally {
      context.stop()
    }

  }


  @ApiOperation(
    nickname = "actActBOND",
    value = "day count using the Act/Act Bond method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActBond (
                   @ApiParam(value = "from date", required = true)
                   @PathParam("fromdate")
                   @Path("/daycount/{fromdate}/{todate}/actActBond") fromdate: Long,
                   @ApiParam(value = "to date", required = true)
                   @PathParam("todate")
                   @Path("/daycount/{fromdate}/{todate}/actActBond") todate: Long) = Action {

    DayCountActActController.invocationActActBond.mark

    val context : Timer.Context = DayCountActActController.invocationActActBondTimer.time

    try {

      val result = actActIsmaBond(fromdate, todate)

      val res = new SingleLongValue(result)

      JsonResponse(res)

    } catch {
      case e : Exception => {
        error(e.getMessage)
        throw e
      }
    } finally {
      context.stop()
    }

  }

  @ApiOperation(
    nickname = "actActBONDYF",
    value = "day count fraction using the Act/Act Bond method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActBondYF (
                     @ApiParam(value = "from date", required = true)
                     @PathParam("fromdate")
                     @Path("/daycount/{fromdate}/{todate}/actActBondYF") fromdate: Long,
                     @ApiParam(value = "to date", required = true)
                     @PathParam("todate")
                     @Path("/daycount/{fromdate}/{todate}/actActBondYF") todate: Long) = Action {

    DayCountActActController.invocationActActBondYF.mark

    val context : Timer.Context = DayCountActActController.invocationActActBondYFTimer.time

    try {

      val result = actActIsmaBondYF(fromdate, todate)

      val res = new SingleDoubleValue(result)

      JsonResponse(res)

    } catch {
      case e : Exception => {
        error(e.getMessage)
        throw e
      }
    } finally {
      context.stop()
    }

  }

}

object DayCountActActController {

  // act/act instrumentation

  val invocationActActISMA : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-invocationActActISMA")
  var invocationActActISMATimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocationActActISMATimer")

  val invocationActActISMAYF : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-invocationActActISMAYF")
  var invocationActActISMAYFTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocationActActISMAYFTimer")

  val invocationActActBond : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-invocationActActBond")
  var invocationActActBondTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocationActActBondTimer")

  val invocationActActBondYF : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-invocationActActBondYF")
  var invocationActActBondYFTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocationActActBondYFTimer")



}
