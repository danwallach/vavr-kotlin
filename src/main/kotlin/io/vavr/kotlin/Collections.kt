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

import io.vavr.Tuple2
import io.vavr.Value
import io.vavr.collection.List
import io.vavr.collection.Map
import io.vavr.collection.Seq
import io.vavr.collection.Stream

/**
 * Constructors and Kotlin collection converters for the Vavr collection values
 *
 * @author Alex Zuzin (github.com/zvozin)
 */

/**
 * @see List.of
 */
fun <T> list(vararg t: T):
        List<T> = List.of(*t)

/**
 * Converts a Value (that is, _any_ Vavr data class) into a Kotlin MutableList
 */
fun <T> Value<T>.toMutableList():
        MutableList<T> = this.toJavaList().toMutableList()

/**
 * Converts a Kotlin Iterable into a List
 */
fun <T> kotlin.collections.Iterable<T>.toVavrList():
        List<T> = List.ofAll(this)

/**
 * Converts a Kotlin Array into a List
 */
fun <T> Array<T>.toVavrList():
        List<T> = List.ofAll(this.asIterable())

/**
 * @see Stream.of
 */
fun <T> stream(vararg t: T):
        Stream<T> = Stream.of(*t)

/**
 * @see Stream.ofAll
 */
fun <T> kotlin.collections.Iterable<T>.toVavrStream():
        Stream<T> = Stream.ofAll(this)

/**
 * Converts a Kotlin Array into a Stream
 */
fun <T> Array<T>.toVavrStream():
        Stream<T> = Stream.ofAll(this.asIterable())

/**
 * Converts a Kotlin Sequence into a Stream
 */
fun <T> Sequence<T>.toVavrStream():
        Stream<T> = Stream.ofAll(this.asIterable())

/**
 * Converts a Kotlin Map into a Vavr Map
 */
fun <K, V> kotlin.collections.Map<K, V>.toVavrMap():
        io.vavr.collection.Map<K, V> = io.vavr.collection.HashMap.ofAll(this)

/**
 * Converts a Sequence of tuples into a Vavr Map
 */
fun <K, V> Sequence<Pair<K, V>>.toVavrMap()
        : io.vavr.collection.Map<K, V> = io.vavr.collection.HashMap.ofEntries(this.asIterable().map { it.tuple() })

/**
 * Converts an iterable of tuples into a Vavr Map
 */
fun <K, V> kotlin.collections.Iterable<Pair<K, V>>.toVavrMap()
        : io.vavr.collection.Map<K, V> = io.vavr.collection.HashMap.ofEntries(this.map { it.tuple() })

/**
 * Converts most Kotlin sequence-like sets of tuples into a Vavr Map
 */
fun <K, V> io.vavr.collection.Traversable<Tuple2<K, V>>.toVavrMap()
        : io.vavr.collection.Map<K, V> = io.vavr.collection.HashMap.ofEntries(this)

/**
 * Converts an iterable of tuples into a Vavr Map
 */
fun <K, V> Array<out Tuple2<K, V>>.toVavrMap()
        : io.vavr.collection.Map<K, V> = when (size) {
            0 -> io.vavr.collection.LinkedHashMap.empty()
            1 -> io.vavr.collection.LinkedHashMap.of(this[0])
            else -> io.vavr.collection.LinkedHashMap.ofEntries(*this)
        }

/**
 * Converts an iterable of pairs into a Vavr Map
 */
fun <K, V> Array<out Pair<K, V>>.toVavrMap()
        : io.vavr.collection.Map<K, V> = when (size) {
          0 -> io.vavr.collection.LinkedHashMap.empty()
          1 -> io.vavr.collection.LinkedHashMap.of(this[0].tuple())
          else -> io.vavr.collection.LinkedHashMap.ofEntries(this.map { it.tuple() })
        }

/**
 * Converts a Vavr Map to a Kotlin MutableMap
 */
fun <K, V> io.vavr.collection.Map<K, V>.toMutableMap():
        kotlin.collections.MutableMap<K, V> = this.toJavaMap().toMutableMap()

/**
 * Creates a Vavr HashMap from a series of Kotlin Pairs
 */
fun <K, V> hashMap(vararg p: Pair<K, V>):
        io.vavr.collection.HashMap<K, V> =
        io.vavr.collection.HashMap.ofEntries(p.asIterable().map { it.tuple() })

/**
 * Creates a Vavr LinkedHashMap from a series of Kotlin Pairs
 */
