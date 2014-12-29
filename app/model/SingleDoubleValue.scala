package model

import com.wordnik.swagger.annotations._

import scala.annotation.meta.field

/**
 * Container object for returning a single value of type Double
 *
 * @author Paul Bernard
 */
@ApiModel("SingleDoubleValue")
case class SingleDoubleValue(@(ApiModelProperty @field)(position=1) value: Double)
