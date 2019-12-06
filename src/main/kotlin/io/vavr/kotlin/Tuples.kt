/*  __    __  __  __    __  ___
 * \  \  /  /    \  \  /  /  __/
 *  \  \/  /  /\  \  \/  /  /
 *   \____/__/  \__\____/__/
 *
 * Copyright 2014-2017 Vavr, http://vavr.io
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

import io.vavr.*

/**
 * Constructors and sequence() extensions for the Vavr Tuple
 *
 * @author Alex Zuzin (github.com/zvozin)
 */

/**
 * @see Tuple.empty
 */
fun emptyTuple():
        Tuple0 = Tuple.empty()

/**
 * @see Tuple.of
 */
fun <T1> tuple(t1: T1):
        Tuple1<T1> = Tuple.of(t1)

/**
 * @see Tuple.of
 */
fun <T1, T2> tuple(t1: T1, t2: T2):
        Tuple2<T1, T2> = Tuple.of(t1, t2)

/**
 * @see Tuple.of
 */
fun <T1, T2, T3> tuple(t1: T1, t2: T2, t3: T3):
        Tuple3<T1, T2, T3> = Tuple.of(t1, t2, t3)

/**
 * @see Tuple.of
 */
fun <T1, T2, T3, T4> tuple(t1: T1, t2: T2, t3: T3, t4: T4):
        Tuple4<T1, T2, T3, T4> = Tuple.of(t1, t2, t3, t4)

/**
 * @see Tuple.of
 */
fun <T1, T2, T3, T4, T5> tuple(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5):
        Tuple5<T1, T2, T3, T4, T5> = Tuple.of(t1, t2, t3, t4, t5)

/**
 * @see Tuple.of
 */
fun <T1, T2, T3, T4, T5, T6> tuple(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6):
        Tuple6<T1, T2, T3, T4, T5, T6> = Tuple.of(t1, t2, t3, t4, t5, t6)

/**
 * @see Tuple.of
 */
fun <T1, T2, T3, T4, T5, T6, T7> tuple(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7):
        Tuple7<T1, T2, T3, T4, T5, T6, T7> = Tuple.of(t1, t2, t3, t4, t5, t6, t7)

/**
 * @see Tuple.of
 */
fun <T1, T2, T3, T4, T5, T6, T7, T8> tuple(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8):
        Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = Tuple.of(t1, t2, t3, t4, t5, t6, t7, t8)

/**
 * Creates a Tuple out of a Kotlin Pair
 */
fun <T1, T2> Pair<T1, T2>.tuple():
        Tuple2<T1, T2> = Tuple.of(this.first, this.second)

/**
 * Creates a Tuple out of a Kotlin Triple
 */
fun <T1, T2, T3> Triple<T1, T2, T3>.tuple():
        Tuple3<T1, T2, T3> = Tuple.of(this.first, this.second, this.third)

/**
 * Creates a Kotlin Pair out of a Tuple
 */
fun <T1, T2> Tuple2<T1, T2>.pair():
        Pair<T1, T2> = Pair(this._1, this._2)

/**
 * @see Tuple.of
 */
fun <T1, T2> Map.Entry<T1, T2>.tuple():
        Tuple2<T1, T2> = Tuple.of(this.key, this.value)

operator fun <T1> Tuple1<T1>.component1(): T1 = this._1

operator fun <T1, T2> Tuple2<T1, T2>.component1(): T1 = this._1
operator fun <T1, T2> Tuple2<T1, T2>.component2(): T2 = this._2

operator fun <T1, T2, T3> Tuple3<T1, T2, T3>.component1(): T1 = this._1
operator fun <T1, T2, T3> Tuple3<T1, T2, T3>.component2(): T2 = this._2
operator fun <T1, T2, T3> Tuple3<T1, T2, T3>.component3(): T3 = this._3

operator fun <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4>.component1(): T1 = this._1
operator fun <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4>.component2(): T2 = this._2
operator fun <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4>.component3(): T3 = this._3
operator fun <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4>.component4(): T4 = this._4

operator fun <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5>.component1(): T1 = this._1
operator fun <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5>.component2(): T2 = this._2
operator fun <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5>.component3(): T3 = this._3
operator fun <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5>.component4(): T4 = this._4
operator fun <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5>.component5(): T5 = this._5