fun <K, V> linkedHashMap(vararg p: Pair<K, V>):
        io.vavr.collection.LinkedHashMap<K, V> =
        io.vavr.collection.LinkedHashMap.ofEntries(p.asIterable().map { it.tuple() })

/**
 * Creates a Vavr TreeMap from a series of Kotlin Pairs.
 * Notice that the keys of a TreeMap must be Comparable
 */
fun <K : Comparable<K>, V> treeMap(vararg p: Pair<K, V>):
        io.vavr.collection.TreeMap<K, V> =
        io.vavr.collection.TreeMap.ofEntries(p.asIterable().map { it.tuple() })

/**
 * Returns the value associated with a key, or null if the key is not contained in the map.
 */
fun <K, V> Map<K, V>.getOrNull(key: K):
        V? = this.getOrElse(key, null)

/**
 * Converts a Kotlin Set into a Vavr Set
 */
fun <T> kotlin.collections.Set<T>.toVavrSet():
        io.vavr.collection.Set<T> = io.vavr.collection.HashSet.ofAll(this)

/**
 * Converts any Kotlin Iterable into a Vavr Set
 */
fun <T> Iterable<T>.toVavrSet():
        io.vavr.collection.Set<T> = io.vavr.collection.HashSet.ofAll(this)

/**
 * Converts any Kotlin Array into a Vavr Set
 */
fun <T> Array<out T>.toVavrSet():
        io.vavr.collection.Set<T> = this.asIterable().toVavrSet()

/**
 * Converts a Vavr Set into a Kotlin MutableSet
 */
fun <T> io.vavr.collection.Set<T>.toMutableSet():
        kotlin.collections.MutableSet<T> = this.toJavaSet().toMutableSet()

/**
 * Creates a Vavr HashSet
 */
fun <T> hashSet(vararg t: T):
        io.vavr.collection.HashSet<T> = io.vavr.collection.HashSet.ofAll(t.asIterable())

/**
 * Creates a Vavr LinkedHashSet
 */
fun <T> linkedHashSet(vararg t: T):
        io.vavr.collection.LinkedHashSet<T> = io.vavr.collection.LinkedHashSet.ofAll(t.asIterable())

/**
 * Creates a Vavr TreeSet.
 * Notice that the elements of a TreeSet must be Comparable
 */
fun <T : Comparable<T>> treeSet(vararg t: T):
        io.vavr.collection.TreeSet<T> =
        io.vavr.collection.TreeSet.ofAll(t.asIterable())

////////////////////////////////////////////////////////////////////////////////
// Extension functions for VAVR's Map to feel more like Kotlin's Map
////////////////////////////////////////////////////////////////////////////////

val <K, V> io.vavr.collection.Map<K, V>.size: Int
  get() = size()

val <K, V> io.vavr.collection.Map<K, V>.keys: io.vavr.collection.Set<K>
  get() = keySet()

val <K, V> io.vavr.collection.Map<K, V>.values: io.vavr.collection.Seq<V>
  get() = values()

val <K, V> io.vavr.collection.Map<K, V>.entries: io.vavr.collection.Seq<Tuple2<K, V>>
  get() = toStream() // could also use toList(), but might as well pick the lazy option

operator fun <K, V> io.vavr.collection.Map<K, V>.contains(k: K)
        : Boolean = containsKey(k)

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(m: io.vavr.collection.Map<out K, out V>)
        : io.vavr.collection.Map<K, V> = merge(m)

operator fun <K, V> kotlin.collections.Map<K, V>.plus(m: io.vavr.collection.Map<out K, V>)
        : io.vavr.collection.Map<K, V> = toVavrMap() + m

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(m: kotlin.collections.Map<out K, V>)
        : io.vavr.collection.Map<K, V> = merge(m.toVavrMap())

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(pair: Pair<K, V>)
        : io.vavr.collection.Map<K, V> = put(pair.tuple())

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(t: Tuple2<K, V>)
        : io.vavr.collection.Map<K, V> = put(t)

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(tuples: Sequence<Pair<K, V>>)
        : io.vavr.collection.Map<K, V> = merge(tuples.toVavrMap())

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(tuples: kotlin.collections.Iterable<Pair<K, V>>)
        : io.vavr.collection.Map<K, V> = merge(tuples.toVavrMap())

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(tuples: Array<out Tuple2<K, V>>)
        : io.vavr.collection.Map<K, V> = merge(tuples.toVavrMap())

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(tuples: Array<out Pair<K, V>>)
        : io.vavr.collection.Map<K, V> = merge(tuples.toVavrMap())

