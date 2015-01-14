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

import com.codahale.metrics.{Timer, Meter}
import com.wordnik.swagger.annotations.{ApiParam, ApiResponse, ApiResponses, ApiOperation}
import grizzled.slf4j.Logging
import model.{SingleDoubleValue, SingleLongValue}
import org.quantintel.ql.time.calendars.Brazil
import org.quantintel.ql.time.{Date => Dt}
import play.api.mvc.Action

/**
 * @author Paul Bernard
 */
class DayCountBus252Controller extends BaseApiController with Logging {


  // TODO: current the method is hard coded with the brazilian holiday, this needs to be parameterized.

  /**
   *
   * @param path
   * @return
   */
  def getOptions(path: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  @ApiOperation(
    nickname = "business252",
    value = "day count using the Business/252 method",
    notes = "returns the day count given a start and end date as serial numbers",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def bus252(
                  @ApiParam(value = "from date", required = true)
                  @PathParam("fromdate")
                  @Path("/daycount/{fromdate}/{todate}/{calendar}/{session}/Bus252/DC") fromdate: Long,

                  @ApiParam(value = "to date", required = true)
                  @PathParam("todate")
                  @Path("/daycount/{fromdate}/{todate}/{calendar}/{session}/Bus252/DC") todate: Long,

                  @ApiParam(value = "calendar id", required = true)
                  @PathParam("calendar")
                  @Path("/daycount/{fromdate}/{todate}/{calendar}/{session}/Bus252/DC") calendar: String,

                  @ApiParam(value = "session id", required = true)
                  @PathParam("session")
                  @Path("/daycount/{fromdate}/{todate}/{calendar}/{session}/Bus252/DC") session: String) = Action {

    DayCountBus252Controller.invocationBus252.mark

    val context : Timer.Context = DayCountBus252Controller.invocationBus252Timer.time

    import org.quantintel.ql.time.daycounters.Business252._
    import org.quantintel.ql.time.calendars._
    import org.quantintel.lang.collections.distributed.Cache

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)

      val cal = Cache.get(session).calendar.get(calendar)
      cal match {
        case Some(f) =>{

          val res = new SingleLongValue(new Business252(f).dayCount(startDate, endDate))

          JsonResponse(res)

        }
        case None => {

          JsonResponse("Calendar was not found. Create calendar before use.")
        }
      }



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
    nickname = "business252yf",
    value = "day count fraction using the Business 252 method",
    notes = "returns the day count fraction given a start and end date as serial numbers",
    response = classOf[model.SingleDoubleValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Required parameter missing"),
    new ApiResponse(code = 404, message = "function error")))
  def bus252YF (
                    @ApiParam(value = "from date", required = true)
                    @PathParam("fromdate")
                    @Path("/daycount/{fromdate}/{todate}/Bus252/YF")fromdate: Long,
                    @ApiParam(value = "to date", required = true)
                    @PathParam("todate")
                    @Path("/daycount/{fromdate}/{todate}/Bus252/YF")todate: Long) = Action {

    DayCountBus252Controller.invocationBus252YF.mark

    val context : Timer.Context = DayCountBus252Controller.invocationBus252YFTimer.time

    import org.quantintel.ql.time.daycounters.Business252._

    try {

      val startDate = Dt(fromdate)
      val endDate = Dt(todate)
      val calendar: org.quantintel.ql.time.Calendar = new Brazil()

      val yf :Double = new Business252(calendar).yearFraction(startDate, endDate)

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


}

object DayCountBus252Controller {


  val invocationBus252 : Meter = Instrumentation.metrics.meter(DayCountBus252Controller.getClass.getCanonicalName + "." + "invocation-Bus252")
  var invocationBus252Timer : Timer  = Instrumentation.metrics.timer(DayCountBus252Controller.getClass.getCanonicalName + "." + "invocation-Bus252Timer")

  val invocationBus252YF : Meter = Instrumentation.metrics.meter(DayCountBus252Controller.getClass.getCanonicalName + "." + "invocation-Bus252YF")
  var invocationBus252YFTimer : Timer  = Instrumentation.metrics.timer(DayCountBus252Controller.getClass.getCanonicalName + "." + "invocation-Bus252YF")

}
