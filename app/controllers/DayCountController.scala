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
import model.SingleLongValue
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
                 @Path("/daycount/{fromdate}/{todate}/actual360") fromdate: Long,
                 @ApiParam(value = "to date", required = true)
                 @PathParam("todate")
                 @Path("/daycount/{fromdate}/{todate}/actual360") todate: Long) = Action {

    DayCountController.invocationActual360 .mark

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



}


object DayCountController {

  val invocationActual360 : Meter = Instrumentation.metrics.meter("invocation-actual360")
  var invocationActual360Timer : Timer  = Instrumentation.metrics.timer(DayCountController.getClass.getCanonicalName + "." + "invocation-actual360Timer")


}