operator fun <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6>.component1(): T1 = this._1
operator fun <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6>.component2(): T2 = this._2
operator fun <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6>.component3(): T3 = this._3
operator fun <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6>.component4(): T4 = this._4
operator fun <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6>.component5(): T5 = this._5
operator fun <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6>.component6(): T6 = this._6

operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7>.component1(): T1 = this._1
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7>.component2(): T2 = this._2
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7>.component3(): T3 = this._3
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7>.component4(): T4 = this._4
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7>.component5(): T5 = this._5
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7>.component6(): T6 = this._6
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7>.component7(): T7 = this._7

operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.component1(): T1 = this._1
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.component2(): T2 = this._2
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.component3(): T3 = this._3
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.component4(): T4 = this._4
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.component5(): T5 = this._5
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.component6(): T6 = this._6
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.component7(): T7 = this._7
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.component8(): T8 = this._8

// Using the + operator for tuple concatenation

operator fun Tuple0.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this

operator fun <T1> Tuple0.plus(t: Tuple1<T1>) = t
operator fun <T1, T2> Tuple0.plus(t: Tuple2<T1, T2>) = t
operator fun <T1, T2> Tuple0.plus(t: Pair<T1, T2>) = t.tuple()
operator fun <T1, T2, T3> Tuple0.plus(t: Tuple3<T1, T2, T3>) = t
operator fun <T1, T2, T3> Tuple0.plus(t: Triple<T1, T2, T3>) = t.tuple()
operator fun <T1, T2, T3, T4> Tuple0.plus(t: Tuple4<T1, T2, T3, T4>) = t
operator fun <T1, T2, T3, T4, T5> Tuple0.plus(t: Tuple5<T1, T2, T3, T4, T5>) = t
operator fun <T1, T2, T3, T4, T5, T6> Tuple0.plus(t: Tuple6<T1, T2, T3, T4, T5, T6>) = t
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple0.plus(t: Tuple7<T1, T2, T3, T4, T5, T6, T7>) = t
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple0.plus(t: Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>) = t

operator fun <T1> Tuple1<T1>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this
operator fun <T1, T2> Tuple1<T1>.plus(t: Tuple1<T2>)
        : Tuple2<T1, T2> = tuple(this._1, t._1)
operator fun <T1, T2, T3> Tuple1<T1>.plus(t: Pair<T2, T3>)
        : Tuple3<T1, T2, T3> = tuple(this._1, t.first, t.second)
operator fun <T1, T2, T3> Tuple1<T1>.plus(t: Tuple2<T2, T3>)
        : Tuple3<T1, T2, T3> = tuple(this._1, t._1, t._2)
operator fun <T1, T2, T3, T4> Tuple1<T1>.plus(t: Tuple3<T2, T3, T4>)
        : Tuple4<T1, T2, T3, T4> = tuple(this._1, t._1, t._2, t._3)
operator fun <T1, T2, T3, T4> Tuple1<T1>.plus(t: Triple<T2, T3, T4>)
        : Tuple4<T1, T2, T3, T4> = tuple(this._1, t.first, t.second, t.third)
operator fun <T1, T2, T3, T4, T5> Tuple1<T1>.plus(t: Tuple4<T2, T3, T4, T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this._1, t._1, t._2, t._3, t._4)
operator fun <T1, T2, T3, T4, T5, T6> Tuple1<T1>.plus(t: Tuple5<T2, T3, T4, T5, T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this._1, t._1, t._2, t._3, t._4, t._5)
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple1<T1>.plus(t: Tuple6<T2, T3, T4, T5, T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this._1, t._1, t._2, t._3, t._4, t._5, t._6)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple1<T1>.plus(t: Tuple7<T2, T3, T4, T5, T6, T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, t._1, t._2, t._3, t._4, t._5, t._6, t._7)

operator fun <T1, T2> Tuple2<T1, T2>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this
operator fun <T1, T2, T3> Tuple2<T1, T2>.plus(t: Tuple1<T3>)
        : Tuple3<T1, T2, T3> = tuple(this._1, this._2, t._1)
operator fun <T1, T2, T3, T4> Tuple2<T1, T2>.plus(t: Tuple2<T3, T4>)
        : Tuple4<T1, T2, T3, T4> = tuple(this._1, this._2, t._1, t._2)
operator fun <T1, T2, T3, T4> Tuple2<T1, T2>.plus(t: Pair<T3, T4>)
        : Tuple4<T1, T2, T3, T4> = tuple(this._1, this._2, t.first, t.second)
