/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2020 Vavr, http://vavr.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.vavr.kotlin

import io.vavr.control.Option
import io.vavr.kotlin.control.none
import io.vavr.kotlin.control.some
import org.junit.Test

class ValueTest {
    @Test
    fun wrapperBasics() {
        val os0 = some("Hello") // inferred type: io.vavr.control.Option<String>
        val ks0 = os0.toKotlin() // inferred type: io.vavr.kotlin.Value<String!> --- why???

        val os = some("Hello") as io.vavr.Value<String>
        val ks = os.toKotlin() // inferred type: io.vavr.kotlin.Value<String>
        val none = none<String>() as io.vavr.Value<String>
        val knone = none.toKotlin()

        assert(!ks.isEmpty())
        val (s) = ks
        assert(s == "Hello")

        assert(knone.isEmpty())

        assert(Option.some("Hello").toKotlin() == ks)
        assert(Option.some("Hello").toKotlin().hashCode() == ks.hashCode())
        assert(Option.some("Hello").hashCode() == ks.hashCode())
        assert(Option.some("Hello").toString() == ks.toString())
    }

    open class A(val s: String)
    open class B(val t: String): A("B")

    private fun aConsumer(a: io.vavr.kotlin.Value<A>) { }

    @Test
    fun subtypesAreSubstitutable() {
        val va = some(A("Test")) // inferred type: io.vavr.control.Option<A>
        val ka = va.toKotlin() // inferred type: io.vavr.kotlin.Value<A!> --- why???
        val vb = some(B("Test 2")) as io.vavr.Value<B>
        val kb = vb.toKotlin()

        aConsumer(ka)
        aConsumer(kb) // note: no need for narrowing or casting!

        val o = Option.some("Hello") as Option<String>
        val ko = o.toKotlin()
    }

    @Test
    fun nullableTypeParametersWork() {
        val va = some<String?>(null) // inferred type: io.vavr.control.Option<String?>
        val ka = va.toKotlin() // inferred type: io.vavr.kotlin.Value<String?>

        assert(!ka.isEmpty())
        assert(ka() == null)
    }
}
