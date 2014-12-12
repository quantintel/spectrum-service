package model

import com.wordnik.swagger.annotations._

import scala.annotation.meta.field

/**
 * Container object for returning a single value of type String
 *
 * @author Paul Bernard
 */
@ApiModel("SingleStringValue")
case class SingleStringValue(@(ApiModelProperty @field)(position=1) value: String)
