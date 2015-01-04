package controllers

import javax.ws.rs.{Path, PathParam}

import com.codahale.metrics.{Timer, Meter}
import com.wordnik.swagger.annotations._
import grizzled.slf4j.Logging
import model.{SingleDoubleValue, SingleLongValue}
import play.api.mvc.Action
import org.quantintel.ql.time.{Date => Dt}

/**
 * This controller supports the following day count method invocations:
 *
 * A. ActualActual(BOND) || ActualActual(ISMA)
 * B. ActualActual(ISDA) || ActualActual(HISTORICAL) || ActualActual(ACTUAL365)
 * C. ActualActual(AFB) || ActualActual(EURO)
 *
 * @author Paul Bernard
 *
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
                  @Path("/daycount/{fromdate}/{todate}/ActAct/ISMA/DC") fromdate: Long,
                  @ApiParam(value = "to date", required = true)
                  @PathParam("todate")
                  @Path("/daycount/{fromdate}/{todate}/ActAct/ISMA/DC") todate: Long) = Action {

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
                    @Path("/daycount/{fromdate}/{todate}/ActAct/ISMA/YF") fromdate: Long,
                    @ApiParam(value = "to date", required = true)
                    @PathParam("todate")
                    @Path("/daycount/{fromdate}/{todate}/ActAct/ISMA/YF") todate: Long) = Action {

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
  def actActBOND (
                   @ApiParam(value = "from date", required = true)
                   @PathParam("fromdate")
                   @Path("/daycount/{fromdate}/{todate}/ActAct/Bond/DC") fromdate: Long,
                   @ApiParam(value = "to date", required = true)
                   @PathParam("todate")
                   @Path("/daycount/{fromdate}/{todate}/ActAct/Bond/DC") todate: Long) = Action {

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
  def actActBONDYF (
                     @ApiParam(value = "from date", required = true)
                     @PathParam("fromdate")
                     @Path("/daycount/{fromdate}/{todate}/ActAct/Bond/YF") fromdate: Long,
                     @ApiParam(value = "to date", required = true)
                     @PathParam("todate")
                     @Path("/daycount/{fromdate}/{todate}/ActAct/Bond/YF") todate: Long) = Action {

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






  // begin Actual/Actual Methods: (ISDA || HISTORICAL || ACTUAL365)

  private def actActIsda(fromdate: Long, todate: Long): Long = {

    import org.quantintel.ql.time.daycounters.ActualActual
    import org.quantintel.ql.time.daycounters.ActualActualConvention._

    val startDate = Dt(fromdate)
    val endDate = Dt(todate)

    ActualActual(ISDA).dayCount(startDate, endDate)

  }


  private def actActIsdaYF(fromdate: Long, todate: Long) : Double = {

    import org.quantintel.ql.time.daycounters.ActualActual
    import org.quantintel.ql.time.daycounters.ActualActualConvention._

    val startDate = Dt(fromdate)
    val endDate = Dt(todate)

    ActualActual(ISDA).yearFraction(startDate, endDate)

  }

  // need to implement web wrappers

  @ApiOperation(
    nickname = "actActISDA",
    value = "day count using the Act/Act ISDA method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActISDA(
                   @ApiParam(value = "from date", required = true)
                   @PathParam("fromdate")
                   @Path("/daycount/{fromdate}/{todate}/ActAct/ISDA/DC") fromdate: Long,
                   @ApiParam(value = "to date", required = true)
                   @PathParam("todate")
                   @Path("/daycount/{fromdate}/{todate}/ActAct/ISDA/DC") todate: Long) = Action {

    DayCountActActController.invocationActActISDA.mark

    val context : Timer.Context = DayCountActActController.invocationActActISDATimer.time

    try {

      val result = actActIsda(fromdate, todate)

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
    nickname = "actActISDAYF",
    value = "day count fraction using the Act/Act ISDA method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActISDAYF (
                     @ApiParam(value = "from date", required = true)
                     @PathParam("fromdate")
                     @Path("/daycount/{fromdate}/{todate}/ActAct/ISDA/YF") fromdate: Long,
                     @ApiParam(value = "to date", required = true)
                     @PathParam("todate")
                     @Path("/daycount/{fromdate}/{todate}/ActAct/ISDA/YF") todate: Long) = Action {

    DayCountActActController.invocationActActBondYF.mark

    val context : Timer.Context = DayCountActActController.invocationActActISDAYFTimer.time

    try {

      val result = actActIsdaYF(fromdate, todate)

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
    nickname = "actActHistorical",
    value = "day count using the Act/Act Historical method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActHistorical (
                  @ApiParam(value = "from date", required = true)
                  @PathParam("fromdate")
                  @Path("/daycount/{fromdate}/{todate}/ActAct/Historical/DC") fromdate: Long,
                  @ApiParam(value = "to date", required = true)
                  @PathParam("todate")
                  @Path("/daycount/{fromdate}/{todate}/ActAct/Historical/DC") todate: Long) = Action {

    DayCountActActController.invocationActActHistorical.mark

    val context : Timer.Context = DayCountActActController.invocationActActHistoricalTimer.time

    try {

      val result = actActIsda(fromdate, todate)

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
    nickname = "actActHistoricalYF",
    value = "day count fraction using the Act/Act Historical method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActHistoricalYF (
                     @ApiParam(value = "from date", required = true)
                     @PathParam("fromdate")
                     @Path("/daycount/{fromdate}/{todate}/ActAct/Historical/YF") fromdate: Long,
                     @ApiParam(value = "to date", required = true)
                     @PathParam("todate")
                     @Path("/daycount/{fromdate}/{todate}/ActAct/Historical/YF") todate: Long) = Action {

    DayCountActActController.invocationActActHistoricalYF.mark

    val context : Timer.Context = DayCountActActController.invocationActActHistoricalYFTimer.time

    try {

      val result = actActIsdaYF(fromdate, todate)

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
    nickname = "actActACTUAL365",
    value = "day count using the Act/Act ACTUAL 365 method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actAct365(
                         @ApiParam(value = "from date", required = true)
                         @PathParam("fromdate")
                         @Path("/daycount/{fromdate}/{todate}/ActAct/ACTUAL365/DC") fromdate: Long,
                         @ApiParam(value = "to date", required = true)
                         @PathParam("todate")
                         @Path("/daycount/{fromdate}/{todate}/ActAct/ACTUAL365/DC") todate: Long) = Action {

    DayCountActActController.invocationActAct365.mark

    val context : Timer.Context = DayCountActActController.invocationActAct365Timer.time

    try {

      val result = actActIsda(fromdate, todate)

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
    nickname = "actActACTUAL365YF",
    value = "day count fraction using the Act/Act ACTUAL 365 method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actAct365YF (
                           @ApiParam(value = "from date", required = true)
                           @PathParam("fromdate")
                           @Path("/daycount/{fromdate}/{todate}/ActAct/ACTUAL365/YF") fromdate: Long,
                           @ApiParam(value = "to date", required = true)
                           @PathParam("todate")
                           @Path("/daycount/{fromdate}/{todate}/ActAct/ACTUAL365/YF") todate: Long) = Action {

    DayCountActActController.invocationActActHistoricalYF.mark

    val context : Timer.Context = DayCountActActController.invocationActActHistoricalYFTimer.time

    try {

      val result = actActIsdaYF(fromdate, todate)

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



  // begin Actual/Actual Methods: (AFB || EURO)


  private def actActAfb(fromdate: Long, todate: Long): Long = {

    import org.quantintel.ql.time.daycounters.ActualActual
    import org.quantintel.ql.time.daycounters.ActualActualConvention._

    val startDate = Dt(fromdate)
    val endDate = Dt(todate)

    ActualActual(AFB).dayCount(startDate, endDate)

  }


  private def actActAfbYF(fromdate: Long, todate: Long) : Double = {

    import org.quantintel.ql.time.daycounters.ActualActual
    import org.quantintel.ql.time.daycounters.ActualActualConvention._

    val startDate = Dt(fromdate)
    val endDate = Dt(todate)

    ActualActual(AFB).yearFraction(startDate, endDate)

  }


  @ApiOperation(
    nickname = "actActAFB",
    value = "day count using the Act/Act AFB method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActAFB(
                 @ApiParam(value = "from date", required = true)
                 @PathParam("fromdate")
                 @Path("/daycount/{fromdate}/{todate}/ActAct/AFB/DC") fromdate: Long,
                 @ApiParam(value = "to date", required = true)
                 @PathParam("todate")
                 @Path("/daycount/{fromdate}/{todate}/ActAct/AFB/DC") todate: Long) = Action {

    DayCountActActController.invocationActActAFB.mark

    val context : Timer.Context = DayCountActActController.invocationActActAFBTimer.time

    try {
      val result = actActAfb(fromdate, todate)

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
    nickname = "actActAFBYF",
    value = "day count fraction using the Act/Act AFB method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActAFBYF (
                    @ApiParam(value = "from date", required = true)
                    @PathParam("fromdate")
                    @Path("/daycount/{fromdate}/{todate}/ActAct/AFB/YF") fromdate: Long,
                    @ApiParam(value = "to date", required = true)
                    @PathParam("todate")
                    @Path("/daycount/{fromdate}/{todate}/ActAct/AFB/YF") todate: Long) = Action {

    DayCountActActController.invocationActActAFBYF.mark

    val context : Timer.Context = DayCountActActController.invocationActActAFBYFTimer.time

    try {

      val result = actActAfbYF(fromdate, todate)

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
    nickname = "actActEURO",
    value = "day count using the Act/Act EURO method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActEURO(
                 @ApiParam(value = "from date", required = true)
                 @PathParam("fromdate")
                 @Path("/daycount/{fromdate}/{todate}/ActAct/EURO/DC") fromdate: Long,
                 @ApiParam(value = "to date", required = true)
                 @PathParam("todate")
                 @Path("/daycount/{fromdate}/{todate}/ActAct/EURO/DC") todate: Long) = Action {

    DayCountActActController.invocationActActEURO.mark

    val context : Timer.Context = DayCountActActController.invocationActActEUROTimer.time

    try {
      val result = actActAfb(fromdate, todate)

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
    nickname = "actActEUROYF",
    value = "day count fraction using the Act/Act EURO method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actActEUROYF (
                    @ApiParam(value = "from date", required = true)
                    @PathParam("fromdate")
                    @Path("/daycount/{fromdate}/{todate}/ActAct/EURO/YF") fromdate: Long,
                    @ApiParam(value = "to date", required = true)
                    @PathParam("todate")
                    @Path("/daycount/{fromdate}/{todate}/ActAct/EURO/YF") todate: Long) = Action {

    DayCountActActController.invocationActActEUROYF.mark

    val context : Timer.Context = DayCountActActController.invocationActActEUROYFTimer.time

    try {

      val result = actActAfbYF(fromdate, todate)

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

  val invocationActActISMA : Meter = Instrumentation.metrics.meter(DayCountActActController .getClass.getCanonicalName + "." + "invocation-invocationActActISMA")
  var invocationActActISMATimer : Timer  = Instrumentation.metrics.timer(DayCountActActController .getClass.getCanonicalName + "." + "invocationActActISMATimer")

  val invocationActActISMAYF : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActISMAYF")
  var invocationActActISMAYFTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController .getClass.getCanonicalName + "." + "invocationActActISMAYFTimer")

  val invocationActActBond : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActBond")
  var invocationActActBondTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController .getClass.getCanonicalName + "." + "invocationActActBondTimer")

  val invocationActActBondYF : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActBondYF")
  var invocationActActBondYFTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController .getClass.getCanonicalName + "." + "invocationActActBondYFTimer")

  val invocationActActISDA : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActISDA")
  var invocationActActISDATimer : Timer  = Instrumentation.metrics.timer(DayCountActActController .getClass.getCanonicalName + "." + "invocationActActISDATimer")

  val invocationActActISDAYF : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActISDAYF")
  var invocationActActISDAYFTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActActISDAYFTimer")

  val invocationActActHistorical : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActHistorical")
  var invocationActActHistoricalTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActActHistoricalTimer")

  val invocationActActHistoricalYF : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActHistoricalYF")
  var invocationActActHistoricalYFTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActActHistoricalYFTimer")

  val invocationActAct365 : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActAct365")
  var invocationActAct365Timer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActAct365Timer")

  val invocationActAct365YF : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActAct365YF")
  var invocationActAct365YFTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActAct365YFTimer")

  val invocationActActAFB : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActAFB")
  var invocationActActAFBTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActActAFBTimer")

  val invocationActActAFBYF : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActAFBYF")
  var invocationActActAFBYFTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActActAFBYFTimer")

  val invocationActActEURO : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActEURO")
  var invocationActActEUROTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActActEUROTimer")

  val invocationActActEUROYF : Meter = Instrumentation.metrics.meter(DayCountActActController.getClass.getCanonicalName + "." + "invocation-invocationActActEUROYF")
  var invocationActActEUROYFTimer : Timer  = Instrumentation.metrics.timer(DayCountActActController.getClass.getCanonicalName + "." + "invocationActActEUROYFTimer")


}
