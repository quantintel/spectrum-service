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

import grizzled.slf4j.Logging
import play.api.mvc.Action

/**
 * This controller is responsible for creation, retrieval and maintenance
 * of Calendar entities.  While there are a variety of pre-configured
 * calendars, it is generally assumed that such calendars are only used
 * as a "base" calendar that will then be customized for particular use
 * cases.
 *
 * Calendars are stateful in that their state persists beyond a single
 * invocation.  Each calendar instance is associated with a particular
 * user and has its own id that is unique within the context of a given
 * user.
 *
 * @author Paul Bernard
 */
class CalendarController extends BaseApiController with Logging{

  /**
   *
   * @param path
   * @return
   */
  def getOptions(path: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  /**
   * Returns a calendar belonging to a given user with a given id.
   *
   * @param userid
   * @param id
   * @return
   */
  def calendar(userid: String, id: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  /**
   * Returns a new calendar. If westOrth = WESTERN then a western
   * calendar will be created.  if westOrth = ORTHODOX then an Orthodox
   * calendar will be created.  if the field is left null a calendar
   * will be created with no recognition of easter or weekends.
   *
   * @param userid
   * @param id
   * @param westOrth
   * @return
   */
  def calendar(userid: String, id: String, westOrth: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  /**
   * Creates a "clone" of an existing calendar with the "to" name specified
   * if the "to" name already exists, it will be overwritten.
   *
   * @param userid
   * @param from
   * @param to
   * @return
   */
  def cloneCalendar(userid: String, from: String, to: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  /**
   * Adds a holiday to an existing calendar associated with a given user.
   *
   * @param userid
   * @param id
   * @param mm
   * @param dd
   * @param yyyy
   * @return
   */
  def addHoliday(userid: String, id: String, mm: Int, dd: Int, yyyy: Int) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  /**
   * removes a holiday from an existing calendar associated with a given user.
   *
   * @param userid
   * @param id
   * @param mm
   * @param dd
   * @param yyyy
   * @return
   */
  def removeHoliday(userid: String, id: String, mm: Int, dd: Int, yyyy: Int) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def isBusinessDay(userid: String, id: String, mm: Int, dd: Int, yyyy: Int) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def isWeekend(userid: String, id: String, mm: Int, dd: Int, yyyy: Int) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def isEndOfMonth(userid: String, id: String, mm: Int, dd: Int, yyyy: Int) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def holidayList(userid: String, id: String, fmm: Int, fdd: Int, fyyyy: Int,
                  tmm: Int, tdd: Int, tyyyy: Int, includeWeekEnds : Boolean) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def adjust(userid: String, id: String, mm: Int, dd: Int, yyyy: Int) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def adjust(userid: String, id: String, mm: Int, dd: Int, yyyy: Int, convention: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def advance(userid: String, id: String, mm: Int, dd: Int, yyyy: Int, period: String, convention: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def advance(userid: String, id: String, mm: Int, dd: Int, yyyy: Int, period: String,
              convention: String, endOfMonth: Boolean) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def advance(userid: String, id: String, mm: Int, dd: Int, yyyy: Int,
              n: Int, unit: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def advance(userid: String, id: String, mm: Int, dd: Int, yyyy: Int, period: String) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def advance(userid: String, id: String, mm: Int, dd: Int, yyyy: Int, n: Int, unit: String,
               convention: String, endOfMonth: Boolean) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def businessDaysBetween(userid: String, id: String, fmm: Int, fdd: Int, fyyyy: Int,
                  tmm: Int, tdd: Int, tyyyy: Int) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }

  def businessDaysBetween(userid: String, id: String, fmm: Int, fdd: Int, fyyyy: Int,
                          tmm: Int, tdd: Int, tyyyy: Int, includeLast: Boolean, includeFirst: Boolean) = Action {
    implicit request => JsonResponse(new value.ApiResponse(200, "Ok"))
  }


}

object CalendarController {

}