operator fun <T1, T2, T3, T4, T5> Tuple2<T1, T2>.plus(t: Tuple3<T3, T4, T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this._1, this._2, t._1, t._2, t._3)
operator fun <T1, T2, T3, T4, T5> Tuple2<T1, T2>.plus(t: Triple<T3, T4, T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this._1, this._2, t.first, t.second, t.third)
operator fun <T1, T2, T3, T4, T5, T6> Tuple2<T1, T2>.plus(t: Tuple4<T3, T4, T5, T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this._1, this._2, t._1, t._2, t._3, t._4)
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple2<T1, T2>.plus(t: Tuple5<T3, T4, T5, T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this._1, this._2, t._1, t._2, t._3, t._4, t._5)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple2<T1, T2>.plus(t: Tuple6<T3, T4, T5, T6, T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, this._2, t._1, t._2, t._3, t._4, t._5, t._6)

operator fun <T1, T2> Pair<T1, T2>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this.tuple()
operator fun <T1, T2, T3> Pair<T1, T2>.plus(t: Tuple1<T3>)
        : Tuple3<T1, T2, T3> = tuple(this.first, this.second, t._1)
operator fun <T1, T2, T3, T4> Pair<T1, T2>.plus(t: Tuple2<T3, T4>)
        : Tuple4<T1, T2, T3, T4> = tuple(this.first, this.second, t._1, t._2)

// We probably don't want to define an operation on two non-VAVR classes like this:
//operator fun <T1, T2, T3, T4> Pair<T1, T2>.plus(t: Pair<T3, T4>)
//        : Tuple4<T1, T2, T3, T4> = tuple(this.first, this.second, t.first, t.second)

operator fun <T1, T2, T3, T4, T5> Pair<T1, T2>.plus(t: Tuple3<T3, T4, T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this.first, this.second, t._1, t._2, t._3)
operator fun <T1, T2, T3, T4, T5> Pair<T1, T2>.plus(t: Triple<T3, T4, T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this.first, this.second, t.first, t.second, t.third)
operator fun <T1, T2, T3, T4, T5, T6> Pair<T1, T2>.plus(t: Tuple4<T3, T4, T5, T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this.first, this.second, t._1, t._2, t._3, t._4)
operator fun <T1, T2, T3, T4, T5, T6, T7> Pair<T1, T2>.plus(t: Tuple5<T3, T4, T5, T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this.first, this.second, t._1, t._2, t._3, t._4, t._5)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Pair<T1, T2>.plus(t: Tuple6<T3, T4, T5, T6, T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this.first, this.second, t._1, t._2, t._3, t._4, t._5, t._6)

operator fun <T1, T2, T3> Tuple3<T1, T2, T3>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this
operator fun <T1, T2, T3, T4> Tuple3<T1, T2, T3>.plus(t: Tuple1<T4>)
        : Tuple4<T1, T2, T3, T4> = tuple(this._1, this._2, this._3, t._1)
operator fun <T1, T2, T3, T4, T5> Tuple3<T1, T2, T3>.plus(t: Tuple2<T4, T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this._1, this._2, this._3, t._1, t._2)
operator fun <T1, T2, T3, T4, T5> Tuple3<T1, T2, T3>.plus(t: Pair<T4, T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this._1, this._2, this._3, t.first, t.second)
operator fun <T1, T2, T3, T4, T5, T6> Tuple3<T1, T2, T3>.plus(t: Tuple3<T4, T5, T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this._1, this._2, this._3, t._1, t._2, t._3)
operator fun <T1, T2, T3, T4, T5, T6> Tuple3<T1, T2, T3>.plus(t: Triple<T4, T5, T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this._1, this._2, this._3, t.first, t.second, t.third)
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple3<T1, T2, T3>.plus(t: Tuple4<T4, T5, T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this._1, this._2, this._3, t._1, t._2, t._3, t._4)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple3<T1, T2, T3>.plus(t: Tuple5<T4, T5, T6, T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, this._2, this._3, t._1, t._2, t._3, t._4, t._5)

operator fun <T1, T2, T3> Triple<T1, T2, T3>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this.tuple()
operator fun <T1, T2, T3, T4> Triple<T1, T2, T3>.plus(t: Tuple1<T4>)
        : Tuple4<T1, T2, T3, T4> = tuple(this.first, this.second, this.third, t._1)
