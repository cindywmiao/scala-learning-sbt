package std

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by Cindy.Wang on 9/18/16.
  */
object BynameParametersTest extends FlatSpec with Matchers {
  /** `() => Int` is a Function type that takes a `Unit` type. `Unit` is known as `void` to a Java programmer. The function returns an `Int`. You can place this as a method parameter so that you can you use it as a block, but still it doesn't look quite right.
    */
  def takesUnitByNameParameter() {
    def calc(x: () ⇒ Int): Either[Throwable, Int] = {
      try {
        Right(x()) //An explicit call the x function
      } catch {
        case b: Throwable ⇒ Left(b)
      }
    }

    val y = calc { () ⇒ //Having explicitly declaring that Unit is a parameter with ()
      14 + 15
    }

    println(y)
    //y should be(res0)
  }

  /** A by-name parameter does the same thing as the previous koan but there is no need to explicitly handle `Unit` or `()`. This is used extensively in scala to create blocks.
    */
  def byNameParameter() {
    def calc(x: ⇒ Int): Either[Throwable, Int] = {
      //x is a call by name parameter
      try {
        Right(x)
      } catch {
        case b: Throwable ⇒ Left(b)
      }
    }

    val y = calc {
      //This looks like a natural block
      println("Here we go!") //Some superfluous call
      val z = List(1, 2, 3, 4) //Another superfluous call
      49 + 20
    }

    println(y)
    //y should be(res0)
  }

  /** By name parameters can also be used with an *Object* and apply to make interesting block-like calls
    */
  def withApplyByNameParameter() {
    object PigLatinizer {
      def apply(x: ⇒ String) = x.tail + x.head + "ay"
    }

    val result = PigLatinizer {
      val x = "pret"
      val z = "zel"
      x ++ z //concatenate the strings
    }

    println(result)
    //result should be(res0)
  }

  def main(args: Array[String]): Unit = {
    takesUnitByNameParameter()
    byNameParameter()
    withApplyByNameParameter()
  }

}
