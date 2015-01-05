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
import com.wordnik.swagger.annotations.{ApiParam, ApiResponse, ApiResponses, ApiOperation}
import grizzled.slf4j.Logging
import model.{SingleDoubleValue, SingleLongValue}
import org.quantintel.ql.time.{Date => Dt}
import play.api.mvc.Action

/**
 * @author Paul Bernard
 */
class DayCountAct365Controller extends BaseApiController with Logging {

  @ApiOperation(
    nickname = "actual365F",
    value = "day count using the Actual/365F method",
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

    DayCountAct365Controller.invocationActual365F.mark

    val context : Timer.Context = DayCountAct365Controller.invocationActual365FTimer.time

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
    value = "day count fraction using the Actual/365F method",
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

    DayCountAct365Controller.invocationActual365FYF.mark

    val context : Timer.Context = DayCountAct365Controller.invocationActual365FYFTimer.time

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
    value = "day count using the Actual/365L method",
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

    DayCountAct365Controller.invocationActual365L.mark

    val context : Timer.Context = DayCountAct365Controller.invocationActual365LTimer.time

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
    value = "day count fraction using the Actual/365L method",
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

    DayCountAct365Controller.invocationActual365LYF.mark

    val context : Timer.Context = DayCountAct365Controller.invocationActual365LYFTimer.time

    import org.quantintel.ql.time.daycounters.Actual365
    import org.quantintel.ql.time.daycounters.Actual365Convention._

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val yf :Double = Actual365(ACT365L).yearFraction(startDate, endDate)

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

  // end 365L

  @ApiOperation(
    nickname = "actual365nl",
    value = "day count using the Actual/365NL method",
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

    DayCountAct365Controller.invocationActual365NL.mark

    val context : Timer.Context = DayCountAct365Controller.invocationActual365NLTimer.time

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
    value = "day count fraction using the Actual/365NL method",
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

    DayCountAct365Controller.invocationActual365NLYF.mark

    val context : Timer.Context = DayCountAct365Controller.invocationActual365NLYFTimer.time

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

}

object DayCountAct365Controller {


  // 365 method instrumentation

  val invocationActual365F : Meter = Instrumentation.metrics.meter(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365F")
  var invocationActual365FTimer : Timer  = Instrumentation.metrics.timer(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365YFFTimer")

  val invocationActual365FYF : Meter = Instrumentation.metrics.meter(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365FYF")
  var invocationActual365FYFTimer : Timer  = Instrumentation.metrics.timer(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365FYFTimer")


  val invocationActual365NL : Meter = Instrumentation.metrics.meter(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365NL")
  var invocationActual365NLTimer : Timer  = Instrumentation.metrics.timer(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365NLTimer")

  val invocationActual365NLYF : Meter = Instrumentation.metrics.meter(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365NLYF")
  var invocationActual365NLYFTimer : Timer  = Instrumentation.metrics.timer(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365NLYFTimer")


  val invocationActual365L : Meter = Instrumentation.metrics.meter(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365L")
  var invocationActual365LTimer : Timer  = Instrumentation.metrics.timer(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365LTimer")

  val invocationActual365LYF : Meter = Instrumentation.metrics.meter(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365LYF")
  var invocationActual365LYFTimer : Timer  = Instrumentation.metrics.timer(DayCountAct365Controller.getClass.getCanonicalName + "." + "invocation-actual365LYFTimer")

}