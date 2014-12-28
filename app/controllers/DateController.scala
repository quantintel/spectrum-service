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

import com.codahale.metrics.{Timer, Meter}
import grizzled.slf4j.Logging
import model._
import org.quantintel.ql.time.{Date => Dt}
import javax.ws.rs.{Path, PathParam}

import com.wordnik.swagger.annotations._

import play.api.mvc._


@Api(value ="/date",
  description = "Date related operations")
class DateController extends BaseApiController with Logging {

  /**
   *
   * @param path
   * @return
   */
  def getOptions(path: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }


  /**
   * This method returns todays date as a serial number.
   *
   * @return serial number representing today's date.
   */
  @ApiOperation(
    nickname = "today",
    value = "today's serial number",
    notes = "returns serial number of current date",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
      new ApiResponse(code = 400, message = "Required parameter missing"),
      new ApiResponse(code = 404, message = "todays serial number not found")))
  def today = Action {

    DateController.invocationToday.mark

    val context : Timer.Context = DateController.invocationTodayTimer.time

    val res = new SingleLongValue(Dt.todaysDate.serialNumber)

    context.stop()

    JsonResponse(res)

  }

  @ApiOperation(
    nickname = "serialNumber",
    value = "serial number of date: mm/dd/yyyy",
    notes = "returns serial number of the date provided",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  def serialNumber (
                    @ApiParam(value = "month", required = true)
                    @PathParam("mm")
                    @Path("/date/{mm}/{dd}/{yyyy}/serialnumber") mm : Int,
                    @ApiParam(value = "day", required = true)
                    @PathParam("dd")
                    @Path("/date/{mm}/{dd}/{yyyy}/serialnumber") dd: Int,
                    @ApiParam(value = "year", required = true)
                    @PathParam("yyyy")
                    @Path("/date/{mm}/{dd}/{yyyy}/serialnumber") yyyy: Int) = Action {

    DateController.invocationSerialNumber.mark

    val context : Timer.Context = DateController.invocationSerialNumberTimer.time

    try {



      import org.quantintel.ql.time.Month._

      var mon : Month = null

      mm match {
        case 1 => mon = JANUARY
        case 2 => mon = FEBRUARY
        case 3 => mon = MARCH
        case 4 => mon = APRIL
        case 5 => mon = MAY
        case 6 => mon = JUNE
        case 7 => mon = JULY
        case 8 => mon = AUGUST
        case 9 => mon = SEPTEMBER
        case 10 => mon = OCTOBER
        case 11 => mon = NOVEMBER
        case 12 => mon = DECEMBER
      }

      val res = new SingleLongValue(Dt(dd, mon, yyyy).serialNumber)
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


  /**
   * This method returns the current date formatted as mmddyyyy
   *
   * @return The current date
   */
  @ApiOperation(
    nickname = "todaySimpleFmt",
    value = "today's date in SimpleFormat",
    notes = "returns the current date formatted in SimpleFormat",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def todaySimpleFmt = Action {

    DateController.invocationTodaySimpleFmt.mark

    val context : Timer.Context = DateController.invocationTodaySimpleFmtTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt.todaysDate))
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





  /**
   * This method returns the current day of the week: Monday, Tuesday, etc.
   *
   * @return current day of the week.
   */
  @ApiOperation(
    nickname = "weekday",
    value = "the current day of the week",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def weekday = Action {

    DateController.invocationWeekday.mark

    val context : Timer.Context = DateController.invocationWeekdayTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt.todaysDate.weekday))
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
    nickname = "year",
    value = "year of the current date",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def year = Action {

    DateController.invocationYear.mark

    val context : Timer.Context = DateController.invocationYearTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt.todaysDate.year))

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
    nickname = "month",
    value = "month for the current date",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def month = Action {

    DateController.invocationMonth.mark

    val context : Timer.Context = DateController.invocationMonthTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt.todaysDate.month))

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
    nickname = "dayOfMonth",
    value = "day of the month for the current date.",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dayOfMonth = Action {

    DateController.invocationDayOfMonth.mark

    val context : Timer.Context = DateController.invocationDayOfMonthTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt.todaysDate.dayOfMonth))

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
    nickname = "dayOfYear",
    value = "day of the current year",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dayOfYear = Action {

    DateController.invocationDayOfYear.mark

    val context : Timer.Context = DateController.invocationDayOfYearTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt.todaysDate.dayOfYear))

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
    nickname = "isLeapYear",
    value = "returns whether the current year is a leap year.",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def isLeapYear = Action {

    DateController.invocationisLeapYear.mark

    val context : Timer.Context = DateController.invocationisLeapYearTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt.todaysDate.isLeapYear))

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

  // end of parameterless functions.


  /**
   * This method returns the simple formatted date of the serial number provided.
   *
   * @param num a serial number associated with a given date.
   * @return the mmddyyyy formatted version of the serial number.
   */
  @ApiOperation(
    nickname = "simpleFmt",
    value = "today's date",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET"
  )
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def simpleFmt(
                 @ApiParam(value = "date serial number", required = true)
                 @PathParam("serialNumber")
                 @Path("/date/{serialNumber}/mmddyyyy")
                 num: Long) = Action {

    DateController.invocationSimpleFmt.mark

    val context : Timer.Context = DateController.invocationSimpleFmtTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(num)))

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


  /**
   * This function returns the corresponding day of the week for the serial number provided.
   *
   * @param num serial number for a given date
   * @return the day of the week for the date indicated.
   */
  @ApiOperation(
    nickname = "dtWeekday",
    value = "the day of the week associated with the serial number provided",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtWeekday(
                 @ApiParam(value = "date serial number", required = true)
                 @PathParam("serialNumber")
                 @Path("/date/{serialNumber}/weekday")
                 num: Long) = Action {


    DateController.invocationDtWeekday.mark

    val context : Timer.Context = DateController.invocationDtWeekdayTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(num).weekday))

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
    nickname = "dtYear",
    value = "year of the serial number provided",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtYear (
               @ApiParam(value = "date serial number", required = true)
               @PathParam("serialNumber")
               @Path("/date/{serialNumber}/year")
               num: Long) = Action {


    DateController.invocationDtYear.mark

    val context : Timer.Context = DateController.invocationDtYearTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(num).year))

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
    nickname = "dtMonth",
    value = "month for the date provided",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtMonth (
                @ApiParam(value = "date serial number", required = true)
                @PathParam("serialNumber")
                @Path("/date/{serialNumber}/month")
                num: Long) = Action {

    DateController.invocationDtMonth.mark

    val context : Timer.Context = DateController.invocationDtMonthTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(num).month))
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
    nickname = "dtDayOfMonth",
    value = "day of the month for the date provided",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtDayOfMonth (
                     @ApiParam(value = "date serial number", required = true)
                     @PathParam("serialNumber")
                     @Path("/date/{serialNumber}/dayOfMonth")
                     num: Long) = Action {


    DateController.invocationDtDayOfMonth.mark

    val context : Timer.Context = DateController.invocationDtDayOfMonthTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(num).dayOfMonth))

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
    nickname = "dtDayOfYear ",
    value = "day of year for the date provided",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtDayOfYear (
                    @ApiParam(value = "date serial number", required = true)
                    @PathParam("serialNumber")
                    @Path("/date/{serialNumber}/dayOfYear")
                    num: Long)  = Action {


    DateController.invocationDtDayOfYear.mark

    val context : Timer.Context = DateController.invocationDtDayOfYearTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(num).dayOfYear))

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
    nickname = "dtIsLeapYear",
    value = "returns whether the date provided is within a leap year.",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtIsLeapYear (
                     @ApiParam(value = "date serial number", required = true)
                     @PathParam("serialNumber")
                     @Path("/date/{serialNumber}/isLeapYear")
                     num: Long)  = Action {


    DateController.invocationDtIsLeapYear.mark

    val context : Timer.Context = DateController.invocationDtIsLeapYearTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(num).isLeapYear))

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



  // end of serial number based functions


  /**
   * Returns the current weekday based upon the mm, dd, yyyy indicated.
   *
   * @param mm two digits representing the current month 01, 02, etc.
   * @param dd two digit representing the current day of the month 01, 02, etc.
   * @param yyyy four digit integer representing the current year.
   * @return The day of the week for the date indicated.
   */
  @ApiOperation(
    nickname = "dtstrWeekday",
    value = "the day of the week for the corresponding dd, mm, yyyy indicated",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrWeekday( @ApiParam(value = "mm - month", required = true)
                    @PathParam("mm")
                    @Path("/date/{mm}/{dd}/{yyyy}/weekday")
                    mm: Int,
                    @ApiParam(value = "dd - day of month", required = true)
                    @PathParam("dd")
                    @Path("/date/{mm}/{dd}/{yyyy}/weekday")
                    dd: Int,
                    @ApiParam(value = "yy - yyyy year", required = true)
                    @PathParam("yyyy")
                    @Path("/date/{mm}/{dd}/{yyyy}/weekday")
                    yyyy: Int) = Action {


    DateController.invocationDtstrWeekday.mark

    val context : Timer.Context = DateController.invocationDtstrWeekdayTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).weekday))

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
    nickname = "dtstrYear",
    value = "date for the mm dd and yy indicated.",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrYear ( @ApiParam(value = "mm - month", required = true)
                  @PathParam("mm")
                  @Path("/date/{mm}/{dd}/{yyyy}/year")
                  mm: Int,
                  @ApiParam(value = "dd - day of month", required = true)
                  @PathParam("dd")
                  @Path("/date/{mm}/{dd}/{yyyy}/year")
                  dd: Int,
                  @ApiParam(value = "yy - yyyy year", required = true)
                  @PathParam("yyyy")
                  @Path("/date/{mm}/{dd}/{yyyy}/year")
                  yyyy: Int) = Action {


    DateController.invocationDtstrYear.mark

    val context : Timer.Context = DateController.invocationDtstrYearTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).year))

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
    nickname = "dtstrMonth",
    value = "month for the date provided",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrMonth ( @ApiParam(value = "mm - month", required = true)
                   @PathParam("mm")
                   @Path("/date/{mm}/{dd}/{yyyy}/month")
                   mm: Int,
                   @ApiParam(value = "dd - day of month", required = true)
                   @PathParam("dd")
                   @Path("/date/{mm}/{dd}/{yyyy}/month")
                   dd: Int,
                   @ApiParam(value = "yy - yyyy year", required = true)
                   @PathParam("yyyy")
                   @Path("/date/{mm}/{dd}/{yyyy}/month")
                   yyyy: Int) = Action {


    DateController.invocationDtstrMonth.mark

    val context : Timer.Context = DateController.invocationDtstrMonthTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).month))

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
    nickname = "dtstrDayOfMonth",
    value = "day of the month for the date provided",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrDayOfMonth ( @ApiParam(value = "mm - month", required = true)
                        @PathParam("mm")
                        @Path("/date/{mm}/{dd}/{yyyy}/dayOfMonth")
                        mm: Int,
                        @ApiParam(value = "dd - day of month", required = true)
                        @PathParam("dd")
                        @Path("/date/{mm}/{dd}/{yyyy}/dayOfMonth")
                        dd: Int,
                        @ApiParam(value = "yy - yyyy year", required = true)
                        @PathParam("yyyy")
                        @Path("/date/{mm}/{dd}/{yyyy}/dayOfMonth")
                        yyyy: Int) = Action {

    DateController.invocationDtstrDayOfMonth.mark

    val context : Timer.Context = DateController.invocationDtstrDayOfMonthTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).dayOfMonth))

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
    nickname = "dtstrDayOfYear ",
    value = "day of year for the date provided",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrDayOfYear (@ApiParam(value = "mm - month", required = true)
                      @PathParam("mm")
                      @Path("/date/{mm}/{dd}/{yyyy}/dayOfYear")
                      mm: Int,
                      @ApiParam(value = "dd - day of month", required = true)
                      @PathParam("dd")
                      @Path("/date/{mm}/{dd}/{yyyy}/dayOfYear")
                      dd: Int,
                      @ApiParam(value = "yy - yyyy year", required = true)
                      @PathParam("yyyy")
                      @Path("/date/{mm}/{dd}/{yyyy}/dayOfYear")
                      yyyy: Int)  = Action {

    DateController.invocationDtstrDayOfYear.mark

    val context : Timer.Context = DateController.invocationDtstrDayOfYearTimer.time

    try {
      val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).dayOfYear))

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
    nickname = "dtstrIsLeapYear ",
    value = "returns whether the date provided is within a leap year.",
    notes = "",
    response = classOf[model.SingleStringValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrIsLeapYear (@ApiParam(value = "mm - month", required = true)
                       @PathParam("mm")
                       @Path("/date/{mm}/{dd}/{yyyy}/isLeapYear")
                       mm: Int,
                       @ApiParam(value = "dd - day of month", required = true)
                       @PathParam("dd")
                       @Path("/date/{mm}/{dd}/{yyyy}/isLeapYear")
                       dd: Int,
                       @ApiParam(value = "yy - yyyy year", required = true)
                       @PathParam("yyyy")
                       @Path("/date/{mm}/{dd}/{yyyy}/isLeapYear")
                       yyyy: Int)  = Action {


    DateController.invocationDtstrIsLeapYear.mark

    val context : Timer.Context = DateController.invocationDtstrIsLeapYearTimer.time

    try {

      val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).isLeapYear))
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


  // utility methods

  @ApiOperation(
    nickname = "incr",
    value = "increments the current days serial number by one.",
    notes = "",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def incr() = Action {

    DateController.invocationIncr.mark

    val context : Timer.Context = DateController.invocationIncrTimer.time

    try {
      val res = new SingleLongValue(Dt.todaysDate.serialNumber + 1)
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
    nickname = "incrBy",
    value = "increments the current days serial number by the number of units indicated.",
    notes = "",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def incrBy(
              @ApiParam(value = "Number of days to increment.", required = true)
              @PathParam("n")
              @Path("/date/today/{n}/incr") n: Int) = Action {

    DateController.invocationIncrBy.mark

    val context : Timer.Context = DateController.invocationIncrByTimer.time

    try {

      val res = new SingleLongValue(Dt.todaysDate.serialNumber + n)

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
    nickname = "decr",
    value = "decrements the current days serial number by one.",
    notes = "",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def decr() = Action {

    DateController.invocationDecr.mark

    val context : Timer.Context = DateController.invocationDecrTimer.time

    try {

      val res = new SingleLongValue(Dt.todaysDate.serialNumber - 1)

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
    nickname = "decrBy",
    value = "decrements the current days serial number by the number of units indicated.",
    notes = "",
    response = classOf[model.SingleLongValue],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def decrBy(
              @ApiParam(value = "Number of days to decrement", required = true)
              @PathParam("n")
              @Path("/date/today/{n}/decr") n: Int) = Action {

    DateController.invocationDecrBy.mark

    val context : Timer.Context = DateController.invocationDecrByTimer.time

    try {

      val res = new SingleLongValue(Dt.todaysDate.serialNumber - n)

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

object DateController {

  val invocationToday : Meter = Instrumentation.metrics.meter("invocation-today")
  var invocationTodayTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." + "invocation-todayTimer")

  val invocationSerialNumber : Meter  = Instrumentation.metrics.meter("invocation-serialNumber")
  var invocationSerialNumberTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." + "invocation-serialNumberTimer")

  val invocationTodaySimpleFmt : Meter  = Instrumentation.metrics.meter("invocation-todaySimpleFmt")
  var invocationTodaySimpleFmtTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." + "invocation-todaySimpleFmtTimer")

  val invocationWeekday : Meter  = Instrumentation.metrics.meter("invocation-weekday")
  var invocationWeekdayTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." + "invocation-weekdayTimer")

  val invocationYear : Meter  = Instrumentation.metrics.meter("invocation-year")
  var invocationYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-yearTimer")

  val invocationMonth : Meter  = Instrumentation.metrics.meter("invocation-month")
  var invocationMonthTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-monthTimer")

  val invocationDayOfMonth : Meter  = Instrumentation.metrics.meter("invocation-dayOfMonth")
  var invocationDayOfMonthTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dayOfMonthTimer")

  val invocationDayOfYear : Meter  = Instrumentation.metrics.meter("invocation-dayOfYear")
  var invocationDayOfYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dayOfYearTimer")

  val invocationisLeapYear : Meter  = Instrumentation.metrics.meter("invocation-isLeapYear")
  var invocationisLeapYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-isLeapYearTimer")

  val invocationSimpleFmt : Meter  = Instrumentation.metrics.meter("invocation-simpleFmt")
  var invocationSimpleFmtTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-simpleFmtTimer")

  val invocationDtWeekday : Meter  = Instrumentation.metrics.meter("invocation-dtWeekday")
  var invocationDtWeekdayTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtWeekdayTimer")

  val invocationDtYear : Meter  = Instrumentation.metrics.meter("invocation-dtYear")
  var invocationDtYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtYearTimer")

  val invocationDtMonth  : Meter  = Instrumentation.metrics.meter("invocation-dtMonth")
  var invocationDtMonthTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtMonthTimer")

  val invocationDtDayOfMonth  : Meter  = Instrumentation.metrics.meter("invocation-dtDayOfMonth")
  var invocationDtDayOfMonthTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtDayOfMonthTimer")

  val invocationDtDayOfYear  : Meter  = Instrumentation.metrics.meter("invocation-dtDayOfYear")
  var invocationDtDayOfYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtDayOfYearTimer")

  val invocationDtIsLeapYear  : Meter  = Instrumentation.metrics.meter("invocation-dtIsLeapYear")
  var invocationDtIsLeapYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtIsLeapYearTimer")

  val invocationDtstrWeekday : Meter  = Instrumentation.metrics.meter("invocation-dtstrWeekday")
  var invocationDtstrWeekdayTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtstrWeekdayTimer")

  val invocationDtstrYear : Meter  = Instrumentation.metrics.meter("invocation-dtstrYear")
  var invocationDtstrYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtstrYearTimer")

  val invocationDtstrMonth : Meter  = Instrumentation.metrics.meter("invocation-dtstrMonth")
  var invocationDtstrMonthTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtstrMonthTimer")

  val invocationDtstrDayOfMonth : Meter  = Instrumentation.metrics.meter("invocation-dtstrDayOfMonth")
  var invocationDtstrDayOfMonthTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtstrDayOfMonthTimer")

  val invocationDtstrDayOfYear : Meter  = Instrumentation.metrics.meter("invocation-dtstrDayOfYear")
  var invocationDtstrDayOfYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtstrDayOfYearTimer")

  val invocationDtstrIsLeapYear : Meter  = Instrumentation.metrics.meter("invocation-dtstrIsLeapYear")
  var invocationDtstrIsLeapYearTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-dtstrIsLeapYearTimer")

  val invocationIncr: Meter  = Instrumentation.metrics.meter("invocation-incr")
  var invocationIncrTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-incrTimer")

  val invocationIncrBy: Meter  = Instrumentation.metrics.meter("invocation-incrBy")
  var invocationIncrByTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-incrByTimer")

  val invocationDecr: Meter  = Instrumentation.metrics.meter("invocation-decr")
  var invocationDecrTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-decrTimer")

  val invocationDecrBy: Meter  = Instrumentation.metrics.meter("invocation-decrBy")
  var invocationDecrByTimer : Timer  = Instrumentation.metrics.timer(DateController.getClass.getCanonicalName + "." +  "invocation-decrByTimer")


}