operator fun <T1, T2, T3, T4, T5> Triple<T1, T2, T3>.plus(t: Tuple2<T4, T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this.first, this.second, this.third, t._1, t._2)
operator fun <T1, T2, T3, T4, T5, T6> Triple<T1, T2, T3>.plus(t: Tuple3<T4, T5, T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this.first, this.second, this.third, t._1, t._2, t._3)

// We probably don't want to define an operation on two non-VAVR classes like this:
//operator fun <T1, T2, T3, T4, T5> Triple<T1, T2, T3>.plus(t: Pair<T4, T5>)
//        : Tuple5<T1, T2, T3, T4, T5> = tuple(this.first, this.second, this.third, t.first, t.second)
//operator fun <T1, T2, T3, T4, T5, T6> Triple<T1, T2, T3>.plus(t: Triple<T4, T5, T6>)
//        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this.first, this.second, this.third, t._1, t._2, t._3)

operator fun <T1, T2, T3, T4, T5, T6, T7> Triple<T1, T2, T3>.plus(t: Tuple4<T4, T5, T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this.first, this.second, this.third, t._1, t._2, t._3, t._4)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Triple<T1, T2, T3>.plus(t: Tuple5<T4, T5, T6, T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this.first, this.second, this.third, t._1, t._2, t._3, t._4, t._5)

operator fun <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this
operator fun <T1, T2, T3, T4, T5> Tuple4<T1, T2, T3, T4>.plus(t: Tuple1<T5>)
        : Tuple5<T1, T2, T3, T4, T5> = tuple(this._1, this._2, this._3, this._4, t._1)
operator fun <T1, T2, T3, T4, T5, T6> Tuple4<T1, T2, T3, T4>.plus(t: Tuple2<T5, T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this._1, this._2, this._3, this._4, t._1, t._2)
operator fun <T1, T2, T3, T4, T5, T6> Tuple4<T1, T2, T3, T4>.plus(t: Pair<T5, T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this._1, this._2, this._3, this._4, t.first, t.second)
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple4<T1, T2, T3, T4>.plus(t: Tuple3<T5, T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this._1, this._2, this._3, this._4, t._1, t._2, t._3)
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple4<T1, T2, T3, T4>.plus(t: Triple<T5, T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this._1, this._2, this._3, this._4, t.first, t.second, t.third)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple4<T1, T2, T3, T4>.plus(t: Tuple4<T5, T6, T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, this._2, this._3, this._4, t._1, t._2, t._3, t._4)

operator fun <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this
operator fun <T1, T2, T3, T4, T5, T6> Tuple5<T1, T2, T3, T4, T5>.plus(t: Tuple1<T6>)
        : Tuple6<T1, T2, T3, T4, T5, T6> = tuple(this._1, this._2, this._3, this._4, this._5, t._1)
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple5<T1, T2, T3, T4, T5>.plus(t: Tuple2<T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this._1, this._2, this._3, this._4, this._5, t._1, t._2)
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple5<T1, T2, T3, T4, T5>.plus(t: Pair<T6, T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this._1, this._2, this._3, this._4, this._5, t.first, t.second)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple5<T1, T2, T3, T4, T5>.plus(t: Tuple3<T6, T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, this._2, this._3, this._4, this._5, t._1, t._2, t._3)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple5<T1, T2, T3, T4, T5>.plus(t: Triple<T6, T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, this._2, this._3, this._4, this._5, t.first, t.second, t.third)

operator fun <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this
operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple6<T1, T2, T3, T4, T5, T6>.plus(t: Tuple1<T7>)
        : Tuple7<T1, T2, T3, T4, T5, T6, T7> = tuple(this._1, this._2, this._3, this._4, this._5, this._6, t._1)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple6<T1, T2, T3, T4, T5, T6>.plus(t: Tuple2<T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, this._2, this._3, this._4, this._5, this._6, t._1, t._2)
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple6<T1, T2, T3, T4, T5, T6>.plus(t: Pair<T7, T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, this._2, this._3, this._4, this._5, this._6, t.first, t.second)

operator fun <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this
operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple7<T1, T2, T3, T4, T5, T6, T7>.plus(t: Tuple1<T8>)
        : Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> = tuple(this._1, this._2, this._3, this._4, this._5, this._6, this._7, t._1)

operator fun <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>.plus(@Suppress("UNUSED_PARAMETER") t: Tuple0) = this
