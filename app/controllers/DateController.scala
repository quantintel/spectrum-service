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
    response = classOf[scala.Long],
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
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def todaySimpleFmt = Action {

    val res = new SingleStringValue(String.valueOf(Dt.todaysDate))
    JsonResponse(res)

  }


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
    response = classOf[String],
    httpMethod = "GET"
    )
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def simpleFmt(
        @ApiParam(value = "date serial number", required = true)
        @PathParam("serialNumber")
        @Path("/date/(serialNumber}/mmddyyyy")
        num: Long) = Action {

    val res = new SingleStringValue(String.valueOf(Dt(num)))
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
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def weekday = Action {
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.weekday))
    JsonResponse(res)
  }

  /**
   * This function returns the cooresponding day of the week for the serial number provided.
   *
   * @param num serial number for a given date
   * @return the day of the week for the date indicated.
   */
  @ApiOperation(
    nickname = "dtWeekday",
    value = "the day of the week associated with the serial number provided",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtWeekday(
                 @ApiParam(value = "date serial number", required = true)
                 @PathParam("serialNumber")
                 @Path("/date/(serialNumber}/weekday")
                 num: Long) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(num).weekday))
    JsonResponse(res)
  }


  /**
   * Returns the current weekday based upon the mm, dd, yyyy indicated.
   *
   * @param mm two digits representing the current month 01, 02, etc.
   * @param dd two digit representing the current day of the month 01, 02, etc.
   * @param yy four digit integer representing the current year.
   * @return The day of the week for the date indicated.
   */
  @ApiOperation(
    nickname = "dtstrWeekday",
    value = "the day of the week for the corresponding dd, mm, yyyy indicated",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrWeekday(mm: Int, dd: Int, yy: Int) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yy).weekday))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "year",
    value = "year of the current date",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def year = Action {
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.year))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "dtYear",
    value = "year of the serial number provided",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtYear (
               @ApiParam(value = "date serial number", required = true)
               @PathParam("serialNumber")
               @Path("/date/(serialNumber}/year")
               num: Long) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(num).year))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "dtstrYear",
    value = "date for the mm dd and yy indicated.",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrYear (mm: Int, dd: Int, yy: Int) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yy).year))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "month",
    value = "month for the current date",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def month = Action {
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.month))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "dtMonth",
    value = "month for the date provided",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtMonth (
                @ApiParam(value = "date serial number", required = true)
                @PathParam("serialNumber")
                @Path("/date/(serialNumber}/month")
                num: Long) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(num).month))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "dtstrMonth",
    value = "month for the date provided",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrMonth (mm: Int, dd: Int, yy: Int) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yy).month))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "dayOfMonth",
    value = "day of the month for the current date.",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dayOfMonth = Action {
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.dayOfMonth))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "dtDayOfMonth",
    value = "day of the month for the date provided",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtDayOfMonth (
                     @ApiParam(value = "date serial number", required = true)
                     @PathParam("serialNumber")
                     @Path("/date/(serialNumber}/dayOfMonth")
                     num: Long) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(num).dayOfMonth))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "dtstrDayOfMonth",
    value = "day of the month for the date provided",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrDayOfMonth (mm: Int, dd: Int, yy: Int) = Action {
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yy).dayOfMonth))
    JsonResponse(res)
  }


  @ApiOperation(
    nickname = "dayOfYear",
    value = "day of the current year",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dayOfYear = Action {
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.dayOfYear))
    JsonResponse(res)
  }


  @ApiOperation(
    nickname = "dtDayOfYear ",
    value = "day of year for the date provided",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtDayOfYear (
                    @ApiParam(value = "date serial number", required = true)
                    @PathParam("serialNumber")
                    @Path("/date/(serialNumber}/dayOfYear")
                    num: Long)  = Action {
    val res = new SingleStringValue(String.valueOf(Dt(num).dayOfYear))
    JsonResponse(res)
  }


  @ApiOperation(
    nickname = "dtstrDayOfYear ",
    value = "day of year for the date provided",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrDayOfYear (mm: Int, dd: Int, yy: Int)  = Action {
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yy).dayOfYear))
    JsonResponse(res)
  }



  @ApiOperation(
    nickname = "isLeapYear",
    value = "returns whether the current year is a leap year.",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def isLeapYear = Action {
    val res = new SingleStringValue(String.valueOf(Dt.todaysDate.isLeapYear))
    JsonResponse(res)
  }


  @ApiOperation(
    nickname = "dtIsLeapYear",
    value = "returns whether the date provided is within a leap year.",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtIsLeapYear (
                     @ApiParam(value = "date serial number", required = true)
                     @PathParam("serialNumber")
                     @Path("/date/(serialNumber}/isLeapYear")
                     num: Long)  = Action {
    val res = new SingleStringValue(String.valueOf(Dt(num).isLeapYear))
    JsonResponse(res)
  }


  @ApiOperation(
    nickname = "dtstrIsLeapYear ",
    value = "returns whether the date provided is within a leap year.",
    notes = "",
    response = classOf[String],
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message="Request cannot be satisfied with parameters provided.")
  ))
  def dtstrIsLeapYear (mm: Int, dd: Int, yy: Int)  = Action {
    val res = new SingleStringValue(String.valueOf(Dt(dd, mm, yy).isLeapYear))
    JsonResponse(res)
  }



}

object DateController {}