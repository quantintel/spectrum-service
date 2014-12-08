package model

import com.wordnik.swagger.annotations._

import scala.annotation.meta.field

/**
 * Created by paulbernard on 12/7/14.
 */
@ApiModel("SingleValue")
case class SingleValue(@(ApiModelProperty @field)(position=1) value: Long)