operator fun <K, V> io.vavr.collection.Map<K, V>.plus(tuples: Seq<Tuple2<K, V>>)
        : io.vavr.collection.Map<K, V> = merge(tuples.toMap { it })

operator fun <K, V> io.vavr.collection.Map<K, V>.minus(keys: io.vavr.collection.Set<out K>)
        : io.vavr.collection.Map<K, V> = removeAll(keys)

operator fun <K, V> io.vavr.collection.Map<K, V>.minus(keys: kotlin.collections.Set<K>)
        : io.vavr.collection.Map<K, V> = removeAll(keys)

operator fun <K, V> io.vavr.collection.Map<K, V>.minus(keys: Seq<K>)
        : io.vavr.collection.Map<K, V> = removeAll(keys)

operator fun <K, V> io.vavr.collection.Map<K, V>.minus(keys: kotlin.collections.Iterable<K>)
        : io.vavr.collection.Map<K, V> = removeAll(keys)

operator fun <K, V> io.vavr.collection.Map<K, V>.minus(keys: Sequence<K>)
        : io.vavr.collection.Map<K, V> = removeAll(keys.asIterable())

operator fun <K, V> io.vavr.collection.Map<K, V>.minus(keys: Array<out K>)
        : io.vavr.collection.Map<K, V> = removeAll(keys.asIterable())

operator fun <K, V> io.vavr.collection.Map<K, V>.minus(key: K)
        : io.vavr.collection.Map<K, V> = remove(key)

////////////////////////////////////////////////////////////////////////////////
// Extension functions for VAVR's Set to feel more like Kotlin's Set
////////////////////////////////////////////////////////////////////////////////

val <T> io.vavr.collection.Set<T>.size: Int
    get() = size()

operator fun <T> io.vavr.collection.Set<T>.plus(s: io.vavr.collection.Set<out T>)
        : io.vavr.collection.Set<T> = union(s)

operator fun <T> io.vavr.collection.Set<T>.plus(s: kotlin.collections.Set<T>)
        : io.vavr.collection.Set<T> = union(s.toVavrSet())

operator fun <T> kotlin.collections.Set<T>.plus(s: io.vavr.collection.Set<T>)
        : io.vavr.collection.Set<T> = s.union(this.toVavrSet())

operator fun <T> io.vavr.collection.Set<T>.plus(e: T)
        : io.vavr.collection.Set<T> = add(e)

operator fun <T> io.vavr.collection.Set<T>.plus(elements: Sequence<T>)
        : io.vavr.collection.Set<T> = union(elements.asIterable().toVavrSet())

operator fun <T> io.vavr.collection.Set<T>.plus(elements: kotlin.collections.Iterable<T>)
        : io.vavr.collection.Set<T> = union(elements.toVavrSet())

operator fun <T> io.vavr.collection.Set<T>.plus(elements: Array<out T>)
        : io.vavr.collection.Set<T> = union(elements.toVavrSet())

operator fun <T> io.vavr.collection.Set<T>.plus(elements: Seq<T>)
        : io.vavr.collection.Set<T> = plus(elements.asIterable())

operator fun <T> io.vavr.collection.Set<T>.minus(s: io.vavr.collection.Set<out T>)
        : io.vavr.collection.Set<T> = diff(s)

operator fun <T> kotlin.collections.Set<T>.minus(s: io.vavr.collection.Set<out T>)
        : io.vavr.collection.Set<T> = toVavrSet().diff(s)

operator fun <T> io.vavr.collection.Set<T>.minus(s: kotlin.collections.Set<T>)
        : io.vavr.collection.Set<T> = diff(s.toVavrSet())

operator fun <T> io.vavr.collection.Set<T>.minus(e: T)
        : io.vavr.collection.Set<T> = remove(e)

operator fun <T> io.vavr.collection.Set<T>.minus(elements: Sequence<T>)
        : io.vavr.collection.Set<T> = diff(elements.asIterable().toVavrSet())

operator fun <T> io.vavr.collection.Set<T>.minus(elements: kotlin.collections.Iterable<T>)
        : io.vavr.collection.Set<T> = diff(elements.toVavrSet())

operator fun <T> io.vavr.collection.Set<T>.minus(elements: Array<out T>)
        : io.vavr.collection.Set<T> = diff(elements.toVavrSet())

