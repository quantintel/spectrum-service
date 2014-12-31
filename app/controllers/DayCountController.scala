/*
 * Copyright (c) 2014  Paul Bernard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Spectrum Finance is based in part on:
 *        QuantLib. http://quantlib.org/
 *
 */

package controllers

import javax.ws.rs.{Path, PathParam}

import com.codahale.metrics.{Meter, Timer}
import com.wordnik.swagger.annotations._
import grizzled.slf4j.Logging
import model.{SingleDoubleValue, SingleLongValue}
import play.api.mvc.Action
import org.quantintel.ql.time.{Date => Dt}


@Api(value ="/daycount",
  description = "Day count related operations")
class DayCountController extends BaseApiController with Logging {

  /**
   *
   * @param path
   * @return
   */
  def getOptions(path: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  /**
   * This method calculates the day count between two dates using the Actual/360 method..
   *
   * @return day count.
   */
  @ApiOperation(
    nickname = "actual360",
    value = "day count using the Actual/360 method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actual360(
                 @ApiParam(value = "from date", required = true)
                 @PathParam("fromdate")
                 @Path("/daycount/{fromdate}/{todate}/Act360/DC") fromdate: Long,
                 @ApiParam(value = "to date", required = true)
                 @PathParam("todate")
                 @Path("/daycount/{fromdate}/{todate}/Act360/DC") todate: Long) = Action {

    DayCountController.invocationActual360.mark

    val context : Timer.Context = DayCountController.invocationActual360Timer.time

    import org.quantintel.ql.time.daycounters.Actual360Convention._
    import org.quantintel.ql.time.daycounters.Actual360

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val res = new SingleLongValue(Actual360(ACTUAL360).dayCount(startDate, endDate))

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
    nickname = "actual360yf",
    value = "day count fraction using the Actual/360 method",
    notes = "returns the day count fraction given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actual360YF (
                    @ApiParam(value = "from date", required = true)
                    @PathParam("fromdate")
                    @Path("/daycount/{fromdate}/{todate}/Act360/YF") fromdate: Long,
                    @ApiParam(value = "to date", required = true)
                    @PathParam("todate")
                    @Path("/daycount/{fromdate}/{todate}/Act360/YF") todate: Long) = Action {

    DayCountController.invocationActual360YF.mark

    val context : Timer.Context = DayCountController.invocationActual360YFTimer.time

    import org.quantintel.ql.time.daycounters.Actual360

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val yf :Double = Actual360().yearFraction(startDate, endDate)

      val res = new SingleDoubleValue(yf.toDouble)

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

  // end actual360

  @ApiOperation(
    nickname = "actual365F",
    value = "day count using the Actual/360F method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actual365F(
                  @ApiParam(value = "from date", required = true)
                  @PathParam("fromdate")
                  @Path("/daycount/{fromdate}/{todate}/Act365F/DC")fromdate: Long,
                  @ApiParam(value = "to date", required = true)
                  @PathParam("todate")
                  @Path("/daycount/{fromdate}/{todate}/Act365F/DC")todate: Long) = Action {

    DayCountController.invocationActual365F.mark

    val context : Timer.Context = DayCountController.invocationActual365FTimer.time

    import org.quantintel.ql.time.daycounters.Actual365
    import org.quantintel.ql.time.daycounters.Actual365Convention._

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val res = new SingleLongValue(Actual365(ACT365F).dayCount(startDate, endDate))

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
    nickname = "actual365Fyf",
    value = "day count fraction using the Actual/360F method",
    notes = "returns the day count fraction given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actual365FYF(
                    @ApiParam(value = "from date", required = true)
                    @PathParam("fromdate")
                    @Path("/daycount/{fromdate}/{todate}/Act365F/YF")fromdate: Long,
                    @ApiParam(value = "to date", required = true)
                    @PathParam("todate")
                    @Path("/daycount/{fromdate}/{todate}/Act365F/YF")todate: Long) = Action {

    DayCountController.invocationActual365FYF.mark

    val context : Timer.Context = DayCountController.invocationActual365FYFTimer.time

    import org.quantintel.ql.time.daycounters.Actual365
    import org.quantintel.ql.time.daycounters.Actual365Convention._

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val yf :Double = Actual365(ACT365F).yearFraction(startDate, endDate)

      val res = new SingleDoubleValue(yf.toDouble)

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

  // end 365F

  @ApiOperation(
    nickname = "actual365L",
    value = "day count using the Actual/360L method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actual365L(
                  @ApiParam(value = "from date", required = true)
                  @PathParam("fromdate")
                  @Path("/daycount/{fromdate}/{todate}/Act365L/DC") fromdate: Long,
                  @ApiParam(value = "to date", required = true)
                  @PathParam("todate")
                  @Path("/daycount/{fromDate}/{toDate}/Act360L/DC")todate: Long) = Action {

    DayCountController.invocationActual365L.mark

    val context : Timer.Context = DayCountController.invocationActual365LTimer.time

    import org.quantintel.ql.time.daycounters.Actual365
    import org.quantintel.ql.time.daycounters.Actual365Convention._

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val res = new SingleLongValue(Actual365(ACT365L).dayCount(startDate, endDate))

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
    nickname = "actual365Lyf",
    value = "day count fraction using the Actual/360l method",
    notes = "returns the day count fraction given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actual365LYF(
                    @ApiParam(value = "from date", required = true)
                    @PathParam("fromdate")
                    @Path("/daycount/{fromdate}/{todate}/Act365L/YF") fromdate: Long,
                    @ApiParam(value = "to date", required = true)
                    @PathParam("todate")
                    @Path("/daycount/{fromdate}/{todate}/Act365L/YF") todate: Long) = Action {

    DayCountController.invocationActual365LYF.mark

    val context : Timer.Context = DayCountController.invocationActual365LYFTimer.time

    import org.quantintel.ql.time.daycounters.Actual365
    import org.quantintel.ql.time.daycounters.Actual365Convention._

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val yf :Double = Actual365(ACT365L).yearFraction(startDate, endDate)

      val res = new SingleDoubleValue(yf.toDouble)

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

  // end 365L

  @ApiOperation(
    nickname = "actual365nl",
    value = "day count using the Actual/360NL method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actual365NL(
                   @ApiParam(value = "from date", required = true)
                   @PathParam("fromdate")
                   @Path("/daycount/{fromdate}/{todate}/Act365NL/DC") fromdate: Long,
                   @ApiParam(value = "to date", required = true)
                   @PathParam("todate")
                   @Path("/daycount/{fromdate}/{todate}/Act365NL/DC")todate: Long) = Action {

    DayCountController.invocationActual365NL.mark

    val context : Timer.Context = DayCountController.invocationActual365NLTimer.time

    import org.quantintel.ql.time.daycounters.Actual365
    import org.quantintel.ql.time.daycounters.Actual365Convention._

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val res = new SingleLongValue(Actual365(ACT365NL).dayCount(startDate, endDate))

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
    nickname = "actual365nlyf",
    value = "day count fraction using the Actual/360NL method",
    notes = "returns the day count fraction given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def actual365NLYF(
                     @ApiParam(value = "from date", required = true)
                     @PathParam("fromdate")
                     @Path("/daycount/{fromdate}/{todate}/Act365NL/YF") fromdate: Long,
                     @ApiParam(value = "to date", required = true)
                     @PathParam("todate")
                     @Path("/daycount/{fromdate}/{todate}/Act365NL/YF") todate: Long) = Action {

    DayCountController.invocationActual365NLYF.mark

    val context : Timer.Context = DayCountController.invocationActual365NLYFTimer.time

    import org.quantintel.ql.time.daycounters.Actual365
    import org.quantintel.ql.time.daycounters.Actual365Convention._

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val yf :Double = Actual365(ACT365NL).yearFraction(startDate, endDate)

      val res = new SingleDoubleValue(yf)

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

  // end 365NL




  // begin Actual/Actual (ISDA || HISTORICAL || ACTUAL365) && (AFB || EURO)


}


object DayCountController {

  // 360 method instrumentation

  val invocationActual360 : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-actual360")
  var invocationActual360Timer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual360Timer")

  val invocationActual360YF : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-actual360YF")
  var invocationActual360YFTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual360YFTimer")


  // 365 method instrumentation

  val invocationActual365F : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365F")
  var invocationActual365FTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365YFFTimer")

  val invocationActual365FYF : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365FYF")
  var invocationActual365FYFTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365FYFTimer")




  val invocationActual365NL : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365NL")
  var invocationActual365NLTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365NLTimer")

  val invocationActual365NLYF : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365NLYF")
  var invocationActual365NLYFTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365NLYFTimer")


  val invocationActual365L : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365L")
  var invocationActual365LTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365LTimer")

  val invocationActual365LYF : Meter = Instrumentation.metrics.meter(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365LYF")
  var invocationActual365LYFTimer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual365LYFTimer")


}