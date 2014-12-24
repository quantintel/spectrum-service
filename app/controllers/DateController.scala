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

import model._
import org.quantintel.ql.time.{Date => Dt}
import javax.ws.rs.{Path, QueryParam, PathParam}

import com.wordnik.swagger.annotations._

import play.api.mvc._


@Api(value ="/date",
  description = "Date related operations")
class DateController extends BaseApiController {

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

    val res = new SingleLongValue(Dt.todaysDate.serialNumber)
    JsonResponse(res)

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

    val res = new SingleStringValue(String.valueOf(Dt.todaysDate))
    JsonResponse(res)

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
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.weekday))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.year))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.month))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.dayOfMonth))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.dayOfYear))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.isLeapYear))
    JsonResponse(res)
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

    val res = new SingleStringValue(String.valueOf(Dt(num)))
    JsonResponse(res)

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
    val res = new SingleStringValue(String.valueOf(Dt(num).weekday))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(num).year))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(num).month))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(num).dayOfMonth))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(num).dayOfYear))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(num).isLeapYear))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).weekday))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).year))
    JsonResponse(res)
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
                   @PathParam("yy")
                   @Path("/date/{mm}/{dd}/{yyyy}/month")
                   yyyy: Int) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).month))
    JsonResponse(res)
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
                        @PathParam("yy")
                        @Path("/date/{mm}/{dd}/{yyyy}/dayOfMonth")
                        yyyy: Int) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).dayOfMonth))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).dayOfYear))
    JsonResponse(res)
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
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yyyy).isLeapYear))
    JsonResponse(res)
  }



}

object DateController {}