operator fun <T> io.vavr.collection.Set<T>.minus(elements: Seq<T>)
        : io.vavr.collection.Set<T> = minus(elements.asIterable())

////////////////////////////////////////////////////////////////////////////////
// Extension functions for VAVR's Seq to feel more like Kotlin's List / Sequence
////////////////////////////////////////////////////////////////////////////////

/**
 * Appends the given element to the end of the sequence. Will run slow for large, linear sequences.
 */
operator fun <T> io.vavr.collection.Seq<T>.plus(elem: T)
        : Seq<T> = append(elem)

/**
 * Prepends the given element to the front of the sequence.
 */
operator fun <T> T.plus(seq: io.vavr.collection.Seq<T>)
        : Seq<T> = seq.prepend(this)

/**
 * Concatenates together a Kotlin sequence with a VAVR sequence
 */
operator fun <T> Sequence<T>.plus(seq: io.vavr.collection.Seq<T>)
        : Seq<T> = this.toVavrStream().appendAll(seq)

/**
 * Concatenates together a Kotlin array with a VAVR sequence
 */
operator fun <T> Array<T>.plus(seq: io.vavr.collection.Seq<T>)
        : Seq<T> = this.toVavrList().appendAll(seq)

/**
 * Concatenates together a VAVR sequence with a Kotlin sequence.
 */
operator fun <T> io.vavr.collection.Seq<T>.plus(seq: Sequence<T>)
        : Seq<T> = this.appendAll(seq.asIterable())

/**
 * Concatenates together a VAVR sequence with any Kotlin iterable.
 */
operator fun <T> io.vavr.collection.Seq<T>.plus(seq: kotlin.collections.Iterable<T>)
        : Seq<T> = this.appendAll(seq)

/**
 * Concatenates together a VAVR sequence with any Array.
 */
operator fun <T> io.vavr.collection.Seq<T>.plus(array: Array<out T>)
        : Seq<T> = this + array.asIterable()

/**
 * Returns a VAVR sequence containing all elements of the original collection without the first occurrence of the given [element].
 */
operator fun <T> io.vavr.collection.Seq<T>.minus(element: T)
        : Seq<T> = remove(element)

/**
 * Returns a VAVR sequence containing all elements of the original collection except the elements contained in the given [elements].
 */
operator fun <T> io.vavr.collection.Seq<T>.minus(elements: Sequence<T>)
        : Seq<T> = removeAll(elements.asIterable())

/**
 * Returns a VAVR sequence containing all elements of the original collection except the elements contained in the given [elements].
 */
operator fun <T> io.vavr.collection.Seq<T>.minus(elements: kotlin.collections.Iterable<T>)
        : Seq<T> = removeAll(elements)

/**
 * Returns a VAVR sequence containing all elements of the original collection except the elements contained in the given [elements].
 */
operator fun <T> io.vavr.collection.Seq<T>.minus(elements: Array<out T>)
        : Seq<T> = removeAll(elements.asIterable())

/**
 * Returns the first *element* from the VAVR sequence.
 */
operator fun <T> io.vavr.collection.Seq<T>.component1(): T = get(0)

/**
 * Returns the second *element* from the VAVR sequence.
 */
operator fun <T> io.vavr.collection.Seq<T>.component2(): T = get(1)

/**
 * Returns the third *element* from the VAVR sequence.
 */
operator fun <T> io.vavr.collection.Seq<T>.component3(): T = get(2)

/**
 * Returns the fourth *element* from the VAVR sequence.
 */
operator fun <T> io.vavr.collection.Seq<T>.component4(): T = get(3)

/**
 * Returns the fifth *element* from the VAVR sequence.
 */
operator fun <T> io.vavr.collection.Seq<T>.component5(): T = get(4)

/**
 * Given a VAVR sequence which might have nulls in it, returns a VAVR sequence without nulls.
 */
@Suppress("UNCHECKED_CAST")
fun <T: Any> io.vavr.collection.Seq<T?>.filterNotNull(): io.vavr.collection.Seq<T> =
        filter { it != null } as Seq<T>

/**
 * If the VAVR sequence has nulls in it, throws an [IllegalArgumentException], otherwise
 * returns the original VAVR sequence.
 */
fun <T: Any> io.vavr.collection.Seq<T?>.requireNoNulls(): io.vavr.collection.Seq<T> =
        if (contains(null)) {
            throw IllegalArgumentException("null element found in $this")
        } else {
            @Suppress("UNCHECKED_CAST")
            this as Seq<T>
        }
