package model

import com.wordnik.swagger.annotations._

import scala.annotation.meta.field

/**
 * Container object for returning a single value of type Long
 *
 * @author Paul Bernard
 */
@ApiModel("SingleValue")
case class SingleLongValue(@(ApiModelProperty @field)(position=1) value: Long)
